package com.example.test2;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class MarathonerModel extends ImageView implements Comparable<MarathonerModel>{
    private String name;
    private static final String resourcePath= "\\test2\\src\\main\\resources\\com\\example\\test2\\";
    private int number;
    public int finishingPosition;
    private boolean running= true;
    private boolean finished= false;
    private TranslateTransition runAnim, resetAnim;
    @Override
    public int compareTo(MarathonerModel o) {
        return o.getAnim().getDuration().compareTo(this.runAnim.getDuration());
    }
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
        runAnim = new TranslateTransition(Duration.seconds(Math.random()*19.0f), this);
        runAnim.setToX(700.0d);

        runAnim.setOnFinished(e ->{
            this.running= false;
            this.finished= true;
            this.finishingPosition= Main.countFinished;
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
            finished= false;
        });
    }
    public MarathonerModel(String name, int number, float speed, String imgName){
        this.name= name;
        this.number= number;
        this.setImage(fetchImage(imgName));

        this.setFitWidth(100.0d);
        this.setFitHeight(100.0d);

        setAnim();
        setResetAnim();
    }
    public Image fetchImage(String name){
        System.out.println(new File("").getAbsolutePath());
        String filePathAndName= new File("").getAbsolutePath()+resourcePath+name;
        System.out.println(filePathAndName);
        return new Image(filePathAndName);
    }
    public String getStatus(){
        if(running){
            return toString()+ " is running";
        }
        else{
            return toString()+ " has finished in "+finishingPosition;
        }
    }
    public String toString(){
        return this.name+"("+this.number+")";
    }



}
