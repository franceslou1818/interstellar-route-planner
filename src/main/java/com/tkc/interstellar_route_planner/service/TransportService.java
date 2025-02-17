package com.tkc.interstellar_route_planner.service;

public interface TransportService {

    Object getCheapestVehicle(Double distance, Integer passengersCount, Integer daysOfParking);

}
