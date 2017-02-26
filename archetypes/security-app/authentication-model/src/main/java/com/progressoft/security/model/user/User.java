package com.progressoft.security.model.user;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.model.otp.OtpGeneratorFactory;
import com.progressoft.security.model.otp.OtpHolder;

import java.util.Deque;
import java.util.Objects;

public class User {
    private final String userName;
    private final String secret;
    private final String tenant;
    private final String displayName;
    private final String email;
    private final Deque<String> chains;

    private User(String userName, String secret, String tenant, String email, String displayName, Deque<String> chains) {
        this.userName = userName;
        this.secret = secret;
        this.tenant = tenant;
        this.email = email;
        this.displayName=displayName;
        this.chains = chains;
    }

    public boolean isSamePassword(String password) {
        return Objects.equals(password, secret);
    }

    public Principal makePrincipal(PrincipalBuilder builder) {
        return builder.name(userName).tenant(tenant).displayName(displayName).chains(chains).build();
    }

    public OtpHolder sendOtp(OtpGeneratorFactory otpGeneratorFactory) {
        OtpHolder otpHolder = otpGeneratorFactory.make(secret).generate();
        otpHolder.sendEmail(email);
        return otpHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (isSameUser(o))
            return false;
        return true;
    }

    private boolean isSameUser(Object o) {
        return isNotSameType(o) || notSameUserName((User) o) || notSameSecret((User) o) ||
                notSameTenant((User) o);
    }

    private boolean notSameTenant(User o) {
        return !tenant.equals(o.tenant);
    }

    private boolean notSameSecret(User o) {
        return !secret.equals(o.secret);
    }

    private boolean notSameUserName(User o) {
        return !userName.equals(o.userName);
    }

    private boolean isNotSameType(Object o) {
        return o == null || getClass() != o.getClass();
    }

    public boolean hasChains() {
        return !chains.isEmpty();
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        result = 31 * result + (tenant != null ? tenant.hashCode() : 0);
        return result;
    }

    public static class UserBuilder {
        private String userName;
        private String displayName;
        private String secret;
        private String tenant;
        private String email;
        private Deque<String> chains;

        public UserBuilder name(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public UserBuilder tenant(String tenant) {
            this.tenant = tenant;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public UserBuilder chains(Deque<String> chains) {
            this.chains = chains;
            return this;
        }

        public User build() {
            return new User(userName, secret, tenant, email, displayName, chains);
        }
    }
}
