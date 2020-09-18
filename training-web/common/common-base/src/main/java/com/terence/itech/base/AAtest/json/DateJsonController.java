package com.terence.itech.base.AAtest.json;

import com.alibaba.fastjson.JSON;
import com.terence.itech.base.result.BaseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author tiancai
 * @version $$Id: DateJsonController, v 0.1 2020/9/18 14:30 ***  Exp $$
 */
@RestController
@RequestMapping("/json")
public class DateJsonController {

  /**
   *试验几个json注解，请查看Order类
   *@author Jing Tiancai
   *@date 2020/9/18 14:52
   */
  @RequestMapping("/date/query")
  public BaseResult<Order> queryOrder(){
    Order order=new Order();
    order.setBillCode("TEST20200918003");
    order.setName("测试单据");
    order.setCreatedAt(new Date());
    order.setBusinessTime(new Date());
    order.setCloseTime(new Date());
    return BaseResult.success(order);
  }
  @RequestMapping("/date/add")
  public BaseResult<Order> addOrder(Order order){
    return BaseResult.success(order);
  }
  @RequestMapping("/date/add-str")
  public BaseResult<Order> addOrderStr(String  orderStr){
    Order order= JSON.parseObject(orderStr,Order.class);
    return BaseResult.success(order);
  }

}
