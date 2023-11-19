package com.example.test2;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class MarathonerModel extends ImageView{
    private String name;
    private static final String resourcePath= "\\test2\\src\\main\\resources\\com\\example\\test2\\";
    private int number;
    public int finishingPosition;
    private boolean running= true;
    private TranslateTransition runAnim, resetAnim;
    public void run(){
        runAnim.play();
        this.running= true;
    }
    public void reset(){
        resetAnim.play();
        setAnim();

    }
    public void pause(){
        runAnim.pause();
    }
    public boolean isRunning(){
        return this.running;
    }
    public void setAnim(){
        runAnim = new TranslateTransition(Duration.seconds(10.0d-Math.random()*4.0f), this);
        runAnim.setToX(700.0d);

        runAnim.setOnFinished(e ->{
            this.running= false;
            if(!Main.weHaveAWinner){
                Main.weHaveAWinner= true;
                Main.winner= this.toString();
            }

        });
    }
    public TranslateTransition getAnim(){
        if(this.runAnim== null) setAnim();
        return this.runAnim;
    }
    public void setResetAnim(){
        resetAnim= new TranslateTransition(Duration.seconds(0.1d), this);
        resetAnim.setToX(20.0d);
        resetAnim.setOnFinished(e->{
            running=true;
        });
    }
    public MarathonerModel(String name, int number, String imgName){
        this.name= name;
        this.number= number;
        this.setImage(fetchImage(imgName));

        this.setFitWidth(100.0d);
        this.setFitHeight(100.0d);

        setAnim();
        setResetAnim();
    }
    public Image fetchImage(String name){
        String filePathAndName= new File("").getAbsolutePath()+resourcePath+name;
        return new Image(filePathAndName);
    }
    public String toString(){
        return this.name+"("+this.number+")";
    }



}
