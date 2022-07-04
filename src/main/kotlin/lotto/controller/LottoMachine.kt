package lotto.controller

import lotto.view.View

class LottoMachine {
    val view = View()
    var money = 0

    fun operate() {
        money = view.inputMoney().toInt()
    }
}
