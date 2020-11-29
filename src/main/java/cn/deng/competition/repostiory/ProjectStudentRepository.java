package cn.deng.competition.repostiory;

import cn.deng.competition.base.BaseRepository;
import cn.deng.competition.model.entity.ProjectStudent;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author verall
 * @date 2020/7/18 下午10:43
 */
@Repository
public interface ProjectStudentRepository extends BaseRepository<ProjectStudent> {

  /**
   * 根据项目 id 获取
   *
   * @param id 项目 id
   * @return 结果
   */
  List<ProjectStudent> findAllByProjectId(Long id);
}
