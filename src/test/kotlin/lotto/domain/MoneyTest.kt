package lotto.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class MoneyTest : FreeSpec({
    "구매금액을 양수로 입력하지 않으면 예외를 반환한다." {
        val testCases = listOf(-1000, -291837918, 0)

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(NON_POSITIVE_MESSAGE) { Money(it) }
        }
    }

    "구매금액에 따른 로또의 개수를 반환한다." {
        val testCases = listOf(
            row(1000, 1),
            row(900, 0),
            row(5500, 5),
            row(14000, 14)
        )

        testCases.forEach { (price, count) ->
            Money(price).divideBy(1000) shouldBe count
        }
    }
}) {
    companion object {
        private const val NON_POSITIVE_MESSAGE = "구매금액을 양수로 입력해주세요."
    }
}
