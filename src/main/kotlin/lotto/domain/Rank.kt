package lotto.domain

enum class Rank(val count: Int, val price: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NONE(0, 0);

    companion object {
        fun findByCount(count: Int): Rank {
            return values().find { it.count == count } ?: NONE
        }
    }
}
