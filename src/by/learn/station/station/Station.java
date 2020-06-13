package by.learn.station.station;

import by.learn.station.car.CarThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Station {
    private int carLimit;
    private List<Thread> parkedCars = new ArrayList<>();
    private List<Thread> visitedCars = new ArrayList<>();

    public Station(int carLimit){
        this.carLimit = carLimit;
    }

    public List<Thread> getVisitedCars() { return visitedCars; }

    public boolean accept(CarThread car) throws InterruptedException {
        while (carLimit == 0) {
            TimeUnit.MILLISECONDS.sleep(car.getWaitTime());
            if(carLimit == 0) {
                return false;
            }
            else break;
        }
        parkedCars.add(car);
        carLimit--;
        return true;
    }

    public void release(CarThread car) {
        if (parkedCars.contains(car)) {
            visitedCars.add(car);
            carLimit++;
            parkedCars.remove(car);
        }
    }
}