package lotto.domain

enum class Rank(val count: Int, val prize: Money) {
    FIRST(6, Money(2_000_000_000)),
    SECOND(5, Money(1_500_000)),
    THIRD(4, Money(50_000)),
    FOURTH(3, Money(5_000)),
    NONE(0, Money(0));

    fun calculateWinning(winningCount: Int): Long {
        return prize.price * winningCount
    }

    companion object {
        fun findByCount(count: Int): Rank {
            return values().find { it.count == count } ?: NONE
        }
    }
}
