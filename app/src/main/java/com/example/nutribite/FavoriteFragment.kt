package com.example.nutribite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutribite.R

class FavoriteFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        val favoriteRecipes: MutableList<Recipe> = mutableListOf()

        val map = sharedPreferences.all
        for (entry in map) {
            val dishName = entry.key
            val imageResource = entry.value as? Int ?: continue // Ensure proper casting

            val recipe = getRecipeByNameAndImageResource(dishName, imageResource)
            if (recipe != null) {
                favoriteRecipes.add(recipe)
            } else {
                Log.e("FavoriteFragment", "Recipe not found: $dishName with image resource: $imageResource")
            }
        }

        recipeAdapter = RecipeAdapter(favoriteRecipes, ::onRecipeClick)
        recyclerView.adapter = recipeAdapter

        return view
    }

    private fun onRecipeClick(recipe: Recipe) {
        // Handle recipe click, e.g., navigate to detail screen
        Log.d("FavoriteFragment", "Clicked on recipe: ${recipe.dishName}")
    }

    private fun getRecipeByNameAndImageResource(dishName: String, imageResource: Int): Recipe? {

        val allRecipes = getAllRecipes() // Replace with actual method to get all recipes
        return allRecipes.find { it.dishName == dishName && it.imageResource == imageResource }
    }

    private fun getAllRecipes(): List<Recipe> {

        return listOf(
            Recipe(
                category = "Breakfast",
                dishName = "Poha",
                ingredients = listOf("Poha", "onions", "green chilies", "mustard seeds", "turmeric", "peanuts", "curry leaves", "lemon", "coriander"),
                recipe = "Wash poha. Sauté mustard seeds, curry leaves, onions, and chilies. Add turmeric, poha, peanuts, and salt. Cook for a few minutes. Garnish with lemon juice.",
                nutritionalInfo = NutritionInfo(
                    calories = 180,
                    protein = 4.0,
                    fat = 5.0,
                    carbs = 30.0
                ),
                imageResource = R.drawable.poha
            ),
            Recipe(
                category = "Breakfast",
                dishName = "Masala Dosa",
                ingredients = listOf("Dosa batter", "potatoes", "onions", "green chilies", "turmeric", "mustard seeds", "curry leaves", "oil"),
                recipe = "Cook dosa on a pan. Prepare filling with sautéed onions, chilies, and boiled potatoes with turmeric and mustard seeds. Fill dosa and fold.",
                nutritionalInfo = NutritionInfo(
                    calories = 250,
                    protein = 6.0,
                    fat = 8.0,
                    carbs = 40.0
                ),
                imageResource = R.drawable.masala_dosa
            ),
            Recipe(
                category = "Breakfast",
                dishName = "Idli",
                ingredients = listOf("Idli batter", "oil"),
                recipe = "Steam the idli batter in an idli maker for 10-15 minutes. Serve hot with chutney and sambar.",
                nutritionalInfo = NutritionInfo(
                    calories = 100,
                    protein = 2.0,
                    fat = 1.0,
                    carbs = 21.0
                ),
                imageResource = R.drawable.idli
            ),
            Recipe(
                category = "Breakfast",
                dishName = "Upma",
                ingredients = listOf("Semolina", "onions", "green chilies", "mustard seeds", "curry leaves", "vegetables", "ghee"),
                recipe = "Sauté mustard seeds, curry leaves, onions, and vegetables. Add semolina and roast. Add water and cook until thick.",
                nutritionalInfo = NutritionInfo(
                    calories = 180,
                    protein = 4.0,
                    fat = 7.0,
                    carbs = 26.0
                ),
                imageResource = R.drawable.upma
            ),
            Recipe(
                category = "Breakfast",
                dishName = "Aloo Paratha",
                ingredients = listOf("Whole wheat flour", "potatoes", "green chilies", "coriander", "spices", "ghee"),
                recipe = "Prepare dough and potato filling. Stuff dough with filling and roll out. Cook on a griddle with ghee.",
                nutritionalInfo = NutritionInfo(
                    calories = 220,
                    protein = 5.0,
                    fat = 10.0,
                    carbs = 30.0
                ),
                imageResource = R.drawable.aloo_paratha
            ),
            Recipe(
                category = "Breakfast",
                dishName = "Chole Bhature",
                ingredients = listOf("Chickpeas", "flour", "yogurt", "spices", "oil"),
                recipe = "Soak and cook chickpeas. Prepare spicy gravy. Make dough with flour and yogurt, roll out, and deep fry until golden. Serve with chole.",
                nutritionalInfo = NutritionInfo(
                    calories = 300,
                    protein = 8.0,
                    fat = 15.0,
                    carbs = 40.0
                ),
                imageResource = R.drawable.chole_bhature
            ),
            Recipe(
                category = "Breakfast",
                dishName = "Pesarattu",
                ingredients = listOf("Green gram", "rice", "onions", "green chilies", "ginger", "cumin seeds", "oil"),
                recipe = "Soak green gram and rice. Blend with spices. Make thin pancakes (like dosa) on a griddle. Serve hot.",
                nutritionalInfo = NutritionInfo(
                    calories = 150,
                    protein = 7.0,
                    fat = 5.0,
                    carbs = 20.0
                ),
                imageResource = R.drawable.pesarattu
            ),
            Recipe(
                category = "Lunch",
                dishName = "Chicken Biryani",
                ingredients = listOf("Basmati rice", "chicken", "yogurt", "onions", "tomatoes", "ginger-garlic paste", "biryani masala", "saffron", "ghee"),
                recipe = "Marinate chicken. Cook rice with spices. Layer chicken and rice, add saffron milk. Cook on low heat (dum) for 20 minutes.",
                nutritionalInfo = NutritionInfo(
                    calories = 450,
                    protein = 20.0,
                    fat = 15.0,
                    carbs = 60.0
                ),
                imageResource = R.drawable.chicken_biryani
            ),
            Recipe(
                category = "Lunch",
                dishName = "Palak Paneer",
                ingredients = listOf("Spinach", "paneer", "onions", "tomatoes", "garlic", "ginger", "green chilies", "cream", "spices"),
                recipe = "Blanch spinach and blend. Sauté onions, garlic, ginger, and tomatoes. Add spices, spinach puree, and paneer. Cook and add cream before serving.",
                nutritionalInfo = NutritionInfo(
                    calories = 300,
                    protein = 12.0,
                    fat = 20.0,
                    carbs = 15.0
                ),
                imageResource = R.drawable.palak_paneer
            ),
            Recipe(
                category = "Lunch",
                dishName = "Rajma",
                ingredients = listOf("Red kidney beans", "onions", "tomatoes", "ginger-garlic paste", "spices", "oil"),
                recipe = "Soak and boil kidney beans. Sauté onions, ginger-garlic paste, and tomatoes with spices. Add beans and simmer until thick. Serve with rice.",
                nutritionalInfo = NutritionInfo(
                    calories = 350,
                    protein = 15.0,
                    fat = 8.0,
                    carbs = 55.0
                ),
                imageResource = R.drawable.rajma
            ),
            Recipe(
                category = "Lunch",
                dishName = "Paneer Butter Masala",
                ingredients = listOf("Paneer", "tomatoes", "cream", "onions", "ginger-garlic paste", "spices", "butter"),
                recipe = "Sauté onions, ginger-garlic paste, and tomatoes with spices. Add paneer and cream. Cook until thick and creamy.",
                nutritionalInfo = NutritionInfo(
                    calories = 400,
                    protein = 10.0,
                    fat = 25.0,
                    carbs = 35.0
                ),
                imageResource = R.drawable.paneer_butter_masala
            ),
            Recipe(
                category = "Lunch",
                dishName = "Fish Curry",
                ingredients = listOf("Fish", "coconut milk", "onions", "tomatoes", "ginger-garlic paste", "spices"),
                recipe = "Sauté onions, ginger-garlic paste, and tomatoes with spices. Add coconut milk and fish. Simmer until fish is cooked. Serve with rice.",
                nutritionalInfo = NutritionInfo(
                    calories = 350,
                    protein = 25.0,
                    fat = 20.0,
                    carbs = 15.0
                ),
                imageResource = R.drawable.fish_curry
            ),
            Recipe(
                category = "Lunch",
                dishName = "Mutton Rogan Josh",
                ingredients = listOf("Mutton", "yogurt", "onions", "ginger-garlic paste", "tomatoes", "spices", "oil"),
                recipe = "Marinate mutton. Sauté onions, ginger-garlic paste, and tomatoes with spices. Add mutton and yogurt. Cook until tender. Serve with rice or naan.",
                nutritionalInfo = NutritionInfo(
                    calories = 500,
                    protein = 30.0,
                    fat = 30.0,
                    carbs = 20.0
                ),
                imageResource = R.drawable.mutton_rogan_josh
            ),
            Recipe(
                category = "Lunch",
                dishName = "Vegetable Pulao",
                ingredients = listOf("Basmati rice", "mixed vegetables", "onions", "green peas", "spices", "oil"),
                recipe = "Sauté spices and onions, add mixed vegetables and rice. Cook with water until rice is done. Serve hot with raita.",
                nutritionalInfo = NutritionInfo(
                    calories = 250,
                    protein = 5.0,
                    fat = 10.0,
                    carbs = 35.0
                ),
                imageResource = R.drawable.vegetable_pulao
            ),
            Recipe(
                category = "Lunch",
                dishName = "Kadai Paneer",
                ingredients = listOf("Paneer", "bell peppers", "tomatoes", "onions", "garlic", "ginger", "spices", "oil"),
                recipe = "Sauté onions, garlic, ginger, and bell peppers with spices. Add tomatoes and paneer. Cook until blended. Serve with naan or roti.",
                nutritionalInfo = NutritionInfo(
                    calories = 300,
                    protein = 12.0,
                    fat = 18.0,
                    carbs = 20.0
                ),
                imageResource = R.drawable.kadai_paneer
            ),
            Recipe(
                category = "Lunch",
                dishName = "Dal Makhani",
                ingredients = listOf("Black lentils", "kidney beans", "onions", "tomatoes", "garlic", "ginger", "cream", "spices", "butter"),
                recipe = "Cook lentils and beans. Sauté onions, garlic, ginger, and tomatoes with spices. Add lentils and cream. Simmer and finish with butter.",
                nutritionalInfo = NutritionInfo(
                    calories = 350,
                    protein = 12.0,
                    fat = 20.0,
                    carbs = 35.0
                ),
                imageResource = R.drawable.dal_makhani
            ),
            Recipe(
                category = "Dinner",
                dishName = "Butter Chicken",
                ingredients = listOf("Chicken", "butter", "cream", "tomatoes", "onions", "ginger-garlic paste", "spices"),
                recipe = "Marinate and cook chicken. Prepare sauce with butter, onions, tomatoes, and spices. Add chicken and cream. Simmer until thick. Serve with naan.",
                nutritionalInfo = NutritionInfo(
                    calories = 490,
                    protein = 24.0,
                    fat = 30.0,
                    carbs = 25.0
                ),
                imageResource = R.drawable.butter_chicken
            ),
            Recipe(
                category = "Dinner",
                dishName = "Dal Tadka",
                ingredients = listOf("Yellow lentils", "onions", "tomatoes", "garlic", "ginger", "green chilies", "ghee", "spices"),
                recipe = "Cook lentils. Sauté onions, garlic, ginger, chilies, and tomatoes. Add spices and lentils. Simmer and finish with a ghee tempering.",
                nutritionalInfo = NutritionInfo(
                    calories = 240,
                    protein = 10.0,
                    fat = 8.0,
                    carbs = 30.0
                ),
                imageResource = R.drawable.dal_tadka
            ),
            Recipe(
                category = "Dinner",
                dishName = "Baingan Bharta",
                ingredients = listOf("Eggplant", "onions", "tomatoes", "garlic", "ginger", "green chilies", "spices", "oil"),
                recipe = "Roast and peel eggplant. Sauté onions, garlic, ginger, chilies, and tomatoes with spices. Add eggplant and cook until blended. Serve with roti.",
                nutritionalInfo = NutritionInfo(
                    calories = 150,
                    protein = 3.0,
                    fat = 10.0,
                    carbs = 15.0
                ),
                imageResource = R.drawable.baingan_bharta
            ),
            Recipe(
                category = "Dinner",
                dishName = "Malai Kofta",
                ingredients = listOf("Paneer", "potatoes", "cream", "tomatoes", "onions", "spices", "oil"),
                recipe = "Prepare kofta balls with paneer and potatoes, fry until golden. Prepare gravy with onions, tomatoes, and cream. Add koftas and simmer.",
                nutritionalInfo = NutritionInfo(
                    calories = 450,
                    protein = 12.0,
                    fat = 25.0,
                    carbs = 45.0
                ),
                imageResource = R.drawable.malai_kofta
            ),
            Recipe(
                category = "Dinner",
                dishName = "Aloo Gobi",
                ingredients = listOf("Potatoes", "cauliflower", "onions", "tomatoes", "ginger", "garlic", "spices", "oil"),
                recipe = "Sauté onions, ginger, garlic, and tomatoes with spices. Add potatoes and cauliflower. Cook until tender. Serve with roti or rice.",
                nutritionalInfo = NutritionInfo(
                    calories = 200,
                    protein = 5.0,
                    fat = 10.0,
                    carbs = 25.0
                ),
                imageResource = R.drawable.aloo_gobi
            ),
            Recipe(
                category = "Dinner",
                dishName = "Shahi Paneer",
                ingredients = listOf("Paneer", "tomatoes", "onions", "cream", "cashews", "garlic", "ginger", "spices", "ghee"),
                recipe = "Blend tomatoes, onions, and cashews into a paste. Cook with spices and ghee. Add paneer and cream. Simmer until thick and creamy.",
                nutritionalInfo = NutritionInfo(
                    calories = 400,
                    protein = 10.0,
                    fat = 25.0,
                    carbs = 35.0
                ),
                imageResource = R.drawable.shahi_paneer
            ),
            Recipe(
                category = "Snacks",
                dishName = "Samosa",
                ingredients = listOf("Potatoes", "peas", "flour", "spices", "oil"),
                recipe = "Prepare filling with boiled potatoes, peas, and spices. Make dough, fill, and fold into triangles. Deep fry until golden brown.",
                nutritionalInfo = NutritionInfo(
                    calories = 150,
                    protein = 3.0,
                    fat = 8.0,
                    carbs = 18.0
                ),
                imageResource = R.drawable.samosa
            ),
            Recipe(
                category = "Snacks",
                dishName = "Pakora",
                ingredients = listOf("Besan (gram flour)", "onions", "spinach", "spices", "oil"),
                recipe = "Mix sliced vegetables with besan and spices. Deep fry small portions until golden brown.",
                nutritionalInfo = NutritionInfo(
                    calories = 200,
                    protein = 4.0,
                    fat = 10.0,
                    carbs = 25.0
                ),
                imageResource = R.drawable.pakora
            ),
            Recipe(
                category = "Snacks",
                dishName = "Bhel Puri",
                ingredients = listOf("Puffed rice", "sev", "potatoes", "onions", "tomatoes", "chutneys", "coriander", "spices"),
                recipe = "Mix puffed rice, chopped vegetables, chutneys, and spices. Top with sev and coriander.",
                nutritionalInfo = NutritionInfo(
                    calories = 120,
                    protein = 2.0,
                    fat = 5.0,
                    carbs = 18.0
                ),
                imageResource = R.drawable.bhel_puri
            ),
            Recipe(
                category = "Snacks",
                dishName = "Dhokla",
                ingredients = listOf("Besan (gram flour)", "yogurt", "green chilies", "ginger", "mustard seeds", "curry leaves", "oil"),
                recipe = "Ferment besan and yogurt mixture. Steam until fluffy. Prepare tempering with mustard seeds and curry leaves, pour over dhokla.",
                nutritionalInfo = NutritionInfo(
                    calories = 160,
                    protein = 6.0,
                    fat = 5.0,
                    carbs = 22.0
                ),
                imageResource = R.drawable.dokhla
            ),
            Recipe(
                category = "Snacks",
                dishName = "Pav Bhaji",
                ingredients = listOf("Pav bread", "potatoes", "tomatoes", "peas", "onions", "capsicum", "butter", "bhaji masala"),
                recipe = "Cook and mash vegetables. Sauté onions and capsicum with bhaji masala. Add mashed vegetables and cook. Serve with buttered pav.",
                nutritionalInfo = NutritionInfo(
                    calories = 300,
                    protein = 8.0,
                    fat = 12.0,
                    carbs = 40.0
                ),
                imageResource = R.drawable.pav_bhaji
            ),
            Recipe(
                category = "Snacks",
                dishName = "Vada Pav",
                ingredients = listOf("Potatoes", "chickpea flour", "pav bread", "garlic", "green chilies", "spices", "oil"),
                recipe = "Make potato filling, coat with chickpea batter, and deep fry. Serve in pav bread with chutneys.",
                nutritionalInfo = NutritionInfo(
                    calories = 250,
                    protein = 6.0,
                    fat = 10.0,
                    carbs = 35.0
                ),
                imageResource = R.drawable.vada_pav
            ),
            Recipe(
                category = "Snacks",
                dishName = "Aloo Tikki",
                ingredients = listOf("Potatoes", "peas", "spices", "breadcrumbs", "oil"),
                recipe = "Mix boiled potatoes and peas with spices. Form patties, coat with breadcrumbs, and shallow fry until golden.",
                nutritionalInfo = NutritionInfo(
                    calories = 200,
                    protein = 4.0,
                    fat = 8.0,
                    carbs = 30.0
                ),
                imageResource = R.drawable.aloo_tikki
            ),
            Recipe(
                category = "Snacks",
                dishName = "Sev Puri",
                ingredients = listOf("Puri", "potatoes", "onions", "tomatoes", "sev", "chutneys", "coriander", "spices"),
                recipe = "Top puris with chopped vegetables, chutneys, and sev. Garnish with coriander.",
                nutritionalInfo = NutritionInfo(
                    calories = 150,
                    protein = 3.0,
                    fat = 6.0,
                    carbs = 20.0
                ),
                imageResource = R.drawable.sev_puri
            ),
            Recipe(
                category = "Snacks",
                dishName = "Kachori",
                ingredients = listOf("Flour", "moong dal", "spices", "oil"),
                recipe = "Prepare dough and filling with moong dal and spices. Fill dough, shape, and deep fry until golden.",
                nutritionalInfo = NutritionInfo(
                    calories = 200,
                    protein = 5.0,
                    fat = 10.0,
                    carbs = 25.0
                ),
                imageResource = R.drawable.kachori
            )
        )
    }
}
