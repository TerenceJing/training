package com.terence.itech.test.api;


import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Wrapper for RestAssured. Uses an HTTP request template and a single record housed in a RecordHandler object to
 * generate and perform an HTTP requests.
 * @author Dell
 */
public class HttpRequest {

  protected static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

  private RequestSpecification request;

  private String body = "";
  private Map<String, String> headers = new HashMap<String, String>();
  private HashMap<String, String> cookie_list = new HashMap<String, String>();

  public Map<String, String> getHeaders() {
    return headers;
  }

  /**
   * Constructor. Initializes the RequestSpecification (relaxedHTTPSValidation avoids certificate errors).
   *
   */
  public HttpRequest() {
    request = given().relaxedHTTPSValidation();
  }

  public HttpRequest(String proxy) {
    request = given().relaxedHTTPSValidation().proxy(proxy);
  }

  public void createRequest(TestCaseRecord record){
    request.baseUri("http://"+record.getHost());
    request.port(Integer.parseInt(record.getPort()));
    request.baseUri(record.getContext());
    request.basePath(record.getUrl());
    request.contentType(record.getContentType());
    request.header("token",record.getToken());
    request.param("userId","C0011782");
  }

  Response sendRequest(TestCaseRecord record){
    System.out.println(record.getRequestUrl());
    Response response = null;
      switch(record.getMethod().toUpperCase()) {
        case "GET": {
          response = request.get(record.getRequestUrl());
          break;
        }
        case "POST": {
          response = request.body(body).post(record.getRequestUrl());
          break;
        }
        case "PUT": {
          response = request.body(body).put(record.getRequestUrl());
          break;
        }
        case "DELETE": {
          response = request.delete(record.getRequestUrl());
          break;
        }
        default: {
          logger.error("Unknown call type: [" + record.getMethod() + "]");
        }
      }
    return response;
  }

  /**
   * Splits a template string into tokens, separating out tokens that look like "<<key>>"
   *
   * @param template String, the template to be tokenized.
   * @return list String[], contains the tokens from the template.
   */
  private String[] tokenizeTemplate(String template) {
    return template.split("(?=[<]{2})|(?<=[>]{2})");
  }

}