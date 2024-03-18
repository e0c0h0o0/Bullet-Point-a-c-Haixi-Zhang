package com.example.maintainingstatebundlesandsharedpreferences_haixizhang

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        val loadImageButton: Button = findViewById(R.id.loadImageButton)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Load image and text from SharedPreferences
        loadImage()
        loadText()

        loadImageButton.setOnClickListener {
            // Load random image
            val randomImageId = getRandomImageId()
            imageView.setImageResource(randomImageId)

            // Save image and text to SharedPreferences
            saveImage(randomImageId)
            saveText()
        }
    }

    private fun getRandomImageId(): Int {
        val images = arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
            // Add more drawable resources as needed
        )
        return images.random()
    }

    private fun saveImage(imageId: Int) {
        sharedPreferences.edit().putInt("imageId", imageId).apply()
    }

    private fun loadImage() {
        val imageId = sharedPreferences.getInt("imageId", -1)
        if (imageId != -1) {
            imageView.setImageResource(imageId)
        }
    }

    private fun saveText() {
        val text = editText.text.toString()
        sharedPreferences.edit().putString("text", text).apply()
    }

    private fun loadText() {
        val text = sharedPreferences.getString("text", "")
        editText.setText(text)
    }
}
