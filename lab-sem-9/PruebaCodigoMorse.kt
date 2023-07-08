/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PruebaCodigoMorse.kt -> Este archivo realiza las pruebas del codigo morse recibido por consola.
 */

fun main(args: Array<String>) {
    println("Bienvenido al decodificador de codigo morse.")
    val codigo = CodigoMorse()
    println("Se ha creado el resolutor de codigo morse.")
    val texto = args[0]
    println("El texto a descifrar es: \u001b[33m$texto \u001b[0m")
    val mensajeDecodificado = codigo.decodificarMensaje(texto)
    if (mensajeDecodificado == "") {
        println("\u001b[31mError:\u001b[0m El codigo morse ingresado no es valido.")
        return
    }
    println("El texto descifrado es: \u001b[34m'${mensajeDecodificado}'\u001b[0m")
}
