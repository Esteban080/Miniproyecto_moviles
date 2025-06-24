package com.example.reminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.reminderapp.ui.theme.ReminderAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReminderAppTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val context = LocalContext.current
    var reminders by remember { mutableStateOf(ReminderStorage.loadReminders(context)) }
    var showAddReminder by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddReminder = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                if (showAddReminder) {
                    AddReminderScreen(
                        onSave = { newReminder ->
                            reminders = reminders + newReminder
                            ReminderStorage.saveReminders(context, reminders)
                            showAddReminder = false
                        },
                        onCancel = {
                            showAddReminder = false
                        }
                    )
                } else {
                    ReminderList(reminders)
                }
            }
        }
    )
}

@Composable
fun ReminderList(reminders: List<Reminder>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(reminders) { reminder ->
            ReminderItem(reminder)
        }
    }
}

@Composable
fun ReminderItem(reminder: Reminder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Título: ${reminder.title}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Descripción: ${reminder.description}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${reminder.date}", style = MaterialTheme.typography.bodySmall)
            reminder.imagePath?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}
