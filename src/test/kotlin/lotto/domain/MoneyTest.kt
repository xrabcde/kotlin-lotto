package lotto.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class MoneyTest : FreeSpec({
    "구매금액을 양수로 입력하지 않으면 예외를 반환한다." {
        val testCases = listOf(-1000L, -291837918L)

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(NON_POSITIVE_MESSAGE) { Money(it) }
        }
    }

    "구매금액에 따른 로또의 개수를 반환한다." {
        val testCases = listOf(
            row(1000L, 1),
            row(900L, 0),
            row(5500L, 5),
            row(14000L, 14)
        )

        testCases.forEach { (price, count) ->
            Money(price).divideBy(LOTTO_PRICE) shouldBe count
        }
    }
}) {
    companion object {
        private const val NON_POSITIVE_MESSAGE = "구매금액을 양수로 입력해주세요."

        private val LOTTO_PRICE = Money(1000)
    }
}
