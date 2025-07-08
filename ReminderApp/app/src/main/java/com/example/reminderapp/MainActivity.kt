package com.example.reminderapp

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.example.reminderapp.ui.theme.ReminderAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Permiso para notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }

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
                    ReminderList(
                        reminders = reminders,
                        onDelete = { reminderToDelete ->
                            reminders = reminders.filter { it != reminderToDelete }
                            ReminderStorage.saveReminders(context, reminders)
                            cancelNotification(context, reminderToDelete)
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun ReminderList(reminders: List<Reminder>, onDelete: (Reminder) -> Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(reminders) { reminder ->
            ReminderItem(reminder = reminder, onDelete = onDelete)
        }
    }
}

@Composable
fun ReminderItem(reminder: Reminder, onDelete: (Reminder) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
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

            // ✅ Botón de eliminar arriba a la derecha
            IconButton(
                onClick = { onDelete(reminder) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}

fun cancelNotification(context: Context, reminder: Reminder) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra("title", reminder.title)
        putExtra("message", reminder.description)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        reminder.hashCode(), // usaremos hashCode para identificarlo
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)
}
