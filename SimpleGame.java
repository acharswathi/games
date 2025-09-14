package GamesPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleGame extends JPanel implements ActionListener {
    private int ballX = 100, ballY = 0, ballSpeed = 2;
    private int paddleX = 100, paddleWidth = 80, paddleHeight = 10;
    private int score = 0;
    private Timer timer;

    public SimpleGame() {
        setPreferredSize(new Dimension(300, 400));
        setBackground(Color.BLACK);
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && paddleX > 0) {
                    paddleX -= 20;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddleX < getWidth() - paddleWidth) {
                    paddleX += 20;
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 20, 20);
        g.fillRect(paddleX, getHeight() - 30, paddleWidth, paddleHeight);
        g.drawString("Score: " + score, 10, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballY += ballSpeed;
        if (ballY >= getHeight() - 50 && ballX >= paddleX && ballX <= paddleX + paddleWidth) {
            ballY = 0;
            ballX = (int) (Math.random() * (getWidth() - 20));
            score++;
        } else if (ballY > getHeight()) {
            ballY = 0;
            ballX = (int) (Math.random() * (getWidth() - 20));
            score = 0; // Reset score if missed
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game");
        SimpleGame game = new SimpleGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
