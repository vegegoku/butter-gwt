package com.progressoft.security.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name="USERNAME")
    public String username;

    @Column(name="SECRET")
    public String secret;

    @Column(name="TENANT")
    public String tenant;

    @Column(name="EMAIL")
    public String email;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", secret='" + secret + '\'' +
                ", tenant='" + tenant + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        return username.equals(((UserEntity) other).username) && tenant.equals(((UserEntity) other).tenant);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + username.hashCode();
        result = 31 * result + secret.hashCode();
        result = 31 * result + tenant.hashCode();
        return result;
    }
}
