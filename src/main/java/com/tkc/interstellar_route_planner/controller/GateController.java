package com.tkc.interstellar_route_planner.controller;

import com.tkc.interstellar_route_planner.model.Gate;
import com.tkc.interstellar_route_planner.repository.GateRepository;
import com.tkc.interstellar_route_planner.service.GateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return "Tested";
    }

    // GET: /gates/{gateCode}/to/{targetGateCode} - returns the cheapest route from gateCode to targetGateCode
    @RequestMapping(value = "/{gateCode}/to/{targetGateCode}", method= RequestMethod.GET)
    public Double getCheapestRoute(@PathVariable("gateCode") String gateCode, @PathVariable("targetGateCode") String targetGateCode) {
        return gateService.getCheapestRoute(gateCode, targetGateCode);
    }


    @RequestMapping(method = RequestMethod.POST)
    public String createGate(@RequestBody Object gate) {
        Map map = (Map) gate;
        Gate newGate = new Gate();
        newGate.setName((String) map.get("name"));
        newGate.setId((String) map.get("id"));
        newGate.setConnections(map.get("connections").toString());
        gateService.saveGate(newGate);
        return "Gate successfully created";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateGate(@PathVariable("id") String id, @RequestBody Object body) {
        Map map = (Map) body;
        Gate newGate = new Gate();
        newGate.setId(id);
        newGate.setName((String) map.get("name"));
        newGate.setConnections(map.get("connections").toString());
        gateService.saveGate(newGate);
        return "Gate successfully updated";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String updateGate(@PathVariable("id") String id) {
        gateService.deleteGate(id);
        return "Gate successfully deleted";
    }


}
