package tankTitans;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayMusic {
    String file_path;
    Clip clip;
    File music_file;
    AudioInputStream audio_input;

    public PlayMusic(String filepath) {
        this.file_path = filepath;
    }

    public void setup(){

    }

    public void play(){
        try {
            music_file = new File(file_path);
            if (music_file.exists()) {
                audio_input = AudioSystem.getAudioInputStream(music_file);
                clip = AudioSystem.getClip();
                clip.open(audio_input);
                clip.start();
                clip.loop(0);
            } else {
                System.out.println("File tidak ada");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stop(){
        clip.stop();
    }

    public void changeSound(String newPath) {
        this.file_path = newPath;
    }
}
