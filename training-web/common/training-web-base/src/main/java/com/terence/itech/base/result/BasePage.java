package com.terence.itech.base.result;

import lombok.Data;

/**
 * <p>分页对象</p>
 *
 * @author Terence
 * @date 2019-09-29 00:12
 */
@Data
public class BasePage {
  private Integer pageNo;
  private Integer pageSize;
}
