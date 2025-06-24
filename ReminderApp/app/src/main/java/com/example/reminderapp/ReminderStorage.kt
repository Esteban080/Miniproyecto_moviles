package com.example.reminderapp
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object ReminderStorage {
    private const val FILE_NAME = "reminders.json"
    private val gson = Gson()

    fun saveReminders(context: Context, reminders: List<Reminder>) {
        val json = gson.toJson(reminders)
        File(context.filesDir, FILE_NAME).writeText(json)
    }

    fun loadReminders(context: Context): List<Reminder> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return emptyList()
        val json = file.readText()
        val type = object : TypeToken<List<Reminder>>() {}.type
        return gson.fromJson(json, type)
    }
}
