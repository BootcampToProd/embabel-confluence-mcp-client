package com.bootcamptoprod.agents;

import com.bootcamptoprod.dto.ConfluenceRequest;
import com.bootcamptoprod.dto.ConfluenceResponse;
import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Agent(
        name = "smart-confluence-agent",
        description = "Intelligent agent that understands natural language commands and performs Confluence operations via MCP",
        version = "1.0.0"
)
public class SmartConfluenceAgent {

    private static final Logger log = LoggerFactory.getLogger(SmartConfluenceAgent.class);

    @Action(
            description = "Process natural language Confluence operation requests and execute appropriate Confluence operations",
            toolGroups = {"confluence-mcp-tool-group"}
    )
    @AchievesGoal(description = "Understand user's natural language command and execute the appropriate Confluence operation")
    public ConfluenceResponse processConfluenceOperation(ConfluenceRequest request, OperationContext context) {

        log.info("[ACTION] processConfluenceOperation START - user command: {}", request.command());

        // Construct a detailed prompt for the LLM to understand and execute the command
        String prompt = String.format("""
                You are a Confluence operations assistant. The user has given you a command in natural language.
                Analyze the command and perform the appropriate Confluence operation using the available tools.
                
                User Command: "%s"
                
                Available Operations:
                1. LIST SPACES - List all Confluence spaces
                2. LIST DOCUMENTS - List all documents/pages in a specific space
                3. CREATE DOCUMENT - Create a new page in a space
                4. GET PAGE HISTORY - Fetch version history of a document
                5. GET DOCUMENT METADATA - Retrieve metadata for a specific document
                
                Your Task:
                1. Analyze the user's command to determine which operation they want to perform
                2. Extract any required parameters from the command:
                   - spaceKey: The key/identifier of the Confluence space (e.g., "ENG", "DOCS")
                   - documentId: The unique identifier of a Confluence page
                   - title: The title for a new document
                   - content: The content/body for a new document
                3. Use the appropriate MCP tool if required
                4. Draft a user-friendly response that includes:
                   - A clear success/failure message
                   - Relevant Confluence metadata (page title, page ID, space title, space ID, etc.)
                   - The operation type that was performed
                   - Any additional helpful information from the response
                5. In case of missing details like space key, space id, page id or document id use respective tools to fetch the information if it is not provided by user
                
                Important Guidelines:
                - Always include relevant Confluence metadata in your response (page IDs, space keys, titles, etc.)
                - Make the response conversational and easy to understand
                - If parameters are missing or unclear, use your best judgment or ask for clarification
                - Include page/space identifiers so users can reference them in future operations
                - Format dates and technical details in a user-friendly way
                
                Execute the Confluence operation and provide a comprehensive response.
                """, request.command());

        // Invoke the LLM with access to Confluence MCP tools to execute the operation
        ConfluenceResponse response = context.ai()
                .withDefaultLlm()
                .createObject(prompt, ConfluenceResponse.class);

        log.info("[ACTION] processConfluenceOperation END");

        return response;
    }
}