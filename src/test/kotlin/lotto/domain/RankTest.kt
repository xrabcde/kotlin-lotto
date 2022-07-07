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
})
