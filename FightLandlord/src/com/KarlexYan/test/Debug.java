package com.KarlexYan.test;

import com.KarlexYan.util.CodeUtil;

public class Debug {
    public static void main(String[] args) {

        CodeUtil codeUtil = new CodeUtil();
        String code = CodeUtil.getCode();
        System.out.println(code);
    }
}
