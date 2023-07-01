// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Creacion de la clase DictionaryChaining usando tablas de hash con encadenamiento
* Por defecto, la tabla de hash tiene 7 elementos. Pero su tamaño es dinámico.
* @property conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
* @property tabla: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
* @property numElementos: Int -> Número de elementos que hay en la tabla de hash
*/
class DictionaryChaining() {
    // conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
    var conocidas: Array<Int?> = Array(7) { null }
    // tabla: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    var tabla: Array<CircularList> = Array(7) { CircularList() }

    // numElementos: Int -> Número de elementos que hay en la tabla de hash
    var numElementos: Int = 0

    // Método constructor de la clase Dictionary
    init {
        // Inicializamos la tabla de hash con 7 elementos
        for (i in 0 until 7) {
            this.tabla[i] = CircularList()
        }
    }

    // Métodos de la clase DictionaryChaining
    // hashFunction(clave: Int): Int -> Función hash que devuelve el índice de la lista en la tabla de hash donde se debe insertar el elemento o donde se encuentra el elemento
    fun hashFunction(clave: Int): Int {
        return clave % this.tabla.size
    }

    // rehash(): Unit -> Función que hace rehash de la tabla de hash
    fun rehash(): Unit {
        // Se crea una nueva tabla de hash con un tamaño mayor
        val nuevaTabla = Array((this.tabla.size + 16) * 3/2) { CircularList() }

        // Se inicializa la nueva tabla de hash
        for (i in 0 until nuevaTabla.size) {
            nuevaTabla[i] = CircularList()
        }

        // Se actualizan las claves conocidas
        this.conocidas = Array(nuevaTabla.size) { 0 }

        // Se recorre la tabla de hash actual
        for (i in 0 until this.tabla.size) {
            // Se obtiene el primer elemento de la lista enlazada en la posición i de la tabla de hash actual
            var elemento = this.tabla[i].obtenerPrimero()
            // Si la lista está vacía entonces saltamos a la siguiente lista
            if (elemento == null) continue
            // Se recorre la lista enlazada en la posición i de la tabla de hash actual
            while (elemento != this.tabla[i].sentinel) {
                // Se obtiene el índice de la nueva tabla de hash donde se debe insertar el elemento
                val indice = this.hashFunction(elemento!!.obtenerClave()!!)
                // Se inserta el elemento en la nueva tabla de hash
                nuevaTabla[indice].agregarAlFinal(elemento.obtenerClave()!!, elemento.obtenerValor()!!)
                // Se actualiza la lista de claves conocidas
                this.conocidas[indice] = elemento.obtenerClave()!!

                // Se obtiene el siguiente elemento de la lista
                elemento = elemento.next!!
            }
        }

        // Se actualiza la tabla de hash
        this.tabla = nuevaTabla
    }

    // obtenerFactorCarga(): Double -> Función que devuelve el factor de carga de la tabla de hash
    fun obtenerFactorCarga(): Double {
        return this.numElementos.toDouble() / this.tabla.size.toDouble()
    }

    // agregar(clave: Int, valor: String): Unit -> Función que agrega un elemento a la tabla de hash
    fun agregar(clave: Int, valor: String): Unit {
        // Si la clave ya está en la tabla de hash, entonces no se agrega
        if (this.existe(clave)) return

        // Si la tabla de hash está llena, entonces se hace rehash
        if (this.obtenerFactorCarga() > 0.7) this.rehash()

        // Se obtiene el índice de la tabla de hash donde se debe insertar el elemento
        val indice = this.hashFunction(clave)

        // Se inserta el elemento en la tabla de hash
        this.tabla[indice].agregarAlFinal(clave, valor)

        // Se agrega la clave a la lista de claves conocidas
        this.conocidas[indice] = clave

        // Se aumenta el número de elementos en la tabla de hash
        this.numElementos++
    }

    // eliminar(clave: Int): Unit -> Función que elimina un elemento de la tabla de hash
    fun eliminar(clave: Int): Unit {
        // Si la tabla de hash está vacía, entonces no se elimina nada
        if (this.obtenerNumElementos() == 0) return

        // Se obtiene el índice de la tabla de hash donde se debe eliminar el elemento
        val indice = this.hashFunction(clave)

        // Si el elemento existe en la tabla de hash, entonces se elimina
        if (this.tabla[indice].eliminar(clave)) {
            // Se elimina la clave de la lista de claves conocidas
            this.conocidas[indice] = null
            // Se disminuye el número de elementos en la tabla de hash
            this.numElementos--
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

    // getSize(): Int -> Función que devuelve el tamaño de la tabla de hash
    fun getSize(): Int {
        return this.tabla.size
    }

    // override fun toString(): String -> Función que devuelve una representación en String de la tabla de hash
    override fun toString(): String {
        var str = ""
        for (i in 0 until this.tabla.size) {
            var claveConocida = this.conocidas[i]
            if (claveConocida == null) str += "[] -> ${this.tabla[i]}\n"
            else str += "[${this.conocidas[i]}] -> ${this.tabla[i]}\n"
        }
        return str
    }
}

// createDictionaryChaining(): DictionaryChaining -> Función que crea un objeto de la clase DictionaryChaining
fun createDictionaryChaining(): DictionaryChaining {
    return DictionaryChaining()
}
