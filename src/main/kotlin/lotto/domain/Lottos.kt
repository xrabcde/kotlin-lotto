package lotto.domain

class Lottos(count: Int) {
    val values = mutableListOf<Lotto>()

    init {
        repeat(count) {
            values.add(Lotto.auto())
        }
    }

    fun addManualLotto(lotto: Lotto) {
        values.add(lotto)
    }
}
