package cn.deng.competition.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author verall
 */
@SuppressWarnings("SqlResolve")
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

  /**
   * 批量删除
   *
   * @param ids id 列表
   */
  @Modifying
  @Query(value = "update #{#entityName} set `enable` = false where id in (:ids)", nativeQuery = true)
  void deleteByIds(Iterable<Long> ids);

  /**
   * 单条删除
   *
   * @param id id 列表
   */
  @Override
  @Modifying
  @Query(value = "update #{#entityName} set `enable` = false where id = (:id)", nativeQuery = true)
  void deleteById(Long id);

}
