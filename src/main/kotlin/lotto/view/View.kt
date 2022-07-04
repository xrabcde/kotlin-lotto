package lotto.view

class View {
    fun inputMoney(): String {
        println("구매금액을 입력해 주세요.")
        return readln()
    }

    fun printLottoCount(count: Int) {
        println("${count}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<List<Int>>) {
        lottos.forEach { println(it) }
    }

    fun inputWinningLotto(): List<Int> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return readln().split(",").map { it.trim().toInt() }
    }

    fun printResult(results: List<Int>) {
        println("\n당첨 통계")
        println("----------")
        println("3개 일치 (5,000원)- ${results[0]}개")
        println("4개 일치 (50,000원)- ${results[1]}개")
        println("5개 일치 (1,500,000원)- ${results[2]}개")
        println("6개 일치 (2,000,000,000원)- ${results[3]}개")
    }
}
