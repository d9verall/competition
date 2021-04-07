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
 * @author echo
 * @date 2021/1/19 15:52:50
 */
@Data
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Entity(name = "sys_log")
@Table(name = "sys_log")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class SysLog extends BaseEntity implements Serializable {

  /**
   * 浏览器
   */
  private String browser;

  /**
   * 状态码
   */
  private String status;

  /**
   * url
   */
  private String url;

  /**
   * 请求方法
   */
  private String method;

}
