package cn.deng.competition.model.entity;

import cn.deng.competition.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

/**
 * @author verall
 */
@Data
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Entity(name = "notice")
@Table(name = "notice")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class Notice extends BaseEntity implements Serializable {

  /**
   * 发送者
   */
  private Long sendId;

  /**
   * 接受者
   */
  private Long receiveId;

  /**
   * 主题
   */
  private String subject;

  /**
   * 内容
   */
  private String content;

  /**
   * 是否已读
   */
  private Boolean read = false;

}
