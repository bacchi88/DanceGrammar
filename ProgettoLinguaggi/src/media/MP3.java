package media;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;

import javazoom.jl.player.Player;

public class MP3 {

    private String filename;
    private Player player;
    private boolean is_playing;

    public MP3(String filename) {
        this.filename = filename;
        is_playing = false;
    }

    public void close() {
        if (player != null) {
            player.close();
            is_playing = false;
        }
    }

    public void play() {
        if (!is_playing) {
            try {
                FileInputStream fis = new FileInputStream(filename);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } catch (FileNotFoundException | JavaLayerException e) {
                System.out.println("Problem playing file " + filename);
                System.out.println(e);
                e.printStackTrace();
            }

            is_playing = true;
            new Thread() {

                @Override
                public void run() {
                    try {

                        player.play();
                    } catch (JavaLayerException e) {
                        System.out.println(e);
                    }
                }
            }.start();

        }
    }
}
