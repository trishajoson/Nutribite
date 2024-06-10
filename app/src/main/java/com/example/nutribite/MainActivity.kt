package com.example.nutribite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var job: Job? = null

    private val tips = listOf(
        NutritionTip(R.drawable.water, "Stay Hydrated", "Drink at least 8 glasses of water a day."),
        NutritionTip(R.drawable.grain, "Eat Whole Grains", "Choose whole grains over refined grains."),
        NutritionTip(R.drawable.greens, "Enjoy Leafy Greens", "Incorporate a variety of leafy greens into your meals."),
        NutritionTip(R.drawable.sleep, "Prioritize Sleep", "Aim for 7-8 hours of quality sleep each night."),
        NutritionTip(R.drawable.portion, "Practice Portion Control", "Be mindful of portion sizes to avoid overeating."),
        NutritionTip(R.drawable.healthy_fats, "Choose Healthy Fats", "Incorporate sources of healthy fats like avocados and nuts."),
        NutritionTip(R.drawable.no_sugar, "Limit Added Sugars", "Reduce the amount of added sugars in your diet."),
        NutritionTip(R.drawable.exercise, "Stay Active", "Aim for at least 30 minutes of moderate activity most days."),
        NutritionTip(R.drawable.fruits_veggies, "Eat the Rainbow", "Include a variety of colored fruits and vegetables."),
        NutritionTip(R.drawable.no_junk_food, "Limit Processed Foods", "Minimize the intake of highly processed foods.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = Navigation.findNavController(this, R.id.main_fragment)

        NavigationUI.setupWithNavController(bottomNavigation, navController)

        cycleNutritionTips()

    }

    private fun cycleNutritionTips() {
        job = lifecycleScope.launch {
            while (isActive) {
                val randomTip = tips.random()
                findViewById<ImageView>(R.id.imageTipIcon)?.setImageResource(randomTip.imageResId)
                findViewById<TextView>(R.id.textRecipeTitle)?.text = randomTip.title
                findViewById<TextView>(R.id.textRecipeDescription)?.text = randomTip.description

                delay(5000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    data class NutritionTip(val imageResId: Int, val title: String, val description: String)
}
