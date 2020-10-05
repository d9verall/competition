package cn.deng.competition.model.entity;

import static javax.persistence.EnumType.STRING;

import cn.deng.competition.base.BaseEntity;
import cn.deng.competition.model.constant.Review;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

/**
 * 项目实体类.
 *
 * @author verall
 */
@Data
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Entity(name = "project")
@Table(name = "project")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity implements Serializable {

  /**
   * 描述信息
   */
  private String description;

  /**
   * 积分规则
   */
  private String gradeRule;

  /**
   * 审批
   */
  @Enumerated(STRING)
  private Review review = Review.WAIT;

  /**
   * 开启报名
   */
  private Boolean apply = false;

  /**
   * 备注信息
   */
  private String remark;
}
