package com.example.test2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
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
    private AudioClip audioClip;{
        try {
            audioClip = new AudioClip(new File("").getAbsolutePath()+
                    MarathonerModel.resourcePath+"introSong.wav");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private void seeRunners(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.0d), event -> nextImage())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        audioClip.play();
    }

    private void nextImage(){
        //This fct cycles through the pictures of all the marathoners
        //by making them visible/invisible at the right time
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
