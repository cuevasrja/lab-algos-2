/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * CodigoMorse.kt -> Este archivo descifra el codigo morse que recibe mediante el archivo PruebaCodigoMorse.kt.
 */

class CodigoMorse() {
    // El alfabeto en morse se encuentra en un arbol binario de busqueda.
    private val alfabeto = ArbolBinario()

    // Se inicializa el arbol binario con el alfabeto en morse.
    init {
        alfabeto.agregar(Nodo('E', "."))
        alfabeto.agregar(Nodo('T', "-"))
        alfabeto.agregar(Nodo('I', ".."))
        alfabeto.agregar(Nodo('A', ".-"))
        alfabeto.agregar(Nodo('N', "-."))
        alfabeto.agregar(Nodo('M', "--"))
        alfabeto.agregar(Nodo('S', "..."))
        alfabeto.agregar(Nodo('U', "..-"))
        alfabeto.agregar(Nodo('R', ".-."))
        alfabeto.agregar(Nodo('W', ".--"))
        alfabeto.agregar(Nodo('D', "-.."))
        alfabeto.agregar(Nodo('K', "-.-"))
        alfabeto.agregar(Nodo('G', "--."))
        alfabeto.agregar(Nodo('O', "---"))
        alfabeto.agregar(Nodo('H', "...."))
        alfabeto.agregar(Nodo('V', "...-"))
        alfabeto.agregar(Nodo('F', "..-."))
        alfabeto.agregar(Nodo('L', ".-.."))
        alfabeto.agregar(Nodo('P', ".--."))
        alfabeto.agregar(Nodo('J', ".---"))
        alfabeto.agregar(Nodo('B', "-..."))
        alfabeto.agregar(Nodo('X', "-..-"))
        alfabeto.agregar(Nodo('C', "-.-."))
        alfabeto.agregar(Nodo('Y', "-.--"))
        alfabeto.agregar(Nodo('Z', "--.."))
        alfabeto.agregar(Nodo('Q', "--.-"))
    }

    /**
     * fun decodificarLetra(codigo: String): Char
     * Metodo que recibe un codigo morse y devuelve la letra correspondiente.
     * @param codigo: String -> Codigo morse a decodificar.
     * @return Char -> Letra correspondiente al codigo morse.
     * Precondicion: El codigo morse debe ser valido.
     * Postcondicion: Se devuelve la letra correspondiente al codigo morse.
     */
    fun decodificarLetra(codigo: String): Char {
        return alfabeto.buscar(codigo)
    }

    /**
     * fun decodificarMensaje(mensaje: String): String
     * Metodo que recibe un mensaje en codigo morse y devuelve el mensaje en texto.
     * @param mensaje: String -> Mensaje en codigo morse a decodificar.
     * @return String -> Mensaje en texto correspondiente al mensaje en codigo morse.
     * Precondicion: El mensaje en codigo morse debe ser valido.
     * Postcondicion: Se devuelve el mensaje en texto correspondiente al mensaje en codigo morse.
     */
    fun decodificarMensaje(mensaje: String): String {
        val palabras = mensaje.toUpperCase().split("/")
        var mensajeDecodificado = ""
        for (palabra in palabras) {
            val letras = palabra.trim().split(" ")
            for (letra in letras) {
                mensajeDecodificado += decodificarLetra(letra)
            }
            mensajeDecodificado += " "
        }
        return mensajeDecodificado.trim().toLowerCase()
    }
}
