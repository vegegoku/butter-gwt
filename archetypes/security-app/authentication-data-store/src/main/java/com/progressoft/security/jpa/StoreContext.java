package com.progressoft.security.jpa;

import com.progressoft.security.jpa.store.UserStore;

public class StoreContext {

    private static UserStore userStore;

    private StoreContext(){}

    public static UserStore userStore() {
        return userStore;
    }

    public static void withUserStore(UserStore userStore) {
        StoreContext.userStore = userStore;
    }
}
