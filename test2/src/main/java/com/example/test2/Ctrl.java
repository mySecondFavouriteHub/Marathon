package com.example.test2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Ctrl {
    @FXML private Button start;
    @FXML private Button pause;
    @FXML private Button exit;

    @FXML private HBox track1;
    @FXML private HBox track2;
    @FXML private HBox track3;
    @FXML private HBox track4;
    private static boolean runnersAdded= false;
    //    SETTING THE ON-BUTTON-PRESSES
    @FXML private void onStartPressed(){

        if(!runnersAdded) {
            addRunners(Main.getRunners());
            runnersAdded= true;
        }
        if(Main.checkFinished()){
            Main.resetRunners();
        }
        else{
            Main.runTransitions();
        }
    }
    @FXML private void onPausePressed(){
        Main.pauseTransitions();
    }
    @FXML private void onExitPressed(){
        System.exit(0);
    }
    private void addRunners(MarathonerModel[] runners){
        track1.getChildren().add(runners[0]);
        track2.getChildren().add(runners[1]);
        track3.getChildren().add(runners[2]);
        track4.getChildren().add(runners[3]);

    }
}