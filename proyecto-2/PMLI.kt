/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PMLI.kt -> Este archivo contiene la implementación del TAD PMLI (Palabras Con la Misma
 * Letra Inicial). También se agregan las funciones auxiliares del proyecto.
 */

/**
 * Clase de la estructura de datos PMLI (Palabras Con la Misma Letra Inicial).
 * @param character: Caracter que representa la letra inicial de las palabras que se almacenarán en la estructura.
 * @property palabras: ConjuntoPalabras -> Conjunto de palabras que se almacenan en la estructura.
 */
class PMLI(val character: Char) {
    // Atributos de la clase PMLI

    // palabras: Conjunto de palabras que se almacenan en la estructura.
    private var palabras: ConjuntoPalabras = ConjuntoPalabras()

    // Métodos de la clase PMLI

    /**
     * Constructor de la clase PMLI.
     * @param character: Caracter que representa la letra inicial de las palabras que se almacenarán en la estructura.
     * Precondición: el caracter debe ser una letra minúscula del alfabeto español.
     * Postcondición: se crea una estructura PMLI con el caracter dado.
     */
    init {
        if (!alfabet.perteneceAlAlfabeto(character)) {
            throw IllegalArgumentException("El caracter debe ser una letra minúscula del alfabeto español.")
        }
    }

    /**
     * agregarPalabra(palabra: String): Unit
     * Método que agrega una palabra a la estructura.
     * @param palabra: String -> Palabra que se desea agregar.
     * Precondición: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Postcondición: se agrega la palabra a la estructura.
     */
    fun agregarPalabra(palabra: String) {
        // Si la palabra no es válida o no empieza con el caracter de la estructura, se lanza una excepción
        if (!esPalabraValida(palabra) || palabra[0] != this.character) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
            println("La palabra debe empezar con el caracter de la estructura: ${this.character}.")
            return
        }
        // Si la palabra ya se encuentra en la estructura, se muestra un mensaje
        if (buscarPalabra(palabra)) {
            println("La palabra ya se encuentra en la estructura.")
            return
        }
        // Se agrega la palabra a palabras
        palabras.agregar(palabra)
    }

    /**
     * buscarPalabra(palabra: String): Boolean
     * Método que busca una palabra en la estructura.
     * @param palabra: String -> Palabra que se desea buscar.
     * Precondición: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Postcondición: devuelve true si la palabra se encuentra en la estructura, false en caso contrario.
     */
    fun buscarPalabra(palabra: String): Boolean {
        // Si la palabra no es válida, se lanza una excepción
        if (!esPalabraValida(palabra)) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
            return false
        }

        // Se busca la palabra en palabras
        return this.palabras.pertenece(palabra)
    }

    /**
     * eliminarPalabra(palabra: String): Unit
     * Método que elimina una palabra de la estructura.
     * @param palabra: String -> Palabra que se desea eliminar.
     * Precondición: la palabra debe pertenecer a la estructura.
     * Postcondición: se elimina la palabra de la estructura.
     */
    fun eliminarPalabra(palabra: String) {
        // Si la palabra no es válida, se lanza una excepción
        if (!esPalabraValida(palabra)) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
            return
        }
        // Si la palabra se encuentra en la estructura, se elimina
        if (buscarPalabra(palabra)) {
            this.palabras.eliminar(palabra)
        }
        // Si no, se lanza una excepción
        else {
            println("La palabra no se encuentra en la estructura.")
        }
    }

    /**
     * getNumPalabras(): Int
     * Método que devuelve el número de palabras que se encuentran en la estructura.
     * Precondición: true.
     * Postcondición: se devuelve el número de palabras que se encuentran en la estructura.
     */
    fun getNumPalabras(): Int {
        return this.palabras.getNumPalabras()
    }

    /**
     * toString(): String
     * Método que devuelve una representación en String de la estructura.
     * Precondición: true.
     * Postcondición: se devuelve una representación en String de la estructura.
     */
    override fun toString(): String {
        // Muestra los elementos del arreglo de palabras separados por un espacio y en orden lexicográfico
        var str = "${this.character}: \n"
        str += this.palabras.toString()
        return str
    }
}

// Variables globales del proyecto

// alfabet: AlfabetHashTable que contiene las letras del alfabeto español.
val alfabet = AlfabetHashTable()

// Funciones auxiliares del proyecto

/**
 * esPalabraValida(palabra: String): Boolean
 * Función que determina si una palabra es válida en el idioma español.
 * @param palabra: String -> Palabra que se desea verificar.
 * Precondición: true.
 * Postcondición: devuelve true si la palabra está formada únicamente por letras minúsculas del alfabeto español, false en caso contrario.
 */
fun esPalabraValida(palabra: String): Boolean {
    return palabra.all { alfabet.perteneceAlAlfabeto(it) }
}

fun damerauLevenshteinDistance(str1: String, str2: String): Int {
    val len1 = str1.length
    val len2 = str2.length

    // Caso base: si una de las cadenas es vacía, la distancia es la longitud de la otra cadena
    if (len1 == 0) return len2
    if (len2 == 0) return len1

    // Si los últimos caracteres de las cadenas son iguales, la distancia es la misma que sin ellos
    if (str1[len1 - 1] == str2[len2 - 1]) {
        return damerauLevenshteinDistance(str1.substring(0, len1 - 1), str2.substring(0, len2 - 1))
    }

    // Calculamos la distancia de Levenshtein sin la transposición
    var distance = levenshteinDistance(str1, str2)

    // Calculamos la distancia de Damerau-Levenshtein con la transposición
    if (len1 > 1 && len2 > 1 && str1[len1 - 1] == str2[len2 - 2] && str1[len1 - 2] == str2[len2 - 1]) {
        distance = minOf(distance, damerauLevenshteinDistance(str1.substring(0, len1 - 2), str2.substring(0, len2 - 2)) + 1)
    }

    return distance
}

fun levenshteinDistance(str1: String, str2: String): Int {
    val len1 = str1.length
    val len2 = str2.length

    // Creamos una matriz para almacenar las distancias parciales
    val distances = Array(len1 + 1) { IntArray(len2 + 1) }

    // Inicializamos la primera fila y la primera columna de la matriz
    for (i in 0..len1) {
        distances[i][0] = i
    }
    for (j in 0..len2) {
        distances[0][j] = j
    }

    // ! REVISAR
    // Calculamos las distancias parciales
    for (i in 1..len1) {
        for (j in 1..len2) {
            val cost = if (str1[i - 1] == str2[j - 1]) 0 else 1
            distances[i][j] = minOf(
                distances[i - 1][j] + 1,
                distances[i][j - 1] + 1,
                distances[i - 1][j - 1] + cost,
            )
        }
    }

    // La distancia final es la última celda de la matriz
    return distances[len1][len2]
}
