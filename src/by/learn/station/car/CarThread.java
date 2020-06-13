package by.learn.station.car;

import by.learn.station.station.Station;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarThread extends Thread {

    private int waitTime;
    private int startTime;
    private int parkedTime;
    private Station station;
    private static int threadAmount = 0;
    private int carAmount = ++threadAmount;
    private ReentrantLock locker;
    private Condition condition;

    public int getWaitTime() { return waitTime; }

    public CarThread (int waitTime, int startTime, int parkedTime, Station station) {
        this.waitTime= waitTime;
        this.startTime = startTime;
        this.parkedTime = parkedTime;
        this.station = station;
        this.locker = new ReentrantLock();
        this.condition = this.locker.newCondition();
    }

    public ReentrantLock getLocker() {
        return locker;
    }

    public void setLocker(ReentrantLock locker) {
        this.locker = locker;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public void run() {
        this.locker.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(this.startTime);
            if (this.station.accept(this)) {
                this.condition.signalAll();
                TimeUnit.MILLISECONDS.sleep(waitTime);
                this.station.release(this);
            } else {
                System.out.println(this + " can't wait more");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.locker.unlock();
        }
    }

    @Override
    public String toString() {
        return "Car " + this.carAmount + "("
                      + "waitTime " +  this.waitTime
                      + ", startTime " +  this.startTime
                      + ", parkedTime " +  this.parkedTime + ")";
    }
}
