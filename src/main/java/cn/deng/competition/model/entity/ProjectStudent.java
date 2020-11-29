package cn.deng.competition.model.entity;

import cn.deng.competition.base.BaseEntity;
import cn.deng.competition.model.constant.Review;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Entity(name = "project_student")
@Table(name = "project_student")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class ProjectStudent extends BaseEntity implements Serializable {

  /**
   * 项目id
   */
  private Long projectId;

  /**
   * 作品描述信息
   */
  private String description;

  /**
   * 评委 id
   */
  private Long studentId;

  /**
   * 审批
   */
  private Review review = Review.WAIT;

  /**
   * 作品文件信息
   */
  private String file;

  /**
   * 学生
   */
  @Transient
  private SysUser student;

}
