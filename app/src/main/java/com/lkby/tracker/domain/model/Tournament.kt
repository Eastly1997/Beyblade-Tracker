package com.lkby.tracker.domain.model

data class Tournament(
    val id: String,
    val name: String,
    val createdBy: String,
    val maxParticipants: Int,
    val createdAt: Long
)