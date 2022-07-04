package lotto

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import lotto.controller.LottoMachine

private const val NON_INTEGER_MESSAGE = "구매금액을 숫자로 입력해주세요."
private const val NON_POSITIVE_MESSAGE = "구매금액을 양수로 입력해주세요."

private val lottoMachine = LottoMachine()

class LottoTest : FreeSpec({
    "구매금액을 숫자로 입력하지 않으면 예외를 반환한다." {
        val testCases = listOf("bada", "바다", ">0<")
        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(NON_INTEGER_MESSAGE) { lottoMachine.validateMoney(it) }
        }
    }

    "구매금액을 양수로 입력하지 않으면 예외를 반환한다." {
        val testCases = listOf("-1000", "-291837918", "0")
        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(NON_POSITIVE_MESSAGE) { lottoMachine.validateMoney(it) }
        }
    }
})
