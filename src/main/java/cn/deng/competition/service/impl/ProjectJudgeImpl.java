package cn.deng.competition.service.impl;

import cn.deng.competition.model.entity.ProjectJudge;
import cn.deng.competition.repostiory.ProjectJudgeRepository;
import cn.deng.competition.repostiory.ProjectStudentRepository;
import cn.deng.competition.service.ProjectJudgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author verall
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProjectJudgeImpl implements ProjectJudgeService {
    ProjectJudgeRepository projectJudgeRepository;

    @Autowired
    public ProjectJudgeImpl(ProjectJudgeRepository projectJudgeRepository) {
        this.projectJudgeRepository = projectJudgeRepository;
    }

    @Override
    public List<ProjectJudge> findByJudgeId(Long judgeId) {
        return projectJudgeRepository.findByJudgeId(judgeId);
    }
}
