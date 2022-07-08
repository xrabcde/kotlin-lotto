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
            val rank = getWinningCount(it.numbers, winningLotto.numbers)
            result[rank] = result[rank]!! + 1
        }
    }

    private fun getWinningCount(lotto: List<Int>, winningLotto: List<Int>): Rank {
        val winningNumbers =
            (lotto + winningLotto).groupBy { it }.filter { it.value.size > 1 }.flatMap { it.value }.distinct()
        return Rank.findByCount(winningNumbers.size)
    }
}
