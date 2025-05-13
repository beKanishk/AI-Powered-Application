package com.Project.AI.stock_image.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	private final OpenAiImageModel imageModel;

	public ImageService(OpenAiImageModel imageModel) {
		this.imageModel = imageModel;
	}
	
	public ImageResponse generateImage(String prompt, String qualtiy, int n, int width, int height) {
		//this is normal response without any configuration for our images
		//ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt));
				
//		ImageResponse imageResponse = imageModel.call(
//		        new ImagePrompt(prompt,
//		        OpenAiImageOptions.builder()
//		        		.withModel("dall-e-2")
//		                .quality("hd")
//		                .N(4) //this specify how much images will be generated
//		                .height(1024)
//		                .width(1024).build())
//
//		);
		
		ImageResponse imageResponse = imageModel.call(
		        new ImagePrompt(prompt,
		        OpenAiImageOptions.builder()
		        		.withModel("dall-e-2")
		                .quality(qualtiy)
		                .N(n) //this specify how much images will be generated
		                .height(height)
		                .width(width).build())

		);
		
		return imageResponse;
	}
}

//package com.Project.AI.stock_image.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;
//import reactor.core.publisher.Mono;
//
//@Service
//public class ImageService {
//
//    private final WebClient webClient;
//    
//    @Value("${gemini.api.key}")
//    private String apiKey;
//
//    public ImageService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent")
//                                        .build();
//    }
//
//    public Mono<String> generateImage(String prompt, String quality, int n, int width, int height) {
//        try {
//            // Creating the request body
//            ImageRequestBody requestBody = new ImageRequestBody(prompt, quality, n, width, height);
//
//            return webClient.post()
//                    .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
//                    .bodyValue(requestBody)  // Sending the body as a DTO
//                    .retrieve()
//                    .onStatus(status -> status.is4xxClientError(), response -> 
//                        Mono.error(new RuntimeException("4xx Client Error: " + response.statusCode().toString())))
//                    .onStatus(status -> status.is5xxServerError(), response -> 
//                        Mono.error(new RuntimeException("5xx Server Error: " + response.statusCode().toString())))
//                    .bodyToMono(String.class)
//                    .onErrorResume(WebClientResponseException.class, ex -> {
//                        // Handling WebClientResponseException
//                        return Mono.error(new RuntimeException("Error during WebClient request: " + ex.getMessage(), ex));
//                    });
//        } catch (Exception e) {
//            return Mono.error(new RuntimeException("Error generating image: " + e.getMessage(), e));
//        }
//    }
//    
//    public List<String> extractUrlsFromGeminiResponse(String json) {
//        List<String> urls = new ArrayList<>();
//        Pattern pattern = Pattern.compile("https?://[^\\s\"}]+");
//        Matcher matcher = pattern.matcher(json);
//        while (matcher.find()) {
//            urls.add(matcher.group());
//        }
//        return urls;
//    }
//
//    // DTO class for the request body
//    private static class ImageRequestBody {
//        private final String prompt;
//        private final String quality;
//        private final int n;
//        private final int width;
//        private final int height;
//
//        public ImageRequestBody(String prompt, String quality, int n, int width, int height) {
//            this.prompt = prompt;
//            this.quality = quality;
//            this.n = n;
//            this.width = width;
//            this.height = height;
//        }
//
//        // Getters for the fields (optional if you are using reflection or other frameworks that rely on them)
//        public String getPrompt() {
//            return prompt;
//        }
//
//        public String getQuality() {
//            return quality;
//        }
//
//        public int getN() {
//            return n;
//        }
//
//        public int getWidth() {
//            return width;
//        }
//
//        public int getHeight() {
//            return height;
//        }
//    }
//}
