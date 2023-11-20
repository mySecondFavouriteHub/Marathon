package com.example.test2;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class AudioClip {
    public String status, filePath;
    Long currentFrame;
    public Clip clip;
    AudioInputStream audioInputStream;

    public AudioClip(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        filePath= path;
        File audioFile= new File(filePath).getAbsoluteFile();
        audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }


    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();
        status = "play";
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            //System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(0);
    }
}
