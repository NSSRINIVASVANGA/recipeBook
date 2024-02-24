package com.example.recipe;

import com.example.recipe.RecipeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.recipe.Recipe;

import java.util.*;

// Don't modify the below code

public class RecipeService implements RecipeRepository {

        private static HashMap<Integer, Recipe> recipeBook = new HashMap<>();
        int unique_Id = 6;

        public RecipeService() {
                recipeBook.put(1,
                                new Recipe(1, "Pasta", "veg",
                                                Arrays.asList("pasta", "tomatoes", "olive oil", "garlic", "basil")));
                recipeBook.put(2, new Recipe(2, "Chicken Curry", "non-veg",
                                Arrays.asList("chicken", "onion", "tomato", "ginger", "garlic", "spices")));
                recipeBook.put(3, new Recipe(3, "Sushi", "non-veg",
                                Arrays.asList("sushi rice", "tuna fish", "seaweed", "wasabi", "ginger")));
                recipeBook.put(4, new Recipe(4, "Mushroom Risotto", "veg",
                                Arrays.asList("rice", "mushrooms", "onion", "garlic", "butter", "parmesan")));
                recipeBook.put(5, new Recipe(5, "Fish and Chips", "non-veg",
                                Arrays.asList("fish", "potatoes", "flour", "oil", "spices")));
        }

        // Don't modify the above code

        // Write your code here

        @Override
        public ArrayList<Recipe> getRecipes() {
                ArrayList<Recipe> recipes = new ArrayList<>(recipeBook.values());
                return recipes;
        }

        @Override
        public Recipe addRecipe(Recipe recipe) {
                recipe.setRecipeId(unique_Id);
                recipeBook.put(unique_Id, recipe);
                unique_Id += 1;
                return recipe;
        }

        @Override
        public Recipe getByRecipeId(int recipeId) {
                if (recipeBook.get(recipeId) == null) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
                return recipeBook.get(recipeId);
        }

        @Override
        public Recipe updateRecipe(int recipeId, Recipe recipe) {
                Recipe existingRecipe = recipeBook.get(recipeId);
                if (existingRecipe == null) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
                if (!(existingRecipe.getRecipeName().equals(recipe.getRecipeName()))
                                && (recipe.getRecipeName() != null)) {
                        existingRecipe.setRecipeName(recipe.getRecipeName());
                }
                if (!(existingRecipe.getRecipeType().equals(recipe.getRecipeType()))
                                && (recipe.getRecipeType() != null)) {
                        existingRecipe.setRecipeType(recipe.getRecipeType());
                }
                List<String> ingredients1 = recipe.getIngredients();
                int len1 = ingredients1.size();
                if (len1 != 0) {
                        existingRecipe.setIngredients(ingredients1);
                }
                return existingRecipe;
        }

        public void deleteRecipe(int recipeId) {
                if (recipeBook.get(recipeId) == null) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                } else {
                        recipeBook.remove(recipeId);
                        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
                }
        }
}
