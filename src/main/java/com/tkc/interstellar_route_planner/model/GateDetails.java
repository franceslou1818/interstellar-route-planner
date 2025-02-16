package com.tkc.interstellar_route_planner.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GateDetails implements Comparable<GateDetails> {

    private String id;
    private String name;
    private HashMap<GateDetails, Integer> connectingGates = new HashMap<>();

    private Integer distance = Integer.MAX_VALUE; // mimic infinity in dijkstra algorithm
    private List<GateDetails> cheapestPath = new LinkedList<>();

    public GateDetails() {}

    public GateDetails(String id, String name, HashMap<GateDetails, Integer> connectingGates) {
        this.id = id;
        this.name = name;
        this.connectingGates = connectingGates;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public HashMap<GateDetails, Integer> getConnectingGates() {
        return connectingGates;
    }

    public void setConnectingGates(HashMap<GateDetails, Integer> connectingGates) {
        this.connectingGates = connectingGates;
    }

    public void addConnectingGate(GateDetails gateDetails, int hyperplaneUnit) {
        connectingGates.put(gateDetails, hyperplaneUnit);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<GateDetails> getCheapestPath() {
        return cheapestPath;
    }

    public void setCheapestPath(List<GateDetails> cheapestPath) {
        this.cheapestPath = cheapestPath;
    }

    @Override
    public int compareTo(GateDetails gateDetails) {
        return Integer.compare(this.distance, gateDetails.distance);
    }

}
