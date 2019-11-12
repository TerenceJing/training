package com.terence.itech.base.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019-10-05 21:08
 */
public class TokenFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    res.addHeader("Access-Control-Allow-Headers", "token");
    filterChain.doFilter(req, res);
  }
}
