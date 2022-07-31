package lotto.domain

import kotlin.math.floor

class WinningResult(val result: Map<Rank, Int>) {
    fun calculateEarning(money: Money): Double {
        val totalWinning = result.map { it.key.calculateWinning(it.value) }.sum()
        return floor((totalWinning.toDouble() / money.price.toDouble()) * 100) / 100
    }
}
