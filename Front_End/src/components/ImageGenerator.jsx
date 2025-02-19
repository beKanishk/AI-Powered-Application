import { useState } from "react";

function ImageGenerator() {
    const [prompt, setPrompt] = useState('');
    const [imageUrls, setImageUrls] = useState([]);
    const [loading, setLoading] = useState(false); // New loading state

    const generateImage = async () => {
        setLoading(true);
        try {
            const response = await fetch(`http://localhost:8080/generate-images?prompt=${prompt}`);
            
            // Handle HTTP errors
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const urls = await response.json();
            setImageUrls(urls);
        } catch (error) {
            console.error("Error generating image:", error);
            alert("Failed to generate image. Please try again.");
        }
        finally {
            setLoading(false); // Stop loading
        }
    };

    return (
        <div className="tab-content">
            <h2>Generate Image</h2>
            <input
                type="text"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                placeholder="Enter prompt for image"
            />
            <button onClick={generateImage} disabled={loading}>
                {loading ? "Generating..." : "Generate Image"}
            </button> 

            {/* Loading Spinner */}
            {loading && <div className="spinner"></div>}

            {/* Why index?
                Each React element needs a unique key to avoid rendering issues.
                The index ensures each <img> has a unique key (key={index}).
                If URLs repeat, React can still differentiate them.
                */}
            <div className="image-grid">
                {(() => {
                    try {
                        return imageUrls.map((url, index) => (
                            <img key={index} src={url} alt={`Generated ${index}`} />
                        ));
                    } catch (error) {
                        console.error("Error rendering images:", error);
                        return <p>Error displaying images.</p>;
                    }
                })()}


                {/* 
                This code ensures that the image grid always maintains a consistent layout 
                by filling any remaining empty slots with placeholder divs.

                - `Array(4 - imageUrls.length)`: Creates an array with a length equal to the number 
                    of empty slots needed to maintain a 4-item grid.
                - `[...Array(...)]`: Expands the array to allow mapping over it.
                - `.map((_, index) => (...))`: Iterates over the empty slots and renders a placeholder div.
                - `key={index + imageUrls.length}`: Ensures each placeholder has a unique key 
                    to prevent React warnings.

                If fewer than 4 images are generated, the empty slots will be visually represented 
                by `empty-image-slot` divs, helping maintain a balanced UI.
                */}
                {[...Array(4 - imageUrls.length)].map((_, index) => (
                    <div key={index + imageUrls.length} className="empty-image-slot"></div>
                ))}
            </div>
        </div>
    );
}

export default ImageGenerator;
