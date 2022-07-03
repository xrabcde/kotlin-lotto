package calculator

class Calculator {
    fun calculate(input: String): Int {
        val extractedNumbers = extractNumbers(input)
        val numbers = extractedNumbers.map { convertNumber(it) }
        return doAddition(numbers)
    }

    private fun extractNumbers(input: String): List<String> {
        return when (input.startsWith(CUSTOM_DELIMITER_START_SIGN)) {
            true -> extractCustomCase(input)
            false -> extractDefaultCase(input)
        }
    }

    private fun extractCustomCase(input: String): List<String> {
        val customDelimiter = input.substringAfter(CUSTOM_DELIMITER_START_SIGN).substringBefore(CUSTOM_DELIMITER_END_SIGN)
        val numbers = input.substringAfter(CUSTOM_DELIMITER_END_SIGN)
        return numbers.split(customDelimiter).map { it.trim() }
    }

    private fun extractDefaultCase(input: String): List<String> {
        return input.split(DEFAULT_DELIMITERS).map { it.trim() }
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

    companion object {
        private const val CUSTOM_DELIMITER_START_SIGN = "//"
        private const val CUSTOM_DELIMITER_END_SIGN = "\\n"
        private val DEFAULT_DELIMITERS = "[,:]".toRegex()
    }
}
