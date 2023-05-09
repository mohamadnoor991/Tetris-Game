package com.example.tetrisgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {

    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    String name;
    public int form = 1;

    public Form(Rectangle a,Rectangle b,Rectangle c,Rectangle d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

    }

    public Form(Rectangle a,Rectangle b,Rectangle c,Rectangle d,String name){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
        chosseRectangleColor(name);
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);

    }

    public String getName() {
        return name;
    }

//    public void changeForm(){
//        if (form !=4){
//            form++;
//        } else {
//            form = 1;
//        }
//    }
    public void chosseRectangleColor(String name){
        switch (name){
            case "j":
                color = Color.ALICEBLUE;
                break;
            case "l":
                color = Color.ROSYBROWN;
                break;
            case "o":
                color = Color.DODGERBLUE;
                break;
            case "s":
                color = Color.GREEN;
                break;
            case "t":
                color = Color.SLATEGREY;
                break;
            case "z":
                color = Color.HOTPINK;
                break;
            case "i":
                color = Color.SANDYBROWN;
                break;

        }
    }
}
