package com.example.nutribite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeTitle: TextView = itemView.findViewById(R.id.recipe_title)
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        private val recipeIngredients: TextView = itemView.findViewById(R.id.recipe_ingredients)
        private val recipeNutrition: TextView = itemView.findViewById(R.id.recipe_nutrition)
        private val recipeInstructions: TextView = itemView.findViewById(R.id.recipe_instructions)
        private val heartIcon: ImageButton = itemView.findViewById(R.id.heart_icon)

        fun bind(recipe: Recipe) {
            recipeTitle.text = recipe.dishName
            recipeImage.setImageResource(recipe.imageResource)
            recipeIngredients.text = "Ingredients: ${recipe.ingredients.joinToString(", ")}"
            recipeNutrition.text = "Calories: ${recipe.nutritionalInfo.calories} kcal, " +
                    "Protein: ${recipe.nutritionalInfo.protein} g, " +
                    "Fat: ${recipe.nutritionalInfo.fat} g, " +
                    "Carbs: ${recipe.nutritionalInfo.carbs} g"
            recipeInstructions.text = recipe.recipe

            val sharedPreferences = itemView.context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)
            val isFavorite = sharedPreferences.contains(recipe.dishName)
            heartIcon.isSelected = isFavorite

            heartIcon.setOnClickListener {
                val isNowFavorite = !heartIcon.isSelected
                heartIcon.isSelected = isNowFavorite
                with(sharedPreferences.edit()) {
                    if (isNowFavorite) {
                        putInt(recipe.dishName, recipe.imageResource)
                        apply()
                    } else {
                        remove(recipe.dishName).apply()
                    }
                }
            }

            itemView.setOnClickListener { onRecipeClick(recipe) }
        }
    }
}
