package com.example.saladinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okio.IOException
import android.util.Log
import com.example.saladinator.models.Recipe
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateRecyclerView()
        addDataSet()
        getRecipes(arrayOf("tomato"))
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        recipeAdapter.submitList(data)
    }

    fun arrToCommaSeparated(arr: Array<String>): String{
        val str = arr.contentToString()
        str.replace(" ", "")
        str.trimStart('[').trimEnd(']')
        return str
    }
    private fun getRecipes(ingredients: Array<String>) : Array<Recipe> {

        val client = OkHttpClient()

        val ingredientsString = arrToCommaSeparated(ingredients)
        val url = URL("http://79.176.225.67:8888/recipe?ingredients=$ingredientsString")
        Log.d("TAG", url.toString())

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {res ->
                    if (!res.isSuccessful) throw IOException("Unexpected code $res")

                    for ((name, value) in res.headers) Log.d("TAG" ,"$name: $value")

                    Log.d("TAG" ,res.body!!.string())
                }
            }
        })
        return arrayOf(
            Recipe(
                "Dehydrating Tomatoes",
                "http://www.recipezaar.com/Dehydrating-Tomatoes-325795",
                arrayOf("tomato"),
                "http://img.recipepuppy.com/37134.jpg"
            )
        )
    }

    private fun generateRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recipeAdapter = RecipeRecyclerAdapter()
            adapter = recipeAdapter
        }
    }
}