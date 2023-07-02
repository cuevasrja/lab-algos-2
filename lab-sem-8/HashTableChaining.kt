// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Creacion de la clase HashTableChaining usando tablas de hash con encadenamiento
* Por defecto, la tabla de hash tiene 7 elementos. Pero su tamaño es dinámico.
* @property conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
* @property tabla: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
* @property numElementos: Int -> Número de elementos que hay en la tabla de hash
*/
class HashTableChaining() {
    // conocidas: CircularList -> Lista enlazada que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
    private var conocidas: CircularList = CircularList()

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
        return ((size + 16) * 3/2).toInt()
    }

    // rehash(): Unit -> Función que hace rehash de la tabla de hash
    private fun rehash(): Unit {
        // Se incrementa el tamaño de la tabla de hash
        this.size = this.incr(this.hashSize())

        // Se crea una nueva tabla de hash más grande que reemplazará a la anterior
        this.tabla = Array(hashSize()) { CircularList() }

        // Se obtiene la primera clave conocida
        var claveConocida = this.conocidas.obtenerPrimero()

        // Se recorren las claves conocidas
        while (claveConocida != this.conocidas.sentinel) {
            // Se obtiene la clave y el valor de la clave conocida
            val clave = claveConocida?.obtenerClave()!!
            val valor = claveConocida.obtenerValor()!!
            // Se agrega la clave conocida a la nueva tabla de hash
            if (!this.agregar(clave, valor)) {
                // Si no se pudo agregar la clave conocida, entonces se elimina de la lista de claves conocidas
                this.conocidas.eliminar(clave)
                numElementos--
            }

            // Se obtiene la siguiente clave conocida
            claveConocida = claveConocida.next
        }
    }

    // obtenerFactorCarga(): Double -> Función que devuelve el factor de carga de la tabla de hash
    fun obtenerFactorCarga(): Double {
        return (this.numElementos.toDouble() / this.hashSize().toDouble())
    }

    // agregar(clave: Int, valor: String): Boolean -> Función que agrega un elemento a la tabla de hash, retorna true si se agrega y false si no
    fun agregar(clave: Int, valor: String): Boolean {
        // Si la clave ya está en la tabla de hash, entonces no se agrega. Se retorna false
        if (this.existe(clave)) return false

        // Si la tabla de hash está llena, entonces se hace rehash
        if (this.obtenerFactorCarga() >= 0.7) this.rehash()

        // Se obtiene el índice de la tabla de hash donde se debe insertar el elemento
        val indice = this.hashFunction(clave)

        // Se inserta el elemento en la tabla de hash
        this.tabla[indice].agregarAlFrente(clave, valor)

        // Se agrega la clave a la lista de claves conocidas
        this.conocidas.agregarAlFinal(clave, valor)

        // Se aumenta el número de elementos en la tabla de hash
        this.numElementos++

        // Se retorna true porque se agregó el elemento
        return true
    }

    // eliminar(clave: Int): Unit -> Función que elimina un elemento de la tabla de hash
    fun eliminar(clave: Int): Unit {
        // Si la tabla de hash está vacía, entonces no se elimina nada
        if (this.obtenerNumElementos() == 0) return

        // Se obtiene el índice de la tabla de hash donde se debe eliminar el elemento
        val indice = this.hashFunction(clave)

        // Si el elemento existe en la tabla de hash, entonces se elimina
        if (this.tabla[indice].eliminar(clave)) {
            // Se disminuye el número de elementos en la tabla de hash
            this.numElementos--
            // Se elimina la clave de conocidas
            this.conocidas.eliminar(clave)
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

    fun obtenerNumClavesConocidas(): Int {
        return this.conocidas.getSize()
    }

    // override fun toString(): String -> Función que devuelve una representación en String de la tabla de hash
    override fun toString(): String {
        var str = "Claves conocidas: ${this.conocidas}\n"
        for (i in 0 until this.hashSize()) {
            if (this.tabla[i].obtenerPrimero() != null) str += "[$i] -> ${this.tabla[i]}\n"
            else str += "[] -> ${this.tabla[i]}\n"
        }
        return str
    }
}

// createDictionaryChaining(): HashTableChaining -> Función que crea un dictionatio que es un objeto de la clase HashTableChaining
fun createDictionaryChaining(): HashTableChaining {
    return HashTableChaining()
}
