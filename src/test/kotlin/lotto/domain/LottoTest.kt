package lotto.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LottoTest : FreeSpec({
    "자동 로또를 생성한다." {
        val lotto = Lotto.auto()

        lotto.numbers shouldNotBe null
        lotto.numbers.size shouldBe 6
    }

    "수동 로또를 생성한다." {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto.manual(numbers)

        lotto.numbers shouldBe listOf(1, 2, 3, 4, 5, 6)
    }

    "6자리가 아닌 숫자를 넘겨주면 예외를 반환한다." {
        val testCases = listOf(
            listOf(1, 2, 3, 4, 5),
            listOf(1, 2, 3, 4, 5, 6, 7)
        )

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(INVALID_DIGIT_MESSAGE) { Lotto.manual(it) }
        }
    }

    "1 ~ 45 사이의 숫자가 아닌 값을 넘겨주면 예외를 반환한다." {
        val testCases = listOf(
            listOf(1, 2, 3, 4, 5, 0),
            listOf(1, 2, 3, 4, 5, 46),
        )

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(INVALID_RANGE_MESSAGE) { Lotto.manual(it) }
        }
    }

    "중복되는 숫자를 넘겨주면 예외를 반환한다." {
        val testCases = listOf(
            listOf(1, 1, 1, 1, 1, 1),
            listOf(1, 1, 2, 3, 4, 5)
        )

        testCases.forAll {
            shouldThrowWithMessage<IllegalArgumentException>(INVALID_DIGIT_MESSAGE) { Lotto.manual(it) }
        }
    }
}) {
    companion object {
        private const val INVALID_DIGIT_MESSAGE = "로또번호를 중복되지 않은 6자리로 입력해주세요."
        private const val INVALID_RANGE_MESSAGE = "로또번호를 1 ~ 45 사이로 입력해주세요."
    }
}
