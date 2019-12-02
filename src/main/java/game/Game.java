package game;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Game {

    private Timer timer;
    private long loopIntervalMilliseconds;

    public Game(long loopIntervalMilliseconds) {
        timer = new Timer();
        this.loopIntervalMilliseconds = loopIntervalMilliseconds;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loop();
            }
        }, 0, loopIntervalMilliseconds);
    }

    public void stop() {
        timer.cancel();
    }

    abstract void loop();

}
