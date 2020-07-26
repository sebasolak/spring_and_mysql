package com.example.demo.dao;

import com.example.demo.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mySql")
public interface AgentDao extends JpaRepository<Agent, Long> {

}
