/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * AyudanteOrtografico.kt -> Este archivo contiene la implementación del TAD Ayudante Ortográfico.
 */

import java.io.File

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

        // Si el archivo no existe, se informa al usuario y se detiene la ejecución del método.
        if (!file.exists()) {
            println("El archivo $fname no existe.\n")
            return
        }

        // Se crea un objeto BufferedReader para leer el archivo.
        var bufferedReader = file.bufferedReader()

        // Se lee el archivo línea por línea, verficando el formato del mismo.
        bufferedReader.useLines { lines ->
            lines.forEach {
                // Se verifica que haya una sola palabra por línea.
                if (it.split(" ").size > 1) {
                    println("No se pudo cargar el diccionario.")
                    println("El archivo no tiene el formato correcto.")
                    println("Debe haber una sola palabra por línea.\n")
                    return
                }

                // Se convierte la palabra a minúsculas y se elimina los espacios en blanco.
                val palabra = it.trim()

                // Si la palabra no es válida, se lanza detiene la ejecución del método.
                if (!esPalabraValida(palabra)) {
                    println("No se pudo cargar el diccionario.")
                    println("El archivo no tiene el formato correcto.")
                    println("La palabra $palabra no es válida.\n")
                    return
                }
            }
        }

        // Se vuelve a crear un objeto BufferedReader para leer el archivo.
        bufferedReader = file.bufferedReader()

        // Una vez verificado el formato del archivo, se procede a cargar el diccionario en la estructura.
        bufferedReader.useLines { lines ->
            lines.forEach {
                // Se convierte la palabra a minúsculas y se elimina los espacios en blanco.
                val palabra = it.trim()

                // Se obtiene la primera letra de la palabra
                val primeraLetra = palabra[0]

                // Se obtiene el índice de la primera letra en el arreglo
                val indice = dicc.indexOfFirst { it.character == primeraLetra }

                // Se inserta la palabra en la estructura
                dicc[indice].agregarPalabra(palabra)
            }
        }

        println("¡Diccionario cargado con éxito!\n")
    }

    /**
     * borrarPalabra(palabra: String)
     * Método que elimina la palabra de la estructura.
     * @param palabra: String -> Palabra que se desea eliminar.
     * Precondición: la palabra debe pertenecer a la estructura y debe ser válida.
     * Postcondición: se elimina la palabra de la estructura.
     */
    fun borrarPalabra(palabra: String) {
        // Si la palabra no es válida, se informa al usuario y se detiene la ejecución del método.
        if (!esPalabraValida(palabra)) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.\n")
            return
        }

        // Se obtiene la primera letra de la palabra
        val primeraLetra = palabra[0]

        // Se obtiene el índice de la primera letra en el arreglo
        val indice = dicc.indexOfFirst { it.character == primeraLetra }

        // Si la palabra se encuentra en la estructura, se elimina
        if (dicc[indice].buscarPalabra(palabra)) {
            dicc[indice].eliminarPalabra(palabra)
            println("La palabra $palabra ha sido eliminada del diccionario.\n")
        }
        // Si la palabra no se encuentra en la estructura, se informa al usuario
        else {
            println("La palabra $palabra no se encuentra en el diccionario.\n")
        }
    }

    /**
     * buscarPalabra(palabra: String)
     * Método que busca la palabra en el diccionario.
     * @param palabra: String -> Palabra que se desea buscar.
     * @return Boolean -> true si la palabra se encuentra en el diccionario, false en caso contrario.
     * Precondición: la palabra debe ser válida.
     * Postcondición: se retorna true si la palabra se encuentra en el diccionario, false en caso contrario.
     */
    fun buscarPalabra(palabra: String): Boolean {
        // Si la palabra no es válida, se devuelve false
        if (!esPalabraValida(palabra)) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.\n")
            return false
        }

        // Se obtiene la primera letra de la palabra
        val primeraLetra = palabra[0]

        // Se obtiene el índice de la primera letra en el arreglo
        val indice = dicc.indexOfFirst { it.character == primeraLetra }

        // Se retorna true si la palabra se encuentra en el diccionario, false en caso contrario.
        return dicc[indice].buscarPalabra(palabra)
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
    fun corregirTexto(finput: String, foutput: String) {
        // Se crea un objeto File con el nombre del archivo de entrada
        val file = File(finput)

        // Si el archivo no existe, se informa al usuario y se detiene la ejecución del método.
        if (!file.exists()) {
            println("El archivo $finput no existe.")
            return
        }

        // Se crea un objeto BufferedReader para leer el archivo.
        val bufferedReader = file.bufferedReader()

        // Leer todo el archivo y almacenarlo en una variable
        val inputString = bufferedReader.use { it.readText() }

        // Se procesa el texto y se almacena en un arreglo
        val arregloPalabras = procesarTexto(inputString)

        // Buscamos las palabras que se encuentran en el diccionario. Las que no se encuentran, se buscan las 4 palabras con menor distancia
        for (i in 0 until arregloPalabras.size) {
            val palabra = arregloPalabras[i].trim()
            // Si la palabra se encuentra en el diccionario, se imprime en el archivo de salida
            if (buscarPalabra(palabra)) {
                arregloPalabras[i] = palabra
                continue
            }
            // En caso contrario, se imprime en el archivo de salida la palabra y las 4 palabras con menor distancia
            val palabrasCercanas = buscarPalabrasConMenorDistancia(palabra)
            arregloPalabras[i] = "$palabra: ${palabrasCercanas[0]}, ${palabrasCercanas[1]}, ${palabrasCercanas[2]}, ${palabrasCercanas[3]}"
        }

        // Se crea un objeto File con el nombre del archivo de salida
        val fileOut = File(foutput)

        // Si el archivo no existe, se crea
        if (!fileOut.exists()) {
            fileOut.createNewFile()
        }

        // Se crea un objeto BufferedWriter para escribir en el archivo.
        val bufferedWriter = fileOut.bufferedWriter()

        // Se escribe en el archivo de salida el arreglo de palabras corregidas
        bufferedWriter.use { out ->
            for (palabra in arregloPalabras) {
                // Se escribe la palabra en el archivo de salida
                out.write(palabra)
                out.newLine()
            }
        }
    }

    /**
     * procesarTexto(texto: String)
     * Método que procesa el texto y devuelve un arreglo con las palabras válidas sin puntos,
     * comas, signos de interrogación, signos de exclamación, espacios en blanco, etc.
     * @param texto: String -> Texto que se desea procesar.
     * @return arregloPalabras: Array<String> -> Arreglo con las palabras válidas.
     * Precondición: texto debe ser un String distinto de vacío.
     * Postcondición: se devuelve un arreglo con las palabras válidas.
     */
    private fun procesarTexto(texto: String): Array<String> {
        val palabras = texto.replace(".", " ").replace(",", " ").replace("?", " ").replace("!", " ").replace(";", " ").replace(":", " ").replace("(", " ").replace(")", " ").replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("¿", " ").replace("¡", " ").replace("\n", " ").replace("\t", " ").replace("\r", " ")
        val arregloPalabras = palabras.split(" ").toTypedArray()
        return arregloPalabras.filter { it != "" && esPalabraValida(it) }.toTypedArray()
    }

    /**
     * buscarPalabrasConMenorDistancia(arregloPalabras: Array<String>)
     * Método que busca las 4 palabras con menor distancia en el diccionario.
     * @param arregloPalabras: Array<String> -> Arreglo ordenado con las palabras válidas.
     * @return palabrasConMenorDistancia: Array<String> -> Arreglo con las 4 palabras con menor distancia.
    */
    private fun buscarPalabrasConMenorDistancia(palabra: String): Array<String> {
        // Se obtiene la primera letra de la palabra
        val primeraLetra = palabra[0]
        // Se obtiene el índice de la primera letra en el arreglo
        val indice = dicc.indexOfFirst { it.character == primeraLetra }
        // Se declara un arreglo con las palabras del diccionario que empiezan con la primera letra de la palabra
        val palabras = dicc[indice].crearArregloPalabras()
        // Se crea un arreglo con las 4 palabras con menor distancia
        val palabrasConMenorDistancia = Array(4) { "" }
        for (i in 0 until 4) {
            var menorDistancia = Int.MAX_VALUE
            for (j in 0 until palabras.size){
                val distancia = damerauLevenshteinDistance(palabra, palabras[j])
                if (distancia < menorDistancia && palabras[j] !in palabrasConMenorDistancia) {
                    menorDistancia = distancia
                    palabrasConMenorDistancia[i] = palabras[j]
                }
            }
        }
        return palabrasConMenorDistancia
    }

    /**
     * dameruLevenshteinDistance(str1: String, str2: String)
     * Método que calcula la distancia de Damerau-Levenshtein entre dos cadenas.
     * @param str1: String -> Primera cadena.
     * @param str2: String -> Segunda cadena.
     * @return distance: Int -> Distancia de Damerau-Levenshtein entre las dos cadenas.
     */
    private fun damerauLevenshteinDistance(str1: String, str2: String): Int {
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

    /**
     * levenshteinDistance(str1: String, str2: String)
     * Método que calcula la distancia de Levenshtein entre dos cadenas.
     * @param str1: String -> Primera cadena.
     * @param str2: String -> Segunda cadena.
     * @return distance: Int -> Distancia de Levenshtein entre las dos cadenas.
     */
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
