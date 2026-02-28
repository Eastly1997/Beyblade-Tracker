package com.lkby.tracker.data.remote.tournament

import com.google.firebase.firestore.FirebaseFirestore
import com.lkby.tracker.data.remote.firestore.FirestoreCollections
import com.lkby.tracker.data.remote.tournament.model.TournamentDto
import kotlinx.coroutines.tasks.await

internal class FirebaseTournamentDataSource(
    private val firestore: FirebaseFirestore
): TournamentDataSource {

    override suspend fun createTournament(tournament: TournamentDto): Boolean {
        return firestore.runTransaction { transaction ->
            val docRef = firestore
                .collection(FirestoreCollections.TOURNAMENTS)
                .document(tournament.id)

            val snapshot = transaction.get(docRef)

            if(snapshot.exists()) {
                return@runTransaction false
            }

            transaction.set(docRef, tournament)

            true
        }.await()
    }

    override suspend fun updateTournament(tournament: TournamentDto): Boolean {
        val docRef = firestore
            .collection(FirestoreCollections.TOURNAMENTS)
            .document(tournament.id)

        val snapshot = docRef.get().await()

        if (!snapshot.exists())
            return false

        docRef.set(tournament).await()

        return true
    }

    override suspend fun getTournamentById(id: String): TournamentDto? {
        val snapshot = firestore
            .collection("tournaments")
            .document(id)
            .get()
            .await()

        if (!snapshot.exists())
            return null

        return snapshot.toObject(
            TournamentDto::class.java
        )
    }
}