package lotto.domain

class Lotto() {
    var numbers: List<Int>

    init {
        numbers = generateLotto()
    }

    constructor(input: List<String>) : this() {
        numbers = validateNumbers(input)
    }

    private fun validateNumbers(input: List<String>): List<Int> {
        val numbers = convertNumbers(input)
        validateDuplicate(numbers)
        validateSize(numbers)
        validateRange(numbers)
        return numbers
    }

    private fun convertNumbers(input: List<String>): List<Int> {
        return runCatching {
            input.map { it.toInt() }.toList()
        }.onFailure {
            throw IllegalArgumentException("로또번호를 숫자로 입력해주세요.")
        }.getOrThrow()
    }

    private fun validateDuplicate(input: List<Int>) {
        if (input.size != input.toSet().toList().size) {
            throw IllegalArgumentException("로또번호를 중복되지 않게 입력해주세요.")
        }
    }

    private fun validateSize(input: List<Int>) {
        if (input.size != LOTTO_DIGIT) {
            throw IllegalArgumentException("로또번호를 6자리로 입력해주세요.")
        }
    }

    private fun validateRange(input: List<Int>) {
        input.forEach {
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
