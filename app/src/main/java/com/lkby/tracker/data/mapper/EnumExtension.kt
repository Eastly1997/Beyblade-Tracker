package com.lkby.tracker.data.mapper

import android.util.Log

internal inline fun <reified T : Enum<T>> safeEnumValueOf(
    value: String,
    default: T
): T {
    return runCatching {
        enumValueOf<T>(value)
    }.getOrElse {
        val type = T::class.java.simpleName
        Log.e(type, "invalid value of $type: $value -> $default")
        default
    }
}