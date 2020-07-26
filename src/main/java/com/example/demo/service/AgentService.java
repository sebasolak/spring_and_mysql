package com.example.demo.service;

import com.example.demo.dao.AgentDao;
import com.example.demo.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {

    private final AgentDao agentDao;

    @Autowired
    public AgentService(@Qualifier("mySql") AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public List<Agent> selectAllAgents(Optional<Integer> seniority) {
        return seniority.map(integer -> agentDao.findAll().stream()
                .filter(agent -> integer == agent.getSeniority())
                .collect(Collectors.toList())).orElseGet(agentDao::findAll);
    }

    public Agent selectAgent(Long id) {
        return agentDao.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException(
                                String.format("Agent %d did not exists", id)));
    }

    public void insertNewAgent(Agent newAgent) {
        agentDao.save(newAgent);
    }

    public void updateAgent(Long id, Agent update) {
        update.setId(id);
        update.setSaleValue(update.getSaleValue());
        agentDao.save(update);
    }

    public void deleteAgent(Long agentId) {
        agentDao.deleteById(agentId);
    }
}
