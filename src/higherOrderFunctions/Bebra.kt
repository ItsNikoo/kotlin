package higherOrderFunctions

data class Student(val name: String, val age: Int) {
    fun isAdult(): Boolean = age >= 18
    override fun toString(): String = "$name - $age лет"
}

class StudentFactory(
    val studentsList: List<Student>,
) {
    fun filter(predicate: (Student) -> Boolean): List<Student> {
        var result = mutableListOf<Student>()
        for (student in studentsList) {
            if (predicate(student)) {
                result.add(student)
            }
        }
        return result
    }
}

fun main() {
    val students = listOf(
        Student("Алиса", 20),
        Student("Саша", 16),
        Student("Маша", 19),
        Student("Аня", 17),
        Student("Ваня", 17),
        Student("Алексей", 18),
    )
    val university = StudentFactory(students)

    println("Все студенты:")
    println(university.studentsList)  // Вызов toString()

    val adults = university.filter { it.isAdult() }
    println("Совершеннолетние студенты:")
    println(adults)

    println("Студенты на А:")
    val studentsOnA = university.filter { it.name.startsWith("А") }
    println(studentsOnA)
}