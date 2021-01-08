package cn.deng.competition.service;

import cn.deng.competition.model.entity.ProjectJudge;

import java.util.List;

/**
 * @author verall
 */
public interface ProjectJudgeService {
    /**
     * 根据当前教师 id 获取可评项目列表
     * @param judgeId 教师id
     * @return List<ProjectJudge>
     */
    List<ProjectJudge> findByJudgeId(Long judgeId);
}
