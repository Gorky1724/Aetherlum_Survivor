package aetherlum_survivor.view;

import aetherlum_survivor.util.ResourcePaths;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceHandler { //makes use of InputStream

    //map where all the instanciated clips are saved - in this way they can be reused and must not be reinstanciated every time
    private static final ConcurrentHashMap<String, Clip> clipCache = new ConcurrentHashMap<>();

    //! IMAGES
    public static BufferedImage loadImage(String path) {
        try (InputStream iS = ResourcePaths.getResourceAsStream(path)) {
            return ImageIO.read(iS);
        } catch (IOException e) {
            throw new RuntimeException("!!!> Error in loading image: " + path, e);
        }
    }
    
    //! AUDIO
    public static Clip loadAudioClip(String path) { //supports .wav
        try { 
            InputStream iS = ResourcePaths.getResourceAsStream(path); //gets path
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(iS); //converts to audioInputStream

            Clip clip = AudioSystem.getClip(); //creates void audioclip
            clip.open(audioStream); //adds audio
            return clip;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("!!!> Error in loading audio: " + path, e);
        }
    }

    //reproduces once the audio .wav associated with the path
    public static void playAudio(String path) {
        try {
            Clip clip = clipCache.computeIfAbsent(path, p -> { //if not present adds to map (key = path, value = loadClip())
                Clip c = loadAudioClip(p);

                // return clip index to the start -- allows it to be replayes
                c.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) { //when the clip stops
                        c.setFramePosition(0); //resets
                    }
                });

                return c;
            });

            // if clip is running stop it and
            if (clip.isRunning()) {
                clip.stop();
            }

            //starts clip
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void releaseAll() {
        
        for (Clip clip : clipCache.values()) {
            if (clip != null) {
                clip.close(); // close clip resources
            }
        }

        // empty cache
        clipCache.clear();
    }
    
}
