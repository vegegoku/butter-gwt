package com.progressoft.security.authentication.configuration.loader;

import com.progressoft.security.authentication.configuration.PropertiesOtpConfigurationLoader;
import com.progressoft.security.otp.shared.extension.OtpConfiguration;
import org.apache.commons.configuration.ConversionException;
import org.junit.Test;

import static org.junit.Assert.*;

public class OtpConfigurationLoaderTest {

    public static final int THIRTY_SECONDS = 30;
    public static final int SIXTY_SECONDS = 60;

    @Test
    public void givenEmptyConfigurationFile_whenLoadingConfiguration_otpTimeOutShouldBeThirty() throws Exception {
        assertEquals(new PropertiesOtpConfigurationLoader("empty-otp-configuration.properties").load().timeout(),
                THIRTY_SECONDS);
    }

    @Test(expected = ConversionException.class)
    public void givenConfigurationFileWithEmptyStringTimeoutValue_whenLoadingConfigurationAndFetchTheTimeout_shouldThrowException() throws Exception {
        new PropertiesOtpConfigurationLoader("invalid-otp-configuration.properties").load().timeout();
    }

    @Test(expected = OtpConfiguration.InvalidTimeoutProvidedException.class)
    public void givenConfigurationFileWithNegativeValue_whenLoadingConfigurationAndFetchTheTimeout_shouldThrowException() throws Exception {
        new PropertiesOtpConfigurationLoader("negative-otp-configuration.properties").load().timeout();
    }

    @Test
    public void givenValidConfigurationFile_whenLoadingConfiguration_shouldReturnOtpTimeout() throws Exception {
        assertNotNull(new PropertiesOtpConfigurationLoader("otp-configuration.properties").load().timeout());
    }
    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationWithTimeOfSixty_shouldReturnTimeoutOf60() throws Exception {
        assertEquals(new PropertiesOtpConfigurationLoader("otp-configuration.properties").load().timeout(), SIXTY_SECONDS);
    }

    @Test
    public void givenValidConfigurationFile_whenLoadingConfiguration_shouldReturnDigitsCount() throws Exception {
        assertNotNull(new PropertiesOtpConfigurationLoader("otp-configuration.properties").load().digitsCount());
    }

    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationWithTimeOfSixty_shouldReturnDigitsCountOf4() throws Exception {
        assertEquals(new PropertiesOtpConfigurationLoader("otp-configuration.properties").load().digitsCount(), 4);
    }

    @Test(expected = OtpConfiguration.InvalidDigitsCountProvidedException.class)
    public void givenValidConfigurationFile_whenLoadingConfigurationWithDigitsCountLessThan4_thenShouldThrowException() throws Exception {
        assertNotNull(new PropertiesOtpConfigurationLoader("invalid-otp-configuration.properties").load().digitsCount());
    }

    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationDigitsCountNotSpecified_shouldReturnDigitsCountOf4() throws Exception {
        assertEquals(new PropertiesOtpConfigurationLoader("otp-default-digits-count-configuration.properties").load().digitsCount(), 4);
    }
}
