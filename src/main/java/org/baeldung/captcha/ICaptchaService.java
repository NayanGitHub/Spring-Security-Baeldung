package org.baeldung.captcha;

import org.baeldung.web.error.ReCaptchaInvalidException;

public interface ICaptchaService {
    void processResponse(final String response) throws ReCaptchaInvalidException;

    String getReCaptchaSite();

    String getReCaptchaSecret();
}
