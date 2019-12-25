package com.terence.itech.base.exception.helper;

import com.terence.itech.base.errorcode.ErrorCodeEnum;
import com.terence.itech.base.exception.BusinessException;
import com.terence.itech.base.exception.helper.entity.ParamMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/11 17:22
 */
public class ParamValidateUtil {
  private ParamMap<String,Object> params;
  /**
   * 一个必填参数的校验
   *验证一个字符串参数是否存在
   *@author Terence
   *@date 2019/10/11 17:31
   */
  public static void validParamExist(String paramName,String param){
    if(StringUtils.isBlank(param)){
      throw new BusinessException(ErrorCodeEnum.PARAMETER_EMPTY.ec(paramName));
    }
  }
  /**
   *多个必填参数的校验
   *判断一些列参数是否为空
   *@author Terence
   *@date 2019/10/17 17:04
   */
  public static void validParamsExist(ParamMap<Object,Object> params){
    if(CollectionUtils.isEmpty(params)){
      return;
    }
    List<String> blankParamList=new ArrayList<>();
    params.forEach((key,value)->{
      if(null==value|| StringUtils.isBlank(String.valueOf(value))){
        blankParamList.add(String.valueOf(key));
      }
    });
    if(!CollectionUtils.isEmpty(blankParamList)){
      String paramNames=String.join(",",blankParamList);
      throw new BusinessException(ErrorCodeEnum.PARAMETER_EMPTY.ec(paramNames));
    }
  }
}
