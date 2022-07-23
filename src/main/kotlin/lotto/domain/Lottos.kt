package lotto.domain

class Lottos private constructor(var values: List<Lotto>) {
    fun calculateResult(winningLotto: Lotto): WinningResult {
        var result = Rank.values().associateWith { 0 }.toMap()

        values.forEach {
            val rank = calculateRank(it, winningLotto)
            val newValue = result.getOrDefault(rank, 0) + 1

            result = result.plus(Pair(rank, newValue))
        }

        return WinningResult(result)
    }

    fun addManualLotto(lotto: Lotto) {
        values = values.plus(lotto)
    }

    private fun calculateRank(lotto: Lotto, winningLotto: Lotto): Rank {
        val matchingCount = winningLotto.howManyMatchesWith(lotto)
        return Rank.findByCount(matchingCount)
    }

    companion object {
        private const val LOTTO_PRICE = 1000

        fun buyWith(money: Money) = Lottos(generateLottos(money))

        private fun generateLottos(money: Money): List<Lotto> {
            val values = mutableListOf<Lotto>()
            repeat(money.divideBy(LOTTO_PRICE)) {
                values.add(Lotto.auto())
            }
            return values
        }
    }
}
