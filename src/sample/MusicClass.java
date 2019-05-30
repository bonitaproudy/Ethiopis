package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.MalformedURLException;

public class MusicClass {
    private static MusicClass musicClass_instance = null;

    private MusicClass() throws MalformedURLException {
        String marioFile = "/resources/01-it-s-a-me-mario-.mp3";
        Media marioSong = new Media(new File(marioFile).toURI().toURL().toString());
        MediaPlayer player = new MediaPlayer(marioSong);
        player.play();
    }

    public static MusicClass getInstance() throws MalformedURLException {
        if (musicClass_instance == null)
            musicClass_instance = new MusicClass();

        return musicClass_instance;
    }
}
