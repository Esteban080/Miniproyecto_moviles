package com.example.reminderapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image


@Composable
fun ReminderListScreen(
    reminders: List<Reminder>,
    onDelete: (Reminder) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(reminders) { reminder ->
            ReminderCard(reminder, onDelete)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder, onDelete: (Reminder) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = MaterialTheme.shapes.medium
    ) {
        Box {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Título: ${reminder.title}", style = MaterialTheme.typography.titleMedium)
                Text("Descripción: ${reminder.description}", style = MaterialTheme.typography.bodyMedium)
                Text("Fecha: ${reminder.date}", style = MaterialTheme.typography.bodySmall)
                reminder.imagePath?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                }
            }

            // Botón de eliminar en la esquina superior derecha
            IconButton(
                onClick = { onDelete(reminder) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
