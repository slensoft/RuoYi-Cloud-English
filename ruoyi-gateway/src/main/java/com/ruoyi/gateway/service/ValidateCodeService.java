package com.ruoyi.gateway.service;

import java.io.IOException;
import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;

/**
 * Captcha processing
 *
 * @author ruoyi
 */
public interface ValidateCodeService
{
    /**
     * Generate captcha
     */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * Verify captcha
     */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}
