package com.terence.itech.base.exception.helper.entity;

import java.util.HashMap;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/11 18:05
 */
public class ParamMap<Key,Value> extends HashMap<Key,Value> {
  private ParamMap<Key,Value> paramMap;
  public static <Key,Value> ParamMap<Key,Value> getParamMap(){
    return new ParamMap<>();
  }

  public ParamMap<Key,Value> build(Key key,Value value){
    put(key,value);
    return this;
  }
}
