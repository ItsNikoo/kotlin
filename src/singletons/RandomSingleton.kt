package singletons// ========================================
// ЗАДАНИЕ: Система управления заказами
// ========================================
// Создайте класс Singletons.Order с companion object
//
// Требования:
// 1. Класс Singletons.Order должен иметь:
//    - id: String
//    - customerName: String
//    - totalAmount: Double
//    - status: String
//
// 2. Конструктор должен быть приватным
//
// 3. В companion object определите константы:
//    - STATUS_PENDING = "Pending"
//    - STATUS_PROCESSING = "Processing"
//    - STATUS_SHIPPED = "Shipped"
//    - STATUS_DELIVERED = "Delivered"
//    - STATUS_CANCELLED = "Cancelled"
//    - MIN_ORDER_AMOUNT = 100.0
//    - MAX_ORDER_AMOUNT = 100000.0
//
// 4. В companion object создайте методы:
//    a) createOrder(id: String, customerName: String, amount: Double): Singletons.Order?
//       - Проверяет, что customerName не пустой
//       - Проверяет, что amount в диапазоне MIN_ORDER_AMOUNT..MAX_ORDER_AMOUNT
//       - Если валидация прошла - создает заказ со статусом STATUS_PENDING
//       - Если нет - возвращает null и выводит сообщение об ошибке
//
//    b) createExpressOrder(id: String, customerName: String, amount: Double): Singletons.Order?
//       - То же самое, но со статусом STATUS_PROCESSING
//       - И amount должна быть минимум MIN_ORDER_AMOUNT * 2
//
//    c) isValidStatus(status: String): Boolean
//       - Проверяет, является ли статус одним из пяти константных статусов
//
// 5. Добавьте метод экземпляра класса:
//    - updateStatus(newStatus: String): Boolean
//      * Использует isValidStatus для проверки
//      * Если статус валиден - обновляет и возвращает true
//      * Если нет - выводит ошибку и возвращает false
//
// 6. Переопределите toString() для красивого вывода заказа

class Order private constructor(
    val id: String,
    val customerName: String,
    val totalAmount: Double,
    var status: String
) {
    companion object {
        const val STATUS_PENDING = "Pending"
        const val STATUS_PROCESSING = "Processing"
        const val STATUS_SHIPPED = "Shipped"
        const val STATUS_DELIVERED = "Delivered"
        const val STATUS_CANCELLED = "Cancelled"
        const val MIN_ORDER_AMOUNT = 100.0
        const val MAX_ORDER_AMOUNT = 100000.0

        private val validStatuses = setOf(
            STATUS_PENDING,
            STATUS_PROCESSING,
            STATUS_SHIPPED,
            STATUS_DELIVERED,
            STATUS_CANCELLED
        )

        fun createOrder(id: String, customerName: String, amount: Double): Order? {
            if (customerName.isEmpty() || amount !in MIN_ORDER_AMOUNT..MAX_ORDER_AMOUNT) {
                println("Ошибка создания заказа")
                return null
            }
            return Order(
                id = id,
                customerName = customerName,
                totalAmount = amount,
                status = STATUS_PENDING
            )
        }

        fun createExpressOrder(id: String, customerName: String, amount: Double): Order? {
            if (customerName.isEmpty() || amount !in (MIN_ORDER_AMOUNT * 2)..MAX_ORDER_AMOUNT) {
                // Выводим сообщение об ошибке
                println("Ошибка создания экспресс-заказа")
                return null
            }
            // Если валидация прошла - создаем заказ со статусом STATUS_PROCESSING
            return Order(
                id = id,
                customerName = customerName,
                totalAmount = amount,
                status = STATUS_PROCESSING
            )
        }

        fun isValidStatus(status: String): Boolean {
            return status in validStatuses
        }
    }

    fun updateStatus(newStatus: String): Boolean {
        if (isValidStatus(newStatus)) {
            status = newStatus
            return true
        } else {
            println("Ошибка обновления статуса")
            return false
        }
    }

    override fun toString(): String {
        return "$customerName | $totalAmount | $status"
    }
}


// ========================================
// ТЕСТЫ
// ========================================
fun main() {
    println("=== Тест 1: Создание обычного заказа ===")
    val order1 = Order.createOrder("ORD-001", "Иван Петров", 500.0)
    println(order1)

    println("\n=== Тест 2: Попытка создать заказ с маленькой суммой ===")
    val order2 = Order.createOrder("ORD-002", "Мария Сидорова", 50.0)
    println(order2)

    println("\n=== Тест 3: Попытка создать заказ с пустым именем ===")
    val order3 = Order.createOrder("ORD-003", "", 1000.0)
    println(order3)

    println("\n=== Тест 4: Создание экспресс-заказа ===")
    val order4 = Order.createExpressOrder("ORD-004", "Алексей Иванов", 300.0)
    println(order4)

    println("\n=== Тест 5: Попытка создать экспресс-заказ с недостаточной суммой ===")
    val order5 = Order.createExpressOrder("ORD-005", "Ольга Смирнова", 150.0)
    println(order5)

    println("\n=== Тест 6: Обновление статуса ===")
    order1?.let {
        println("Текущий заказ: $it")
        it.updateStatus(Order.STATUS_SHIPPED)
        println("После обновления: $it")
        it.updateStatus("InvalidStatus") // попытка установить невалидный статус
    }

    println("\n=== Тест 7: Проверка валидности статусов ===")
    println("STATUS_PENDING валиден? ${Order.isValidStatus(Order.STATUS_PENDING)}")
    println("'WrongStatus' валиден? ${Order.isValidStatus("WrongStatus")}")

    println("\n=== Тест 8: Граничные значения ===")
    val order6 = Order.createOrder("ORD-006", "Тест", Order.MIN_ORDER_AMOUNT)
    println(order6)

    val order7 = Order.createOrder("ORD-007", "Тест", Order.MAX_ORDER_AMOUNT)
    println(order7)

    val order8 = Order.createOrder("ORD-008", "Тест", Order.MAX_ORDER_AMOUNT + 1)
    println(order8)
}


// ========================================
// ОЖИДАЕМЫЙ ВЫВОД (примерно):
// ========================================
// === Тест 1: Создание обычного заказа ===
// Singletons.Order(id=ORD-001, customer=Иван Петров, amount=500.0, status=Pending)
//
// === Тест 2: Попытка создать заказ с маленькой суммой ===
// Ошибка: Сумма заказа должна быть от 100.0 до 100000.0
// null
//
// === Тест 3: Попытка создать заказ с пустым именем ===
// Ошибка: Имя клиента не может быть пустым
// null
//
// === Тест 4: Создание экспресс-заказа ===
// Singletons.Order(id=ORD-004, customer=Алексей Иванов, amount=300.0, status=Processing)
//
// === Тест 5: Попытка создать экспресс-заказ с недостаточной суммой ===
// Ошибка: Для экспресс-заказа минимальная сумма 200.0
// null
//
// === Тест 6: Обновление статуса ===
// Текущий заказ: Singletons.Order(id=ORD-001, customer=Иван Петров, amount=500.0, status=Pending)
// После обновления: Singletons.Order(id=ORD-001, customer=Иван Петров, amount=500.0, status=Shipped)
// Ошибка: Невалидный статус 'InvalidStatus'
//
// === Тест 7: Проверка валидности статусов ===
// STATUS_PENDING валиден? true
// 'WrongStatus' валиден? false
//
// === Тест 8: Граничные значения ===
// Singletons.Order(id=ORD-006, customer=Тест, amount=100.0, status=Pending)
// Singletons.Order(id=ORD-007, customer=Тест, amount=100000.0, status=Pending)
// Ошибка: Сумма заказа должна быть от 100.0 до 100000.0
// null