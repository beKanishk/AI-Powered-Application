import { useState } from "react";

function RecipeGenerator(){
    const [ingredients, setIngredients] = useState('')
    const [cuisine, setCuisine] = useState('')
    const [dietaryRestrictions, setDietaryRestrictions] = useState('')
    const [recipe, setRecipe] = useState('')

    const createRecipe = async () => {
        try {
            const response = await fetch (`http://localhost:8080/recipe-creator?ingredients=${ingredients}&cuisine=${cuisine}&dietaryRestrictions=${dietaryRestrictions}`)

            if(!response.ok){
                throw new Error(`HTTP error! Status: ${response.status}`)
            }
            const ans = await response.text()
            console.log(ans)
            setRecipe(ans)
        } 
        catch (error) {
            console.error("Enter getting the response :", error)
        }
    }

    return (
        <div>
            <h2>Generate Recipe</h2>
            <input type="text"
                value={ingredients}
                onChange={(e) => (setIngredients(e.target.value))}
                placeholder="Enter the Ingredients (comma seperated)"
            />
            <input type="text"
                value={cuisine}
                onChange={(e) => (setCuisine(e.target.value))}
                placeholder="Enter the Cuisine if any"
            />
            <input type="text"
                value={dietaryRestrictions}
                onChange={(e) => (setDietaryRestrictions(e.target.value))}
                placeholder="Enter the Dietary Restrictions if any"
            />
            
            <button onClick={createRecipe}>Create Recipe</button>

            <div className="output">
                <pre className="recipe-text">{recipe}</pre>
            </div>
        </div>
    );
}

export default RecipeGenerator