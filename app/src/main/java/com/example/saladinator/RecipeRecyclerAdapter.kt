package com.example.saladinator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.saladinator.models.Recipe
//import com.example.saladinator.MainActivity.arrToCommaSeparated
import kotlinx.android.synthetic.main.layout_recipe_list_item.view.*
import kotlin.collections.ArrayList

class RecipeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.layout_recipe_list_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RecipeViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(recipeList: List<Recipe>){
        items = recipeList
    }

    class RecipeViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        private val recipeImage: ImageView = itemView.recipe_thumbnail
        private val recipeName: TextView = itemView.recipe_name
        private val recipeIngredients: TextView = itemView.recipe_ingredients

        fun bind(recipe: Recipe){
            recipeName.text = recipe.title
            recipeIngredients.text = recipe.ingredients.contentToString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(recipe.thumbnail)
                .into(recipeImage)
        }
    }
}