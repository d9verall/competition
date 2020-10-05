package cn.deng.competition.repostiory;

import cn.deng.competition.model.entity.ProjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author verall
 * @date 2020/7/18 下午10:43
 */
@Repository
public interface ProjectStudentRepository extends JpaRepository<ProjectStudent, Long> {

}