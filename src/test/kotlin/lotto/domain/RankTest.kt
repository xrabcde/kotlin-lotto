package lotto.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RankTest : FreeSpec({
    "개수에 맞는 등수를 찾아 반환한다." {
        val testCases = listOf(
            row(6, Rank.FIRST),
            row(5, Rank.SECOND),
            row(4, Rank.THIRD),
            row(3, Rank.FOURTH),
            row(2, Rank.NONE),
            row(1, Rank.NONE),
            row(0, Rank.NONE)
        )

        testCases.forEach { (count, rank) ->
            Rank.findByCount(count) shouldBe rank
        }
    }

    "당첨개수에 대한 당첨금을 계산한다." {
        val testCases = listOf(
            row(Rank.FIRST, 4_000_000_000),
            row(Rank.SECOND, 3_000_000),
            row(Rank.THIRD, 100_000),
            row(Rank.FOURTH, 10_000),
            row(Rank.NONE, 0)
        )

        testCases.forEach { (rank, winnings) ->
            rank.calculateWinning(2) shouldBe winnings
        }
    }
})
