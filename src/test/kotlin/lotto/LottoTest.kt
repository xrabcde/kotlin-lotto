package lotto

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import lotto.controller.LottoMachine

private const val MONEY_ERROR_MESSAGE = "구매금액을 숫자로 입력해주세요."

private val lottoMachine = LottoMachine()

class LottoTest : FreeSpec({
    "구매금액을 숫자로 입력하지 않으면 예외를 반환한다." {
        val testCases = listOf("bada", "바다", ">0<")
        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(MONEY_ERROR_MESSAGE) { lottoMachine.validateMoney(it) }
        }
    }
})
