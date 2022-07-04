package lotto.controller

import lotto.view.View

class LottoMachine {
    val view = View()
    var money = 0

    fun operate() {
        money = validateMoney(view.inputMoney())
    }

    fun validateMoney(input: String): Int {
        return runCatching {
            input.toInt()
        }.onFailure {
            throw IllegalArgumentException("구매금액을 숫자로 입력해주세요.")
        }.getOrThrow()
    }
}
