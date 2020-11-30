package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.text.StyleConstants.setIcon;

public class Main extends JFrame implements KeyListener{

    private Thread thread;
    private static Random random = new Random();
    private static final int DIR_STEP = 20;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private int x, y, i = 0;
    private boolean[][] full = new boolean[12][21];
    private int[][] pict = new int[10][20]; // число в матрице - цвет квадрата. 7 - фон, остальное как в фигурах
    private int s = (int) ( Math.random() * 7 );
    private int rot = 0; //поворот по часовой. 0 - 0, 1 - 90, 2 - 180, 3 - 270
    private boolean set = false;
    public int points = 0;



    public Main(int width, int height) {

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {

                pict[i][j] = 7;
            }
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                full[i][j] = false;
            }
        }

        for (int j = 0; j < 21; ++j) {
            full[10][j] = true;
        }
        for (int i = 0; i < 10; ++i) {
            full[i][20] = true;
        }

        this.setSize(width, height);
        x = (width-16)/2+13;
        y = 36;
        this.addKeyListener(this);



        thread = new MoveThread(this);
        thread.start();
    }









    public static void main(String... string) {
        JFrame frame = new Main(216,439);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




    }



    //Listener

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_A) {isLeft = true;}
        if (e.getKeyCode()==KeyEvent.VK_D) {isRight = true;}
        if (e.getKeyCode()==KeyEvent.VK_W) {isUp = true;}
        if (e.getKeyCode()==KeyEvent.VK_S) {isDown = true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_A) {isLeft = false; i = 0;}
        if (e.getKeyCode()==KeyEvent.VK_D) {isRight = false; i = 0;}
        if (e.getKeyCode()==KeyEvent.VK_W) {isUp = false; i = 0;}
        if (e.getKeyCode()==KeyEvent.VK_S) {isDown = false; i = 0;}
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*if (e.getKeyCode()==KeyEvent.VK_A) {isLeft=true; }
        if (e.getKeyCode()==KeyEvent.VK_D) {isRight=true; }
        if (e.getKeyCode()==KeyEvent.VK_S) {isDown=true; }*/

    }

    //Graphics



    int x1,x2,x3,x4,y1,y2,y3,y4; // координаты тетрамино

    public void paint(Graphics gr) {


        // ФОН

        Image image = new ImageIcon("xyz.png") .getImage();
        gr.drawImage(image,0,0,null);


        Graphics2D g2d = (Graphics2D)gr;
        int r;
        int g;
        int b;

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                    switch (pict[i][j]) {
                        case 0: {
                            g2d.setColor(new Color(231, 121, 116));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(184, 40, 33));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 1: {
                            g2d.setColor(new Color(88, 179, 82));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(58, 112, 54));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 2: {
                            g2d.setColor(new Color(109, 145, 216));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(43, 81, 162));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 3: {
                            g2d.setColor(new Color(71, 141, 141));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(41, 84, 84));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 4: {
                            g2d.setColor(new Color(234, 117, 0));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(128, 64, 0));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 5: {
                            g2d.setColor(new Color(206, 206, 0));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(111, 111, 0));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 6: {
                            g2d.setColor(new Color(190, 97, 231));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13, j * 20 + 36, 10, 10);
                            g2d.setColor(new Color(125, 26, 168));
                            g2d.setStroke(new BasicStroke(10f));
                            g2d.drawRect(i * 20 + 13 + 3, j * 20 + 36 + 3, 4, 4);
                            break;
                        }
                        case 7: { /*g2d.setColor(new Color(150,200,150));*/
                            break;
                        }

                }
            }
        }



        switch(s) {
            case 0: // палка


                r = 231;
                g = 121;
                b = 116;

                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));
                switch (rot) {
                    case 0:
                    case 2 : {
                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x + 20;
                            y3 = y;
                            x4 = x + 40;
                            y4 = y;


                            break;
                    }
                    case 1:
                    case 3 : {
                            x1 = x;
                            y1 = y - 20;
                            x2 = x;
                            y2 = y;
                            x3 = x;
                            y3 = y + 20;
                            x4 = x;
                            y4 = y + 40;
                            break;
                    }

                }
                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(184, 40, 33));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);



                break;
            case 1: // квадрат


                r = 88;
                g = 179;
                b = 82;
                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));

                x1 = x - 20; y1 = y;
                x2 = x; y2 = y;
                x3 = x - 20; y3 = y + 20;
                x4 = x; y4 = y + 20;




                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(58, 112, 54));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);



                break;
            case 2: // L

                r = 109;
                g = 145;
                b = 216;
                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));

                switch (rot) {
                    case 0 : {
                        x1 = x - 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x + 20;
                        y3 = y;
                        x4 = x - 20;
                        y4 = y + 20;
                        break;
                    }
                    case 1 : {
                        x1 = x;
                        y1 = y - 20;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y + 20;
                        x4 = x - 20;
                        y4 = y - 20;
                        break;
                    }
                    case 2 : {
                        x1 = x + 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x - 20;
                        y3 = y;
                        x4 = x + 20;
                        y4 = y - 20;
                        break;
                    }
                    case 3 : {
                        x1 = x;
                        y1 = y + 20;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y - 20;
                        x4 = x + 20;
                        y4 = y + 20;
                        break;
                    }
                }

                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(43, 81, 162));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);


                break;
            case 3: // обратная L


                r = 71;
                g = 141;
                b = 141;
                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));

                switch (rot) {
                    case 0 : {
                        x1 = x - 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x + 20;
                        y3 = y;
                        x4 = x + 20;
                        y4 = y + 20;
                        break;
                    }
                    case 1 : {
                        x1 = x;
                        y1 = y - 20;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y + 20;
                        x4 = x - 20;
                        y4 = y + 20;
                        break;
                    }
                    case 2 : {
                        x1 = x + 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x - 20;
                        y3 = y;
                        x4 = x - 20;
                        y4 = y - 20;
                        break;
                    }
                    case 3 : {
                        x1 = x;
                        y1 = y + 20;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y - 20;
                        x4 = x + 20;
                        y4 = y - 20;
                        break;
                    }
                }

                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(41, 84, 84));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);


                break;
            case 4: // Т

                r = 234;
                g = 117;
                b = 0;
                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));



                switch (rot) {
                    case 0 : {
                        x1 = x - 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x + 20;
                        y3 = y;
                        x4 = x;
                        y4 = y + 20;
                        break;
                    }
                    case 1 : {
                        x1 = x;
                        y1 = y - 20;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y + 20;
                        x4 = x - 20;
                        y4 = y;
                        break;
                    }
                    case 2 : {
                        x1 = x + 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x - 20;
                        y3 = y;
                        x4 = x;
                        y4 = y - 20;
                        break;
                    }
                    case 3 : {
                        x1 = x;
                        y1 = y + 20;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y - 20;
                        x4 = x + 20;
                        y4 = y;
                        break;
                    }
                }

                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(128, 64, 0));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);

                break;
            case 5: // Z

                r = 206;
                g = 206;
                b = 0;
                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));

                switch (rot) {//--------------------------------------------------------------------------------------------
                    case 0 :
                    case 2 : {
                        x1 = x - 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y + 20;
                        x4 = x + 20;
                        y4 = y + 20;
                        break;
                    }
                    case 1 :
                    case 3 : {
                        x1 = x;
                        y1 = y - 20;
                        x2 = x;
                        y2 = y;
                        x3 = x - 20;
                        y3 = y;
                        x4 = x - 20;
                        y4 = y + 20;
                        break;
                    }
                }

                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(111, 111, 0));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);

                break;
            case 6: // обратная Z

                r = 190;
                g = 97;
                b = 231;
                g2d.setColor(new Color(r,g,b));
                g2d.setStroke(new BasicStroke(10f));

                switch (rot) {
                    case 0 :
                    case 2 : {
                        x1 = x + 20;
                        y1 = y;
                        x2 = x;
                        y2 = y;
                        x3 = x;
                        y3 = y + 20;
                        x4 = x - 20;
                        y4 = y + 20;
                        break;
                    }
                    case 1 :
                    case 3 : {
                        x1 = x;
                        y1 = y - 20;
                        x2 = x;
                        y2 = y;
                        x3 = x + 20;
                        y3 = y;
                        x4 = x + 20;
                        y4 = y + 20;
                        break;
                    }
                }

                g2d.drawRect(x1, y1, 10, 10);
                g2d.drawRect(x2, y2, 10, 10);
                g2d.drawRect(x3, y3, 10, 10);
                g2d.drawRect(x4, y4, 10, 10);
                g2d.setColor(new Color(125, 26, 168));
                g2d.setStroke(new BasicStroke(10f));
                g2d.drawRect(x1+3, y1+3, 4, 4);
                g2d.drawRect(x2+3, y2+3, 4, 4);
                g2d.drawRect(x3+3, y3+3, 4, 4);
                g2d.drawRect(x4+3, y4+3, 4, 4);

                break;
        }


    }
    public boolean move(int dir, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) { //dir - направление. право(->) - 0, низ - 1, лево (<-) - 2
        boolean canMove = true;
        if (dir == 0) {
            if (x1 + 20 >= 13 && x2 + 20 >= 13 && x3 + 20 >= 13 && x4 + 20 >= 13 && y1 - 36 >= 0 && y2 - 36 >= 0 && y3 - 36 >= 0 && y4 - 36 >= 0) {
                if (full[(x1+20-13)/20][(y1-36)/20] || x1+20>213) canMove = false;
                if (full[(x2+20-13)/20][(y2-36)/20] || x2+20>213) canMove = false;
                if (full[(x3+20-13)/20][(y3-36)/20] || x3+20>213) canMove = false;
                if (full[(x4+20-13)/20][(y4-36)/20] || x4+20>213) canMove = false;
            } else {
                canMove = false;
            }
        } else {
            if (dir == 1) {
                if (x1 >= 13 && x2 >= 13 && x3 >= 13 && x4 >= 13 && y1 + 20 - 36 >= 0 && y2 + 20 - 36 >= 0 && y3 + 20 - 36 >= 0 && y4 + 20 - 36 >= 0) {
                    if (full[(x1-13)/20][(y1+20-36)/20]) canMove = false;
                    if (full[(x2-13)/20][(y2+20-36)/20]) canMove = false;
                    if (full[(x3-13)/20][(y3+20-36)/20]) canMove = false;
                    if (full[(x4-13)/20][(y4+20-36)/20]) canMove = false;
                } else {
                    canMove = false;
                }
            } else {
                if (dir == 2) {
                    if (x1 - 20 >= 13 && x2 - 20 >= 13 && x3 - 20 >= 13 && x4 - 20 >= 13 && y1 - 36 >= 0 && y2 - 36 >= 0 && y3 - 36 >= 0 && y4 - 36 >= 0) {
                        if (full[(x1 - 20 - 13) / 20][(y1 - 36) / 20]) canMove = false;
                        if (full[(x2 - 20 - 13) / 20][(y2 - 36) / 20]) canMove = false;
                        if (full[(x3 - 20 - 13) / 20][(y3 - 36) / 20]) canMove = false;
                        if (full[(x4 - 20 - 13) / 20][(y4 - 36) / 20]) canMove = false;
                    } else {
                        canMove = false;
                    }
                }
            }
        }
        return canMove;
    }

    public int fall(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int step = 19;
        int i0 = (x1 - 13) / 20;
        int j0 = (y1 - 36) / 20;
        if (i0 >= 0 && j0 >= 0) {
            for (int j = j0; j < 21; ++j) {
                if (full[i0][j]) {
                    step = Math.min(step, j - j0 - 1);
                    break;
                }
            }
        }
        i0 = (x2 - 13) / 20;
        j0 = (y2 - 36) / 20;
        if (i0 >= 0 && j0 >= 0) {
            for (int j = j0; j < 21; ++j) {
                if (full[i0][j]) {
                    step = Math.min(step, j - j0 - 1);
                    break;
                }
            }
        }
        i0 = (x3 - 13) / 20;
        j0 = (y3 - 36) / 20;
        if (i0 >= 0 && j0 >= 0) {
            for (int j = j0; j < 21; ++j) {
                if (full[i0][j]) {
                    step = Math.min(step, j - j0 - 1);
                    break;
                }
            }
        }
        i0 = (x4 - 13) / 20;
        j0 = (y4 - 36) / 20;
        if (i0 >= 0 && j0 >= 0) {
            for (int j = j0; j < 21; ++j) {
                if (full[i0][j]) {
                    step = Math.min(step, j - j0 - 1);
                    break;
                }
            }
        }
        return step * 20;
    }

    public boolean isRotatable (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        if (x1 - 13 < 0 || x2 - 13 < 0 || x3 - 13 < 0 || x4 - 13 < 0 || y1 - 36 < 0 || y2 - 36 < 0 || y3 - 36 < 0 || y4 - 36 < 0) return false;
        boolean rotatable = true;
        if (full[(x1 - 13)/20][(y1 - 36) / 20]) rotatable = false;
        if (full[(x2 - 13)/20][(y2 - 36) / 20]) rotatable = false;
        if (full[(x3 - 13)/20][(y3 - 36) / 20]) rotatable = false;
        if (full[(x4 - 13)/20][(y4 - 36) / 20]) rotatable = false;
        return rotatable;
    }
    //------------------------------------------------------------------------------------------------------------------
    public void rotation() { //Поворот

        if (isUp && i < 1 ) {
            //проверка на возможность поворота!!!!!!!!!!!!!!!!!!!

            switch(s) {
                case 0: // палка
                    switch (rot) {
                        case 0:
                        case 2 : {
                            if (isRotatable(x,y-20,x,y,x,y+20,x,y+40)) {
                                x1 = x;
                                y1 = y - 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y + 20;
                                x4 = x;
                                y4 = y + 40;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();

                            }
                            break;
                        }
                        case 1:
                        case 3 : {
                            if (isRotatable(x-20,y,x,y,x+20,y,x+40,y)) {
                                x1 = x - 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x + 20;
                                y3 = y;
                                x4 = x + 40;
                                y4 = y;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();

                            }
                            break;
                        }

                    }
                    /*if (move(2,x - 20, y, x, y, x-40, y, x+20, y)) {
                        x-=DIR_STEP;
                        ++i;
                        this.repaint();
                    }*/
                    break;
                case 1: // квадрат
                    break;
                case 2: // L
                    switch (rot) {
                        case 3 : {
                            if (isRotatable(x-20,y,x,y,x+20,y,x-20,y+20)) {
                                x1 = x - 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x + 20;
                                y3 = y;
                                x4 = x - 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 0 : {
                            if (isRotatable(x,y-20,x,y,x,y+20,x-20,y-20)) {
                                x1 = x;
                                y1 = y - 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y + 20;
                                x4 = x - 20;
                                y4 = y - 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 1 : {
                            if (isRotatable(x+20,y,x,y,x-20,y,x+20,y-20)) {
                                x1 = x + 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x - 20;
                                y3 = y;
                                x4 = x + 20;
                                y4 = y - 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 2 : {
                            if (isRotatable(x,y+20,x,y,x,y-20,x+20,y+20)) {
                                x1 = x;
                                y1 = y + 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y - 20;
                                x4 = x + 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                    }
                    break;
                case 3: // обратная L
                    switch (rot) {
                        case 3 : {
                            if (isRotatable(x-20,y,x,y,x+20,y,x+20,y+20)) {
                                x1 = x - 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x + 20;
                                y3 = y;
                                x4 = x + 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 0 : {
                            if (isRotatable(x,y-20,x,y,x,y+20,x-20,y+20)) {
                                x1 = x;
                                y1 = y - 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y + 20;
                                x4 = x - 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 1 : {
                            if (isRotatable(x+20,y,x,y,x-20,y,x-20,y-20)) {
                                x1 = x + 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x - 20;
                                y3 = y;
                                x4 = x - 20;
                                y4 = y - 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 2 : {
                            if (isRotatable(x,y+20,x,y,x,y-20,x+20,y-20)) {
                                x1 = x;
                                y1 = y + 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y - 20;
                                x4 = x + 20;
                                y4 = y - 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                    }
                    break;
                case 4: // Т
                    switch (rot) {
                        case 3 : {
                            if (isRotatable(x-20,y,x,y,x+20,y,x,y+20)) {
                                x1 = x - 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x + 20;
                                y3 = y;
                                x4 = x;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                this.repaint();
                                ++i;
                            }
                            break;
                        }
                        case 0 : {
                            if (isRotatable(x,y-20,x,y,x,y+20,x-20,y)) {
                                x1 = x;
                                y1 = y - 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y + 20;
                                x4 = x - 20;
                                y4 = y;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 1 : {
                            if (isRotatable(x+20,y,x,y,x-20,y,x,y-20)) {
                                x1 = x + 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x - 20;
                                y3 = y;
                                x4 = x;
                                y4 = y - 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 2 : {
                            if (isRotatable(x,y+20,x,y,x,y-20,x+20,y)) {
                                x1 = x;
                                y1 = y + 20;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y - 20;
                                x4 = x + 20;
                                y4 = y;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                    }
                    break;
                case 5: // Z
                    switch (rot) {//--------------------------------------------------------------------------------------------
                        case 1 :
                        case 3 : {
                            if (isRotatable(x-20,y,x,y,x,y+20,x+20,y+20)) {
                                x1 = x - 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y + 20;
                                x4 = x + 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 0 :
                        case 2 : {
                            if (isRotatable(x,y-20,x,y,x-20,y,x-20,y+20)) {
                                x1 = x;
                                y1 = y - 20;
                                x2 = x;
                                y2 = y;
                                x3 = x - 20;
                                y3 = y;
                                x4 = x - 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                    }
                    break;
                case 6: // обратная Z
                    switch (rot) {
                        case 1 :
                        case 3 : {
                            if (isRotatable(x+20,y,x,y,x,y+20,x-20,y+20)) {
                                x1 = x + 20;
                                y1 = y;
                                x2 = x;
                                y2 = y;
                                x3 = x;
                                y3 = y + 20;
                                x4 = x - 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                        case 0 :
                        case 2 : {
                            if (isRotatable(x,y-20,x,y,x+20,y,x+20,y+20)) {
                                x1 = x;
                                y1 = y - 20;
                                x2 = x;
                                y2 = y;
                                x3 = x + 20;
                                y3 = y;
                                x4 = x + 20;
                                y4 = y + 20;
                                rot = (rot + 1) % 4;
                                ++i;
                                this.repaint();
                            }
                            break;
                        }
                    }
                    break;
            }
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    public void animate() {

        if (isLeft && i < 1 ) {
            if (move(2,x1, y1, x2, y2, x3, y3, x4, y4)) {
                x-=DIR_STEP;
                x1-=DIR_STEP;
                x2-=DIR_STEP;
                x3-=DIR_STEP;
                x4-=DIR_STEP;
                ++i;
                this.repaint();
            }
        }
        if (isRight && i < 1) {
            if (move(0,x1, y1, x2, y2, x3, y3, x4, y4)) {
                x+=DIR_STEP;
                x1+=DIR_STEP;
                x2+=DIR_STEP;
                x3+=DIR_STEP;
                x4+=DIR_STEP;
                ++i;
                this.repaint();
            }
        }
        if (isDown && i < 1) {

            int f = fall(x1, y1, x2, y2, x3, y3, x4, y4);

            y += f;
            y1 += f;
            y2 += f;
            y3 += f;
            y4 += f;

            if (x1-13 >= 0 && x2-13 >= 0 && x3-13 >= 0 && x4-13 >= 0 && y1-36 >= 0 && y2-36 >= 0 && y3-36 >= 0 && y4-36 >= 0) {
                pict[(x1 - 13) / 20][(y1 - 36) / 20] = s;
                pict[(x2 - 13) / 20][(y2 - 36) / 20] = s;
                pict[(x3 - 13) / 20][(y3 - 36) / 20] = s;
                pict[(x4 - 13) / 20][(y4 - 36) / 20] = s;

                full[(x1 - 13) / 20][(y1 - 36) / 20] = true;
                full[(x2 - 13) / 20][(y2 - 36) / 20] = true;
                full[(x3 - 13) / 20][(y3 - 36) / 20] = true;
                full[(x4 - 13) / 20][(y4 - 36) / 20] = true;

            }
            set = true;

            this.repaint();
            ++i;

        }
    }
    public void animate2() { // Постоянное движение вниз

        if (move(1,x1, y1, x2, y2, x3, y3, x4, y4)) {
            y += DIR_STEP;
            y1 += DIR_STEP;
            y2 += DIR_STEP;
            y3 += DIR_STEP;
            y4 += DIR_STEP;
            this.repaint();
        } else {
            if (x1-13 >= 0 && x2-13 >= 0 && x3-13 >= 0 && x4-13 >= 0 && y1-36 >= 0 && y2-36 >= 0 && y3-36 >= 0 && y4-36 >= 0) {
                pict[(x1 - 13) / 20][(y1 - 36) / 20] = s;
                pict[(x2 - 13) / 20][(y2 - 36) / 20] = s;
                pict[(x3 - 13) / 20][(y3 - 36) / 20] = s;
                pict[(x4 - 13) / 20][(y4 - 36) / 20] = s;

                full[(x1 - 13) / 20][(y1 - 36) / 20] = true;
                full[(x2 - 13) / 20][(y2 - 36) / 20] = true;
                full[(x3 - 13) / 20][(y3 - 36) / 20] = true;
                full[(x4 - 13) / 20][(y4 - 36) / 20] = true;
            }

            set = true;

        }

    }

    public void animate3(int k) { // Удаление линии
        for (int i = k; i > 0; --i) {
            for (int j = 0; j < 10; ++j) {
                pict[j][i] = pict[j][i-1];
                full[j][i] = full[j][i-1];
            }
        }
        for (int j = 0; j < 10; ++j) {
            pict[j][0] = 7;
            full[j][0] = false;
        }
        this.repaint();
    }

    public void coords () {
        switch(s) {
            case 0: // палка
                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x + 20;
                            y3 = y;
                            x4 = x + 40;
                            y4 = y;
                            //this.repaint();

                break;
            case 1: // квадрат
                x1 = x - 20; y1 = y;
                x2 = x; y2 = y;
                x3 = x - 20; y3 = y + 20;
                x4 = x; y4 = y + 20;
                break;
            case 2: // L
                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x + 20;
                            y3 = y;
                            x4 = x - 20;
                            y4 = y + 20;

                            //this.repaint();

                break;
            case 3: // обратная L

                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x + 20;
                            y3 = y;
                            x4 = x + 20;
                            y4 = y + 20;

                            //this.repaint();

                break;
            case 4: // Т
                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x + 20;
                            y3 = y;
                            x4 = x;
                            y4 = y + 20;

                            //this.repaint();

                break;
            case 5: // Z
                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x;
                            y3 = y + 20;
                            x4 = x + 20;
                            y4 = y + 20;
                            //this.repaint();

                break;
            case 6: // обратная Z
                            x1 = x - 20;
                            y1 = y;
                            x2 = x;
                            y2 = y;
                            x3 = x;
                            y3 = y - 20;
                            x4 = x + 20;
                            y4 = y - 20;
                            //this.repaint();

                break;
        }
    }

    //Engine thread

    private class MoveThread extends Thread {

        Main MAIN;
        //JFrame END;
        public MoveThread(Main MAIN) {
            super("MoveThread");
            this.MAIN = MAIN;
        }

        public void run() {

            boolean fin = true;
            while (fin) {
                int a = (int) ( Math.random() * 7 );
                s = (a != s ? a : (a+1)%7);
                coords ();
                while (!set) {
                    MAIN.animate();
                    try {
                        for (int i = 0; i < (10000 - points)/25; ++i) {
                            MAIN.rotation();
                            MAIN.animate();
                            Thread.sleep(1);
                        }
                        MAIN.animate2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                rot = 0;
                int delLines = 0;
                for (int i = 0; i < 20; ++i) {
                    boolean line = true;
                    for (int j = 0; j < 10; ++j) {
                        if (!full[j][i]) line = false;
                    }
                    if (line) {
                        animate3(i);
                        ++delLines;
                    }
                }
                switch (delLines) {
                    case 1: points += 100; break;
                    case 2: points += 300; break;
                    case 3: points += 700; break;
                    case 4: points += 1500; break;
                }
                points += 10;
                delLines = 0;
                x = 113;
                y = 36;
                set = false;

                for (int j = 0; j < 10; ++j) { // конец игры
                    if (full[j][0]) {
                        fin = false;
                    }
                }
            }
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame frame = new JFrame("End of the game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("Your points:"+points);
            frame.getContentPane().add(label);

            frame.setPreferredSize(new Dimension(200, 100));

            frame.pack();
            frame.setVisible(true);
        }
    }
}
