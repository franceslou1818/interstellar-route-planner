package com.tkc.interstellar_route_planner.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkc.interstellar_route_planner.model.Gate;
import com.tkc.interstellar_route_planner.model.GateDetails;
import com.tkc.interstellar_route_planner.repository.GateRepository;
import com.tkc.interstellar_route_planner.service.GateService;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    GateRepository gateRepository;

    public GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public GateDetails getGateDetails(String gateId) {
        GateDetails gateDetails = new GateDetails();
        Gate gate = gateRepository.findById(gateId).get();
        gateDetails.setId(gate.getId());
        gateDetails.setName(gate.getName());
        gateDetails.setConnections(convertJSONStrConnectionsToList(gate.getConnections()));

        return gateDetails;
    }

    public List<GateDetails> getAllGateDetails() {
        List<GateDetails> gateDetails = new ArrayList<>();

        List<Gate> gates = gateRepository.findAll();
        for (Gate gate : gates) {
            GateDetails currentGateDetails = new GateDetails(gate.getId(), gate.getName(), convertJSONStrConnectionsToList(gate.getConnections()));
            gateDetails.add(currentGateDetails);
        }

        return gateDetails;
    }

    public int getCheapestRoute(String gateCode, String targetGateCode) {
        return 15;
    }

    private List<GateDetails.Connection> convertJSONStrConnectionsToList(String jsonStrConnections) {
        JSONArray jsonArray = new JSONArray(jsonStrConnections);
        ObjectMapper mapper = new ObjectMapper();
        List<GateDetails.Connection> connections;
        try {
            connections = mapper.readValue(String.valueOf(jsonArray), List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return connections;
    }

}
