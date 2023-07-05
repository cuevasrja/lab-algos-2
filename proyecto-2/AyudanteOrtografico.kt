/**
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

class AyudanteOrtografico(){
    // Atributos de la clase AyudanteOrtografico
    val MAX = 27
    var diccionario: Array<PMLI> = arrayOf(PMLI('a'), PMLI('b'), PMLI('c'), PMLI('d'), PMLI('e'), PMLI('f'), PMLI('g'), PMLI('h'), PMLI('i'), PMLI('j'), PMLI('k'), PMLI('l'), PMLI('m'), PMLI('n'), PMLI('ñ'), PMLI('o'), PMLI('p'), PMLI('q'), PMLI('r'), PMLI('s'), PMLI('t'), PMLI('u'), PMLI('v'), PMLI('w'), PMLI('x'), PMLI('y'), PMLI('z'))

    // Métodos de la clase AyudanteOrtografico
    init {
        println("¡Bienvenido al Ayudante Ortográfico!")
    }

    /**
    * cargarDiccionario(fname: String)
    * Método que carga el diccionario en la estructura.
    * @param fname: String -> Nombre del archivo que contiene el diccionario.
    * Precondición: true.
    * Postcondición: se carga el diccionario en la estructura.
    */
    fun cargarDiccionario(fname: S){
        val file = File(fname)
        val bufferedReader = file.bufferedReader()
        bufferedReader.useLines { lines ->
            lines.forEach {
                val palabra = it.toLowerCase().trim()
                if (esPalabraValida(palabra)) {
                    val primeraLetra = palabra[0]
                    val indice = diccionario.indexOfFirst { it.character == primeraLetra }
                    diccionario[indice].agregarPalabra(palabra)
                }
                else {
                    throw IllegalArgumentException("La palabra $palabra no es válida.")
                }
            }
        }
    }

    /**
    * eliminarPalabra(palabra: String)
    * Método que elimina la palabra de la estructura.
    * @param palabra: String -> Palabra que se desea eliminar.
    * Precondición: la palabra debe pertenecer a la estructura.
    * Postcondición: se elimina la palabra de la estructura.
    */
    fun eliminarPalabra(palabra: String) {
        // Si la palabra no es válida, se lanza una excepción
        if (!esPalabraValida(palabra)) {
            throw IllegalArgumentException("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
        }
        // Se obtiene la primera letra de la palabra
        val primeraLetra = palabra[0]
        // Se obtiene el índice de la primera letra en el arreglo
        val indice = diccionario.indexOfFirst { it.character == primeraLetra }
        // Si la palabra se encuentra en la estructura, se elimina
        if (diccionario[indice].buscarPalabra(palabra)) {
            diccionario[indice].eliminarPalabra(palabra)
        }
        // Si la palabra no se encuentra en la estructura, se lanza una excepción
        else {
            throw IllegalArgumentException("La palabra no se encuentra en la estructura.")
        }
    }

    /**
    * corregirTexto(fin: String, fout: String)
    * Método que corrige el texto de entrada y lo escribe en el archivo de salida.
    * @param fin: String -> Nombre del archivo de entrada.
    * @param fout: String -> Nombre del archivo de salida.
    * Precondición: true.
    * Postcondición: se corrige el texto de entrada e imprime en el archivo de salida las palabras que no se encuentran en el diccionario,
    *               seguidas de las 4 palabras con menor distancia.
    */
    fun corregirTexto(fin: String, fout: String){
        // TODO: Implementar
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
                str += diccionario[i].toString()
            }
            else {
                str += diccionario[i].toString() + "\n"
            }
        }
        return str
    }
}
