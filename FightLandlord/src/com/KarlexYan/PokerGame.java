package com.KarlexYan;

//import com.sun.source.tree.Tree;

import java.util.*;

public class PokerGame {
    static HashMap<Integer, String> hm = new HashMap<>();
    static ArrayList<Integer> list = new ArrayList<>();

    static {
        // 准备牌
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        // 序号
        int serialNumber = 1;

        // 将牌与序号对应存放
        for (String n : number) {
            for (String c : color) {
                hm.put(serialNumber, c + n);
                list.add(serialNumber);
                serialNumber++;

            }
        }

        hm.put(serialNumber, "小王");
        list.add(serialNumber);
        serialNumber++;
        hm.put(serialNumber, "大王");
        list.add(serialNumber);
//        System.out.println(hm);

//        System.out.println(list);
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(list);
//        System.out.println(list);

        // 发牌
        TreeSet<Integer> lord = new TreeSet<>(Comparator.reverseOrder());
        TreeSet<Integer> player1 = new TreeSet<>(Comparator.reverseOrder());
        TreeSet<Integer> player2 = new TreeSet<>(Comparator.reverseOrder());
        TreeSet<Integer> player3 = new TreeSet<>(Comparator.reverseOrder());

        for (int i = 0; i < list.size(); i++) {
            int serialNumber = list.get(i);
            if (i <= 2) {
                lord.add(serialNumber);
                continue;
            } else if (i % 3 == 0) {
                player1.add(serialNumber);
            } else if (i % 3 == 1) {
                player2.add(serialNumber);
            } else if (i % 3 == 2) {
                player3.add(serialNumber);
            }
        }


//         看牌
        lookPoker("底牌", lord);
        lookPoker("玩家1", player1);
        lookPoker("玩家2", player2);
        lookPoker("玩家3", player3);
    }

    public static void lookPoker(String name, TreeSet<Integer> ts) {
        System.out.print(name + ":");
        for (Integer serialNumber : ts) {
//            System.out.print(serialNumber+" ");
            String poker = hm.get(serialNumber);
            System.out.print(poker + " ");

        }
        System.out.println();
    }
}
