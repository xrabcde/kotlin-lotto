package calculator

class Calculator {
    fun calculate(input: String): Int {
        val numbers = extractNumbers(input)
        return numbers.sumOf { it.toInt() }
    }
}
