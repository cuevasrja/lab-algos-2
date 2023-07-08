/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PruebaCodigoMorse.kt -> Este archivo realiza las pruebas del codigo morse recibido por consola.
 * Es importante mencionar que este cliente recive un solo argumento por consola,
 * el cual debe ser el codigo morse que se desea decodificar entre comillas dobles.
 */

/**
 * main(args: Array<String>)
 * Funcion principal que decodifica el codigo morse recibido por consola.
 * @param args: Array<String> -> Arreglo de argumentos recibidos por consola.
 * Precondicion: El argumento recibido por consola debe ser el codigo morse que se desea decodificar entre comillas dobles.
 * Postcondicion: Se imprime por consola el texto decodificado del codigo morse recibido por consola.
 */
fun main(args: Array<String>) {
    val codigo = CodigoMorse()
    val texto = args[0]
    val mensajeDecodificado = codigo.decodificarMensaje(texto)
    if (mensajeDecodificado == "") {
        println("\u001b[31mError:\u001b[0m El codigo morse ingresado no es valido.")
        return
    }
    println("El texto descifrado es: \u001b[34m'$mensajeDecodificado'\u001b[0m")
}
