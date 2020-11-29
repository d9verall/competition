package cn.deng.competition.service;

import cn.deng.competition.controller.ProjectController.ProjectDetail;
import cn.deng.competition.model.constant.Review;
import cn.deng.competition.model.entity.Project;
import java.util.List;

/**
 * @author verall
 */
public interface ProjectService {

  /**
   * 查找所有
   *
   * @return 结果
   */
  List<Project> findAll();

  /**
   * 保存
   *
   * @param project 项目信息
   */
  void save(Project project);

  /**
   * 报名
   *
   * @param ids    项目编号
   * @param result 是否开启报名
   */
  void apply(List<Long> ids, Boolean result);

  /**
   * 审核
   *
   * @param ids      项目编号
   * @param judgeIds 评委编号
   * @param result   审核结果
   * @param reason   不通过原因
   */
  void review(List<Long> ids, List<Long> judgeIds, Review result, String reason);

  /**
   * 根据 ids 删除数据
   *
   * @param ids ids
   */
  void delete(List<Long> ids);

  /**
   * 获取指定的项目信息
   *
   * @param id id
   * @return 结果
   */
  ProjectDetail view(Long id);
}
