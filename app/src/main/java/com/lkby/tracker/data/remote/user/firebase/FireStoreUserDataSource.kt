package com.lkby.tracker.data.remote.user.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.lkby.tracker.data.remote.firestore.FirestoreCollections
import com.lkby.tracker.data.remote.user.UserDataSource
import com.lkby.tracker.data.remote.user.model.UserDto
import kotlinx.coroutines.tasks.await

internal class FireStoreUserDataSource(
    private val firestore: FirebaseFirestore
): UserDataSource {
    override suspend fun createUser(user: UserDto) {
        firestore.collection(FirestoreCollections.USERS)
            .document(user.uid)
            .set(user)
            .await()
    }

    override suspend fun getUser(uid: String): UserDto? {
        val snapshot = firestore.collection(FirestoreCollections.USERS)
            .document(uid)
            .get()
            .await()
        return snapshot.toObject(UserDto::class.java)
    }
}