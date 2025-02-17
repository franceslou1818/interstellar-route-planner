package com.tkc.interstellar_route_planner.controller;

import com.tkc.interstellar_route_planner.service.TransportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transport")
public class TransportController {

    TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    //GET: /transport/{distance}?passengers={number}&parking={days}
    // returns the cheapest vehicle to use (and the cost of the journey)
    // for the given distance (in AUs),
    // number or passengers and
    // days of parking (i.e. vehicle storage at the gate)
    @RequestMapping(value = "/{distance}", method= RequestMethod.GET)
    public Object getCheapestVehicleTest(@PathVariable("distance") String distance,
                                         @RequestParam("passengers") int passengersCount,
                                         @RequestParam("parking") int daysOfParking) {
        return transportService.getCheapestVehicle(Double.valueOf(distance), passengersCount, daysOfParking);
    }
}
