package com.example.test2;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MarathonerModel extends ImageView{
    private String name;
    public static final String resourcePath= "\\test2\\src\\main\\resources\\com\\example\\test2\\";
    private int number;
    public int finishingPosition;
    private boolean running= true;
    private TranslateTransition runAnim, resetAnim;
    private AudioClip endOfRaceCheer;
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
        runAnim = new TranslateTransition(Duration.seconds(5.0d-Math.random()*4.0f), this);
        runAnim.setToX(700.0d);

        runAnim.setOnFinished(e ->{
            this.running= false;
            if(!Main.weHaveAWinner){
                Main.weHaveAWinner= true;
                Main.winner= this.toString();
            }

            try {
                endOfRaceCheer.restart();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
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
        try {
            this.endOfRaceCheer= fetchAudioPlayer();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setFitWidth(100.0d);
        this.setFitHeight(100.0d);

        setAnim();
        setResetAnim();
    }
    public Image fetchImage(String name){
        String filePathAndName= new File("").getAbsolutePath()+resourcePath+name;
        return new Image(filePathAndName);
    }
    public AudioClip fetchAudioPlayer() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        String filePathAndName= new File("").getAbsolutePath()+resourcePath+"cheeringAudience.wav";
        return new AudioClip(filePathAndName);
    }
    public String toString(){
        return this.name+"("+this.number+")";
    }



}
