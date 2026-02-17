package com.lkby.tracker.domain.util

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

internal object IdGenerator {
    private val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()

    private const val LENGTH = 6

    fun generateTournamentId(): String {
        return NanoIdUtils.randomNanoId(
            NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
            ALPHABET,
            LENGTH
        )
    }
}