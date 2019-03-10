package snake;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    public static int curColour = 0;
    public static Color green = new Color(62500);

    Snake snake;

    public RenderPanel(){}
    public RenderPanel(Snake snake){
        this.snake = snake;
    }
//
//    @Override
//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//        g.setColor(green);
//        g.fillRect(0,0,500, 500);
//        curColour++;
//
//
//        g.setColor(Color.RED);
//        for (Point point : snake.snakeParts){
//        //    g.setColor(Color.RED);
//            g.fillRect(point.x * snake.SCALE, point.y * snake.SCALE, snake.SCALE, snake.SCALE);
//          //
//        }
//
//
//    }

    @Override
    protected void paintComponent(Graphics g) {
        snake = Snake.snake;
        g.fillRect(snake.head.x * 10, snake.head.y * 10, 20, 20);

        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0,0,500, 500);



        for (Point point : snake.snakeParts) {
            g.setColor(new Color(curColour));
            g.fillRect(point.x * snake.SCALE, point.y * snake.SCALE, snake.SCALE, snake.SCALE);
            g.setColor(Color.black);
        }
        g.setColor(Color.black);
        g.fillRect(snake.cherry.x, snake.cherry.y, 10, 10);
        curColour++;
    }


}
