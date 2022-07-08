package lotto.domain

class Money(input: String) {
    val price: Int

    init {
        price = convertNumber(input)
        require(price > 0) { "구매금액을 양수로 입력해주세요." }
    }

    fun divideBy(unit: Int): Int {
        return price / unit
    }

    private fun convertNumber(input: String): Int {
        return runCatching {
            input.toInt()
        }.onFailure {
            throw IllegalArgumentException("구매금액을 숫자로 입력해주세요.")
        }.getOrThrow()
    }
}
