package com.lkby.tracker.data.remote.tournament.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.lkby.tracker.data.remote.firestore.FirestoreCollections
import com.lkby.tracker.data.remote.tournament.TournamentDataSource
import com.lkby.tracker.data.remote.tournament.model.TournamentDto
import kotlinx.coroutines.tasks.await

internal class FirebaseTournamentDataSource(
    private val firestore: FirebaseFirestore
): TournamentDataSource {

    override suspend fun createTournamentWithUniqueId(tournament: TournamentDto): Boolean {
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
}