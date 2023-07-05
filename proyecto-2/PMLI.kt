/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * PMLI.kt -> Este archivo contiene la implementación del TAD PMLI (Palabras Con la Misma
 * Letra Inicial). También se agregan las funciones auxiliares del proyecto.
 */

/*
 * Clase de la estructura de datos PMLI (Palabras Con la Misma Letra Inicial).
 * @param character: Caracter que representa la letra inicial de las palabras que se almacenarán en la estructura.
 * @property text: Arreglo de Strings que almacena las palabras que se agregan a la estructura.
 * @property textIndex: Entero que representa el índice del arreglo text en el que se almacenará la siguiente palabra.
 * @property letrasValidas: Arreglo de caracteres que contiene las letras del alfabeto español.
 */
class PMLI(val character: Char) {
    // Atributos de la clase PMLI

    // text: Arreglo de Strings que almacena las palabras que se agregan a la estructura.
    private var text: Array<String> = Array(10) { "" }

    // textIndex: Entero que representa el índice del arreglo text en el que se almacenará la siguiente palabra.
    private var textIndex: Int = 0

    // Métodos de la clase PMLI

    /*
     * Constructor de la clase PMLI.
     * @param character: Caracter que representa la letra inicial de las palabras que se almacenarán en la estructura.
     * Precondición: el caracter debe ser una letra minúscula del alfabeto español.
     * Postcondición: se crea una estructura PMLI con el caracter dado.
     */
    init {
        if (!letraValida(character)) {
            throw IllegalArgumentException("El caracter debe ser una letra minúscula del alfabeto español.")
        }
    }

    /*
     * agregarPalabra(palabra: String): Unit
     * Método que agrega una palabra a la estructura.
     * @param palabra: String -> Palabra que se desea agregar.
     * Precondición: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Postcondición: se agrega la palabra a la estructura.
     */
    fun agregarPalabra(palabra: String) {
        // Si la palabra no es válida o no empieza con el caracter de la estructura, se lanza una excepción
        if (!esPalabraValida(palabra) || palabra[0] != this.character) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }
        // Si la palabra ya se encuentra en la estructura, se muestra un mensaje
        if (buscarPalabra(palabra)) {
            println("La palabra ya se encuentra en la estructura.")
            return
        }
        // Si el arreglo text está lleno, se duplica su tamaño
        if (textIndex == text.size) {
            text = text.copyOf(text.size * 2)
        }
        // Se agrega la palabra al arreglo text y se aumenta el índice
        text[textIndex] = palabra
        textIndex++
    }

    /*
     * buscarPalabra(palabra: String): Boolean
     * Método que busca una palabra en la estructura.
     * @param palabra: String -> Palabra que se desea buscar.
     * Precondición: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Postcondición: devuelve true si la palabra se encuentra en la estructura, false en caso contrario.
     */
    fun buscarPalabra(palabra: String): Boolean {
        // Si la palabra no es válida, se lanza una excepción
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }

        // Se busca la palabra en el arreglo text
        var palabraBuscada = this.text.firstOrNull { it == palabra }

        // Se devuelve true si la palabra se encontró, o false si no
        return palabraBuscada != null
    }

    /*
     * eliminarPalabra(palabra: String): Unit
     * Método que elimina una palabra de la estructura.
     * @param palabra: String -> Palabra que se desea eliminar.
     * Precondición: la palabra debe pertenecer a la estructura.
     * Postcondición: se elimina la palabra de la estructura.
     */
    fun eliminarPalabra(palabra: String) {
        // Si la palabra no es válida, se lanza una excepción
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }
        // Si la palabra se encuentra en la estructura, se elimina
        if (buscarPalabra(palabra)) {
            this.text = this.text.filter { it != palabra }.toTypedArray()
            this.textIndex--
        }
        // Si no, se lanza una excepción
        else {
            throw IllegalArgumentException("La palabra no se encuentra en la estructura.")
        }
    }

    /*
     * toString(): String
     * Método que devuelve una representación en String de la estructura.
     * Precondición: true.
     * Postcondición: se devuelve una representación en String de la estructura.
     */
    override fun toString(): String {
        // Muestra los elementos del arreglo de palabras separados por un espacio y en orden lexicográfico
        val palabrasNoVacias = this.text.filter { it != "" }.sorted()
        var str = "${this.character}: \n"
        for (i in 0 until palabrasNoVacias.size) {
            if (i == palabrasNoVacias.size - 1) {
                str += "  * ${palabrasNoVacias[i]}"
            } else {
                str += "  * ${palabrasNoVacias[i]}\n"
            }
        }
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
    for (i in 0 until palabra.length) {
        if (!alfabet.perteneceAlAlfabeto(palabra[i])) {
            return false
        }
    }
    return true
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
