package lotto.controller

import lotto.domain.*
import lotto.view.View

class LottoMachine {
    private val view = View()

    fun operate() {
        val money = Money(view.inputMoney())
        val count = money.getLottoCount()
        view.printLottoCount(count)

        val lottos = Lottos(count)
        view.printLottos(lottos)

        val winningLotto = Lotto(view.inputWinningLotto())

        val winningResult = WinningResult(lottos, winningLotto)
        view.printResult(winningResult.result)
        val earning = winningResult.calculateEarning(money)
        view.printEarning(earning)
    }
}
