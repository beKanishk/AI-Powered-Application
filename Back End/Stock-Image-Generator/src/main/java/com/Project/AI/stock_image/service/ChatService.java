package com.Project.AI.stock_image.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.prompt.Prompt; // ✅ Correct



@Service
public class ChatService {
	private final ChatModel chatModel;
	
	public ChatService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}
	
	public String getResponse(String prompt) {
		return chatModel.call(prompt);
	}
	
	//this is additional configuration
	public String getResponseOptions(String prompt) {
		ChatResponse chatResponse = chatModel.call(
			    new Prompt(
			            prompt,
			            OpenAiChatOptions.builder()
			                .model("gpt-4-o")
			                .temperature(0.4)
			            .build()
			        ));
		return chatResponse.getResult().getOutput().getContent();
	}
}
