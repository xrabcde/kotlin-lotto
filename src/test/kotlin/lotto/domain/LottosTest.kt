package lotto.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LottosTest : FreeSpec({
    "로또 개수를 인자로 넘겨주면 개수만큼의 자동로또를 생성한다." {
        val lottos = Lottos(3)

        lottos.values shouldNotBe null
        lottos.values.size shouldBe 3
    }
})
