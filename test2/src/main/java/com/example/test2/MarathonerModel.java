package com.example.test2;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class MarathonerModel extends ImageView {
    private String name;
    private static final String resourcePath= "\\src\\main\\resources\\com\\example\\test2\\";
    private int number;
    private float speed;
    private boolean running= true;
    private TranslateTransition runAnim, resetAnim;
    public void run(){
        runAnim.play();
        this.running= true;
    }
    public void reset(){
        resetAnim.play();
    }
    public void pause(){
        runAnim.pause();
    }
    public boolean isRunning(){
        return this.running;
    }
    public void setAnim(){
        runAnim = new TranslateTransition(Duration.seconds(19.0d/this.speed), this);
        runAnim.setToX(700.0d);

        runAnim.setOnFinished(e ->{
            this.running= false;
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
    public MarathonerModel(String name, int number, float speed, String imgName){
        this.name= name;
        this.number= number;
        this.speed= speed;
        this.setImage(fetchImage(imgName));

        this.setFitWidth(100.0d);
        this.setFitHeight(100.0d);

        setAnim();
        setResetAnim();
    }
    public Image fetchImage(String name){
        String filePathAndName= new File("").getAbsolutePath()+resourcePath+name;
        System.out.println(filePathAndName);
        return new Image(filePathAndName);
    }
}
