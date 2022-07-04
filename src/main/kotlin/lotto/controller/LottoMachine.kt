package lotto.controller

import lotto.view.View
import kotlin.math.floor

class LottoMachine {
    val view = View()
    var money = 0
    var count = 0
    val lottoNumbers = mutableListOf<Int>()
    val lottos = mutableListOf<List<Int>>()
    lateinit var winningLotto: List<Int>

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

        winningLotto = view.inputWinningLotto()

        val result = calculateResults().reversed()
        val ratio = calculateRatio(result)
        view.printResult(result, ratio)
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

    private fun calculateResults(): List<Int> {
        var first = 0
        var second = 0
        var third = 0
        var fourth = 0

        lottos.forEach {
            when (getWinningCount(it)) {
                6 -> {first++}
                5 -> {second++}
                4 -> {third++}
                3 -> {fourth++}
            }
        }

        return listOf(first, second, third, fourth)
    }

    private fun getWinningCount(lotto: List<Int>): Int {
        val winningNumbers = (lotto + winningLotto).groupBy { it }.filter { it.value.size > 1 }.flatMap { it.value }.distinct()
        return winningNumbers.size
    }

    private fun calculateRatio(result: List<Int>): Double {
        val winnings = 5_000 * result[0] + 50_000 * result[1] + 1_500_000 * result[2] + 2_000_000_000 * result[3]
        return floor((winnings.toDouble() / money.toDouble())*100) /100
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val LOTTO_DIGIT = 6
    }
}
