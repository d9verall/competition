package cn.deng.competition.base;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author verall
 */
@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  /**
   * id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 名字
   */
  private String name;

  /**
   * 创建时间
   */
  @CreatedDate
  private LocalDateTime createTime;

  /**
   * 更新时间
   */
  @LastModifiedDate
  private LocalDateTime updateTime;

  /**
   * 创建用户
   */
  @CreatedBy
  private String createUser;

  /**
   * 修改用户
   */
  @LastModifiedBy
  private String updateUser;

  /**
   * 是否启用
   */
  private Boolean enable = true;

}
