package com.Project.AI.stock_image.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Project.AI.stock_image.service.ChatService;
import com.Project.AI.stock_image.service.GeminiService;
import com.Project.AI.stock_image.service.ImageService;
import com.Project.AI.stock_image.service.RecipeService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AIController {
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	GeminiService geminiService;
	
	@GetMapping("/ask-ai")
	public String getResponse(@RequestParam String prompt) {
		return chatService.getResponse(prompt);
	}
	
	/*For one image only
	 * @GetMapping("/generate-image") public void getImages(HttpServletResponse
	 * response, @RequestParam String prompt) throws IOException { ImageResponse
	 * imageResponse = imageService.generateImage(prompt); String imageUrl =
	 * imageResponse.getResult().getOutput().getUrl();
	 * response.sendRedirect(imageUrl); }
	 */
	
	//to get more than one image as output
	@GetMapping("/generate-images")
	public List<String> getImages(HttpServletResponse response, @RequestParam String prompt,
									@RequestParam(defaultValue = "hd") String qualtiy,
									@RequestParam(defaultValue = "1") int n,
									@RequestParam(defaultValue = "1024") int width,
									@RequestParam(defaultValue = "1024") int height) throws IOException {
		ImageResponse imageResponse = imageService.generateImage(prompt, qualtiy, n, width, height);
		List<String> imageUrls = imageResponse.getResults().stream()
				.map(result -> result.getOutput().getUrl()).toList();
		
		return imageUrls;
	}
	
	@GetMapping("recipe-creator")
	public String recipeCreator(@RequestParam String ingredients, 
			@RequestParam(defaultValue = "any") String cuisine, 
			@RequestParam(defaultValue = "") String dietaryRestrictions) {
		
		return geminiService.createRecipe(ingredients, cuisine, dietaryRestrictions);
//		return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
	}
}
