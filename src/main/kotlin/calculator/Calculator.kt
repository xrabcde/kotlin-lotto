package calculator

class Calculator {
    fun calculate(input: String): Int {
        val numbers = extractNumbers(input)
        return numbers.sumOf { it.toInt() }
    }

    private fun extractNumbers(input: String): List<String> {
        return input.split(DEFAULT_DELIMITERS).map { it.trim() }
    }

    companion object {
        private val DEFAULT_DELIMITERS = "[,:]".toRegex()
    }
}
