package com.example.saladinator

import com.example.saladinator.models.Recipe

class DataSource {

    companion object{

        fun createDataSet(): List<Recipe>{
            return listOf(
                Recipe(
                    "Vegetable-Pasta Oven Omelet",
                    "http://find.myrecipes.com/recipes/recipefinder.dyn?action=displayRecipe&recipe_id=520763",
                    arrayOf(
                        "tomato",
                        "onions",
                        "red pepper",
                        "garlic",
                        "olive oil",
                        "zucchini",
                        "cream cheese",
                        "vermicelli, eggs",
                        "parmesan cheese",
                        "milk",
                        "italian seasoning",
                        "salt",
                        "black pepper"
                    ),
                    "http://img.recipepuppy.com/560556.jpg"
                ),
                Recipe(
                    "Dehydrating Tomatoes",
                    "http://www.recipezaar.com/Dehydrating-Tomatoes-325795",
                    arrayOf("tomato"),
                    "http://img.recipepuppy.com/37134.jpg"
                )
            )
        }
    }
}