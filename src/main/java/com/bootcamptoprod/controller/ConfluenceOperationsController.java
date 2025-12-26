package com.bootcamptoprod.controller;

import com.bootcamptoprod.dto.ConfluenceRequest;
import com.bootcamptoprod.dto.ConfluenceResponse;
import com.embabel.agent.api.invocation.AgentInvocation;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.ProcessOptions;
import com.embabel.agent.core.Verbosity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/confluence")
public class ConfluenceOperationsController {

    private static final Logger log = LoggerFactory.getLogger(ConfluenceOperationsController.class);

    private final AgentPlatform agentPlatform;
    private final ProcessOptions processOptions;

    public ConfluenceOperationsController(AgentPlatform agentPlatform) {
        this.agentPlatform = agentPlatform;

        // Configure verbosity for detailed logging of agent operations
        Verbosity verbosity = Verbosity.DEFAULT
                .showLlmResponses()
                .showPrompts()
                .showPlanning();

        this.processOptions = ProcessOptions.DEFAULT.withVerbosity(verbosity);
    }

    @PostMapping
    public ConfluenceResponse executeConfluenceOperation(@RequestBody ConfluenceRequest request) {
        log.info("Natural language Confluence operation request received: {}", request.command());

        // Build agent invocation with configured process options
        var agentInvocation = AgentInvocation
                .builder(agentPlatform)
                .options(processOptions)
                .build(ConfluenceResponse.class);

        // Invoke the agent to process the request
        ConfluenceResponse response = agentInvocation.invoke(request);

        log.info("Confluence operation result - success: {}, operation: {}, message: {}",
                response.success(), response.operationType(), response.message());

        return response;
    }
}