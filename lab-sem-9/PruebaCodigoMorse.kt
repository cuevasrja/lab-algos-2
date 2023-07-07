/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PruebaCodigoMorse.kt -> Este archivo realiza las pruebas del codigo morse recibido por consola.
 */

fun main(args: Array<String>) {
    val codigo = CodigoMorse()
    val texto = args[0]
    println("El texto a descifrar es: $texto")
    println("El texto descifrado es: ${codigo.descifrar(texto)}")
}