package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestorePlaylist

@Composable
fun EditFirestorePlaylistDialog(
    playlist: FirestorePlaylist,
    onConfirm: (name: String, description: String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf(playlist.name) }
    var description by remember { mutableStateOf(playlist.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Playlist") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, description) }) { Text("Save") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}