package lotto.view

import lotto.domain.Lottos
import lotto.domain.Rank

class View {
    fun inputMoney(): Long {
        println("구매금액을 입력해 주세요.")
        return convertNumber(readln()).toLong()
    }

    fun printLottos(lottos: Lottos) {
        println("${lottos.values.size}개를 구매했습니다.")
        lottos.values.forEach { println(it.numbers) }
    }

    fun inputWinningLotto(): List<Int> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        val input = readln().split(",").map { it.trim() }
        return input.map { convertNumber(it) }.toList()
    }

    fun printResult(results: Map<Rank, Int>) {
        println("\n당첨 통계")
        println("----------")
        Rank.values().reversed().forEach {
            if (it.count >= 3) println("${it.count}개 일치 (${it.prize.price}원)- ${results[it]}개")
        }
    }

    fun printEarning(earning: Double) {
        println("총 수익률은 ${earning}입니다.")
    }

    private fun convertNumber(input: String): Int {
        return runCatching {
            input.toInt()
        }.onFailure {
            throw IllegalArgumentException("숫자로 입력해주세요.")
        }.getOrThrow()
    }
}
