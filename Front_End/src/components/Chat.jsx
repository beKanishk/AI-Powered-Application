import { useState } from "react";

function Chat() {
    const [prompt, setPrompt] = useState('');
    const [chatResponse, setChatResponse] = useState('');

    const askAI = async () => {
        if (!prompt.trim()) return;

        try {
            const response = await fetch(`http://localhost:8080/ask-ai?prompt=${encodeURIComponent(prompt)}`);

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const ans = await response.text();
            console.log(ans);
            setChatResponse(ans);
        } catch (error) {
            console.error("Error getting the response:", error);
        }
    };

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            askAI();
        }
    };

    // Convert **bold** Markdown to <strong>bold</strong>
    const formatResponse = (text) => {
        const formatted = text.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');
        return { __html: formatted };
    };

    return (
        <div style={{ padding: '1rem', fontFamily: 'Arial, sans-serif' }}>
            <h2>Talk to AI</h2>
            <input
                type="text"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                onKeyDown={handleKeyDown}
                placeholder="Enter your question"
                style={{ width: '60%', padding: '0.5rem', fontSize: '1rem' }}
            />
            <button
                onClick={askAI}
                style={{ marginLeft: '1rem', padding: '0.5rem 1rem', fontSize: '1rem' }}
            >
                Ask AI
            </button>

            <div className="output" style={{ marginTop: '1rem' }}>
                <p dangerouslySetInnerHTML={formatResponse(chatResponse)} />
            </div>
        </div>
    );
}

export default Chat;
