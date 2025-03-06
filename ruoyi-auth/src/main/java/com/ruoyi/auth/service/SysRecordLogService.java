package com.ruoyi.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.system.api.RemoteLogService;
import com.ruoyi.system.api.domain.SysLogininfor;

/**
 * Record log method
 *
 * @author ruoyi
 */
@Component
public class SysRecordLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * Record login information
     *
     * @param username Username
     * @param status Status
     * @param message Message content
     * @return
     */
    public void recordLogininfor(String username, String status, String message)
    {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr());
        logininfor.setMsg(message);
        // Log status
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
        {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}
