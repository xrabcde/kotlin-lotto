package lotto.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LottosTest : FreeSpec({
    "구매 금액만큼의 자동로또를 생성한다." {
        val lottos = Lottos.buyWith(Money(3000))

        lottos.values shouldNotBe null
        lottos.values.size shouldBe 3
    }

    "당첨로또를 통해 당첨통계를 계산한다." {
        val lottos = Lottos.buyWith(Money(0))
        val lotto = Lotto.manual(listOf(1, 2, 3, 4, 5, 6))
        lottos.addManualLotto(lotto)

        val testCases = listOf(
            row(listOf(1, 2, 3, 7, 8, 9), listOf(0, 0, 0, 1, 0)),
            row(listOf(1, 2, 3, 4, 8, 9), listOf(0, 0, 1, 0, 0)),
            row(listOf(1, 2, 3, 4, 5, 9), listOf(0, 1, 0, 0, 0)),
            row(listOf(1, 2, 3, 4, 5, 6), listOf(1, 0, 0, 0, 0))
        )

        testCases.forEach { (winningNumbers, result) ->
            val winningLotto = Lotto.manual(winningNumbers)
            val winningResult = lottos.calculateResult(winningLotto)
            winningResult.result.values shouldBe result
        }
    }
})
