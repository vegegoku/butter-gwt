package org.akab.engine.annotations.processor.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class FullClassName {
    private final String fullClassName;

    public FullClassName(String fullClassName) {
        if(Objects.isNull(fullClassName) || fullClassName.trim().isEmpty())
            throw new InvalidClassName();
        this.fullClassName = fullClassName.trim().replace(" ","");
    }

    public String asSimpleName() {
        return getSimpleName(simpleFullName());
    }

    private String getSimpleName(String name) {
        return name.substring(name.lastIndexOf(".")+1, name.length());
    }

    public String asPackage() {
        return getPackage(simpleFullName());
    }

    private String getPackage(String name) {
        return name.substring(0, name.lastIndexOf(".")>-1?name.lastIndexOf("."):0);
    }

    private String simpleFullName() {
        return new StringTokenizer(fullClassName, "<,>").nextToken();
    }

    public String asImport() {
        return new String(simpleFullName());
    }

    public String asSimpleGenericName() {
        String result=this.fullClassName;
        StringTokenizer st=new StringTokenizer(fullClassName, "<,>");
        while(st.hasMoreTokens())
            result=result.replace(appendDot(new FullClassName(st.nextToken()).asPackage()),"");
        return result;
    }

    private String appendDot(String part) {
        return part.isEmpty()?part:(part.endsWith(".")?part:(part+"."));
    }

    public List<String> allImports() {
        StringTokenizer st=new StringTokenizer(fullClassName, "<,>");
        List<String> imports=new LinkedList<>();
        while (st.hasMoreTokens()){
            String s=new FullClassName(st.nextToken()).asImport();
            if(!imports.contains(s))
                imports.add(s);
        }
        return imports;
    }

    public class InvalidClassName extends RuntimeException{
    }
}
