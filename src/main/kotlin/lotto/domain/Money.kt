package lotto.domain

class Money(input: String) {
    val price: Int

    init {
        price = validateMoney(input)
    }

    fun getLottoCount(): Int {
        return price / LOTTO_PRICE
    }

    private fun validateMoney(input: String): Int {
        return validatePositive(convertNumber(input))
    }

    private fun convertNumber(input: String): Int {
        return runCatching {
            input.toInt()
        }.onFailure {
            throw IllegalArgumentException("구매금액을 숫자로 입력해주세요.")
        }.getOrThrow()
    }

    private fun validatePositive(input: Int): Int {
        when (input > 0) {
            true -> return input
            false -> throw IllegalArgumentException("구매금액을 양수로 입력해주세요.")
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
