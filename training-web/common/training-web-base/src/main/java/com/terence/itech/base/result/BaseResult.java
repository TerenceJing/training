package com.terence.itech.base.result;

import cn.hutool.core.bean.BeanUtil;
import com.terence.itech.base.errorcode.ErrorCode;
import com.terence.itech.base.errorcode.ErrorCodeEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>数据传输基础类</p>
 *
 * @author JingTiancai
 * @date 2019-09-28 20:50
 */
@Data
public class BaseResult<D> {
  /**0:成功； 1：系统内部异常； -1：外部异常*/
  private int type;
  /**返回码*/
  private String code;
/**返回信息*/
  private String msg;
  private D data;
  private BaseResult(int type,String code,String msg,D data){
    this.type=type;
    this.code=code;
    this.msg=msg;
    this.data=data;
  }
  public static <T> BaseResult<T> success(T data) {
    return new BaseResult<>(0, "0", "操作成功", data);
  }
  public static <T> BaseResult<T> success() {
    return new BaseResult<>(0, "0", "操作成功", null);
  }
  /**
   *系统内部错误
   *@author Jing Tiancai
   *@date 2019/10/14 11:42
   */
  public static BaseResult fail(ErrorCodeEnum ece){
    return fail(ece.getCode(),ece.getMsg());
  }
  public static BaseResult fail(String code,String msg){
    return new BaseResult<>(1, code, msg,null);
  }
  public static BaseResult fail(ErrorCode ec){
    return fail(1,ec.getCode(),ec.getMsg());
  }
  /**
   *外部异常
   *@author Jing Tiancai
   *@date 2019/10/14 11:35
   */
  public static BaseResult failEx(ErrorCode ec){
    return fail(-1,ec.getCode(),ec.getMsg());
  }

  public static BaseResult fail(int type,String code,String msg){
    return new BaseResult<>(type, code, msg,null);
  }

  /**
   *获取一个成功的实例，和put(key,value)连用
   *@author Jing Tiancai
   *@date 2019/10/21 16:45
   */
  public static BaseResult<Map<String,Object>> getInstance() {
    return new BaseResult<Map<String,Object>>(0, "0", "操作成功", new HashMap());
  }
  public  BaseResult put(String key,Object value) {
    if(null==this.getData()){
      this.setData((D) new HashMap<>());
    }
    Map<String,Object> data= BeanUtil.beanToMap(this.getData());
    data.put(key,value);
    this.setData((D)data);
    return this;
  }
}
