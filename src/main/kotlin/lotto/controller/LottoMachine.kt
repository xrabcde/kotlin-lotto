package lotto.controller

import lotto.domain.*
import lotto.view.View

class LottoMachine {
    private val view = View()

    fun operate() {
        val money = Money(view.inputMoney())
        val lottos = Lottos.buyWith(money)
        view.printLottos(lottos)

        val winningLotto = Lotto.manual(view.inputWinningLotto())
        val winningResult = lottos.calculateResult(winningLotto)
        view.printResult(winningResult.result)

        val earning = winningResult.calculateEarning(money)
        view.printEarning(earning)
    }
}
