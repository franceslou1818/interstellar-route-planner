package com.tkc.interstellar_route_planner.repository;

import com.tkc.interstellar_route_planner.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<Gate, String>  {
}
