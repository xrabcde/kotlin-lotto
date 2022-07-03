package calculator

class Calculator {
    private val separator = StringSeparator()

    fun calculate(input: String): Int {
        val extractedNumbers = separator.separate(input)
        val numbers = extractedNumbers.map { convertNumber(it) }
        return doAddition(numbers)
    }

    private fun convertNumber(input: String): Int {
        return runCatching { input.toInt() }
            .onFailure { throw RuntimeException("숫자 이외의 값은 입력하실 수 없습니다.") }
            .getOrThrow()
    }

    private fun doAddition(numbers: List<Int>): Int {
        validateNegativeNumber(numbers)
        return numbers.sumOf { it }
    }

    private fun validateNegativeNumber(inputs: List<Int>) {
        if (inputs.any { it < 0 }) {
            throw RuntimeException("음수는 입력하실 수 없습니다.")
        }
    }
}
