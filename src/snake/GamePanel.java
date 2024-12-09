package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//游戏的面板
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //定义蛇的数据结构
    int length;//蛇的长度
    int[] snakeX=new int[600];//蛇的x坐标
    int[] snakeY=new int[500];//蛇的y坐标
    String fx;

    //食物的坐标
    int foodx;
    int foody;
    Random random=new Random();


    boolean start=false;//游戏的状态默认不开始
    Timer timer=new Timer(100,this);//100ms执行一次
    boolean fail=false;//游戏默认不失败
    int score;

    public GamePanel(){
        init();
        this.setFocusable(true);//获得焦点事件
        this.addKeyListener(this);
        timer.start();
    }

    //初始化方法
    public void init(){
        length=3;
        snakeX[0]=100;snakeY[0]=100;//头部
        snakeX[1]=75;snakeY[1]=100;//第一个身体的坐标
        snakeX[2]=50;snakeY[2]=100;//第二个身体的坐标
        fx="R";//初始方向为向右
        //把食物随机放在面板上
        foodx=25*random.nextInt(34)+25;
        foody=25*random.nextInt(24)+75;
        score=0;
    }
    //绘制面板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        //绘制静态的面板
        this.setBackground(Color.white);
        Data.header.paintIcon(this,g,25,11);//头部广告栏
        g.fillRect(25,75,850,600);//默认的游戏界面

        //画积分
        g.setColor(Color.black);
        g.setFont(new Font("微软雅黑",Font.BOLD,15));
        g.drawString("长度"+length,750,35);
        g.drawString("分数"+score,750,50);

        //画食物
        Data.food.paintIcon(this,g,foodx,foody);

        //把小蛇画上去
        if(fx.equals("R"))
        {
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);//蛇头初始化向右
        }
        else if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        else {
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }


        for (int i=1;i<length;i++)
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);

        //游戏状态
        if(start==false)
        {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("按下空格开始游戏",300,300);

        }
        if(fail){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("游戏失败,按下空格重新开始",300,300);
        }
    }

    //键盘监听
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode=e.getKeyCode();
        if(keycode==KeyEvent.VK_SPACE)
        {
            if(fail)
            {
                fail=false;
                init();
            }else {
                start=!start;
            }

            repaint();
        }
        //小蛇移动
        if(keycode==KeyEvent.VK_LEFT && fx!="R"){
            fx="L";
        }
        else if(keycode==KeyEvent.VK_RIGHT && fx!="L")
        {
            fx="R";
        }
        else if(keycode==KeyEvent.VK_UP && fx!="D"){
            fx="U";
        }
        else if(keycode==KeyEvent.VK_DOWN && fx!="U"){
            fx="D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //事件监听
    @Override
    public void actionPerformed(ActionEvent e) {
        if(start && !fail){//如果游戏是开始状态
            if(snakeX[0]==foodx && snakeY[0]==foody){
                length++;//长度加一
                score+=10;
                //再次随机食物
                foodx=25*random.nextInt(34)+25;
                foody=25*random.nextInt(24)+75;
            }

            for (int i = length-1; i >0 ; i--) {
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }
            if(fx.equals("R")){
                snakeX[0]+=25;
                //边界判断
                if(snakeX[0]>850)
                {
                    snakeX[0]=25;
                }
            } else if (fx.equals("L")) {
                snakeX[0]-=25;
                //边界判断
                if(snakeX[0]<25)
                {
                    snakeX[0]=850;
                }
            } else if (fx.equals("U")) {
                snakeY[0]-=25;
                if(snakeY[0]<75){
                    snakeY[0]=650;
                }
            } else if (fx.equals("D")) {
                snakeY[0]+=25;
                if(snakeY[0]>650)
                {
                    snakeY[0]=75;
                }
            }

            //失败判定,撞到自己
            for (int i = 1; i < length; i++) {
                if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i])
                {
                    fail=true;
                }
            }

            repaint();//重画
        }
        timer.start();//定时器开始
    }
}
