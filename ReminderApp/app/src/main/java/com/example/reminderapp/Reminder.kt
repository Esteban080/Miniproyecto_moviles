package com.example.reminderapp
data class Reminder(
    val title: String,
    val description: String,
    val date: String,
    val imagePath: String? = null
)