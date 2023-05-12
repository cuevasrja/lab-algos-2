import java.time.LocalDateTime

fun horaActual() {
    val actual = LocalDateTime.now()
    println("La hora actual es: $actual")
}

fun saludo(nombre: String, apellido: String) {
    println("Hola $nombre $apellido")
}

fun main() {
    val name: String = "Sasha" 
    val surname: String  = "Ramirez"
    saludo(name, surname)
    horaActual()
}

