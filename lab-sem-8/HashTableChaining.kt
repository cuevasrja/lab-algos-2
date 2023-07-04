/*
 * Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

/**
 * Creacion de la clase HashTableChaining usando tablas de hash con encadenamiento
 * Por defecto, la tabla de hash tiene 7 elementos. Pero su tamaño es dinámico.
 * @property conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
 * @property tabla: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
 * @property numElementos: Int -> Número de elementos que hay en la tabla de hash
 */
class HashTableChaining() {
    // conocidas: Array<CircularList> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
    private var conocidas: Array<CircularList> = Array(7) { CircularList() }

    // tabla: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    private var tabla: Array<CircularList> = Array(7) { CircularList() }

    // numElementos: Int -> Número de elementos que hay en la tabla de hash
    private var numElementos: Int = 0

    // size: Int -> Tamaño de la tabla de hash
    private var size: Int = 7

    // Métodos de la clase HashTableChaining

    // hashFunction(clave: Int): Int -> Función hash que devuelve el índice de la lista en la tabla de hash donde se debe insertar el elemento o donde se encuentra el elemento
    private fun hashFunction(clave: Int): Int {
        // Esta función hash devuelve el residuo de la división de la clave entre el tamaño de la tabla de hash
        return clave % this.hashSize()
    }

    // incr(size: Int): Int -> Función que devuelve el nuevo tamaño de la tabla de hash
    private fun incr(size: Int): Int {
        return ((size + 16) * 3 / 2).toInt()
    }

    // rehash(): Unit -> Función que hace rehash de la tabla de hash
    private fun rehash() {
        // Se incrementa el tamaño de la tabla de hash
        this.size = this.incr(this.hashSize())

        // Se crea una nueva tabla de hash más grande que reemplazará a la anterior
        this.tabla = Array(hashSize()) { CircularList() }

        // Reiniciamos el número de elementos que hay en la tabla
        this.numElementos = 0

        // Se crea un registro de las claves que ya fueron insertadas en la tabla de hash anterior
        val anterioresConocidas = this.conocidas

        // Se reinicia el registro de las claves que ya fueron insertadas en la tabla de hash
        this.conocidas = Array(hashSize()) { CircularList() }

        // Se recorre la tabla de hash anterior
        for (i in 0 until anterioresConocidas.size) {
            var nodo = anterioresConocidas[i].obtenerPrimero()
            // Se verifica si la lista de la posición i de la tabla de hash anterior está vacía
            if (nodo != null) {
                // Si no está vacía, entonces se insertan los elementos de la lista en la nueva tabla de hash
                while (nodo != anterioresConocidas[i].sentinel) {
                    this.agregar(nodo?.obtenerClave()!!, nodo.obtenerValor()!!)
                    // Se avanza al siguiente nodo
                    nodo = nodo.next!!
                }
            }
        }
    }

    // obtenerFactorCarga(): Double -> Función que devuelve el factor de carga de la tabla de hash
    fun obtenerFactorCarga(): Double {
        return (this.numElementos.toDouble() / this.hashSize().toDouble())
    }

    // agregar(clave: Int, valor: String): Unit -> Función que agrega un elemento a la tabla de hash
    fun agregar(clave: Int, valor: String) {
        // Si la clave ya está en la tabla de hash, entonces no se agrega
        if (this.existe(clave)) return

        // Si el factor de carga es mayor o igual a 0.7, entonces se hace rehash
        if (this.obtenerFactorCarga() >= 0.7) this.rehash()

        // Se obtiene el índice de la tabla de hash donde se debe insertar el elemento
        val indice = this.hashFunction(clave)

        // Se inserta el elemento en la tabla de hash
        this.tabla[indice].agregarAlFrente(clave, valor)

        // Se agrega la clave a conocidas
        this.conocidas[indice].agregarAlFrente(clave, valor)

        // Se aumenta el número de elementos en la tabla de hash
        this.numElementos++
    }

    // eliminar(clave: Int): Unit -> Función que elimina un elemento de la tabla de hash
    fun eliminar(clave: Int) {
        // Si la tabla de hash está vacía, entonces no se elimina nada
        if (this.obtenerNumElementos() == 0) return

        // Se obtiene el índice de la tabla de hash donde se debe eliminar el elemento
        val indice = this.hashFunction(clave)

        // Si el elemento existe en la tabla de hash, entonces se elimina
        if (this.tabla[indice].eliminar(clave)) {
            // Se disminuye el número de elementos en la tabla de hash
            this.numElementos--
            // Se elimina la clave de conocidas
            this.conocidas[indice].eliminar(clave)
        }
    }

    // buscar(clave: Int): String? -> Función que busca un elemento en la tabla de hash
    fun buscar(clave: Int): String? {
        // Se obtiene el índice de la tabla de hash donde se debe buscar el elemento
        val indice = this.hashFunction(clave)

        // Se busca el elemento en la tabla de hash
        return this.tabla[indice].buscar(clave)
    }

    // existe(clave: Int): Boolean -> Función que verifica si un elemento existe en la tabla de hash
    fun existe(clave: Int): Boolean {
        // Se obtiene el índice de la tabla de hash donde se debe buscar el elemento
        val indice = this.hashFunction(clave)

        // Se busca el elemento en la tabla de hash
        return this.tabla[indice].existe(clave)
    }

    // obtenerNumElementos(): Int -> Función que devuelve el número de elementos en la tabla de hash
    fun obtenerNumElementos(): Int {
        return this.numElementos
    }

    // hashSize(): Int -> Función que devuelve el tamaño de la tabla de hash
    fun hashSize(): Int {
        return this.size
    }

    // override fun toString(): String -> Función que devuelve una representación en String de la tabla de hash
    override fun toString(): String {
        var str = ""
        for (i in 0 until this.hashSize()) {
            if (this.tabla[i].obtenerPrimero() != null) {
                str += "[$i] -> ${this.tabla[i]}\n"
            } else {
                str += "[] -> ${this.tabla[i]}\n"
            }
        }
        return str
    }
}
