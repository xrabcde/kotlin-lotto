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
            val rank = getWinningRank(it.numbers, winningLotto.numbers)
            result[rank] = result[rank]!! + 1
        }
    }

    private fun getWinningRank(lotto: Set<Int>, winningLotto: Set<Int>): Rank {
        val winningCount = LOTTO_DIGIT - winningLotto.minus(lotto).size
        return Rank.findByCount(winningCount)
    }

    companion object {
        private const val LOTTO_DIGIT = 6
    }
}
