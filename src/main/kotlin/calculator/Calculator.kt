package calculator

class Calculator {
    fun calculate(input: String): Int {
        val numbers = extractNumbers(input)
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

    private fun doAddition(numbers: List<String>): Int {
        try {
            return numbers.sumOf { it.toInt() }
        } catch (e: NumberFormatException) {
            throw RuntimeException("숫자 이외의 값 또는 음수는 입력하실 수 없습니다.")
        }
    }

    companion object {
        private const val CUSTOM_DELIMITER_START_SIGN = "//"
        private const val CUSTOM_DELIMITER_END_SIGN = "\\n"
        private val DEFAULT_DELIMITERS = "[,:]".toRegex()
    }
}
