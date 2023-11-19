package com.example.test2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static Stage stage;
    public static void main(String[] args) {
        launch();
    }
    public static MarathonerModel[] marathoners;
    public static boolean paused;
    public static String winner;
    public static boolean weHaveAWinner= false;
    public static String getEvents(){
        if(!weHaveAWinner) return "Race in progress.";
        else{
            return winner+" has won!";
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader slideshowLoader = new FXMLLoader(Main.class.getResource("slideshow.fxml"));
        Scene slideshowScene = new Scene(slideshowLoader.load(), 1000, 600);
        stage.setTitle("Marathon!");
        stage.setScene(slideshowScene);
        stage.show();
    }
    public static void switchToRaceScene() throws IOException {
        FXMLLoader layoutLoader = new FXMLLoader(Main.class.getResource("layout.fxml"));
        Scene raceScene = new Scene(layoutLoader.load(), 1000, 600);
        stage.setScene(raceScene);
        setRunners();
    }
    private static void setRunners(){
        MarathonerModel jamal= new MarathonerModel("Jamal", 52,  "Jamal.png");
        MarathonerModel troy= new MarathonerModel("Troy", 69,  "Troy.png");
        MarathonerModel parsa= new MarathonerModel("Parsa", 13,  "Parsa.png");
        MarathonerModel massi= new MarathonerModel("Massi", 420,  "Massi.png");
        marathoners= new MarathonerModel[]{jamal, troy, parsa, massi}   ;
    }
    public static MarathonerModel[] getRunners(){
        if(marathoners== null) setRunners();
        return marathoners;
    }
    public static void resetRunners(){
        for(MarathonerModel mm: getRunners()){

            //this resets the animation of each runner
            //so it can be replayed
            mm.reset();
        }
        weHaveAWinner=false;

    }
    public static void runTransitions(){
        paused= false;
        for(MarathonerModel mm: getRunners()){
            mm.run();
        }
        //finished= true;
    }
    public static boolean checkFinished(){
        for(MarathonerModel mm: getRunners()){
            if(mm.isRunning()){
                System.out.println(false);
                return false;
            }
        }
        System.out.print("true");
        return true;
    }
    public static void pauseTransitions(){
        paused= true;
        for(MarathonerModel mm: getRunners()){
            mm.pause();
        }
    }

}