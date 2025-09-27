package com.addo.basicserver.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.addo.basicserver.config.openAiconfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GPTService {
	private final openAiconfig aiconfig;
	private final RestTemplate restlmplt;
	private final ObjectMapper objmapper; // to read and parse json safley
	
	public GPTService(openAiconfig aiconfig, RestTemplate restlmplt, ObjectMapper objmapper) {
		this.aiconfig = aiconfig;
		this.restlmplt = restlmplt;
		this.objmapper = objmapper;
	}

	public openAiconfig getAiconfig() {
		return aiconfig;
	}
	
	// here i will start to run my gpt calls asyn
	@Async
	public CompletableFuture<Map<String, Object>> AskGPT(String usermessage){
		Map<String, Object> results = new HashMap<>();
		// lets test out our application properties information if it set or not
		try {
			// here my logic will go on
			// request body
            Map<String, Object> requestBody = Map.of(
                    "model", aiconfig.getModel(),
                    "messages", List.of(Map.of("role", "user", "content", usermessage))
            );

            // headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(aiconfig.getKey());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // call OpenAI API
            ResponseEntity<String> response = restlmplt.exchange(
                    aiconfig.getUrl(),
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                results.put("message", "No response from ChatGPT.");
                return CompletableFuture.completedFuture(results);
            }

            // parse JSON safely
            JsonNode root = objmapper.readTree(response.getBody());
            JsonNode choices = root.path("choices");

            if (choices.isArray() && choices.size() > 0) {
                String content = choices.get(0).path("message").path("content").asText("No content");
                results.put("message", content);
                results.put("model", aiconfig.getModel());
            } else {
            	results.put("message", "No choices found in ChatGPT response.");
            }
		} catch(Exception e) {
			results.put("message", "Error Contacting GPT service" + e.getMessage());
		}
		
		return CompletableFuture.completedFuture(results);
	}
}
