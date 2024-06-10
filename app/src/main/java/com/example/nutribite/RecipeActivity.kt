import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.nutribite.R

class RecipeActivity : AppCompatActivity() {
    private var isFavorite = false
    private lateinit var recipeTitle: String
    private var recipeImageResource: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_item)

        val heartIcon: ImageButton = findViewById(R.id.heart_icon)
        val sharedPreferences = getSharedPreferences("Favorites", Context.MODE_PRIVATE)

        recipeTitle = intent.getStringExtra("RECIPE_TITLE") ?: "Default Recipe Title"
        recipeImageResource = intent.getIntExtra("RECIPE_IMAGE", R.drawable.mutton_rogan_josh)

        isFavorite = sharedPreferences.contains(recipeTitle)
        updateHeartIcon(heartIcon)

        heartIcon.setOnClickListener {
            isFavorite = !isFavorite
            with(sharedPreferences.edit()) {
                if (isFavorite) {
                    putInt(recipeTitle, recipeImageResource)
                    apply()
                } else {
                    remove(recipeTitle).apply()
                }
            }
            updateHeartIcon(heartIcon)
        }
    }

    private fun updateHeartIcon(heartIcon: ImageButton) {
        heartIcon.isSelected = isFavorite
    }
}
