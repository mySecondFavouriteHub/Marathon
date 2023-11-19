package com.example.test2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Ctrl {
    @FXML private Button start, pause, exit, update;
    @FXML private HBox track1, track2, track3, track4;
    @FXML public TextArea textArea;
    private static boolean runnersAdded= false;
    private static int countFinished=0;
    //    SETTING THE ON-BUTTON-PRESSES
    @FXML private void onStartPressed(){
        if(!runnersAdded) {
            addRunners(Main.getRunners());
            runnersAdded= true;
        }
        if(Main.checkFinished()){
            System.out.println("Resetting marathoners!");
            Main.resetRunners();
        }
        else{
            Main.runTransitions();
        }
    }
    public void updateText(){
        textArea.setText(Main.getEvents());
    }
    public void updateTextBox(){
        textArea.setText(Main.getEvents());
    }
    @FXML private void onPausePressed(){
        Main.pauseTransitions();
    }
    @FXML private void onExitPressed(){
        runnersAdded=false;
        try {
            Main.switchToSlideshowScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void addRunners(MarathonerModel[] runners){
        track1.getChildren().add(runners[0]);
        track2.getChildren().add(runners[1]);
        track3.getChildren().add(runners[2]);
        track4.getChildren().add(runners[3]);

    }
}