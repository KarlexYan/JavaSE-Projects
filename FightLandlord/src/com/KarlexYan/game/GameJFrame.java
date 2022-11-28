package com.KarlexYan.game;

import com.KarlexYan.domain.Poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

;

public class GameJFrame extends JFrame implements ActionListener {

    public static Container container = null;

    //管理抢地主和不抢两个按钮
    JButton landlord[] = new JButton[2];

    //管理出牌和不要两个按钮
    JButton publishCard[] = new JButton[2];

    //游戏界面中地主的图标
    JLabel dizhu;

    //集合嵌套集合
    //大集合中有三个小集合
    //小集合中装着每一个玩家当前要出的牌
    //0索引：左边的电脑玩家
    //1索引：中间的自己
    //2索引：右边的电脑玩家
    ArrayList<ArrayList<Poker>> currentList = new ArrayList<>();

    //集合嵌套集合
    //大集合中有三个小集合
    //小集合中装着每一个玩家手上的牌
    //0索引：左边的电脑玩家
    //1索引：中间的自己
    //2索引：右边的电脑玩家
    ArrayList<ArrayList<Poker>> playerList = new ArrayList<>();

    //底牌
    ArrayList<Poker> lordList = new ArrayList<>();

    //牌盒，装所有的牌
    ArrayList<Poker> pokerList = new ArrayList();

    //三个玩家前方的文本提示
    //0索引：左边的电脑玩家
    //1索引：中间的自己
    //2索引：右边的电脑玩家
    JTextField time[] = new JTextField[3];

    public GameJFrame() {
        //设置任务栏的图标
        setIconImage(Toolkit.getDefaultToolkit().getImage("FightLandlord/image/poker/dizhu.png"));

        initJFrame();

        initView();

        this.setVisible(true);

        initCard();

        initGame();

    }

    private void initGame() {
        // 创建三个集合用来装三个玩家准备要出的牌
        for (int i = 0; i < 3; i++) {
            ArrayList<Poker> list = new ArrayList<>();
            //增加到大集合中方便管理
            currentList.add(list);
        }

        landlord[0].setVisible(true);
        landlord[1].setVisible(true);

        for (JTextField field : time) {
            field.setText("倒计时30秒");
            field.setVisible(true);
        }
    }

    public int getValue(Poker poker) {
        // 获取每张牌的价值
        String name = poker.getName();
        int color = Integer.parseInt(name.substring(0, 1));
        int value = Integer.parseInt(name.substring(2));

        //在本地文件中，每张牌的文件名为：数字1-数字2
        //数字1表示花色，数字2表示牌的数字
        //其中3~K对应的数字2，可以视为牌的价值
        //所以，我们单独判断大小王，A，2即可


        // 大小王
        if (color == 5) {
            return value = value + 100;
        }

        //计算A的价值
        if (value == 1) {
            return value = value + 20;
        }

        // 计算2的价值
        if (value == 2) {
            return value = value + 30;
        }

//        如果不是大小王 A 2 牌的价值就是牌对应的数字
        return value;
    }

    public void order(ArrayList<Poker> list) {
        // 排序
        Collections.sort(list, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                // 获取o1的花色和价值
                int color1 = Integer.parseInt(o1.getName().substring(0, 1));
                int value1 = getValue(o1);

                // 获取o2的花色和价值
                int color2 = Integer.parseInt(o2.getName().substring(0, 1));
                int value2 = getValue(o2);

                // 比较
                int i = value2 - value1;
                return i = i == 0 ? color2 - color1 : i;
            }
        });
    }

    private void initCard() {
        // 准备牌，将所有牌添加到牌盒cardList当中
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 13; j++) {
                if ((i == 5) && (j > 2)) {
                    break;
                } else {
                    Poker poker = new Poker(i + "-" + j, false);
                    poker.setLocation(350, 150);
                    pokerList.add(poker);
                    container.add(poker);
                }
            }
        }

        // 洗牌
        Collections.shuffle(pokerList);

        // 装三个玩家的牌，三个集合放到大集合中管理
        ArrayList<Poker> player1 = new ArrayList<>();
        ArrayList<Poker> player2 = new ArrayList<>();
        ArrayList<Poker> player3 = new ArrayList<>();

        // 发牌
        for (int i = 0; i < pokerList.size(); i++) {
            // 获取当前遍历的牌
            Poker poker = pokerList.get(i);
            System.out.println(poker);
            if (i <= 2) {
                lordList.add(poker);
                continue;
            } else if (i % 3 == 0) {

                player1.add(poker);
            } else if (i % 3 == 1) {
                player2.add(poker);
            } else if (i % 3 == 2) {
                player3.add(poker);
            }

            // 存储玩家的手牌
            playerList.add(player1);
            playerList.add(player2);
            playerList.add(player3);

            //把当前的牌置于最顶多，这样才会有牌依次错开且叠起来的效果
            container.setComponentZOrder(poker, 0);
        }
        // 排序
        for (int i = 0; i < 3; i++) {

        }


    }


    public void initView() {

        //不抢地主按钮
        JButton noBut = new JButton("不抢");
        noBut.setBounds(320, 400, 75, 20);
        noBut.addActionListener(this);
        noBut.setVisible(false);
        landlord[0] = noBut;
        container.add(noBut);

        // 抢地主按钮
        JButton robBut = new JButton("抢地主");
        robBut.setBounds(420, 400, 75, 20);
        robBut.addActionListener(this);
        robBut.setVisible(false);
        landlord[1] = robBut;
        container.add(robBut);


        // 不出牌按钮
        JButton noCardBut = new JButton("不要");
        noCardBut.setBounds(320, 400, 60, 20);
        noCardBut.addActionListener(this);
        noCardBut.setVisible(false);
        publishCard[0] = noCardBut;
        container.add(noCardBut);

        // 出牌按钮
        JButton outCardBut = new JButton("出牌");
        outCardBut.setBounds(420, 400, 60, 20);
        outCardBut.addActionListener(this);
        outCardBut.setVisible(false);
        publishCard[1] = outCardBut;
        container.add(outCardBut);

        // 倒计时提示
        for (int i = 0; i < 3; i++) {
            time[i] = new JTextField("倒计时：");
            time[i].setEditable(false);
            time[i].setVisible(false);
            container.add(time[i]);
            time[i].setBackground(Color.LIGHT_GRAY);
            time[i].setBorder(null);
        }
        time[0].setBounds(140, 230, 60, 20);
        time[1].setBounds(374, 360, 60, 20);
        time[2].setBounds(620, 230, 60, 20);

        // 创建地主图标
        dizhu = new JLabel(new ImageIcon("FightLandlord/image/poker/dizhu.png"));
        dizhu.setVisible(false);
        dizhu.setSize(40, 40);
        container.add(dizhu);
    }

    public void initJFrame() {
        this.setTitle("斗地主");
        this.setSize(830, 620);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        container = this.getContentPane();

        container.setLayout(null);
        container.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == landlord[1]) {
            //点击抢地主
            time[1].setText("抢地主");
            ArrayList<Poker> player1 = playerList.get(1);
            player1.addAll(lordList);
            lordList.clear();
            order(playerList.get(1));
            for (Poker poker : player1) {
                poker.turnFront();
            }
            rePosition(playerList.get(1), 1);
        } else if (e.getSource() == landlord[0]) {
            //点击不抢
            time[1].setText("不抢");
        } else if (e.getSource() == publishCard[0]) {
            //点击不要
            time[1].setText("不要");
        } else if (e.getSource() == publishCard[1]) {
            time[1].setText("出牌");
        }
    }

    private void rePosition(ArrayList<Poker> list, int flag) {
        Point p = new Point();
        if (flag == 0) {
            p.x = 50;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        if (flag == 1) {
            p.x = (800 / 2) - (list.size() + 1) * 21 / 2;
            p.y = 450;
        }
        if (flag == 2) {
            p.x = 700;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Poker poker = list.get(i);
            move(poker, poker.getLocation(), p);
            container.setComponentZOrder(poker, 0);
            if (flag == 1)
                p.x += 21;
            else
                p.y += 15;
        }
    }

    private void move(Poker poker, Point from, Point to) {

        if (to.x != from.x) {
            double k = (1.0) * (to.y - from.y) / (to.x - from.x);
            double b = to.y - to.x * k;
            int flag = 0;
            if (from.x < to.x)
                flag = 20;
            else {
                flag = -20;
            }
            for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
                double y = k * i + b;

                poker.setLocation(i, (int) y);

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        poker.setLocation(to);
    }
}
