# 🤖 Embabel Framework: Build a Confluence MCP Client

This repository demonstrates how to build an **intelligent MCP (Model Context Protocol) Client** using the **Embabel Framework** and **Spring Boot**. The application connects to a Confluence MCP server and uses AI (LLMs like DeepSeek) to interpret natural language commands, executing Confluence operations like listing spaces, creating pages, and fetching metadata.

📖 **Complete Guide**: For detailed explanations and a full code walkthrough, read our comprehensive tutorial.<br>
👉 [**Build Confluence MCP Client with Embabel Framework**](https://bootcamptoprod.com/embabel-confluence-mcp-client/)

🎥 **Video Tutorial**: Prefer hands-on learning? Watch our step-by-step implementation guide.<br>
👉 **YouTube Tutorial - Coming Soon!**

---

## ✨ What This Project Demonstrates

This application showcases how to **build an AI-powered Confluence assistant** that understands human intent:

- **Natural Language Documentation Management** - Send commands like *"Create a new page in the Engineering space titled 'API Specs'"*.
- **Intelligent Goal-Driven Agent** - The AI automatically identifies the correct operation to perform (LIST_SPACES, CREATE_DOCUMENT, GET_HISTORY, etc.).
- **Unified Confluence API** - A single REST endpoint handles all Confluence interactions through simple, conversational commands.
- **MCP Tool Orchestration** - Demonstrates how an AI agent discovers, selects, and invokes tools provided by an external MCP server dynamically.

---

## 🛠️ Prerequisites

To run this client effectively, you need to have the **Confluence MCP Server** (from our previous tutorial) running on your machine:

1. **Confluence MCP Server**: [Get the Server Repository Here](https://github.com/BootcampToProd/embabel-confluence-mcp-server)
2. **Setup Environment Variables**: You must set the following environment variables for both the server and client to function correctly
```bash
# LLM API Key (Required for the Client and Server both)
export OPENAI_API_KEY=your_openrouter_api_key_here

# Confluence Credentials (Required for the Server to "Act")
export CONFLUENCE_BASE_URL=https://your-domain.atlassian.net/wiki/api/v2
export CONFLUENCE_AUTH_TOKEN=your_confluence_api_token
```
For more details on understanding and setting up the Confluence MCP Server, refer to our dedicated guide: [Build Confluence MCP Server Using Embabel Framework](https://bootcamptoprod.com/embabel-confluence-mcp-server/)

---

## 🚀 How to Run and Test

**For detailed instructions on how to set up, configure, and test the application, kindly go through our comprehensive article:**  
👉 [**Click here for Setup & Testing Instructions**](https://bootcamptoprod.com/embabel-confluence-mcp-client/)

---