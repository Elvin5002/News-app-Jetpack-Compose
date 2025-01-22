package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int
)

val pages = listOf(
    Page(
        title = "Welcome to NewsApp",
        description = "Get the latest news from around the world",
        imageRes = com.example.newsapp.R.drawable.onboarding1
    ),
    Page(
        title = "Personalized News",
        description = "Get news that matters to you",
        imageRes = com.example.newsapp.R.drawable.onboarding2
    ),
    Page(
        title = "Stay Updated",
        description = "Get breaking news alerts",
        imageRes = com.example.newsapp.R.drawable.onboarding3
    )
)
