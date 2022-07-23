package lotto.domain

class Lotto(numbers: List<Int> = generateLotto()) {
    val numbers: Set<Int>

    init {
        this.numbers = convertToSet(numbers)
        validateNumbers(this.numbers)
    }

    private fun convertToSet(numbers: List<Int>): Set<Int> {
        if (numbers.size != numbers.toSet().size) {
            throw IllegalArgumentException("로또번호를 중복되지 않게 입력해주세요.")
        }
        return numbers.toSet()
    }

    private fun validateNumbers(numbers: Set<Int>) {
        validateSize(numbers)
        validateRange(numbers)
    }

    private fun validateSize(numbers: Set<Int>) {
        if (numbers.size != LOTTO_DIGIT) {
            throw IllegalArgumentException("로또번호를 6자리로 입력해주세요.")
        }
    }

    private fun validateRange(numbers: Set<Int>) {
        numbers.forEach {
            require(it in LOTTO_NUMBER_RANGE) { "로또번호를 1 ~ 45 사이로 입력해주세요." }
        }
    }

    companion object {
        private const val LOTTO_DIGIT = 6
        private val LOTTO_NUMBER_RANGE: IntRange = (1..45)
        private val CACHE_LOTTO_NUMBERS: List<Int> = List(45) { it + 1 }

        private fun generateLotto(): List<Int> {
            return CACHE_LOTTO_NUMBERS.shuffled().subList(0, LOTTO_DIGIT).sorted()
        }
    }
}
