package cn.deng.competition.service.impl;

import cn.deng.competition.base.BaseEntity;
import cn.deng.competition.controller.ProjectController.ProjectDetail;
import cn.deng.competition.model.constant.Review;
import cn.deng.competition.model.entity.Project;
import cn.deng.competition.model.entity.ProjectJudge;
import cn.deng.competition.model.entity.ProjectStudent;
import cn.deng.competition.model.exception.ResourceNotFoundException;
import cn.deng.competition.repostiory.ProjectJudgeRepository;
import cn.deng.competition.repostiory.ProjectRepository;
import cn.deng.competition.repostiory.ProjectStudentRepository;
import cn.deng.competition.repostiory.SysUserRepository;
import cn.deng.competition.service.ProjectService;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author verall
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectJudgeRepository projectJudgeRepository;
  private final ProjectStudentRepository projectStudentRepository;
  private final SysUserRepository sysUserRepository;

  @Override
  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  @Override
  public void save(Project project) {
    projectRepository.save(project);
  }

  @Override
  public void apply(List<Long> ids, Boolean result) {
    List<Project> projects = projectRepository.findAllById(ids).stream()
        .map(project -> project.setApply(result))
        .collect(Collectors.toList());
    projectRepository.saveAll(projects);
  }

  @Override
  public void review(List<Long> ids, List<Long> judgeIds, Review result, String reason) {
    List<Project> projects = projectRepository
        // 根据 id 查找所有符合条件的项目
        .findAllById(ids).stream()
        .map(project -> {
          // 对每个项目设置其属性
          project.setReview(result);
          if (result.isFail() && StringUtils.isNotBlank(reason)) {
            return project.setRemark(reason);
          }
          return project;
        })
        .collect(Collectors.toList());
    projectRepository.saveAll(projects);
    if (result.isFail()) {
      projectJudgeRepository.deleteByIds(
          projectJudgeRepository.findAllByProjectIdIn(ids).stream()
              .map(BaseEntity::getId)
              .collect(Collectors.toList()));
      return;
    }
    // 构建多条 项目评委关联数据
    List<ProjectJudge> projectJudges = projects.stream()
        .map(project -> judgeIds.stream().map(id ->
                ProjectJudge.builder()
                    .judgeId(id)
                    .projectId(project.getId())
                    .enable(true)
                    .build()
            ).collect(Collectors.toList())
        )
        // 将两层潜逃的集合合并成为一个集合
        .flatMap(List::stream)
        // 收集结果
        .collect(Collectors.toList());
    // 保存
    projectJudgeRepository.saveAll(projectJudges);
  }

  @Override
  public void delete(List<Long> ids) {
    projectRepository.deleteByIds(ids);
    List<Long> projectJudgesIds = projectJudgeRepository
        .findAllByProjectIdIn(ids)
        .stream()
        .map(BaseEntity::getId)
        .collect(Collectors.toList());
    projectJudgeRepository.deleteByIds(projectJudgesIds);
  }

  @Override
  public ProjectDetail view(Long id) {
    Project project = projectRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("未找到项目信息"));
    List<ProjectJudge> projectJudges = projectJudgeRepository
        .findAllByProjectId(project.getId())
        .stream()
        .peek(projectJudge ->
            projectJudge.setJudge(sysUserRepository.getOne(projectJudge.getJudgeId())))
        .collect(Collectors.toList());
    List<ProjectStudent> projectStudents = projectStudentRepository
        .findAllByProjectId(project.getId())
        .stream()
        .peek(projectStudent ->
            projectStudent.setStudent(sysUserRepository.getOne(projectStudent.getStudentId())))
        .collect(Collectors.toList());
    ProjectDetail detail = new ProjectDetail();
    detail.setProject(project);
    detail.setJudges(projectJudges);
    detail.setStudents(projectStudents);
    return detail;
  }
}
