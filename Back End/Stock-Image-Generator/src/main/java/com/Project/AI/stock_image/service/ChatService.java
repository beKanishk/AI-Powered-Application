package com.Project.AI.stock_image.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public ChatService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent").build();
    }

    public String getResponse(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        var content = (Map<String, Object>) candidates.get(0).get("content");
                        var parts = (List<Map<String, Object>>) content.get("parts");
                        return (String) parts.get(0).get("text");
                    }
                    return "No response from Gemini.";
                })
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just("Error calling Gemini: " + e.getMessage());
                })
                .block();
    }
}
