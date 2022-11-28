package com.KarlexYan.game;

import com.KarlexYan.domain.User;
import com.KarlexYan.util.CodeUtil;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> allUsers = new ArrayList<>();

    static {
        allUsers.add(new User("zhangsan", "123"));
        allUsers.add(new User("lisi", "1234"));
    }

    JButton login = new JButton();
    JButton register = new JButton();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    //正确的验证码
    JLabel rightCode = new JLabel();

    public LoginJFrame() {
        //初始化界面
        initJFrame();
        //初始化组件，在这个界面中添加内容
        initView();
        //让当前界面显示出来
        this.setVisible(true);
    }

    //在这个界面中添加内容
    private void initView() {
        //1. 添加用户名文字
        Font usernameFont = new Font(null, 1, 16);
        JLabel usernameText = new JLabel("用户名");
        usernameText.setForeground(Color.white);
        usernameText.setFont(usernameFont);
        usernameText.setBounds(140, 55, 55, 22);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(223, 46, 200, 30);
        username.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel("密码");
        Font passwordFont = new Font(null, 1, 16);
        passwordText.setForeground(Color.white);
        passwordText.setFont(passwordFont);
//        passwordText.setBounds(197, 95, 40, 22);
        passwordText.setBounds(140, 95, 55, 22);
        this.getContentPane().add(passwordText);

        //4.密码输入框
//        password.setBounds(263, 87, 160, 30);
        password.setBounds(223, 87, 200, 30);
        password.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel("验证码");
        Font codeFont = new Font(null, 1, 16);
        codeText.setForeground(Color.white);
        codeText.setFont(codeFont);
//        codeText.setBounds(215, 142, 55, 22);
        codeText.setBounds(140, 142, 55, 22);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(223, 133, 100, 30);
        code.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.getContentPane().add(code);

        //获取正确的验证码
        String codeStr = CodeUtil.getCode();
        Font rightCodeFont = new Font(null, 1, 15);
        //设置颜色
        rightCode.setForeground(Color.RED);
        //设置字体
        rightCode.setFont(rightCodeFont);
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(350, 133, 100, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        login.setBounds(170, 330, 128, 47);
        login.setIcon(new ImageIcon("FightLandlord/image/login/登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(320, 330, 128, 47);
        register.setIcon(new ImageIcon("FightLandlord/image/login/注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("FightLandlord/image/login/background.png"));
        background.setBounds(0, 0, 633, 423);
        this.getContentPane().add(background);

    }

    //初始化组件，在这个界面中添加内容
    private void initJFrame() {
        this.setSize(633, 423);//设置宽高
        this.setTitle("斗地主 V1.0");//设置标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login) {
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            //获取用户输入的验证码
            String codeInput = code.getText();

            //判断验证码是否为空
            if (codeInput.length() == 0) {
                showJDialog("验证码不能为空");
                return;
            }
            //判断用户名和密码是否为空
            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                showJDialog("用户名或者密码为空");
                return;
            }

            //判断验证码是否正确
            if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("验证码输入错误");
                return;
            }

            //判断集合钟是否包含当前用户对象
            User userInfo = new User(usernameInput, passwordInput);
            if (allUsers.contains(userInfo)) {
                //关闭当前登录界面
                this.setVisible(false);
                //打开游戏主页面
                new GameJFrame();
            } else {
                showJDialog("用户名或密码错误");
            }
        } else if (obj == register) {
            //实现注册功能，待补
            System.out.println("点击了注册按钮");
            showJDialog("还在开发，别急！");
        } else if (obj == rightCode) {
            // 获取新的验证码
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }

    // 按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("FightLandlord/image/login/登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("FightLandlord/image/login/注册按下.png"));
        }
    }

    // 松开按钮
    @Override
    public void mouseReleased(MouseEvent e) {
//        if(e.getSource() == login){
//            login.setIcon(new ImageIcon("image/login/登录按钮.png"));
//        } else if (e.getSource() == register) {
//            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
//        }
    }

    // 鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("FightLandlord/image/login/登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("FightLandlord/image/login/注册按下.png"));
        }
    }

    // 鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("FightLandlord/image/login/登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("FightLandlord/image/login/注册按钮.png"));
        }
    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        //让弹框展示出来
        jDialog.setVisible(true);
    }
}
