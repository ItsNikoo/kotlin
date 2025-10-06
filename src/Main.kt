fun main() {
    val product = Product("Пирожок", 35.0)
    println(
        "Стоимость продукта - " + product.name + " " +
                product.price + " руб."
    )
}

class Product(
    val name: String?, // ваши правки на этой строчке кода
    val price: Double // ваши правки на этой строчке кода
) 
