package cn.deng.competition.service.impl;

import cn.deng.competition.base.BaseEntity;
import cn.deng.competition.model.constant.Review;
import cn.deng.competition.model.entity.Project;
import cn.deng.competition.model.entity.ProjectJudge;
import cn.deng.competition.repostiory.ProjectJudgeRepository;
import cn.deng.competition.repostiory.ProjectRepository;
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
        .map(project -> project.setApply(result)).collect(
            Collectors.toList());
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
      return;
    }
    // 构建多条 项目评委关联数据
    List<ProjectJudge> projectJudge = projects.stream()
        .map(project -> judgeIds.stream().map(id ->
                ProjectJudge.builder()
                    .judgeId(id)
                    .projectId(project.getId())
                    .build()
            ).collect(Collectors.toList())
        )
        // 将两层潜逃的集合合并成为一个集合
        .flatMap(List::stream)
        // 收集结果
        .collect(Collectors.toList());
    // 保存
    projectJudgeRepository.saveAll(projectJudge);
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
}
