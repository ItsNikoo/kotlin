abstract class Vehicle(var mark: String = "", var model: String = "", var year: Int = 0) {
    abstract fun displayInfo(): String
    abstract fun calculateFuelCost(mileage: Int): Int
}

class Truck(mark: String = "", model: String = "", year: Int = 0, var load: Int = 0) : Vehicle(mark, model, year) {
    override fun displayInfo(): String {
        return "Грузовик $mark $model $year года выпуска с грузоподъемностью $load килограмм"
    }

    override fun calculateFuelCost(mileage: Int): Int {
        return mileage * 50 // базовая стоимость
    }

    fun calculateFuelCost(mileage: Int, loaded: Int): Int {
        if (loaded < load) {
            return (mileage * 50 + loaded * 0.5).toInt()
        } else {
            return -1
        }
    }
}

class Taxi(mark: String = "", model: String = "", year: Int = 0, var fuelConsumption: Int = 0) :
    Vehicle(mark, model, year) {
    override fun displayInfo(): String {
        return "Такси $mark $model $year года выпуска (расход: $fuelConsumption/100км)"
    }

    override fun calculateFuelCost(mileage: Int): Int {
        // Цена за литр топлива (руб)
        val fuelPrice = 50

        // Расчет расхода топлива на заданное расстояние
        // fuelConsumption л/100км = (fuelConsumption / 100) л/1км
        val fuelUsed = (fuelConsumption * mileage) / 100.0

        // Стоимость топлива
        val fuelCost = fuelUsed * fuelPrice

        // Добавляем надбавку в зависимости от расстояния
        return if (mileage > 20) {
            (fuelCost + 1000).toInt()
        } else {
            (fuelCost + 500).toInt()
        }
    }

}

fun main() {
    val gazel = Truck("Газель", "Гигачад", 2024, 10000)
    println(gazel.displayInfo())

    val loaded = 5898
    val priceForGazel = gazel.calculateFuelCost(100, 5898)
    if (priceForGazel > -1) println("Цена за перевозку на ${gazel.mark} ${gazel.model} ${loaded} килограмм стоит $priceForGazel рублей")

    val taxi = Taxi("Mercedes-Benz", "GLS Class", 2021, 17)
    println(taxi.displayInfo())

    val mileage = 19
    val priceForTaxi = taxi.calculateFuelCost(mileage)
    println("Цена за поездку на такси ${taxi.model} ${taxi.mark} на $mileage километров стоит $priceForTaxi рубль")
}