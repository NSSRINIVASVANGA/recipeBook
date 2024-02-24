package com.example.recipe;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class RecipeController {
    RecipeService recipeServise = new RecipeService();

    @GetMapping("/recipes")
    public ArrayList<Recipe> getRecipes() {
        return recipeServise.getRecipes();
    }

    @PostMapping("/recipes")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeServise.addRecipe(recipe);
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe getByrecipeId(@PathVariable("recipeId") int recipeId) {
        return recipeServise.getByRecipeId(recipeId);
    }

    @PutMapping("/recipes/{recipeId}")
    public Recipe updateRecipe(@PathVariable("recipeId") int recipeId, @RequestBody Recipe recipe) {
        return recipeServise.updateRecipe(recipeId, recipe);
    }

    @DeleteMapping("/recipes/{recipeId}")
    public void deleteRecipe(@PathVariable("recipeId") int recipeId) {
        recipeServise.deleteRecipe(recipeId);
    }
}