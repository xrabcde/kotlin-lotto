package lotto.controller

import lotto.view.View

class LottoMachine {
    val view = View()
    var money = 0
    var count = 0
    val lottoNumbers = mutableListOf<Int>()
    val lottos = mutableListOf<List<Int>>()

    init {
        for (i in 1..45) {
            lottoNumbers.add(i)
        }
    }

    fun operate() {
        money = validateMoney(view.inputMoney())
        count = money / LOTTO_PRICE
        view.printLottoCount(count)

        generateLottos()
        view.printLottos(lottos)
    }

    fun validateMoney(input: String): Int {
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

    private fun generateLottos() {
        repeat(count) {
            val lotto = lottoNumbers.shuffled().subList(0, LOTTO_DIGIT).sorted()
            lottos.add(lotto)
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val LOTTO_DIGIT = 6
    }
}
