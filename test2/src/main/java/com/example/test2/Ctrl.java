package com.example.test2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ctrl {
    @FXML private Button start, pause, exit, update;
    @FXML private HBox track1, track2, track3, track4;
    @FXML public TextArea textArea;
    private static boolean runnersAdded= false;
    private boolean textUpdates= false;
    @FXML private void onStartPressed(){
        //checks if the text-updating has been started, starts if not.
        if(!textUpdates) startUpdatingText();
        //if runners aren't added, adds them to the tracks.
        if(!runnersAdded) {
            addRunners(Main.getRunners());
            runnersAdded= true;
        }
        //if the race is finished, resets the marathoners
        if(Main.checkFinished()){
            System.out.println("Resetting marathoners!");
            Main.resetRunners();
        }
        //else, begins the race
        else{
            Main.runTransitions();
        }
    }
    public void startUpdatingText(){
        //this updates the label in the bottom right, every 100ms
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            textArea.setText(Main.getEvents());
        }, 0, 100, TimeUnit.MILLISECONDS);
        textUpdates=true;
    }
    @FXML private void onPausePressed(){
        //clicking the pause button stops the race
        Main.pauseTransitions();
    }
    @FXML private void onExitPressed(){
        //if Exit button is pressed, switches back to the slideshow scene
        runnersAdded=false;
        try {
            Main.switchToSlideshowScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void addRunners(MarathonerModel[] runners){
        //this fct adds the runners (MarathonerModel) to the tracks (HBox)
        track1.getChildren().add(runners[0]);
        track2.getChildren().add(runners[1]);
        track3.getChildren().add(runners[2]);
        track4.getChildren().add(runners[3]);

    }
}