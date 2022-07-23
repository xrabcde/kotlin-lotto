package lotto.domain

class Lotto private constructor(val numbers: Set<Int>) {
    init {
        validateSize()
        validateRange()
    }

    private fun validateSize() {
        if (numbers.size != LOTTO_DIGIT) {
            throw IllegalArgumentException("로또번호를 중복되지 않은 6자리로 입력해주세요.")
        }
    }

    private fun validateRange() {
        numbers.forEach {
            require(it in LOTTO_NUMBER_RANGE) { "로또번호를 1 ~ 45 사이로 입력해주세요." }
        }
    }

    fun howManyMatchesWith(other: Lotto): Int {
        val nonMatchingValue = numbers.minus(other.numbers)
        return LOTTO_DIGIT - nonMatchingValue.size
    }

    companion object {
        private const val LOTTO_DIGIT = 6
        private val LOTTO_NUMBER_RANGE: IntRange = (1..45)
        private val CACHE_LOTTO_NUMBERS: List<Int> = List(45) { it + 1 }

        fun auto() = Lotto(generateLotto())
        fun manual(numbers: List<Int>) = Lotto(numbers.toSet())

        private fun generateLotto(): Set<Int> {
            return CACHE_LOTTO_NUMBERS.shuffled().take(LOTTO_DIGIT).sorted().toSet()
        }
    }
}
