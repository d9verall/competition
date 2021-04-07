package cn.deng.competition.service;

import cn.deng.competition.model.entity.SysLog;
import java.util.List;

/**
 * @author verall
 */
public interface SysLogService {

  /**
   * 查询所有
   * @return list
   */
  List<SysLog> findAll();

}
