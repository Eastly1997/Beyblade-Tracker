package com.lkby.common.util

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

internal class NanoIdProvider : IdProvider {
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()

    override fun generateId(length: Int): String {
        return NanoIdUtils.randomNanoId(
            NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
            alphabet,
            length
        )
    }
}