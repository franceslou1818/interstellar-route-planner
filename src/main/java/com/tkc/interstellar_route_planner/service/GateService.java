package com.tkc.interstellar_route_planner.service;

import com.tkc.interstellar_route_planner.model.Gate;

import java.util.List;

public interface GateService {

    Object getGateDetails(String gateId);
    List<Object> getAllGateDetails();
    Object getCheapestRoute(String gateCode, String targetGateCode);
    void saveGate(Gate gate);
    void deleteGate(String id);
}
