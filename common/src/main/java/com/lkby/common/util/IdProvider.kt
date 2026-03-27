package com.lkby.common.util

interface IdProvider {
    fun generateId(length: Int = 21): String
}