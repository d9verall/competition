package cn.deng.competition.service;

import cn.deng.competition.model.entity.ProjectStudent;

import java.util.List;

/**
 * @author verall
 */
public interface ProjectStudentService {
    /**
     * 查找所有
     *
     * @return 结果
     */
    List<ProjectStudent> findAll();

    /**
     * 保存
     *
     * @param projectStudent 项目信息
     */
    void save(ProjectStudent projectStudent);

    /**
     * 根据项目 id 和学生 id 获取
     *
     * @param projectId 项目 id
     * @param studentId 学生 id
     * @return 结果
     */
    ProjectStudent findByProjectIdAndStudentId(Long projectId,Long studentId);

    /**
     * 根据项目 id 获取
     *
     * @param id 项目 id
     * @return 结果
     */
    List<ProjectStudent> findAllByProjectId(Long id);

    /**
     * 根据 id 获取
     *
     * @param id 编号
     * @return 结果
     */
    ProjectStudent findById(Long id);
}
