package cn.deng.competition.repostiory;

import cn.deng.competition.base.BaseRepository;
import cn.deng.competition.model.entity.ProjectJudge;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author verall
 * @date 2020/7/18 下午10:43
 */
@Repository
public interface ProjectJudgeRepository extends BaseRepository<ProjectJudge> {

  /**
   * 根据项目id查询
   *
   * @param projectIds 项目 id
   * @return 结果
   */
  List<ProjectJudge> findAllByProjectIdIn(List<Long> projectIds);

}
