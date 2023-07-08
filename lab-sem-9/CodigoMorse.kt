/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * CodigoMorse.kt -> Este archivo contiene la clase que representa el TAD CodigoMorse,
 * que sirve para descifrar el codigo morse.
 */

/**
 * Clase CodigoMorse -> Representa el TAD Código Morse que sirve para descifrar el codigo morse.
 * @property alfabeto: Arbol de decision binario que representa el alfabeto en morse.
 */
public class CodigoMorse() {
    // Atributos de la clase CodigoMorse.

    // alfabeto: ArbolDeDecision -> Arbol de decision binario que representa el alfabeto en morse.
    private val alfabeto = ArbolDeDecision()

    /*
     * Representacion del arbol de decision binario que almacenara el alfabeto en morse.
     *
     *                        raiz
     *                        /  \
     *                       /    \
     *                      /      \
     *                     /        \
     *                .   /          \ -
     *                   /            \
     *                  /              \
     *                 /                \
     *                /                  \
     *               /                    \
     *              /                      \
     *             E                        T
     *            / \                      / \
     *        .  /   \  -               . /   \ -
     *          /     \                  /     \
     *         /       \                /       \
     *        I         A              N         M
     *     . / \ -   . / \ -        . / \ -   . / \ -
     *      /   \     /   \          /   \     /   \
     *     S    U   R     W        D     K    G     O
     *   ./ \- ./ ./   . / \-    ./ \- ./ \- .| \-
     *   H   V F  L     P  J     B   X C   Y  Z  Q
     */

    // Métodos de la clase CodigoMorse.

    /**
     * init
     * Método constructor que crea un arbol de decision binario con todas las letras del alfabeto en morse.
     * Precondicion: true.
     * Postcondicion: Se crea un arbol de decision binario con todas las letras del alfabeto en morse.
     */
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
     * Metodo que recibe un codigo morse y devuelve la letra correspondiente,
     * o null si el codigo morse no es valido.
     * @param codigo: String -> Codigo morse a decodificar.
     * @return Char -> Letra correspondiente al codigo morse.
     * Precondicion: true.
     * Postcondicion: Se devuelve la letra correspondiente al codigo morse,
     * o null si el codigo morse no es valido.
     */
    fun decodificarLetra(codigo: String): Char? {
        // Se busca el codigo morse en el arbol binario.
        return alfabeto.buscar(codigo)
    }

    /**
     * fun decodificarMensaje(mensaje: String): String
     * Metodo que recibe un mensaje en codigo morse y devuelve el mensaje en texto correspondiente,
     * o un mensaje vacio si el mensaje en codigo morse no es valido.
     * @param mensaje: String -> Mensaje en codigo morse a decodificar.
     * @return String -> Mensaje en texto correspondiente al mensaje en codigo morse.
     * Precondicion: true.
     * Postcondicion: Se devuelve el mensaje en texto correspondiente al mensaje en codigo morse,
     * o un mensaje vacio si el mensaje en codigo morse no es valido.
     */
    fun decodificarMensaje(mensaje: String): String {
        // Se separan las palabras del mensaje, el mensaje se separa por espacios y las palabras por "/".
        val palabras = mensaje.uppercase().split("/")
        // Se inicializa el mensaje decodificado.
        var mensajeDecodificado = ""
        // Si hay un caracter que no sea '.', '-' o espacio, se devuelve un mensaje vacio.
        for (palabra in palabras) {
            for (letra in palabra) {
                if (letra != '.' && letra != '-' && letra != ' ') {
                    return ""
                }
            }
        }
        // Se recorren las palabras del mensaje.
        for (palabra in palabras) {
            // Se separan las letras de la palabra, la palabra se separa por espacios.
            val letras = palabra.trim().split(" ")
            // Se recorren las letras de la palabra.
            for (letra in letras) {
                // Se decodifica la letra.
                val letraDecodificada: Char? = decodificarLetra(letra.trim())
                // Si la letra no se encuentra en el alfabeto, se devuelve un mensaje vacio.
                if (letraDecodificada == null) {
                    return ""
                }
                // En caso contrario, se agrega la letra decodificada al mensaje decodificado.
                mensajeDecodificado += letraDecodificada
            }
            // Se agrega un espacio al mensaje decodificado para separar las palabras.
            mensajeDecodificado += " "
        }
        return mensajeDecodificado.trim().lowercase()
    }

    /**
     * toString
     * Metodo que devuelve la representacion en String del alfabeto en morse.
     * Precondicion: true.
     * Postcondicion: Se devuelve la representacion en String del alfabeto en morse.
     */
    override fun toString(): String {
        return alfabeto.toString()
    }
}
