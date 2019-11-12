package com.terence.itech.flowable.controller;

import com.terence.itech.base.errorcode.ErrorCodeEnum;
import com.terence.itech.base.exception.GateException;
import com.terence.itech.base.exception.helper.ParamValidateUtil;
import com.terence.itech.base.exception.helper.entity.ParamMap;
import com.terence.itech.base.result.BaseResult;
import com.terence.itech.common.controller.BaseController;
import com.terence.itech.flowable.service.CommonProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/24 8:28
 */
@RestController
@RequestMapping(value = "/launch")
public class ProcessLaunchController extends BaseController {
  @Autowired
  private CommonProcessService processService;
  /**
   * 发起kpi考核确认流程
   *
   * @author Terence
   * @date 2019/10/22 16:48
   */
  @PostMapping("/confirm")
  @GateException(error = ErrorCodeEnum.PROCESS_CONFIRM_LAUNCH_FAILED)
  public BaseResult launchKpiConfirmTask(String businessId) {
    ParamValidateUtil.validParamsExist(ParamMap.getParamMap().build("businessId",businessId));
    String userId=getUserId();
    String processDiagramId="KpiConfirmProcess";
    Map<String, Object> variables = new HashMap<String, Object>() {{
      put("staff", userId);
      put("leader", "Ha20011021");
    }};
    processService.launchProcess(userId,businessId,processDiagramId,variables);
    return BaseResult.success();
  }

}
