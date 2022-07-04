package calculator

import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

private val separator = StringSeparator()

class SeparatorTest : FreeSpec({
    "기본 구분자를 가지는 경우 문자열 구분 결과를 반환한다." {
        val testCases = listOf("1,2,3", "1:2:3", "1,2:3", "1:2,3")
        testCases.forAll {
            separator.separate(it) shouldBe listOf("1", "2", "3")
        }
    }

    "커스텀 구분자를 가지는 경우 문자열 구분 결과를 반환한다." {
        val testCases = listOf("//;\\n1;2;3", "//-\\n1-2-3", "//.\\n1.2.3")
        testCases.forAll {
            separator.separate(it) shouldBe listOf("1", "2", "3")
        }
    }
})
