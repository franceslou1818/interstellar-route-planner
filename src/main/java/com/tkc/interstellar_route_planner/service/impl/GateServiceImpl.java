package com.tkc.interstellar_route_planner.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkc.interstellar_route_planner.model.Gate;
import com.tkc.interstellar_route_planner.model.GateDetails;
import com.tkc.interstellar_route_planner.repository.GateRepository;
import com.tkc.interstellar_route_planner.service.GateService;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GateServiceImpl implements GateService {

    private final double SPACE_FLIGHT_COST_PER_HU = 0.1;

    GateRepository gateRepository;

    HashMap<String, GateDetails> allGates = new HashMap<>();

    public GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public Object getGateDetails(String gateId) {
        Gate gate = gateRepository.findById(gateId).get();

        Map<String, Object> map = new HashMap<>();
        map.put("gateID", gate.getId());
        map.put("gateName", gate.getName());
        map.put("connections", convert(gate.getConnections()));

        return map;
    }

    public List<Object> getAllGateDetails() {

        List<Object> toReturn = new ArrayList<>();
        List<Gate> gates = gateRepository.findAll();
        for (Gate gate : gates) {
            Map<String, Object> map = new HashMap<>();
            map.put("gateID", gate.getId());
            map.put("gateName", gate.getName());
            map.put("connections", convert(gate.getConnections()));
            toReturn.add(map);
        }

        return toReturn;
    }

    @Override
    public Double getCheapestRoute(String gateCode, String targetGateCode) {
        initGates();
        calculateShortestPath(allGates.get(gateCode));
        printPaths(Arrays.asList(allGates.get(gateCode), allGates.get(targetGateCode)));
        int distanceFromSrcToTarget = allGates.get(targetGateCode).getDistance();
        return distanceFromSrcToTarget*SPACE_FLIGHT_COST_PER_HU;
    }

    public void saveGate(Gate gate) {
        gateRepository.save(gate);
    }

    public void deleteGate(String id) {
        gateRepository.deleteById(id);
    }

    private void initGates() {
        List<Gate> gates = gateRepository.findAll();
        for (Gate gate : gates) {
            GateDetails gateDetails = new GateDetails();
            gateDetails.setId(gate.getId());
            gateDetails.setName(gate.getName());
            allGates.put(gate.getId(), gateDetails);
        }

        // Add connecting gates of each gate
        for (Gate gate : gates) {
            List<HashMap<String, String>> connections = convert(gate.getConnections());
            GateDetails gateDetails = allGates.get(gate.getId());
            for (HashMap<String, String> connection : connections) {
                String connectingGateId = connection.get("id");
                String hyperplaneUnit = connection.get("hu");
                GateDetails connectingGate = allGates.get(connectingGateId);
                gateDetails.addConnectingGate(connectingGate, Integer.parseInt(hyperplaneUnit));
            }

        }
    }


    private List<HashMap<String, String>> convert(String jsonStrConnections) {
        JSONArray jsonArray = new JSONArray(jsonStrConnections);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> map;
        try {
            map = mapper.readValue(String.valueOf(jsonArray), List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public void calculateShortestPath(GateDetails gateSource) {
        gateSource.setDistance(0);
        Set<GateDetails> visitedGates = new HashSet<>();
        Queue<GateDetails> unvisitedGates = new PriorityQueue<>(Collections.singleton(gateSource));
        while (!unvisitedGates.isEmpty()) {
            GateDetails currentGate = unvisitedGates.poll();
            currentGate.getConnectingGates().entrySet().stream()
                    .filter(entry -> !visitedGates.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentGate);
                        unvisitedGates.add(entry.getKey());
                    });
            visitedGates.add(currentGate);

        }

    }

    private void evaluateDistanceAndPath(GateDetails connectingGate, Integer distance, GateDetails sourceGate) {
        Integer newDistance = sourceGate.getDistance() + distance;
        if (newDistance < connectingGate.getDistance()) {
            connectingGate.setDistance(newDistance);
            connectingGate.setCheapestPath(
                    Stream.concat(sourceGate.getCheapestPath().stream(), Stream.of(sourceGate)).toList());

        }
    }

    private static void printPaths(List<GateDetails> gates) {
        gates.forEach(gate -> {
            String path = gate.getCheapestPath().stream()
                    .map(GateDetails::getId)
                    .collect(Collectors.joining(" -> "));
            System.out.println(path.isBlank()
                    ? "%s : %s".formatted(gate.getId(), gate.getDistance())
                    : "%s -> %s : %s".formatted(path, gate.getId(), gate.getDistance()));
        });

    }

}
