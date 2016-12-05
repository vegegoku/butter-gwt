package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class TestRequest extends ServerRequest {

    private StringBuilder testWord=new StringBuilder("");

    public void appendTestWord(String testWord){
        this.testWord.append(testWord);
    }

    public String getTestWord(){
        return this.testWord.toString();
    }
}
