package com.example.mobileprogrammingproject.model.data.repository.mappers

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class FirestorePlaylist(
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val description: String = ""
)

class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val playlistsCollection = firestore.collection("playlists")

    // GET
    fun getPlaylistsForUser(userId: String): Flow<List<FirestorePlaylist>> = callbackFlow {
        val subscription = playlistsCollection
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val playlists = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(FirestorePlaylist::class.java)?.copy(id = doc.id)
                } ?: emptyList()
                trySend(playlists)
            }
        awaitClose { subscription.remove() }
    }

    // POST
    suspend fun createPlaylist(userId: String, name: String, description: String): Result<String> {
        return try {
            val playlist = hashMapOf(
                "userId" to userId,
                "name" to name,
                "description" to description
            )
            val doc = playlistsCollection.add(playlist).await()
            Result.success(doc.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // PUT
    suspend fun updatePlaylist(id: String, name: String, description: String): Result<Unit> {
        return try {
            playlistsCollection.document(id).update(
                mapOf("name" to name, "description" to description)
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // DELETE
    suspend fun deletePlaylist(id: String): Result<Unit> {
        return try {
            playlistsCollection.document(id).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}