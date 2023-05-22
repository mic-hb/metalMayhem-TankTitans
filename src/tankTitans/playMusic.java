package tankTitans;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class playMusic {
    String file_path;
    Clip clip;

    public playMusic(String filepath) {
        this.file_path = filepath;
    }

    public void play(){
        try {
            File music_file = new File(file_path);
            if (music_file.exists()) {
                AudioInputStream audio_input = AudioSystem.getAudioInputStream(music_file);
                clip = AudioSystem.getClip();
                clip.open(audio_input);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                Thread.sleep(100);
            } else {
                System.out.println("File tidak ada");
            }
        } catch (Exception e) {

        }
    }
}
