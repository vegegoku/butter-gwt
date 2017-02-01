package com.progressoft.security.model;

import com.progressoft.notification.configuration.SmtpConfiguration;
import com.progressoft.notification.configuration.SmtpConfigurationContext;
import com.progressoft.security.model.otp.Otp;
import com.progressoft.security.model.otp.OtpHolder;
import com.progressoft.security.model.otp.OtpHolderFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OtpTest {

    private OtpHolderFactory fakeOtpHolderFactory=OtpHolderSpy::new;
    private SmtpConfiguration configuration;

    @Before
    public void setUp() throws Exception {
        configuration=SmtpConfigurationContext.configure("smtp-configuration.properties");
    }

    @Test
    public void canGenerateOtp() throws Exception {
        new Otp("someSecret", 4,30);
    }

    @Test(expected = Otp.InvalidSecretProvidedException.class)
    public void givenAnOtp_whenSecretIsNull_thenThrowException() throws Exception {
        new Otp(null, 4, 30);
    }

    @Test(expected = Otp.InvalidSecretProvidedException.class)
    public void givenAnOtp_whenSecretIsEmpty_thenThrowException() throws Exception {
        new Otp("", 4,30);
    }
    @Test(expected = Otp.InvalidSecretProvidedException.class)
    public void givenAnOtp_whenSecretIsSpaces_thenThrowException() throws Exception {
        new Otp("   ", 4,30);
    }

    @Test(expected = Otp.InvalidTimeoutProvidedException.class)
    public void givenAnOtp_whenTimeOutZero_thenThrowException() throws Exception {
        new Otp("secret", 4,0);
    }

    @Test(expected = Otp.InvalidTimeoutProvidedException.class)
    public void givenAnOtp_whenTimeOutIsNegative_thenThrowException() throws Exception {
        new Otp("secret", 4,-1);
    }

    @Test(expected = Otp.DigitsCountLessAllowedMinimumException.class)
    public void givenAnOtp_whenDiditsCountIsLessThan4_thenThrowException() throws Exception {
        new Otp("secret", 3,30);
    }

    @Test
    public void givenAnOtp_whenGenerateAnOtpCode_thenShouldObtainOtpHolder() throws Exception {
        OtpHolder holder=new Otp("secret", 4,30).generate();
        assertNotNull(holder);
    }

    @Test
    public void givenAnOtp_whenGenerateAnOtpCodeAndThenValidateThatOtpRightAway_thenOtpShouldBeValid() throws Exception {
        OtpHolder otpHolder=new Otp("secret", 4,1).generate(fakeOtpHolderFactory);
        assertTrue(otpHolder.verify(((OtpHolderSpy)otpHolder).code));
    }

    @Test
    public void givenAnOtpGeneratedWith30SecondsTimeout_whenVerifyCodeBefore30SecondsPass_thenOtpShouldBeValid() throws Exception {
        OtpHolderSpy otpHolder=(OtpHolderSpy)new Otp("secret", 4,30).generate(fakeOtpHolderFactory);
        for(int i=1;i<31;i++){
            otpHolder.addElapsedSeconds(1);
            assertTrue(otpHolder.verify(otpHolder.code));
        }
    }

    @Test
    public void givenAnOtpGeneratedWith30SecondsTimeout_whenVerifyCodeAfter30SecondsPass_thenOtpShouldBeInvalid()
            throws Exception {
        OtpHolderSpy otpHolder = (OtpHolderSpy)new Otp("secret", 4,30).generate(fakeOtpHolderFactory);
        otpHolder.addElapsedSeconds(31);
        assertFalse(otpHolder.verify(otpHolder.code));
    }

    @Test
    public void givenAnOtpGenerated_whenVerifyCodeDifferentFromTheGeneratedCode_thenOtpShouldBeInvalid()
            throws Exception {
        OtpHolderSpy otpHolder = (OtpHolderSpy)new Otp("secret", 4,30).generate(fakeOtpHolderFactory);
        assertFalse(otpHolder.verify(new StringBuilder(otpHolder.code).reverse().toString()));
    }
}
