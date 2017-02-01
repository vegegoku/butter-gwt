package com.progressoft.security.model;

import com.progressoft.notification.configuration.SmtpConfiguration;
import com.progressoft.security.model.otp.SimpleOtpHolder;

public class OtpHolderSpy extends SimpleOtpHolder {
    public final String code;
    public final int timeout;
    private int elapsedSeconds=0;

    public OtpHolderSpy(String code, int timeout, SmtpConfiguration configuration) {
        super(code, timeout, configuration);
        this.code = code;
        this.timeout=timeout;
    }

    public void addElapsedSeconds(int seconds){
        this.elapsedSeconds+=seconds;
    }

    @Override
    protected long getCurrentTimeMillis() {
        return super.getCurrentTimeMillis()+(elapsedSeconds*1000);
    }
}
