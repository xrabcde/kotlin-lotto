package lotto.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LottoTest : FreeSpec({
    "생성자에 아무것도 넘겨주지 않으면 랜덤(자동)로또를 생성한다." {
        val lotto = Lotto()

        lotto.numbers shouldNotBe null
        lotto.numbers.size shouldBe 6
    }

    "생성자에 로또번호를 넘겨주면 수동로또를 생성한다." {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(numbers)

        lotto.numbers shouldBe listOf(1, 2, 3, 4, 5, 6)
    }

    "6자리가 아닌 숫자를 넘겨주면 예외를 반환한다." {
        val testCases = listOf(
            listOf(1, 2, 3, 4, 5),
            listOf(1, 2, 3, 4, 5, 6, 7)
        )

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(INVALID_DIGIT_MESSAGE) { Lotto(it) }
        }
    }

    "1 ~ 45 사이의 숫자가 아닌 값을 넘겨주면 예외를 반환한다." {
        val testCases = listOf(
            listOf(1, 2, 3, 4, 5, 0),
            listOf(1, 2, 3, 4, 5, 46),
        )

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(INVALID_RANGE_MESSAGE) { Lotto(it) }
        }
    }

    "중복되는 숫자를 넘겨주면 예외를 반환한다." {
        val testCases = listOf(
            listOf(1, 1, 1, 1, 1, 1),
            listOf(1, 1, 2, 3, 4, 5)
        )

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(DUPLICATE_NUMBER_MESSAGE) { Lotto(it) }
        }
    }
}) {
    companion object {
        private const val INVALID_DIGIT_MESSAGE = "로또번호를 6자리로 입력해주세요."
        private const val INVALID_RANGE_MESSAGE = "로또번호를 1 ~ 45 사이로 입력해주세요."
        private const val DUPLICATE_NUMBER_MESSAGE = "로또번호를 중복되지 않게 입력해주세요."
    }
}
