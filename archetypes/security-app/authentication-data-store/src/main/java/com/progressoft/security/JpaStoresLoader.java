package com.progressoft.security;

import com.progressoft.security.jpa.StoreContext;
import com.progressoft.security.jpa.store.Stores;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

public class JpaStoresLoader {
    private JpaStoresLoader(){}

    public static void load() {
        if(Objects.isNull(StoreContext.userStore())){
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
            StoreContext.withUserStore(context.getBean("repositories", Stores.class).getUserStore());
        }

    }
}
