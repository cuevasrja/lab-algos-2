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
        // Se ordena lexicográficamente el arreglo
        quicksortLexicografico(arregloPalabras)

        // Se crea un diccionario temporal para almacenar las palabras que no se encuentran en el diccionario
        val diccTemporal = AyudanteOrtografico()

        // Buscamos las palabras que se encuentran en el diccionario. Las que no se encuentran, se buscan las 4 palabras con menor distancia
        for (i in 0 until arregloPalabras.size) {
            val palabra = arregloPalabras[i].trim()
            // Si la palabra se encuentra en el diccionario, se salta a la siguiente palabra
            if (buscarPalabra(palabra)) {
                arregloPalabras[i] = ""
                continue
            } else if (diccTemporal.buscarPalabra(palabra)) {
                // Si la palabra ya se encuentra en el diccionario temporal, se salta a la siguiente palabra
                arregloPalabras[i] = ""
                continue
            } else {
                // En caso contrario, se imprime en el archivo de salida la palabra y las 4 palabras con menor distancia
                val palabrasCercanas = buscarPalabrasConMenorDistancia(palabra)
                arregloPalabras[i] = "$palabra,${palabrasCercanas[0]},${palabrasCercanas[1]},${palabrasCercanas[2]},${palabrasCercanas[3]}"
                // Se agrega la palabra al diccionario temporal
                val primeraLetra = palabra[0]
                val indice = diccTemporal.dicc.indexOfFirst { it.character == primeraLetra }
                diccTemporal.dicc[indice].agregarPalabra(palabra)
            }
        }

        // Se crea un objeto File con el nombre del archivo de salida
        val fileOut = File(foutput)

        // Si el archivo no existe, se crea. En caso contrario, se sobreescribe.
        if (!fileOut.exists()) fileOut.createNewFile() else fileOut.writeText("")

        // Se crea un objeto BufferedWriter para escribir en el archivo.
        val bufferedWriter = fileOut.bufferedWriter()

        // Se escribe en el archivo de salida el arreglo de palabras corregidas
        bufferedWriter.use { out ->
            for (palabra in arregloPalabras) {
                // Se escriben las palabras que no estén en el diccionario con sus sugerencias
                if (palabra != "") {
                    out.write(palabra)
                    out.newLine()
                }
            }
        }
        // Se cierra el archivo de salida
        bufferedWriter.close()

        // Se informa al usuario que el archivo de salida ha sido creado y que ahí se encuentran las palabras corregidas
        println("El archivo $foutput ha sido creado con las palabras corregidas.\n")
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
        return arregloPalabras.filter { it != "" && it != " " && esPalabraValida(it) }.toTypedArray()
    }

    private fun crearArregloPalabras(): Array<String> {
        var arregloPalabras = arrayOf<String>()
        // Iterar sobre el diccionario. Cada elemento del diccionario es de tipo PMLI
        // es decir, el conjunto de palabras que empiezan con una letra. Y cuyas palabras estan en una lista circular
        for (i in 0 until dicc.size) {
            // Se obtiene el arreglo de palabras de la letra i
            val palabras = dicc[i].crearArregloPalabras()
            // Se agrega el arreglo de palabras al arreglo de palabras
            arregloPalabras += palabras
        }
        return arregloPalabras
    }

    /**
     * buscarPalabrasConMenorDistancia(arregloPalabras: Array<String>)
     * Método que busca las 4 palabras con menor distancia en el diccionario.
     * @param arregloPalabras: Array<String> -> Arreglo ordenado con las palabras válidas.
     * @return palabrasConMenorDistancia: Array<String> -> Arreglo con las 4 palabras con menor distancia.
     */
    private fun buscarPalabrasConMenorDistancia(palabra: String): Array<String> {
        // Se declara un arreglo con las palabras del diccionario
        val palabras = this.crearArregloPalabras()
        // Se crea un arreglo con las 4 palabras con menor distancia
        val palabrasConMenorDistancia = Array(4) { "" }
        // Iteramos sobre las 4 palabras con menor distancia
        for (i in 0 until 4) {
            // Se inicializa la menor distancia con un valor muy grande
            var menorDistancia = Int.MAX_VALUE
            // Iteramos sobre las palabras del diccionario
            for (j in 0 until palabras.size) {
                // Se calcula la distancia de Damerau-Levenshtein entre la palabra y la palabra del diccionario
                val distancia = osaDistance(palabra, palabras[j])
                // Si la distancia es menor que la menor distancia y la palabra no se encuentra en el arreglo de palabras con menor distancia,
                // se actualiza la menor distancia y se agrega la palabra al arreglo de palabras con menor distancia
                if (distancia < menorDistancia && palabras[j] !in palabrasConMenorDistancia) {
                    menorDistancia = distancia
                    palabrasConMenorDistancia[i] = palabras[j]
                }
            }
        }
        return palabrasConMenorDistancia
    }

    /**
     * osaDistance(a: String, b: String)
     * Metodo para buscar la distancia entre dos palabras usando el algoritmo de Damerau-Levenshtein
     * @param a: String -> Primera palabra
     * @param b: String -> Segunda palabra
     * @return Int -> Distancia entre las dos palabras
     */
    fun osaDistance(a: String, b: String): Int {
        // Se crea una matriz de tamaño a.length + 1 por b.length + 1
        val d = Array(a.length + 1) { IntArray(b.length + 1) }
        // Se inicializa la primera fila y la primera columna
        for (i in 0..a.length) {
            d[i][0] = i
        }
        for (j in 0..b.length) {
            d[0][j] = j
        }
        // Se calcula la distancia de Damerau-Levenshtein
        for (i in 1..a.length) {
            for (j in 1..b.length) {
                // Se calcula el costo
                val cost = if (a[i - 1] == b[j - 1]) 0 else 1
                // Se toma el mínimo entre la eliminación, la inserción, la sustitución y la transposición
                d[i][j] = minOf(
                    d[i - 1][j] + 1, // deletion
                    d[i][j - 1] + 1, // insertion
                    d[i - 1][j - 1] + cost, // substitution
                )
                // Si se puede hacer la transposición, se toma el mínimo entre la transposición y el costo
                if (i > 1 && j > 1 && a[i - 1] == b[j - 2] && a[i - 2] == b[j - 1]) {
                    d[i][j] = minOf(d[i][j], d[i - 2][j - 2] + 1) // transposition
                }
            }
        }
        // Se devuelve la distancia de Damerau-Levenshtein, que se encuentra en la última posición de la matriz
        return d[a.length][b.length]
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
