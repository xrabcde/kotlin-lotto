package lotto.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class WinningResultTest : FreeSpec({
    beforeSpec {
        val lotto = Lotto.manual(listOf(1, 2, 3, 7, 8, 9))
        lottos.addManualLotto(lotto)
    }

    "구입로또와 당첨로또를 비교해 당첨 통계를 계산한다." {
        val winningResult = WinningResult(lottos, winningLotto)

        winningResult.result.values shouldBe listOf(0, 0, 0, 1, 0)
    }

    "수익률을 계산한다." {
        val winningResult = WinningResult(lottos, winningLotto)

        val testCases = listOf(
            row(Money(1000), 5),
            row(Money(5000), 1),
            row(Money(10000), 0.5)
        )

        testCases.forEach { (money, earning) ->
            winningResult.calculateEarning(money) shouldBe earning
        }
    }
}) {
    companion object {
        private val lottos = Lottos(0)
        private val winningLotto = Lotto.manual(listOf(1, 2, 3, 4, 5, 6))
    }
}
