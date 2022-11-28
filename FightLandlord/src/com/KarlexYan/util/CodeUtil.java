package com.KarlexYan.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {
    public static String getCode() {
        // 创建一个集合
        ArrayList<Character> list = new ArrayList<>();

        // 添加字母
        for (int i = 0; i < 26; i++) {
            list.add((char) ('A' + i));
            list.add((char) ('a' + i));

        }

        // 生成4个随机字母
        String result = "";
        Random rd = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = rd.nextInt(list.size());
            char c = list.get(randomIndex);
            result = result + c;
        }
        // 在字符串后拼接数字
        int number = rd.nextInt(10);
        result = result + number;

        // 把字符串变成字符数组
        char[] arr = result.toCharArray();

        // 生成随机索引，交换
        int index = rd.nextInt(arr.length);
        char temp = arr[4];
        arr[4] = arr[index];
        arr[index] = temp;

        // 把数组变回字符串，输出验证码
        String code = new String(arr);
        return code;

    }
}
