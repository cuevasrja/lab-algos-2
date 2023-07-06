/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PruebaOrtografia.kt -> Este archivo contiene la implementación de la interfaz de usuario
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
    println("¡Disfruta de este increíble Asistente Ortográfico!")
    println("Este programa te ayudará a corregir tus textos.\n")
    println("Para ello, debes ingresar un diccionario con las palabras que consideres válidas.")
    println("Luego, ingresa el texto que deseas corregir y el programa te mostrará las palabras\nque no se encuentran en el diccionario y sus respectivas sugerencias.\n")
    println("¡Esperamos que disfrutes de esta experiencia!")
    println("Creado por: Juan Cuevas (19-10056) y Luis Isea (19-10175).\n")
}

/**
 * imprimirOpciones(): Unit
 * Función que imprime las opciones que puede realizar el usuario.
 * Precondición: true.
 * Postcondición: se imprime las opciones que puede realizar el usuario.
 */
fun imprimirOpciones() {
    println("Puedes realizar las siguientes operaciones:")
    println("1. Crear un nuevo ayudante ortográfico.")
    println("2. Cargar un diccionario. 3. Eliminar una palabra del diccionario.")
    println("4. Corregir texto. 5. Mostrar diccionario. 6. Salir de la aplicación.")
    print("¿Qué operación deseas realizar?: ")
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
        when (opcion) {
            1 -> {
                ayudante = AyudanteOrtografico()
                println("¡Se ha creado un nuevo Ayudante Ortográfico!\n")
            }
            2 -> {
                println("El diccionario debe ser un archivo de texto con una palabra por línea.")
                println("Ingrese el nombre del archivo que contiene el diccionario:")
                val fname = readLine()!!
                ayudante.cargarDiccionario(fname)
            }
            3 -> {
                print("Ingrese la palabra que deseas eliminar del diccionario: ")
                val palabra = readLine()!!
                ayudante.borrarPalabra(palabra)
            }
            4 -> {
                println("Ingrese el nombre del archivo que contiene el texto:")
                val fname = readLine()!!
                println("Ingrese el nombre del archivo donde desea guardar las sugerecias de corrección:")
                println("(Este archivo se creará si no existe)")
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
                println("Opción inválida. Escriba un número entre 1 y 6.\n")
            }
        }
    }
    exitProcess(0)
}
