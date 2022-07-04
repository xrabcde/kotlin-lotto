package calculator

class StringSeparator {
    fun separate(input: String): List<String> {
        return when (isCustomCase(input)) {
            true -> customCase(input)
            false -> defaultCase(input)
        }
    }

    private fun isCustomCase(input: String): Boolean {
        return input.startsWith(CUSTOM_DELIMITER_START_SIGN)
    }

    private fun defaultCase(input: String): List<String> {
        return input.split(DEFAULT_DELIMITERS).map { it.trim() }
    }

    private fun customCase(input: String): List<String> {
        val delimiter = input.substringAfter(CUSTOM_DELIMITER_START_SIGN).substringBefore(CUSTOM_DELIMITER_END_SIGN)
        val numbers = input.substringAfter(CUSTOM_DELIMITER_END_SIGN)
        return numbers.split(delimiter).map { it.trim() }
    }

    companion object {
        private const val CUSTOM_DELIMITER_START_SIGN = "//"
        private const val CUSTOM_DELIMITER_END_SIGN = "\\n"
        private val DEFAULT_DELIMITERS = "[,:]".toRegex()
    }
}
