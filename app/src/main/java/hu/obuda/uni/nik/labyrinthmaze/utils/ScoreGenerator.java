package hu.obuda.uni.nik.labyrinthmaze.utils;

import android.os.SystemClock;

/**
 * Created by Budai Krisztián on 2017. 05. 17..
 */

public class ScoreGenerator {
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;
    private double score;
    private static final int BASESCORE = 60000;
    private static final int TRAPPENALTY = 1000;

    public ScoreGenerator() {
        elapsedTime = 0;
        score = 0;
        isRunning = false;
    }

    public long getElapsedTime() {
        if (isRunning) {
            return (SystemClock.elapsedRealtime() - startTime) / 1000;
        } else {
            return elapsedTime;
        }
    }

    public boolean isTimerRunning() {
        return this.isRunning;
    }

    public double getScore() {
        return this.score;
    }

    public void start() {
        startTime = SystemClock.elapsedRealtime();
        isRunning = true;
    }

    public void nextMap() {
        elapsedTime = SystemClock.elapsedRealtime() - startTime;
        calculateScore();
        elapsedTime = 0;
        startTime = System.currentTimeMillis();
    }

    public void end() {
        elapsedTime = System.currentTimeMillis() - startTime;
        isRunning = false;
    }

    public void calculateScore() {
        score += BASESCORE / getElapsedTime();
    }

    public void calculatePenalty() {
        score -= TRAPPENALTY / getElapsedTime();
    }
}