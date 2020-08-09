package cn.deng.competition.base;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author verall
 */
@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
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
   * 是否启用
   */
  private Boolean enable;

}
