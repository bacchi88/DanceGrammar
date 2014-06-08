package media;

import java.awt.Canvas;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class Video {

    private String filename;
    private EmbeddedMediaPlayer mediaPlayer;

    private final String WIN64_PATH = "C:/Program Files/VideoLAN/VLC";

    public Video(String filename, Canvas c) {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), WIN64_PATH);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

		//Canvas c = new Canvas();
        //JPanel p = new JPanel();
        //p.setLayout(new BorderLayout());
        //p.add(c, BorderLayout.CENTER);
        //frame.add(p, BorderLayout.CENTER);
        //Canvas c = ((DanceFrame)frame).getCanvas();
        this.filename = filename;
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
		//frame.setLocation(100, 100);
        //frame.setSize(1050, 600);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
        //frame.revalidate();
        //frame.repaint();
        // mediaPlayer.playMedia("C:\\Temp\\Prova.avi");
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
    
        public void close() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void play() {
        if (!mediaPlayer.isPlaying()) {
            new Thread() {
                public void run() {
                    try {
                        //playmedia-> non bloccante; start media -> bloccante
                    	mediaPlayer.startMedia(filename);
                        //mediaPlayer.playMedia(filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
