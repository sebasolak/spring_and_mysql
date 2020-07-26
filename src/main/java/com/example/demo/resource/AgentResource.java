package com.example.demo.resource;

import com.example.demo.model.Agent;
import com.example.demo.model.Estate;
import com.example.demo.model.PropertyType;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/agents")
public class AgentResource {

    private final AgentService agentService;

    @Autowired
    public AgentResource(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('BOSS','MANAGER')")
    public List<Agent> getAllAgents(@QueryParam("seniority") Integer seniority) {
        return agentService.selectAllAgents(Optional.ofNullable(seniority));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}"
    )
    @PreAuthorize("hasAnyRole('BOSS','MANAGER')")
    public Agent getAgent(@PathVariable("id") Long id) {
        return agentService.selectAgent(id);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('BOSS')")
    public void addNewAgent(@RequestBody Agent newAgent) {
        agentService.insertNewAgent(newAgent);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    @PreAuthorize("hasRole('BOSS')")
    public void updateAgent(@PathVariable("studentId") Long id,
                            @RequestBody Agent update) {
        agentService.updateAgent(id, update);
    }

    @DeleteMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    @PreAuthorize("hasRole('BOSS')")
    public void removeAgent(@PathVariable("studentId") Long id) {
        agentService.deleteAgent(id);
    }

    //////////////////////////////////////////////////////////
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "test"
    )
    public void addTestAgent() {
        Estate estate =
                new Estate(PropertyType.APARTMENT,
                        9532.00,
                        107.62);

        Estate estate2 =
                new Estate(PropertyType.HOUSE,
                        5342.00,
                        55.73);

        Agent agent =
                new Agent("Tom", "Clark", 2, 2000, estate, estate2);

        agentService.insertNewAgent(agent);
    }
}
