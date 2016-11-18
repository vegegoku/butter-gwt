package org.akab.engine.core.client.history;


import org.akab.engine.core.api.client.History.TokenizedPath;

import java.util.*;



public class Test {
    public static void main(String[] args) {

        Deque<TokenizedPath> tokens=new LinkedList<>();
//
//        StringTokenizer st=new StringTokenizer("_path=somePath&parameter1=value1&parameter2=value2&_path=someOtherPath&parameter1=value1","&");
//        while(st.hasMoreTokens()){
//            String token=st.nextToken();
//            parsPart(tokens, token);
//        }



        String[] parts="_path=somePath&parameter1=value1&parameter2=value2&_path=someOtherPath&parameter1=value1".split("&");



        tokens.forEach(t -> System.out.println(t));


    }

    private static void parsPart(Deque<TokenizedPath> tokens, String token) {
        if(token.startsWith("_path"))
            tokens.addLast(new UrlTokenizedPath(getPath(token)));
        else
            tokens.getLast().addParameter(token.split("=")[0], token.split("=").length>1?token.split("=")[1]:"");
    }

    private static String getPath(String token) {
        return token.split("=")[1];
    }


}