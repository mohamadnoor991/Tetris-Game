package com.example.tetrisgame;

import javafx.scene.shape.Rectangle;

public class Controller {

    public static final int MOVE = Tertris.MOVE;
    public static final int SIZE = Tertris.SIZE;
    public static int XMAX = Tertris.XMAX;
    public static int YMAX = Tertris.YMAX;
    public static int[][] MESH = Tertris.MESH;

    public static void moveBlocksRight(Form form) {
//manipulate the process??
        if (form.a.getX() + MOVE <= XMAX - SIZE && form.b.getX() + MOVE <= XMAX - SIZE
                && form.c.getX() + MOVE <= XMAX - SIZE && form.d.getX() + MOVE <= XMAX - SIZE) {
            int movea = MESH[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
            int moveb = MESH[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
            int movec = MESH[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
            int moved = MESH[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() + SIZE);
                form.b.setX(form.b.getX() + SIZE);
                form.c.setX(form.c.getX() + SIZE);
                form.d.setX(form.d.getX() + SIZE);
            }
        }

    }

    public static void moveBlocksLeft(Form form) {
//manipulate the process??
        if (form.a.getX() - MOVE >= 0 && form.b.getX() - MOVE >= 0
                && form.c.getX() - MOVE >= 0 && form.d.getX() - MOVE >= 0) {
            int movea = MESH[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
            int moveb = MESH[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
            int movec = MESH[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
            int moved = MESH[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() - SIZE);
                form.b.setX(form.b.getX() - SIZE);
                form.c.setX(form.c.getX() - SIZE);
                form.d.setX(form.d.getX() - SIZE);
            }
        }

    }

    public static Form makeRect() {
        int block = (int) (Math.random() * 100);


        String name;
        Rectangle a = new Rectangle(SIZE - 1, SIZE - 1),
                b = new Rectangle(SIZE - 1, SIZE - 1),
                c = new Rectangle(SIZE - 1, SIZE - 1),
                d = new Rectangle(SIZE - 1, SIZE - 1);

        if (block < 15) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "j";
        } else if (block < 30) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "l";
        } else if (block < 45) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 - SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2);
            d.setY(SIZE);
            name = "o";
        } else if (block < 60) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 - SIZE);
            d.setY(SIZE);
            name = "s";
        } else if (block < 75) {

//            a.setX(XMAX / 2 - SIZE);
//            b.setX(XMAX / 2);
//            c.setX(XMAX / 2);
//            c.setY(SIZE);
//            d.setX(XMAX / 2 + SIZE);
//            maine
            a.setX(XMAX / 2);
            a.setY(SIZE);
            b.setX(XMAX / 2);
            b.setY(2 * SIZE);
            c.setX(XMAX / 2);
            c.setY(3 * SIZE);
            d.setX(XMAX / 2);
            d.setY(4 * SIZE);
            name = "t";
        } else if (block < 90) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 + SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name = "z";
        } else {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2 - SIZE -SIZE);
            c.setX(XMAX / 2);//????
            d.setX(XMAX / 2 + SIZE);
            name = "i";
        }





        return new Form(a,b,c,d,name);
    }


}