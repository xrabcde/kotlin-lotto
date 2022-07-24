package lotto.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class WinningResultTest : FreeSpec({
    "수익률을 계산한다." {
        val lottos = Lottos.buyWith(Money(0))
        val lotto = Lotto.manual(listOf(1, 2, 3, 7, 8, 9))
        lottos.addManualLotto(lotto)

        val winningLotto = Lotto.manual(listOf(1, 2, 3, 4, 5, 6))
        val winningResult = lottos.calculateResult(winningLotto)

        val testCases = listOf(
            row(Money(1000), 5),
            row(Money(5000), 1),
            row(Money(10000), 0.5)
        )

        testCases.forEach { (money, earning) ->
            winningResult.calculateEarning(money) shouldBe earning
        }
    }
})
