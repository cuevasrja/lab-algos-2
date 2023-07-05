/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

import kotlin.system.exitProcess

fun main() {
    println("¡Bienvenido al Ayudante Ortográfico!")
    var ayudante = AyudanteOrtografico()
    var opcion: Int
    var salir = false
    while (!salir) {
        println("¿Qué desea hacer?")
        println("1. Crear un nuevo ayudante ortográfico.")
        println("2. Cargar un diccionario.")
        println("3. Eliminar palabra.")
        println("4. Corregir texto.")
        println("5. Mostrar diccionario.")
        println("6. Salir de la aplicación.")
        opcion = readLine()!!.toInt()
        when (opcion) {
            1 -> {
                ayudante = AyudanteOrtografico()
                println("¡Se ha creado un nuevo Ayudante Ortográfico!")
            }
            2 -> {
                println("Ingrese el nombre del archivo que contiene el diccionario:")
                val fname = readLine()!!
                ayudante.cargarDiccionario(fname)
            }
            3 -> {
                println("Ingrese la palabra que desea eliminar:")
                val palabra = readLine()!!
                ayudante.borrarPalabra(palabra)
            }
            4 -> {
                println("Ingrese el nombre del archivo que contiene el texto:")
                val fname = readLine()!!
                println("Ingrese el nombre del archivo donde desea guardar el texto corregido:")
                val fname2 = readLine()!!
                // ayudante.corregirTexto(fname, fname2)
            }
            5 -> {
                ayudante.imprimirDiccionario()
            }
            6 -> {
                println("¡Hasta luego!")
                salir = true
            }
            else -> {
                println("Opción inválida.")
            }
        }
    }
    exitProcess(0)
}
