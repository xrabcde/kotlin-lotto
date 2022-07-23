package lotto.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LottosTest : FreeSpec({
    "구매 금액을 인자로 넘겨주면 금액만큼의 자동로또를 생성한다." {
        val lottos = Lottos.buyWith(Money(3000))

        lottos.values shouldNotBe null
        lottos.values.size shouldBe 3
    }
})
