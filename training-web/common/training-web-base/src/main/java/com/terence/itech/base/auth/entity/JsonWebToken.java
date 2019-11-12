package com.terence.itech.base.auth.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019-10-05 21:55
 */
@Data
public class JsonWebToken {
  /**
   * 标准声明
   * iss: jwt签发者
   * sub: jwt所面向的用户
   * aud: 接收jwt的一方
   * exp: jwt的过期时间，这个过期时间必须要大于签发时间
   * nbf: 定义在什么时间之前，该jwt都是不可用的.
   * iat: jwt的签发时间
   * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
   */
  private String              iss;
  private String              aud;
  private String              sub;
  private Date iat;
  private Date                exp;
  private String              jti;
  private Date                nbf;

  /**
   * 声明
   */
  private Map<String, Object> claims;
}
