package com.bootcamptoprod.config;

import com.embabel.agent.core.ToolGroup;
import com.embabel.agent.core.ToolGroupDescription;
import com.embabel.agent.core.ToolGroupPermission;
import com.embabel.agent.tools.mcp.McpToolGroup;
import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Configuration
public class ConfluenceToolsConfig {

    private final List<McpSyncClient> mcpSyncClients;

    @Autowired
    public ConfluenceToolsConfig(@Lazy List<McpSyncClient> mcpSyncClients) {
        Assert.notNull(mcpSyncClients, "McpSyncClients must not be null");
        this.mcpSyncClients = mcpSyncClients;
    }

    @Bean
    public ToolGroup confluenceMcpToolGroup() {
        return new McpToolGroup(
                ToolGroupDescription.Companion.invoke(
                        "Confluence operations including list spaces, list documents, create documents, get history, and get metadata via MCP",
                        "confluence-mcp-tool-group"  // ← MUST match toolGroups = {"confluence-mcp-tool-group"} in agent
                ),
                "Confluence Provider",
                "confluence-tool-group",
                Set.of(ToolGroupPermission.INTERNET_ACCESS),
                mcpSyncClients,
                toolCallback -> {
                    String toolName = toolCallback.getToolDefinition().name().toLowerCase();
                    return toolName.contains("space") ||
                            toolName.contains("document") ||
                            toolName.equals("page");
                }
        );
    }
}