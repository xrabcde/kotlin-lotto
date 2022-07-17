package lotto.domain

class Lotto() {
    var numbers: List<Int>

    init {
        numbers = generateLotto()
    }

    constructor(input: List<String>) : this() {
        numbers = convertNumbers(input)
        validateNumbers()
    }

    private fun convertAfterValidate(numbers: List<Int>): Set<Int> {
        validateSize(numbers)
        validateRange(numbers)
        return convertToSet(numbers)
    }

    private fun validateDuplicate() {
        if (numbers.size != numbers.toSet().size) {
            throw IllegalArgumentException("로또번호를 중복되지 않게 입력해주세요.")
        }
    }

    private fun validateSize() {
        if (numbers.size != LOTTO_DIGIT) {
            throw IllegalArgumentException("로또번호를 6자리로 입력해주세요.")
        }
    }

    private fun validateRange() {
        numbers.forEach {
            require(it in LOTTO_NUMBER_RANGE) { "로또번호를 1 ~ 45 사이로 입력해주세요." }
        }
    }

    companion object {
        private const val LOTTO_DIGIT = 6
        private val LOTTO_NUMBER_RANGE: IntRange = (1..45)
        private val CACHE_LOTTO_NUMBERS: List<Int> = List(45) { it + 1 }

        fun generateLotto(): List<Int> {
            return CACHE_LOTTO_NUMBERS.shuffled().subList(0, 6).sorted()
        }
    }
}
