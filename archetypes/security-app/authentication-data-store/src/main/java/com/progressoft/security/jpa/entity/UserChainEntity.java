package com.progressoft.security.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_CHAINS")
public class UserChainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "CHAIN_NAME", unique = true)
    public String chainName;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "CHAIN_ORDER", nullable = false)
    public long chainOrder;

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        return chainName.equals(((UserChainEntity) other).chainName) && username.equals(((UserChainEntity) other).username);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + chainName.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
