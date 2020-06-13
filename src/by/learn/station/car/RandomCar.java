package by.learn.station.car;

import by.learn.station.station.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCar {
    public List<Thread> asList(int carAmount, Station station) {
        List<Thread> carList = new ArrayList<>();
        for (int carNumber = 0; carNumber < carAmount; carNumber++) {
            int waitTime = new Random().nextInt(15);
            int startTime = new Random().nextInt(7);
            int parkedTime = new Random().nextInt(10);
            carList.add(new CarThread(waitTime, startTime, parkedTime, station));
        }
        return carList;
    }
}
