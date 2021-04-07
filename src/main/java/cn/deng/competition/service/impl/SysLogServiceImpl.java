package cn.deng.competition.service.impl;

import cn.deng.competition.model.entity.SysLog;
import cn.deng.competition.repostiory.SysLogRepository;
import cn.deng.competition.service.SysLogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author verall
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl implements SysLogService {

  private final SysLogRepository sysLogRepository;

  @Override
  public List<SysLog> findAll() {
    return sysLogRepository.findAll();
  }
}
