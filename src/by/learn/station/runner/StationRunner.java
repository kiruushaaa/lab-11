package by.learn.station.runner;

import by.learn.station.car.RandomCar;
import by.learn.station.station.Station;

import java.util.List;

public class StationRunner {
    public static void main(String[] args) {
        Station station = new Station(3);
        List<Thread> carList = new RandomCar().asList(10, station);
        carList.forEach(Thread::start);
        carList.forEach(c -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(station.getVisitedCars());
        System.out.println(station.getVisitedCars().size());
    }
}
