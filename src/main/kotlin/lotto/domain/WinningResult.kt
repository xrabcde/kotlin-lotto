package lotto.domain

import kotlin.math.floor

class WinningResult(lottos: Lottos, winningLotto: Lotto) {
    val result: MutableMap<Rank, Int> = mutableMapOf()

    init {
        Rank.values().forEach {
            result[it] = 0
        }
        calculateRank(lottos, winningLotto)
    }

    fun calculateEarning(money: Money): Double {
        val totalWinning = result.map { it.key.calculateWinning(it.value) }.sum()
        return floor((totalWinning.toDouble() / money.price.toDouble()) * 100) / 100
    }

    private fun calculateRank(lottos: Lottos, winningLotto: Lotto) {
        lottos.values.forEach {
            val matchingCount = winningLotto.howManyMatchesWith(it)
            val rank = Rank.findByCount(matchingCount)
            when(val tmp = result[rank]) {
                null -> result[rank] = 1
                else -> result[rank] = tmp + 1
            }
        }
    }
}
