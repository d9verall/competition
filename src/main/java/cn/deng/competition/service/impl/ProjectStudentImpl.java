package cn.deng.competition.service.impl;

import cn.deng.competition.repostiory.ProjectStudentRepository;
import cn.deng.competition.model.entity.ProjectStudent;
import cn.deng.competition.service.ProjectStudentService;
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
public class ProjectStudentImpl implements ProjectStudentService {
    private final ProjectStudentRepository projectStudentRepository;

    @Override
    public List<ProjectStudent> findAll() {
        return projectStudentRepository.findAll();
    }

    @Override
    public void save(ProjectStudent projectStudent) {
        projectStudentRepository.save(projectStudent);
    }

    @Override
    public ProjectStudent findByProjectIdAndStudentId(Long projectId, Long studentId) {
        return projectStudentRepository.findByProjectIdAndStudentId(projectId,studentId);
    }

    @Override
    public List<ProjectStudent> findAllByProjectId(Long id) {
        return projectStudentRepository.findAllByProjectId(id);
    }

    @Override
    public ProjectStudent findById(Long id) {
        return projectStudentRepository.findById(id).get();
    }
}
