/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PruebaOrtografia.kt -> Este archivo contiene la implementación del cliente
 * del proyecto.
 */

import kotlin.system.exitProcess

/**
 * imprimirMensajeBienvenida(): Unit
 * Función que imprime un mensaje de bienvenida al usuario.
 * Precondición: true.
 * Postcondición: se imprime un mensaje de bienvenida al usuario.
 */
fun imprimirMensajeBienvenida() {
    println("\u001b[32m¡Disfruta de este increíble Asistente Ortográfico!\u001b[0m")
    println("Este programa te ayudará a corregir tus textos.\n")
    println("Para ello, debes ingresar un diccionario con las palabras que consideres válidas.")
    println("Luego, ingresa el texto que deseas corregir y el programa te mostrará las palabras\nque no se encuentran en el diccionario y sus respectivas sugerencias.\n")
    println("¡Esperamos que disfrutes de esta experiencia!")
    println("Creado por: \u001b[33mJuan Cuevas (19-10056)\u001b[0m y \u001b[33mLuis Isea (19-10175).\u001b[0m\n")
}

/**
 * imprimirOpciones(): Unit
 * Función que imprime las opciones que puede realizar el usuario.
 * Precondición: true.
 * Postcondición: se imprime las opciones que puede realizar el usuario.
 */
fun imprimirOpciones() {
    println("Puedes realizar las siguientes operaciones:")
    println("\u001b[34m1.\u001b[0m Crear un nuevo ayudante ortográfico.")
    println("\u001b[34m2.\u001b[0m Cargar un diccionario.")
    println("\u001b[34m3.\u001b[0m Eliminar una palabra del diccionario.")
    println("\u001b[34m4.\u001b[0m Corregir texto.")
    println("\u001b[34m5.\u001b[0m Mostrar diccionario.")
    println("\u001b[34m6.\u001b[0m Salir de la aplicación.")
    print("\u001b[33m¿Qué operación deseas realizar?:\u001b[36m ")
}

/**
 * main(): Unit
 * Función principal del proyecto.
 * Precondición: true.
 * Postcondición: se ejecuta el cliente del proyecto.
 */
fun main() {
    imprimirMensajeBienvenida()
    var ayudante = AyudanteOrtografico()
    var opcion: Int
    var salir = false
    while (!salir) {
        imprimirOpciones()
        opcion = readLine()!!.toInt()
        print("\u001b[0m")
        when (opcion) {
            1 -> {
                ayudante = AyudanteOrtografico()
                println("¡Se ha creado un nuevo Ayudante Ortográfico!\n")
            }
            2 -> {
                println("El diccionario debe ser un archivo de texto con una palabra por línea.")
                println("Las palabras deben estar en minúsculas y sin acentos.")
                println("\u001b[34mIngrese el nombre del archivo que contiene el diccionario:\u001b[0m")
                val fname = readLine()!!
                ayudante.cargarDiccionario(fname)
            }
            3 -> {
                print("\u001b[34mIngrese la palabra que deseas eliminar del diccionario:\u001b[0m ")
                val palabra = readLine()!!
                ayudante.borrarPalabra(palabra)
            }
            4 -> {
                println("\u001b[34mIngrese el nombre del archivo que contiene el texto:\u001b[0m")
                val fname = readLine()!!
                println("\u001b[34mIngrese el nombre del archivo donde desea guardar las sugerecias de corrección:\u001b[0m")
                println("\u001b[31;3m(El archivo de salida se creará si no existe)\u001b[0m")
                val fname2 = readLine()!!
                ayudante.corregirTexto(fname, fname2)
            }
            5 -> {
                ayudante.imprimirDiccionario()
            }
            6 -> {
                println("\u001b[34m¡Hasta luego!\u001b[0m")
                salir = true
            }
            else -> {
                println("\u001b[31mOpción inválida. Escriba un número entre 1 y 6.\u001b[0m\n")
            }
        }
    }
    exitProcess(0)
}
