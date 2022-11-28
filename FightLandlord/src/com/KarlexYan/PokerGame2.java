package com.KarlexYan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PokerGame2 {
    // 牌盒
    static ArrayList<String> list = new ArrayList<>();

    // 创建一个集合，用来添加牌的价值
    static HashMap<String, Integer> hm = new HashMap<>();

    static {
        // 准备牌
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        for (String c : color) {
            for (String n : number) {
                list.add(c + n);
            }
        }
        list.add(" 小王");
        list.add(" 大王");

        // 指定牌价值
        // 牌上的数字到Map集合中判断是否存在
        // 存在，获取价值，不存在，本身的数字就是价值
        hm.put("J", 11);
        hm.put("Q", 12);
        hm.put("K", 13);
        hm.put("A", 14);
        hm.put("2", 15);
        hm.put("小王", 50);
        hm.put("大王", 100);
    }

    public PokerGame2() {
        Collections.shuffle(list);

        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String poker = list.get(i);
            if (i <= 2) {
                lord.add(poker);
                continue;
            } else if (i % 3 == 0) {
                player1.add(poker);
            } else if (i % 3 == 1) {
                player2.add(poker);
            } else if (i % 3 == 2) {
                player3.add(poker);
            }
        }
        order(lord);
        order(player1);
        order(player2);
        order(player3);

//        System.out.println(lord);
//        System.out.println(player1);
//        System.out.println(player2);
//        System.out.println(player3);
        lookPoker("底牌", lord);
        lookPoker("玩家1", player1);
        lookPoker("玩家2", player2);
        lookPoker("玩家3", player3);
    }

    public void order(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String color1 = o1.substring(0, 1);
                int number1 = getValue(o1);

                String color2 = o2.substring(0, 1);
                int number2 = getValue(o2);

                int i = number2 - number1;

                return i = i == 0 ? color1.compareTo(color2) : i;
            }
        });
    }

    public int getValue(String poker) {
        String value = poker.substring(1);
        if (hm.containsKey(value)) {
            return hm.get(value);
        } else {
            return Integer.parseInt(value);
        }
    }

    public static void lookPoker(String name, ArrayList<String> list) {
        System.out.print(name + ":");
        for (String poker : list) {
            System.out.print(poker + " ");
        }
        System.out.println();
    }
}
