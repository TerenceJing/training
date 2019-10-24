package com.terence.itech.base.result;

import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @author JingTiancai
 * @date 2019-09-29 00:15
 */
@Data
public class BasePageData<D> extends BasePage{
  private Integer total;
  private List<D> list;
}
