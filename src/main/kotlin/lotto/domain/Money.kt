package lotto.domain

class Money(val price: Long) {
    init {
        require(price >= 0) { "구매금액을 양수로 입력해주세요." }
    }

    fun divideBy(unit: Int): Int {
        return (price / unit).toInt()
    }
}
