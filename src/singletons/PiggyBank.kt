package singletons

object PiggyBank {

    val moneys: ArrayList<Money> = ArrayList()
    var isBroken: Boolean = false

    fun putMoney(money: Money) {
        if (isBroken) {
            println("Вы разбили копилку, вы больше ничего положить туда не можете")
            return
        } else {
            moneys.add(money)
            println("Добавлено в копилку $money")
        }
    }

    fun shake(): Money? {
        if (isBroken) {
            println("Вы разбили копилку, больше оттуда ничего не вытрясти")
            return null
        }
        val iterator = moneys.iterator()
        while (iterator.hasNext()) {
            val money = iterator.next()
            if (money.isCoin) {
                iterator.remove()
                return money
            }
        }
        return null
    }

    fun smash(): ArrayList<Money> {
        isBroken = true
        println("Копилка разбита, вы достали оттуда: $moneys")
        val result = ArrayList(moneys)
        moneys.clear()
        return result
    }
}

// создайте класс Singletons.Money, который будет отражать сущность монетки/купюры с двумя полями: amount и isCoin
class Money private constructor(val amount: Float, val isCoin: Boolean) {
    override fun toString(): String {
        return if (isCoin) {
            when (amount) {
                0.1f -> "10 коп."
                0.5f -> "50 коп."
                1f -> "1 руб."
                else -> throw IllegalArgumentException("Invalid coin amount")
            }
        } else {
            "${amount.toInt()} руб."
        }
    }

    companion object {
        val coins_10 = Money(0.1f, true)
        val coins_50 = Money(0.5f, true)
        val coins_100 = Money(1f, true)
        val bill_50 = Money(50f, false)
        val bill_100 = Money(100f, false)
        val bill_500 = Money(500f, false)
        val bill_1000 = Money(1000f, false)
    }
}
