package com.tkc.interstellar_route_planner.service.impl;

import com.tkc.interstellar_route_planner.model.AbstractTransport;
import com.tkc.interstellar_route_planner.model.HSTCTransport;
import com.tkc.interstellar_route_planner.model.PersonalTransport;
import com.tkc.interstellar_route_planner.service.TransportService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransportServiceImpl implements TransportService {

    public Object getCheapestVehicle(Double distance, Integer passengersCount, Integer daysOfParking) {

        AbstractTransport personalTransport = new PersonalTransport();
        AbstractTransport hstcTransport = new HSTCTransport();

        Map.Entry<Integer, Double> personalTransportTransportInfo = personalTransport.getTransportCost(passengersCount,distance,daysOfParking);
        int noOfPersonalTransportNeeded = personalTransportTransportInfo.getKey();
        Double personalTransportCost = personalTransportTransportInfo.getValue();

        Map.Entry<Integer, Double> hstcTransportInfo = hstcTransport.getTransportCost(passengersCount,distance,daysOfParking);
        int noOfHSTCTransportNeeded = hstcTransportInfo.getKey();
        Double hstcTransportCost = hstcTransportInfo.getValue();

        Map<String, Object> toReturn = new HashMap<>();

        String cheapestVehicle;

        if (personalTransportCost <= hstcTransportCost) {
            cheapestVehicle = personalTransport.getVehicleId();
        } else {
            cheapestVehicle = hstcTransport.getVehicleId();
        }

        toReturn.put("cheapest_vehicle", cheapestVehicle);
        toReturn.put("no_personal_transport_needed", noOfPersonalTransportNeeded);
        toReturn.put("personal_transport_cost", personalTransportCost);
        toReturn.put("no_hstc_transport_needed", noOfHSTCTransportNeeded);
        toReturn.put("hstc_transport_cost", hstcTransportCost);


        return toReturn;
    }

}
