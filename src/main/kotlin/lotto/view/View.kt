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
}
