package com.Project.AI.stock_image.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
	private final ChatModel chatModel;

	public RecipeService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}
	
	public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {
		 String template = """
			        I want to create a recipe using the following ingredients: %s.
			        The cuisine type I prefer is %s.
			        I have the following dietary restrictions: %s.
			        Please provide me with a detailed recipe including:
			        - Title
			        - List of ingredients
			        - Cooking instructions
			    """;
		 String.format(template, ingredients, cuisine, dietaryRestrictions);
		 PromptTemplate promptTemplate = new PromptTemplate(template);
		 
		 Map<String, Object> params = Map.of(
				 "ingredients", ingredients,
				 "cuisine", cuisine,
				 "dietaryRestrictions", dietaryRestrictions);
		 
		 Prompt prompt = promptTemplate.create(params);
		 
		 return chatModel.call(prompt).getResult().getOutput().getContent();
		 }
}
