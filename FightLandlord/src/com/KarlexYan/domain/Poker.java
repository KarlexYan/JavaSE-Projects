package com.KarlexYan.domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Poker extends JLabel implements MouseListener {

    // 牌的名字 x-x
    private String name;
    private boolean up;

    private boolean canClicked = false;
    private boolean clicked = false;


//    public Poker() {
//    }

    public Poker(String name, boolean up) {
        this.name = name;
        this.up = up;

        //判断当前的牌是显示正面还是背面
        if (this.up) {
            this.turnFront();
        } else {
            this.turnRear();
        }

        //设置牌的宽高大小
        this.setSize(71, 96);
        //把牌显示出来
        this.setVisible(true);
        //给每一张牌添加鼠标监听
        this.addMouseListener(this);

    }

    public void turnFront() {
        this.setIcon(new ImageIcon("FightLandlord/image/poker/" + name + ".png"));
        this.up = true;
    }

    public void turnRear() {
        this.setIcon(new ImageIcon("FightLandlord/image/poker/rear.png"));
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * 设置
     *
     * @param up
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * 获取
     *
     * @return canClicked
     */
    public boolean isCanClicked() {
        return canClicked;
    }

    /**
     * 设置
     *
     * @param canClicked
     */
    public void setCanClicked(boolean canClicked) {
        this.canClicked = canClicked;
    }

    /**
     * 获取
     *
     * @return clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * 设置
     *
     * @param clicked
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String toString() {
        return "Poker{name = " + name + ", up = " + up + ", canClicked = " + canClicked + ", clicked = " + clicked + "}";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (canClicked) {
            Point from = this.getLocation();
            int step;
            if (clicked) {
                step = -20;
            } else {
                step = 20;
            }
            clicked = !clicked;
            Point to = new Point(from.x, from.y + step);
            this.setLocation(to);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
