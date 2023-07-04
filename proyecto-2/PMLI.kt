/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

/**
* Clase de la estructura de datos PMLI (Palabras Con la Misma Letra Inicial).
* @param character: Caracter que representa la letra inicial de las palabras que se almacenarán en la estructura.
* @property text: Arreglo de Strings que almacena las palabras que se agregan a la estructura.
* @property textIndex: Entero que representa el índice del arreglo text en el que se almacenará la siguiente palabra.
* @property letrasValidas: Arreglo de caracteres que contiene las letras del alfabeto español.
* @constructor Crea un objeto de la clase PMLI.
*/
class PMLI(var character: Char) {
    private var text: Array<String> = Array(10) { "" }
    private var textIndex: Int = 0
    private val letrasValidas: Array<Char> = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                                     'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's',
                                                     't', 'u', 'v', 'w', 'x', 'y', 'z')

    init {
        if (!letrasValidas.contains(character)) {
            throw IllegalArgumentException("El caracter debe ser una letra minúscula del alfabeto español.")
        }
    }

    fun esPalabraValida(palabra: String): Boolean {
        return palabra.all { it in letrasValidas }
    }

    fun agregarPalabra(palabra: String) {
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }

        if (textIndex == text.size) {
            text = text.copyOf(text.size * 2)
        }

        text[textIndex] = palabra
        textIndex++
    }

    fun buscarPalabra(palabra: String): String {
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }

        var palabraBuscada = this.text.firstOrNull { it == palabra }

        return if(palabraBuscada != null) palabraBuscada else ""
    }

    fun eliminarPalabra(palabra: String) {
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }

        var palabraBuscada = this.text.firstOrNull { it == palabra }

        if(palabraBuscada != null) {
            this.text = this.text.filter { it != palabra }.toTypedArray()
            this.textIndex--
        }
    }

    override fun toString(): String {
        // Muestra los elementos del arreglo de palabras separados por un espacio y en orden lexicográfico
        val palabrasNoVacias = this.text.filter { it != "" }
        return palabrasNoVacias.sorted().joinToString(" ")
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

    private fun levenshteinDistance(str1: String, str2: String): Int {
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

        // Calculamos las distancias parciales
        for (i in 1..len1) {
            for (j in 1..len2) {
                val cost = if (str1[i - 1] == str2[j - 1]) 0 else 1
                distances[i][j] = minOf(
                    distances[i - 1][j] + 1,
                    distances[i][j - 1] + 1,
                    distances[i - 1][j - 1] + cost
                )
            }
        }

        // La distancia final es la última celda de la matriz
        return distances[len1][len2]
    }
}