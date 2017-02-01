package com.progressoft.security.authentication.shared.extension;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

public interface Principal extends Serializable{
    String getUserName();
    String getTenant();
    default Deque<String> chains(){
        return new LinkedList<>();
    }
}
