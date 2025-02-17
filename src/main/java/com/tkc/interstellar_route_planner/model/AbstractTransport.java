package com.tkc.interstellar_route_planner.model;

import java.util.Map;

public abstract class AbstractTransport {

    private String VEHICLE_ID;
    private final int CAPACITY;
    private final Double COST_PER_AU;
    private final Double STORAGE_COST_PER_DAY;

    public AbstractTransport(String vehicleId, int capacity, Double costPerAu, Double storageCostPerDay) {
        VEHICLE_ID = vehicleId;
        CAPACITY = capacity;
        COST_PER_AU = costPerAu;
        STORAGE_COST_PER_DAY = storageCostPerDay;
    }

    public String getVehicleId() {
        return VEHICLE_ID;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public Double getCostPerAU() {
        return COST_PER_AU;
    }

    public Double getStorageCostPerDay() {
        return STORAGE_COST_PER_DAY;
    }

    public Map.Entry<Integer, Double> getTransportCost(int noOfPassengers, Double dist, int parkingDays) {

        int noOfVehiclesNeeded = calculateNoOfVehiclesNeeded(noOfPassengers);
        Double costOfTransport = calculateCostOfTransport(noOfVehiclesNeeded, dist, parkingDays);

        return Map.entry(noOfVehiclesNeeded, costOfTransport);
    }

    private int calculateNoOfVehiclesNeeded(int noOfPassengers) {

        Double d = ((double) noOfPassengers)/((double) CAPACITY);
        Double res = Math.ceil(d);

        return res.intValue();

    }

    private Double calculateCostOfTransport(int noOFVehicles, Double dist, int parkingDays) {

        Double baseCost = noOFVehicles * dist * COST_PER_AU;
        Double storageCost = noOFVehicles * STORAGE_COST_PER_DAY * parkingDays;

        return baseCost + storageCost;

    }


}
