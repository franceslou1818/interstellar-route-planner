package com.tkc.interstellar_route_planner.controller;

import com.tkc.interstellar_route_planner.model.Gate;
import com.tkc.interstellar_route_planner.repository.GateRepository;
import com.tkc.interstellar_route_planner.service.GateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gates")
public class GateController {

    private final GateRepository gateRepository;
    GateService gateService;

    public GateController(GateService gateService, GateRepository gateRepository) {
        this.gateService = gateService;
        this.gateRepository = gateRepository;
    }

    // GET: /gates - returns a list of gates with their information
    @GetMapping()
    public List<Object> getAllGateDetails() {
        return gateService.getAllGateDetails();
    }

    // GET: /gates/{gateCode} - returns the details of a single gate
    @GetMapping("{gateId}")
    public Object getGateDetails(@PathVariable("gateId") String gateId) {
        return gateService.getGateDetails(gateId);
    }

    @GetMapping("testing")
    public Object getTesting() {
        return "testing2";
    }

    // GET: /gates/{gateCode}/to/{targetGateCode} - returns the cheapest route from gateCode to targetGateCode
    @RequestMapping(value = "/{gateCode}/to/{targetGateCode}", method= RequestMethod.GET)
    public Double getCheapestRoute(@PathVariable("gateCode") String gateCode, @PathVariable("targetGateCode") String targetGateCode) {
        return gateService.getCheapestRoute(gateCode, targetGateCode);
    }

    @PostMapping
    public Gate saveGate(@RequestBody Gate gate) {
        return gateRepository.save(gate);

    }


}
