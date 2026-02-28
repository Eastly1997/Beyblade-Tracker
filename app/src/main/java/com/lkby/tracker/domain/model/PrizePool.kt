package com.lkby.tracker.domain.model

data class PrizePool(
    val type: PrizeType,
    val rules: List<PrizeRule>
)

enum class PrizeType {
    FIXED,
    DYNAMIC,
    OTHER
}

data class PrizeRule(
    val minPlayers: Int,
    val maxPlayers: Int?,
    val prizes: List<Prize>
)

data class Prize(
    val placement: Placement,
    val name: String
)

enum class Placement {
    FIRST,
    SECOND,
    THIRD,
    OTHER
}