package calculator

class Calculator {
    fun calculate(input: String): Int {
        val numbers = extractNumbers(input)
        return numbers.sumOf { it.toInt() }
    }

    private fun extractNumbers(input: String): List<String> {
        return if (input.startsWith(CUSTOM_DELIMITER_START_SIGN)) {
            extractCustomCase(input)
        } else {
            extractDefaultCase(input)
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

    companion object {
        private const val CUSTOM_DELIMITER_START_SIGN = "//"
        private const val CUSTOM_DELIMITER_END_SIGN = "\\n"
        private val DEFAULT_DELIMITERS = "[,:]".toRegex()
    }
}
