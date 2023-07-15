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
class PMLI(character: Char) {
    // Atributos de la clase PMLI

    // character: Caracter que representa la letra inicial de las palabras que se almacenarán en la estructura.
    val character: Char

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
        // Se verifica que el caracter sea una letra minúscula del alfabeto español.
        require(alfabet.perteneceAlAlfabeto(character)) { "Carácter inválido: Debe ser una letra minúscula del alfabeto español." }
        this.character = character
    }

    /**
     * agregarPalabra(palabra: String): Unit
     * Método que agrega una palabra a la estructura.
     * @param palabra: String -> Palabra que se desea agregar.
     * Precondición: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Postcondición: se agrega la palabra a la estructura.
     */
    fun agregarPalabra(palabra: String) {
        // Si la palabra no cumple con las precondiciones, se informa al usuario y se detiene la ejecución del método.
        if (!esPalabraValida(palabra) || palabra[0] != this.character) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
            println("La palabra debe empezar con el caracter de la estructura: ${this.character}.")
            return
        }
        // Si la palabra ya se encuentra en la estructura, no se agrega
        if (buscarPalabra(palabra)) {
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
        // Si la palabra no es válida, se informa al usuario y se detiene la ejecución del método.
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
        // Si la palabra no es válida, se informa al usuario y se detiene la ejecución del método.
        if (!esPalabraValida(palabra)) {
            println("La palabra debe contener únicamente letras minúsculas del alfabeto español.")
            return
        }
        // Si la palabra se encuentra en la estructura, se elimina
        if (buscarPalabra(palabra)) {
            this.palabras.eliminar(palabra)
        }
        // Si no, se informa al usuario
        else {
            println("La palabra $palabra no se encuentra en la estructura.")
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
     * crearArregloPalabras(): Array<String>
     * Método que devuelve un arreglo con las palabras que se encuentran en la estructura.
     * Precondición: true.
     * Postcondición: se devuelve un arreglo con las palabras que se encuentran en la estructura.
     */
    fun crearArregloPalabras(): Array<String> {
        return this.palabras.crearArregloPalabras()
    }

    /**
     * toString(): String
     * Método que devuelve una representación en String de la estructura.
     * Precondición: true.
     * Postcondición: se devuelve una representación en String de la estructura.
     */
    override fun toString(): String {
        // Muestra los elementos del arreglo de palabras separados por un espacio y en orden lexicográfico
        var str = "\u001b[34m${this.character}\u001b[0m: \n"
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

/**
 * compararPalabras(palabra1: String, palabra2: String): Int
 * Función que compara dos palabras en el idioma español.
 * @param palabra1: String -> Primera palabra que se desea comparar.
 * @param palabra2: String -> Segunda palabra que se desea comparar.
 * Precondición: true.
 * Postcondición: devuelve -1 si la primera palabra es menor que la segunda, 1 si la primera palabra es mayor que la segunda, 0 si son iguales.
 */
fun compararPalabras(palabra1: String, palabra2: String): Int {
    var i = 0
    while (i < palabra1.length && i < palabra2.length) {
        if (alfabet.obtenerValor(palabra1[i]) < alfabet.obtenerValor(palabra2[i])) {
            return -1
        } else if (alfabet.obtenerValor(palabra1[i]) > alfabet.obtenerValor(palabra2[i])) {
            return 1
        }
        i++
    }
    if (palabra1.length < palabra2.length) {
        return -1
    }
    if (palabra1.length > palabra2.length) {
        return 1
    }
    return 0
}
