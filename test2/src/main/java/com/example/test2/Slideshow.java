package com.example.test2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;

public class Slideshow {

    @FXML
    private ImageView jamalPicture, troyPicture, parsaPicture, massiPicture;
    @FXML
    private Button seeRaceBtn, seeRunnersBtn;
    @FXML
    private void seeRace() throws IOException {
        Main.switchToRaceScene();
    }
    @FXML private void seeRunners(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.0d), event -> nextImage())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void nextImage(){

        //System.out.println(""+jamalPicture.isVisible()+troyPicture.isVisible()+parsaPicture.isVisible()+massiPicture.isVisible());

        if(jamalPicture.isVisible())jamalPicture.setVisible(false);
        else if(troyPicture.isVisible())troyPicture.setVisible(false);
        else if(parsaPicture.isVisible())parsaPicture.setVisible(false);
        else {
            jamalPicture.setVisible(true);
            troyPicture.setVisible(true);
            parsaPicture.setVisible(true);
        }

    }

}
