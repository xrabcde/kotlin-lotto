package calculator

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

private const val NEGATIVE_NUMBER_MESSAGE = "음수는 입력하실 수 없습니다."
private const val NON_INTEGER_MESSAGE = "숫자 이외의 값은 입력하실 수 없습니다."

private val calculator = Calculator()

class CalculatorTest : FreeSpec({
    "기본 구분자를 가지는 문자열을 입력하면 덧셈 결과를 반환한다." {
        val testCases = listOf("1,2,3", "1:2:3", "1,2:3", "1:2,3")
        testCases.forAll {
            calculator.calculate(it) shouldBe 6
        }
    }

    "커스텀 구분자를 가지는 문자열을 입력하면 덧셈 결과를 반환한다." {
        val testCases = listOf("//;\\n1;2;3", "//-\\n1-2-3", "//.\\n1.2.3")
        testCases.forAll {
            calculator.calculate(it) shouldBe 6
        }
    }

    "음수를 입력하면 예외를 반환한다." {
        val testCases = listOf("-1,-2,-3", "-1,-2", "0,1,-10")
        testCases.forAll {
            shouldThrowWithMessage<RuntimeException>(NEGATIVE_NUMBER_MESSAGE) { calculator.calculate(it) }
        }
    }

    "숫자 이외의 값을 입력하면 예외를 반환한다." {
        val testCases = listOf("a,b,c", "ㄱ,ㄴ,ㄷ")
        testCases.forAll {
            shouldThrowWithMessage<RuntimeException>(NON_INTEGER_MESSAGE) { calculator.calculate(it) }
        }
    }
})
