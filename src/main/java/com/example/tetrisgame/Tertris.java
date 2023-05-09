package com.example.tetrisgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tertris extends Application {
    //Variables
public static final int MOVE = 25;
public static final int SIZE = 25;
public static int XMAX = SIZE * 14;
public static int YMAX = SIZE * 28;
public static int[][] MESH = new int[XMAX/SIZE][YMAX/SIZE];
private static Pane group = new Pane();
private static Form object;
 Scene scene = new Scene(group,XMAX + 250, YMAX);
public static int score =0;
public static int top =0;
private static boolean game = true;
private static Form nextObject = Controller.makeRect();
private static int linesNo = 0;

public static void main (String[] args){launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
    for (int[] a: MESH) {
        Arrays.fill(a,0);
    }

    //create score and level
        Line line = new Line(XMAX,0,XMAX,YMAX);
        Text scoretext = new Text("Score: ");
        scoretext.setStyle("-fx-font: 20 arials;");
        scoretext.setY(50);
        scoretext.setX(XMAX + 5);
        Text level = new Text("Lines: ");
        level.setStyle("-fx-font: 20 arials;");
        level.setY(100);
        level.setX(XMAX + 5);
        level.setFill(Color.GREEN);
        group.getChildren().addAll(scoretext,line,level);
        group.setBackground(Background.fill(Color.CYAN));

        //create first block and stage
        Form a = nextObject; //contain 4 rect
        group.getChildren().addAll(a.a,a.b,a.c,a.d); //add the form
        moveOnKeyPress(a);
        object = a;//Current Form object in the scene
        nextObject = Controller.makeRect();
        Image programIcon =new Image(getClass().getClassLoader().getResource("tetris.jpeg").toString());
        stage.setScene(scene);
        stage.setTitle("T E T R I S G A M E");
        stage.getIcons().add(programIcon);
        stage.show();

        //Timer
        Timer fall = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {
                Platform.runLater(new Runnable() {

                    public void run() {
                        if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0
                                || object.d.getY() == 0){
                            top++;
                        }else top = 0;
                        if (top ==2){
                            //GameOver
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 70 arial;");
                            over.setY(250);
                            over.setX(10);
                            group.getChildren().add(over);
                            game = false;
                        }

                        //Exit
                        if (top == 10){
                            System.exit(0);//Exit of the game
                        }

                        if (game) {
                            MoveDown(object);
                            scoretext.setText("Score: "+ Integer.toString(score));
                            level.setText("Lines: "+ Integer.toString(linesNo));

                        }
                    }
                });
            }
        };
        fall.schedule(task,0,400);//speed=400, repeat the task each 400 millisecond


    }

    private void moveOnKeyPress(Form form) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            switch (keyEvent.getCode()) {
                case RIGHT:
                    Controller.moveBlocksRight(form);
                    break;
                case LEFT:
                    Controller.moveBlocksLeft(form);
                    break;
                case DOWN:
                    score++;
                    MoveDown(form);
                    break;
//                case UP:
//                    MoveTurn(form);
//                    break;
            }
        }
    });
    }


    private void MoveDown(Form form) {
        CheckForLine(form);


        if (form.a.getY() + MOVE < YMAX && form.b.getY() + MOVE < YMAX && form.c.getY() + MOVE < YMAX
                && form.d.getY() + MOVE < YMAX) {
            int movea = MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
            int moveb = MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
            int movec = MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
            int moved = MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setY(form.a.getY() + MOVE);
                form.b.setY(form.b.getY() + MOVE);
                form.c.setY(form.c.getY() + MOVE);
                form.d.setY(form.d.getY() + MOVE);
            }
        }
    }

    private void CheckForLine(Form form){
        //if any rect of the form tech the ground or has below of it a piece of other form
        //=> give 1 for all the rect of form in the MESH array then check for the full line to gain line.
        if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
                || form.d.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
            MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
            MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
            MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
            RemoveRows(group);

            Form a = nextObject;
            nextObject = Controller.makeRect();
            object = a;
            group.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

    }

    private void RemoveRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        for (int i = 0; i < MESH[0].length; i++) {
            for (int j = 0; j < MESH.length; j++) {
                if (MESH[j][i] == 1)
                    full++;
            }
            if (full == MESH.length)
                lines.add(i);
            //lines.add(i + lines.size());
            full = 0;
        }
        if (lines.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                score += 50;
                linesNo++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }

                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }


    private boolean moveA(Form form) {
        return (MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
    }

}
