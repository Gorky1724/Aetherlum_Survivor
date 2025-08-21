package aetherlum_survivor.to_delete;

public class OLDGameLoop extends Thread {
    private volatile boolean running = false; //VOLATILE to be visible between threads if needed
    private volatile boolean paused = false; //to pause game
    //private final int TARGET_FPS = 1;
    //private final long FRAME_TIME = 1000 / TARGET_FPS; // frame duration (seconds)
        
    public OLDGameLoop() {
        this.setDaemon(true); // thread auto-closes when the app is closed
    }
    
    @Override
    public void run() {
        running = true;
        long lastTime = System.currentTimeMillis();
        
        while (running) {
            if (!paused) { //game is running
                long currentTime = System.currentTimeMillis();

                /*DELTA TIME:
                 * time passed from last frame. Makes animation indipendent from frame_per_seconds
                 * In this way animations are more fluid on every computer for indipendenxe from hardware.
                 */
                long deltaTime = currentTime - lastTime;
                
                // Aggiorna la logica di gioco
                update(deltaTime);
                
                lastTime = currentTime;
            }
            /*
            try {
                Thread.sleep(FRAME_TIME); //to maintain FPS constant
            } catch (InterruptedException e) { //if something interrupts thread
                Thread.currentThread().interrupt();
                break;
            }*/
        }
    }
    
    private void update(long deltaTime) {
        /* 
        // update game logic
        ControllerForModel.getInstance().modelUpdate(deltaTime);

        // update game view
        ControllerForView.getInstance().requestViewUpdate();
        
        // Controlla condizioni di fine gioco
        if (ControllerForModel.getInstance().isGameOver()) {
            stopGame();
        }
        */
    }
    
    public void startGame() {
        if (!running) {
            start(); // start thread (gameloop)
        }
    }
    
    public void stopGame() {
        running = false;
    }
    
    public void pauseGame() {
        paused = true;
    }
    
    public void resumeGame() {
        paused = false;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public boolean isPaused() {
        return paused;
    }
}