/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * AyudanteOrtografico.kt -> Este archivo contiene la implementación del TAD Ayudante Ortográfico.
 */

import java.io.File
import java.lang.IllegalArgumentException

/**
* Clase AyudanteOrtografico, que representa el TAD Ayudante Ortográfico.
* Este tipo de dato abstracto permite almacenar palabras tal como lo hace un diccionario.
* @property MAX: Entero que representa el tamaño máximo del arreglo dicc.
* @property dicc: Arreglo de PMLI que almacena las estructuras PMLI.
*/
class AyudanteOrtografico() {
    // Atributos de la clase AyudanteOrtografico

    // MAX: Entero que representa el tamaño máximo del arreglo dicc.
    private val MAX = 27

    // dicc: Arreglo de PMLI que almacena las estructuras PMLI.
    private val dicc: Array<PMLI> = arrayOf(PMLI('a'), PMLI('b'), PMLI('c'), PMLI('d'), PMLI('e'), PMLI('f'), PMLI('g'), PMLI('h'), PMLI('i'), PMLI('j'), PMLI('k'), PMLI('l'), PMLI('m'), PMLI('n'), PMLI('ñ'), PMLI('o'), PMLI('p'), PMLI('q'), PMLI('r'), PMLI('s'), PMLI('t'), PMLI('u'), PMLI('v'), PMLI('w'), PMLI('x'), PMLI('y'), PMLI('z'))

    // Métodos de la clase AyudanteOrtografico

    /**
     * cargarDiccionario(fname: String)
     * Método que carga el diccionario en la estructura.
     * @param fname: String -> Nombre del archivo que contiene el diccionario.
     * Precondición: El archivo fname debe existir y tener el formato correcto, que es
     * una palabra por línea, con todas las letras en minúscula.
     * Postcondición: se carga el diccionario en la estructura.
     */
    fun cargarDiccionario(fname: String) {
        // Se crea un objeto File con el nombre del archivo
        val file = File(fname)

        // Se crea un objeto BufferedReader para leer el archivo.
        val bufferedReader = file.bufferedReader()

        // Se lee el archivo línea por línea.
        bufferedReader.useLines { lines ->
            lines.forEach {
                // Se convierte la palabra a minúsculas y se elimina los espacios en blanco.
                val palabra = it.trim()

                // Si la palabra es válida, se agrega a la estructura.
                if (esPalabraValida(palabra)) {
                    // Se obtiene la primera letra de la palabra.
                    val primeraLetra = palabra[0]

                    // Se obtiene el índice de la primera letra de la palabra en el arreglo dicc.
                    val indice = dicc.indexOfFirst { it.character == primeraLetra }

                    // Se agrega la palabra a la estructura.
                    dicc[indice].agregarPalabra(palabra)
                } else {
                    // Si la palabra no es válida, se lanza una excepción
                    throw IllegalArgumentException("La palabra $palabra no es válida.")
                }
            }
        }
    }

    /**
     * borrarPalabra(palabra: String)
     * Método que elimina la palabra de la estructura.
     * @param palabra: String -> Palabra que se desea eliminar.
     * Precondición: la palabra debe pertenecer a la estructura y debe ser válida.
     * Postcondición: se elimina la palabra de la estructura.
     */
    fun borrarPalabra(palabra: String) {
        // Si la palabra no es válida, se lanza una excepción
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }

        // Se obtiene la primera letra de la palabra
        val primeraLetra = palabra[0]

        // Se obtiene el índice de la primera letra en el arreglo
        val indice = dicc.indexOfFirst { it.character == primeraLetra }

        // Si la palabra se encuentra en la estructura, se elimina
        if (dicc[indice].buscarPalabra(palabra)) {
            dicc[indice].eliminarPalabra(palabra)
        }
        // Si la palabra no se encuentra en la estructura, se lanza una excepción
        else {
            throw IllegalArgumentException("La palabra no se encuentra en el diccionario.")
        }
    }

    /**
     * corregirTexto(finput: String, foutput: String)
     * Método que procesa el archivo de entrada y escribe el archivo de salida con las
     * palabras válidas que no se encuentran en el diccionario, seguidas de las 4 palabras
     * con menor distancia.
     * @param finput: String -> Nombre del archivo de entrada.
     * @param foutput: String -> Nombre del archivo de salida.
     * Precondición: El archivo de entrada debe existir.
     * Postcondición: se imprime en el archivo de salida las palabras válidas que no se
     * encuentran en el diccionario, seguidas de las 4 palabras con menor distancia.
     */
    // fun corregirTexto(finput: String, foutput: String) {
    //     // TODO: Implementar
    // }

    /**
     * toString()
     * Método que imprime la estructura.
     * @return str: String -> Representación de la estructura en forma de String.
     * Precondición: true.
     * Postcondición: se imprime la estructura.
     */
    override fun toString(): String {
        var str = ""
        for (i in 0 until MAX) {
            if (i == MAX - 1) {
                str += dicc[i].toString()
            } else {
                str += dicc[i].toString() + "\n"
            }
        }
        return str
    }

    fun imprimirDiccionario() {
        for (i in 0 until MAX) {
            println(dicc[i].toString())
        }
    }
}
