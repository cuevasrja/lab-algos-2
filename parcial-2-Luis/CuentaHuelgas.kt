/*
 * Parcial 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autor: Luis Isea (19-10175).
 *
 * CuentaHuelgas.kt: Implementacion de la solución al problema de
 * contar cuántos días de huelga habrá en un periodo de tiempo dado.
 *
 * Para la solución se hará uso de la estructura de datos basada en una
 * tabla de hash. Pero con las modificaciones necesarias para que no se tomen
 * en cuenta ni los días sábados ni los domingos.
 */

/**
 * Clase HuegaHashTable: Implementación de una tabla de hash modificada para
 * resolver el problema de contar cuántos días de huelga habrá en un periodo
 * de días dado.
 * @param size: Tamaño de la tabla de hash, que también es el número de días a estudiar.
 * @property table: Tabla de hash que contiene las listas circulares.
 * @property diasHuelga: Número de días de huelga que hay en el periodo de tiempo dado.
 * Para diasHuelga no se toman en cuenta los días sábados ni los domingos.
 *
 * No se agrega el método buscar porque no es necesario para la solución del problema.
 */
class HuelgaHashTable(private val size: Int) {
    // Atributos de la clase HuelgaHashTable.

    // table: Tabla de hash que contiene las listas circulares, que representan los días.
    private val table: Array<CircularList> = Array(size) { CircularList() }

    // diasHuelga: Número de días de huelga que hay en el periodo de tiempo dado.
    private var diasHuelga: Int = 0

    // Métodos de la clase HuelgaHashTable.

    /**
     * hash(periodo: Int) -> Método que calcula los índices de la tabla de hash que
     * corresponden a los días de huelga de un sindicato.
     * @param periodo: Int -> Periodo de días de huelga de un sindicato.
     * @return Array<Int>: Arreglo de índices de la tabla de hash, que corresponden
     * a los días de huelga de un sindicato. No se agregan los días sábados ni los domingos.
     */
    private fun hash(periodo: Int): Array<Int> {
        var i = 1
        var num = 0
        // Se calcula primero el número de días de huelga que hay en el periodo de tiempo dado.
        while (i * periodo <= size) {
            // Si el día no es sábado ni domingo, se aumenta el contador.
            if (((i * periodo) % 7 != 6) && ((i * periodo) % 7 != 0)) {
                num++
            }
            i++
        }
        i = 1
        var j = 0
        // Se crea un arreglo con los índices de la tabla de hash que corresponden a los días de huelga.
        val arr = Array(num) { 0 }
        while (i * periodo <= size) {
            // Si el día no es sábado ni domingo, se agrega el índice correspondiente al arreglo.
            if (((i * periodo) % 7 != 6) && ((i * periodo) % 7 != 0)) {
                arr[j] = (i * periodo) - 1
                j++
            }
            i++
        }
        return arr
    }

    /**
     * insert(periodo: Int) -> Método que agrega a la tabla de hash los días
     * de huelga de un sindicato.
     * @param periodo: Int -> Periodo de días de huelga de un sindicato.
     */
    fun insert(periodo: Int) {
        // Se calculan los índices de la tabla de hash que corresponden a los días de huelga.
        val index = hash(periodo)

        // Se agregan los días de huelga a la tabla de hash.
        for (i in index) {
            // Si la lista circular está vacía, se aumenta el número de días de huelga.
            if (table[i].isEmpty()) this.diasHuelga++
            table[i].addHuelga()
        }
    }

    /**
     * delete(periodo: Int) -> Método que elimina de la tabla de hash los días
     * de huelga de un sindicato.
     * @param periodo: Int -> Periodo de días de huelga de un sindicato.
     */
    fun delete(periodo: Int) {
        // Se calculan los índices de la tabla de hash que corresponden a los días de huelga.
        val index = hash(periodo)

        // Se eliminan los días de huelga de la tabla de hash.
        for (i in index) {
            table[i].deleteHuelga()
            if (table[i].isEmpty()) this.diasHuelga--
        }
    }

    /**
     * contarDiasHuelga() -> Método que cuenta los días de huelga que hay en el periodo de tiempo dado.
     * @return Int: Número de días de huelga que hay en el periodo de tiempo dado.
     */
    fun contarDiasHuelga(): Int {
        return this.diasHuelga
    }

    /**
     * toString() -> Método que devuelve una representación en String de la tabla de hash.
     * @return String: Representación en String de la tabla de hash.
     */
    override fun toString(): String {
        var str = ""
        for (i in 0 until size) {
            str += "[${i + 1}] -> "
            var nodo = table[i].sentinel.next
            while (nodo != table[i].sentinel) {
                str += "${nodo!!.huelga} -> "
                nodo = nodo.next
            }
            str += "null\n"
        }
        return str
    }
}

/**
 * Clase CircularList: Implementación de una lista circular.
 * Los elementos de la lista son de tipo Nodo.
 * @property sentinel: Nodo centinela de la lista circular.
 */
class CircularList() {
    // Atributos de la clase CircularList.

    // sentinel: Nodo centinela de la lista circular.
    // Este nodo apunta tanto al primer como al último nodo de la lista.
    val sentinel = Nodo(null)

    // Métodos de la clase CircularList.

    /**
     * init -> Método constructor que inicializa la lista circular.
     */
    init {
        sentinel.next = sentinel
        sentinel.prev = sentinel
    }

    /**
     * isEmpty() -> Método que verifica si la lista circular está vacía.
     * @return Boolean: True si la lista circular está vacía, false en caso contrario.
     */
    fun isEmpty(): Boolean {
        return sentinel.next == sentinel
    }

    /**
     * addHuelga() -> Método que agrega una huelga representada por un nodo true
     * en la lista circular.
     * Se agrega en la cabeza de la lista.
     */
    fun addHuelga() {
        val newNode = Nodo(true)
        newNode.next = sentinel.next
        newNode.prev = sentinel
        sentinel.next!!.prev = newNode
        sentinel.next = newNode
    }

    /**
     * deleteHuelga() -> Método que elimina una huelga representada por un nodo true
     * Se elimina de la cola de la lista.
     */
    fun deleteHuelga() {
        if (!this.isEmpty()) {
            sentinel.prev!!.prev!!.next = sentinel
            sentinel.prev = sentinel.prev!!.prev
        }
    }
}

/**
 * Clase Nodo: Implementación de un nodo de una lista circular.
 * @property huelga: Booleano que indica si el nodo representa un día de huelga.
 * @property next: Referencia al siguiente nodo de la lista circular.
 * @property prev: Referencia al nodo anterior de la lista circular.
 */
class Nodo(var huelga: Boolean?) {
    var next: Nodo? = null
    var prev: Nodo? = null
}

/**
 * main(args: Array<String>) -> Función principal del programa.
 * Recibe como argumentos el número de días del periodo de tiempo, el número de sindicatos
 * y los periodos de huelga de cada sindicato.
 * @param args: Array<String> -> Argumentos de la función main.
 * Por último, imprime el número de días de huelga que hay en el periodo de tiempo dado.
 */
fun main(args: Array<String>) {
    // Se procesan los argumentos de la función main.
    val numDias = args[0].toInt()
    val numSindicatos = args[1].toInt()
    val periodosSindicatos = Array(numSindicatos) { 0 }
    for (i in 0 until numSindicatos) {
        periodosSindicatos[i] = args[i + 2].toInt()
    }

    // Se crea la tabla de hash.
    val registroHuelgas = HuelgaHashTable(numDias)
    // Se insertan los periodos de huelga de los sindicatos en la tabla de hash.
    for (i in 0 until numSindicatos) {
        registroHuelgas.insert(periodosSindicatos[i])
    }

    // Se imprime el número de días de huelga que hay en el periodo de tiempo dado.
    println(registroHuelgas.contarDiasHuelga())
}
