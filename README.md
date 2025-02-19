# AI-Powered Application with Spring Boot and React

## Overview
This project is a full-stack web application that integrates AI-powered features using a React frontend and a Spring Boot backend. The application offers three main functionalities:

1. **AI Chat** - Users can interact with an AI-powered chatbot.
2. **Image Generation** - Users can generate images based on text prompts.
3. **Recipe Generator** - Users can generate recipes based on ingredients, cuisine type, and dietary restrictions.

## Features
### 1. AI Chat
- Users enter a question or prompt.
- The AI provides a textual response.
- Responses are displayed dynamically on the UI.

### 2. Image Generation
- Users enter a prompt to generate an image.
- The backend processes the request and returns an image URL.
- The generated images are displayed in a grid format.

### 3. Recipe Generator
- Users enter ingredients, cuisine preferences, and dietary restrictions.
- The AI generates a recipe based on the input.
- The generated recipe is displayed in text format.

## Technologies Used
### Frontend
- **React** - For building the user interface.
- **CSS** - For styling.

### Backend
- **Spring Boot** - For handling API requests and processing AI responses.

## Installation and Setup
### Prerequisites
- Node.js installed
- Java 17 or later installed


### Backend Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo-link.git
   cd backend
   ```
2. Build and run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```
3. The backend runs on `http://localhost:8080`.

### Frontend Setup
1. Navigate to the frontend directory:
   ```sh
   cd frontend
   ```
2. Install dependencies:
   ```sh
   npm install
   ```
3. Start the React application:
   ```sh
   npm start
   ```
4. The frontend runs on `http://localhost:5173`.

## API Endpoints
### 1. AI Chat
- **Endpoint:** `GET /ask-ai?prompt={question}`
- **Response:** AI-generated text response.

### 2. Image Generation
- **Endpoint:** `GET /generate-images?prompt={text}`
- **Response:** List of image URLs.

### 3. Recipe Generator
- **Endpoint:** `GET /recipe-creator?ingredients={ingredients}&cuisine={cuisine}&dietaryRestrictions={restrictions}`
- **Response:** AI-generated recipe in text format.

## Contribution
Feel free to contribute by creating issues, fixing bugs, or suggesting new features.



