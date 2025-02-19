import { useState } from "react";

function Chat(){
    const [prompt, setPrompt] = useState('')
    const [chatResponse, setChatResponse] = useState('')

    const askAI = async () => {
        try {
            const response = await fetch (`http://localhost:8080/ask-ai?prompt=${prompt}`)

            if(!response.ok){
                throw new Error(`HTTP error! Status: ${response.status}`)
            }
            const ans = await response.text()
            console.log(ans)
            setChatResponse(ans)
        } 
        catch (error) {
            console.error("Enter getting the response :", error)
        }
    }

    return (
        <div>
            <h2>Talk to AI</h2>
            <input type="text" 
                value={prompt} 
                onChange={(e) => (setPrompt(e.target.value))}
                placeholder="Enter the question"
            />
            <button onClick={askAI}>Ask AI</button>
            <div className="output">
                <p>{chatResponse}</p>
            </div>
        </div>
    );
}

export default Chat