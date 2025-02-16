package com.tkc.interstellar_route_planner.service;

import com.tkc.interstellar_route_planner.model.GateDetails;

import java.util.List;

public interface GateService {

    GateDetails getGateDetails(String gateId);
    List<GateDetails> getAllGateDetails();
    int getCheapestRoute(String gateCode, String targetGateCode);

    }
