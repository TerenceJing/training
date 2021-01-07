package com.terence.itech.base.AAtest.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author tiancai
 * @version $$Id: Order, v 0.1 2020/9/18 14:22 ***  Exp $$
 */
@Data
public class Order {
  private String billCode;
  private String name;


  private Date createdAt;

  /**
   *即可以放在字段上，又可以放在属性上
   */
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date businessTime;

  private Date closeTime;

  /**
   * " @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") "
   * 出参无用，入参格式化，入参必须是yyyy-MM-dd HH:mm:ss这个格式
   */
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  public void setCreatedAt(Date createdAt){
    this.createdAt=createdAt;
  }

  /**
   * " @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8") "
   * 入参无用，出参有用,需要加上时区
   */
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  public Date getCreatedAt(){
    return createdAt;
  }

//  @JSONField(format = "yyyy-MM-dd HH:mm:ss") 也有用
}
