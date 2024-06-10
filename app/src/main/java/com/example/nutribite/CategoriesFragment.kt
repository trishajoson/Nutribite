package com.example.nutribite

import CategoriesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesFragment : Fragment() {

    private lateinit var categoriesRecyclerView: RecyclerView
    private val categories = listOf(
        Category("Breakfast", R.drawable.breakfast, listOf()),
        Category("Lunch", R.drawable.lunch, listOf()),
        Category("Dinner", R.drawable.dinner, listOf()),
        Category("Snacks", R.drawable.snacks, listOf())
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        categoriesRecyclerView = view.findViewById(R.id.categories_recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesAdapter(categories) { category ->
            onCategoryClick(category)
        }
        categoriesRecyclerView.adapter = adapter
        categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onCategoryClick(category: Category) {
        val navController = findNavController()
        val bundle = Bundle()
        bundle.putString("categoryName", category.name)
        bundle.putInt("categoryImage", category.imageResource)
        navController.navigate(R.id.action_categoriesFragment_to_recipesFragment, bundle)
    }
}