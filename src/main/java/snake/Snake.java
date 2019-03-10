package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

public class Snake implements ActionListener, KeyListener, EventListener, ContainerListener {

    public int boundary = 500;
    public int size = 10;
    public static Snake snake;
    private JFrame jFrame;
    private Toolkit toolkit;
    private RenderPanel renderPanel;
    private Timer timer = new Timer(200, this);
    Random random;
    Dimension dimension;
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public Point head, cherry, other;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT=3, SCALE = 10;
    private int direction  = RIGHT;
    private int score;
    private int tailLength =3;
    public boolean gameOver = false;
    public boolean paused;
    public int movement = 1;



    public Snake(){
        head = new Point(1,1);
        renderPanel = new RenderPanel();
        toolkit = Toolkit.getDefaultToolkit();
        random = new Random();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame = new JFrame("snake.Snake");
        jFrame.setFocusable(true);
        jFrame.setVisible(true);
        jFrame.setSize(boundary, boundary);
        jFrame.setLocation(dimension.width/2, dimension.height/2);
        jFrame.add(renderPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addKeyListener(this);

        generateCherry();

        for(int i =0; i < tailLength; i++){
            snakeParts.add(new Point(head.x, head.y));
        }
        timer.start();
    }

    public void startGane(){
        gameOver = false;
        paused = false;
    }


    public void move(Point point, int movement){
        point += movement;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        XboundaryCheck();
            snakeParts.add(new Point(head.x, head.y));
            if(direction==DOWN){
                head.y += movement;
            }
            if(direction==UP){
                head.y -= movement;
            }
            if(direction==LEFT){
                head.x -= movement;
            }
            if(direction==RIGHT){
                head.x += movement;
            }
            snakeParts.remove(0);
            if (cherry != null){
                if(head.x==cherry.x/10 && head.y == cherry.y/10){
                    head = cherry;
                    System.out.println("yooooou got that shit!!");
                    score+=10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(boundary)%10*10, random.nextInt(boundary)%10*10);
                }
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_DOWN && direction != UP){
            direction = DOWN;
        }
        if (i == KeyEvent.VK_UP && direction != DOWN){
            direction = UP;
        }
        if (i == KeyEvent.VK_RIGHT && direction != LEFT){
            direction = RIGHT;
        }
        if (i == KeyEvent.VK_LEFT && direction != RIGHT){
            direction = LEFT;
        }
        if (i == KeyEvent.VK_SPACE && paused == false){
            paused = true;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void componentAdded(ContainerEvent e) {
        System.out.println(head.x);
    }

    @Override
    public void componentRemoved(ContainerEvent e) {
    }


    public static void main(String[] args) {
        snake = new Snake();
    }

    public void XboundaryCheck(){
        if(head.x > boundary/size){
            head.x = 1;
        }
        if(head.x < 0){
            head.x = boundary/size;
        }
        if(head.y > boundary/size){
            head.y = 0;
        }
        if(head.y < 0){
            head.y = boundary/size;
        }

    }

    public void generateCherry(){
        cherry = new Point(random.nextInt(boundary)/10*10, random.nextInt(boundary)/10*10);
        System.out.println(random.nextInt(boundary));
        System.out.println(random.nextInt(boundary)/10*10);

        System.out.println("cherry x: "+cherry.x );
        System.out.println("cherry y: "+cherry.y );
    }
}
