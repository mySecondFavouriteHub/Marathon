package com.example.test2;

import javafx.animation.Animation;
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
    private static Scene slideshowScene, raceScene;
    public static void main(String[] args) {
        launch();
    }
    public static MarathonerModel[] marathoners;
    public static boolean paused;
    public static String winner;
    public static boolean weHaveAWinner= false;
    public static String getEvents(){
        //This method returns the current status of the race:
        //whether it is over, waiting to be started or paused.
        if(paused) return "Race paused.";
        if(MarathonerModel.waiting) return "Race pending.";
        if(!weHaveAWinner) return "Race in progress.";
        return winner+" has won!";
    }
    @Override
    public void start(Stage stage) throws IOException {
        //stage is declared statically to allow for a change of scene on command
        //for example, to go to the race scene or back to the menu (slideshow scene)
        Main.stage = stage;
        Main.stage.setTitle("Marathon!");
        //The program begins in the slideshow scene:
        switchToSlideshowScene();
        Main.stage.show();
    }
    public static void switchToRaceScene() throws IOException {
        //this loads the scene with the actual race (runners, tracks, finish line)
        FXMLLoader layoutLoader = new FXMLLoader(Main.class.getResource("layout.fxml"));
        raceScene = new Scene(layoutLoader.load(), 1000, 600);
        stage.setScene(raceScene);
        //the runners are set right when the scene is loaded
        setRunners();
    }
    public static void switchToSlideshowScene() throws IOException{
        //this loads the scene where you can watch all the marathoners in the slideshow
        FXMLLoader slideshowLoader = new FXMLLoader(Main.class.getResource("slideshow.fxml"));
        slideshowScene = new Scene(slideshowLoader.load(), 1000, 600);
        stage.setScene(slideshowScene);
    }
    private static void setRunners(){
        //this fucntion initiates all 4 of the marathoners
        MarathonerModel jamal= new MarathonerModel("Jamal", 52,  "Jamal.png");
        MarathonerModel troy= new MarathonerModel("Troy", 69,  "Troy.png");
        MarathonerModel parsa= new MarathonerModel("Parsa", 13,  "Parsa.png");
        MarathonerModel massi= new MarathonerModel("Massi", 420,  "Massi.png");
        marathoners= new MarathonerModel[]{jamal, troy, parsa, massi}   ;
    }
    public static MarathonerModel[] getRunners(){
        //this function guarantees that a MarathonerModel array is returned
        //to avoid a situation where the marathoners array is not initialized
        if(marathoners== null) setRunners();
        return marathoners;
    }
    public static void resetRunners(){
        for(MarathonerModel mm: getRunners()){
            //this resets the animation of each runner
            //so it can be replayed
            mm.reset();
        }
        //obviously, when the race is reset, the winner is not decided.
        weHaveAWinner=false;

    }
    public static void runTransitions(){
        //this makes all the marathoners run
        paused= false;
        for(MarathonerModel mm: getRunners()){
            mm.run();
        }
    }
    public static boolean checkFinished(){
        //this function checks whether the race is finished
        for(MarathonerModel mm: getRunners()){
            if(mm.isRunning()){
                return false;
            }
        }
        return true;
    }
    public static void pauseTransitions(){
        //This function pauses all the marathoners' animations
        paused= true;
        for(MarathonerModel mm: getRunners()){
            mm.pause();
        }
    }

}