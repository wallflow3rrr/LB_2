package com.example.lb_2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Список с ресурсами изображений и текстами
    private val images = listOf(
        Triple(R.drawable.seiling, "Sailing Under the Bridge", "Kat Kuan (2017)"),
        Triple(R.drawable.night, "Starry Night", "Vincent van Gogh (1889)"),
        Triple(R.drawable.lisa, "Mona Lisa", "Leonardo da Vinci (1503)")
    )

    // Переменная для хранения текущего индекса изображения
    private var currentIndex = 0

    // UI элементы
    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация UI элементов
        imageView = findViewById(R.id.imageView)
        titleTextView = findViewById(R.id.titleTextView)
        authorTextView = findViewById(R.id.authorTextView)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)

        // Восстановление состояния при повороте экрана
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0)
        }

        // Обновление UI
        updateUI()

        // Обработка нажатия на кнопку "Next"
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % images.size // Циклическое переключение
            updateUI()
        }

        // Обработка нажатия на кнопку "Previous"
        previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + images.size) % images.size // Циклическое переключение
            updateUI()
        }
    }

    // Функция для обновления UI
    private fun updateUI() {
        val (imageRes, title, author) = images[currentIndex]
        imageView.setImageResource(imageRes)
        titleTextView.text = title
        authorTextView.text = author
    }

    // Сохранение состояния при повороте экрана
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
    }
}