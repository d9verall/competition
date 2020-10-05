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
@Entity(name = "project_judge")
@Table(name = "project_judge")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class ProjectJudge extends BaseEntity implements Serializable {

  /**
   * 项目 id
   */
  private Long projectId;

  /**
   * 评委 id
   */
  private Long judgeId;

  /**
   * 分数
   */
  private Integer score;

  /**
   * 评分信息
   */
  private String info;

}
