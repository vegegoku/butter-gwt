package com.progressoft.security.authentication.shared.extension;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public interface Principal extends Serializable{
    default Deque<String> chains(){
        return new LinkedList<>();
    }
}
