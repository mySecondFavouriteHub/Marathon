package com.example.test2;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    public static MarathonerModel[] marathoners;
    public static boolean paused;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

        stage.setTitle("Marathon!");
        stage.setScene(scene);
        stage.show();
        setRunners();
    }
    private static void setRunners(){
        MarathonerModel jamal= new MarathonerModel("Jamal", 52, 12.0f, "Jamal.png");
        MarathonerModel troy= new MarathonerModel("Troy", 69, 11.0f, "Troy.png");
        MarathonerModel parsa= new MarathonerModel("Parsa", 13, 14.0f, "Parsa.png");
        MarathonerModel massi= new MarathonerModel("Massi", 420, 11.0f, "Massi.png");
        marathoners= new MarathonerModel[]{jamal, troy, parsa, massi};
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
                return false;
            }
        }
        return true;
    }
    public static void pauseTransitions(){
        paused= true;
        for(MarathonerModel mm: getRunners()){
            mm.pause();
        }
    }

}