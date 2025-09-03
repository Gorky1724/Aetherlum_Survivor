package aetherlum_survivor.view;

import aetherlum_survivor.util.ResourcePaths;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceHandler { //makes use of InputStream

    //map where all the instanciated clips are saved - in this way they can be reused and must not be reinstanciated every time
    private static ConcurrentHashMap<String, Clip> clipCache = new ConcurrentHashMap<>();

    private static Clip activeMusicClip = null;

    //! IMAGES
    public static BufferedImage loadImage(String path) { //get image as stream
        try (InputStream iS = ResourcePaths.getResourceAsStream(path)) {
            return ImageIO.read(iS);
        } catch (IOException e) {
            throw new RuntimeException("!!!> Error in loading image: " + path, e);
        }
    }
    
    //! AUDIO
    public static Clip loadAudioClip(String path) { //supports .wav -- get as stream
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

    private static void loadAndCacheClip(String path) {
        if (!clipCache.containsKey(path)) {
            try {
                Clip clip = loadAudioClip(path);
                clipCache.put(path, clip); //adds to preloaded audioclips
            } catch (Exception e) {
                System.err.println("!!!> Error in loading audio: " + path);
                e.printStackTrace();
            }
        }
    }

    public static void preloadAll() {
        //music
        loadAndCacheClip(ResourcePaths.Audio.START_PANEL_MUSIC);
        loadAndCacheClip(ResourcePaths.Audio.LEVELUP_PANEL_MUSIC);
        loadAndCacheClip(ResourcePaths.Audio.SCENARIO_PANEL_MUSIC);
        loadAndCacheClip(ResourcePaths.Audio.PAUSE_PANEL_MUSIC);
        loadAndCacheClip(ResourcePaths.Audio.GAMEOVER_PANEL_MUSIC);
        loadAndCacheClip(ResourcePaths.Audio.SETTINGS_PANEL_MUSIC);
        loadAndCacheClip(ResourcePaths.Audio.GAME_PANEL_MUSIC);
        //sfx
        //loadAndCacheClip(ResourcePaths.Audio.PROJECTILE_FIRED_SFX);
        //loadAndCacheClip(ResourcePaths.Audio.PROJECTILE_HIT_SFX);
        //loadAndCacheClip(ResourcePaths.Audio.COLLECTIBLE_TAKEN_SFX);
        //loadAndCacheClip(ResourcePaths.Audio.PANEL_CHANGE_SFX);
        //loadAndCacheClip(ResourcePaths.Audio.LEVEL_UP_SFX);

        System.out.println(">> Audio preload Completed");
    }

    //stops all audio
    public static void stopAudio() {
        for (Clip clip : clipCache.values()) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        }
    }

    //automatically loops music
    public static void playMusic(String path) {

        boolean audioEnabled = View.getInstance().getAudioStatus();
        if(!audioEnabled) { //if disabled nothing will play
            return;
        }

        if(activeMusicClip != null && activeMusicClip.isRunning()) {
            activeMusicClip.stop();
        }
        
        Clip clip = clipCache.get(path);

        if (clip != null) {
            if (clip.isRunning()) { //stops clip if it was already running
                clip.stop();
            }

            clip.setFramePosition(0); //to replay it from the start
            
            clip.loop(Clip.LOOP_CONTINUOUSLY); //loop

            activeMusicClip = clip;

        } else {
            System.err.println("!!!> Music clip not found in cache: " + path);
        }
    }

    //plays once effect
    public static void playSfx(String path) {

        boolean audioEnabled = View.getInstance().getAudioStatus();
        if(!audioEnabled) { //if disabled nothing will play
            return;
        }

        Clip clip = clipCache.get(path);

        if (clip != null) {
            if (clip.isRunning()) { //stops clip if it was already running
                clip.stop();
            }

            clip.setFramePosition(0); //to replay it from the start
            
            clip.start();
        } else {
            System.err.println("!!!> Sound effect clip not found in cache: " + path);
        }

    }
    
}
