package com.tkc.interstellar_route_planner.service;

import java.util.List;

public interface GateService {

    Object getGateDetails(String gateId);
    List<Object> getAllGateDetails();
    Double getCheapestRoute(String gateCode, String targetGateCode);

    }
