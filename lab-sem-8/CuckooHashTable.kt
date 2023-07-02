// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Clase CuckooHashTable, que representa una tabla de hash con resolución de colisiones por el método de Cuckoo Hashing
* @property conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
* @property tabla1: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la primera tabla de hash
* @property tabla2: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la segunda tabla de hash
*/
class CuckooHashTable() {
    // conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
    private var conocidas: Array<Int?> = Array(7) { null }
    // tabla1: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    private var tabla1: Array<CircularList> = Array(7) { CircularList() }
    // tabla2: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    private var tabla2: Array<CircularList> = Array(7) { CircularList() }

    // numElementos: Int -> Número de elementos que hay en la tabla de hash
    private var numElementos: Int = 0

    // A: Double -> Constante que se usa en la función hash por el método de la multiplicación
    // Este es el valor de la constante sugerido por Donald Knuth
    private val A: Double = 0.6180339887

    // Métodos de la clase CuckooHashTable
    // h1(clave: Int): Int -> Función hash que se usa para la primera tabla de hash
    private fun h1(clave: Int): Int {
        // Se usa el método de la división
        return clave % this.conocidas.size
    }

    // h2(clave: Int): Int -> Función hash que se usa para la segunda tabla de hash
    private fun h2(clave: Int): Int {
        // Se usa el método de la multiplicación
        return (((clave * this.A) % 1) * this.conocidas.size).toInt()
    }

    // incr(size: Int): Int -> Función que devuelve el nuevo tamaño de la tabla de hash
    private fun incr(size: Int): Int {
        return ((size + 16) * 3/2).toInt()
    }

    // rehash(): Unit -> Función que hace rehash de la tabla de hash
    private fun rehash(): Unit {
        // Se duplica el tamaño de las tablas de hash
        val newSize = ((this.conocidas.size + 16) * 3/2).toInt()
        this.conocidas = Array(newSize) { null }
        this.tabla1 = Array(newSize) { CircularList() }
        this.tabla2 = Array(newSize) { CircularList() }
        this.numElementos = 0

        // Se insertan todos los elementos de la tabla original en las nuevas tablas de hash
        for (i in 0 until this.conocidas.size / 2) {
            val clave1 = this.tabla1[i].obtenerClave()
            val clave2 = this.tabla2[i].obtenerClave()
            // Si la clave no es nula, se inserta en la tabla de hash
            if (clave1 != null) {
                this.insertar(clave1)
            }
            // Si la clave no es nula, se inserta en la tabla de hash
            if (clave2 != null) {
                this.insertar(clave2)
            }
        }
    }

    // obtenerFactorCarga(): Double -> Función que devuelve el factor de carga de la tabla de hash
    fun obtenerFactorCarga(): Double {
        return this.numElementos.toDouble() / this.conocidas.size.toDouble()
    }

    // insertar(clave: Int, valor: String): Unit -> Función que inserta una clave en la tabla de hash.
    fun insertar(clave: Int, valor: String): Unit {
        if(this.existe(clave)) return

        // Si el factor de carga es mayor o igual a 0.7, se hace rehash
        if(this.obtenerFactorCarga() >= 0.7) {
            this.rehash()
        }

        // Se calculan los hashes de la clave
        val hash1 = hashFunction(clave)
        val hash2 = hashFunction(hash1 xor clave)

        // !REVISAR: Se inserta la clave en la tabla de hash
        if (this.tabla1[hash1].obtenerClave() == null) {
            this.tabla1[hash1].cambiarClave(clave)
            this.tabla1[hash1].cambiarValor(valor)
        } else if (this.tabla2[hash2].obtenerClave() == null) {
            this.tabla2[hash2].cambiarClave(clave)
            this.tabla2[hash2].cambiarValor(valor)
        } else {
            // Se obtiene la clave que se va a reemplazar
            val claveReemplazar = this.tabla1[hash1].obtenerClave()
            // Se reemplaza la clave
            this.tabla1[hash1].cambiarClave(clave)
            this.tabla1[hash1].cambiarValor(valor)
            // Se inserta la clave reemplazada en la tabla de hash
            this.insertar(claveReemplazar)
        }

        // Se aumenta el número de elementos de la tabla de hash
        this.numElementos++
    }

    // buscar(clave: Int): String? -> Función que busca una clave en la tabla de hash. Retorna el valor asociado a la clave si la encuentra y null en caso contrario
    fun buscar(clave: Int): String? {
        // Se calculan las posibles posiciones de la clave
        val indice1 = h1(clave)
        val indice2 = h2(clave)

        // Se busca la clave en la tabla de hash
        if (this.tabla1[indice1].obtenerPrimero()?.obtenerClave() == clave) {
            return this.tabla1[indice1].obtenerPrimero()?.obtenerValor()
        } else if (this.tabla2[indice2].obtenerPrimero()?.obtenerClave() == clave) {
            return this.tabla2[indice2].obtenerPrimero()?.obtenerValor()
        } else return null
    }

    // eliminar(clave: Int): Unit -> Función que elimina una clave de la tabla de hash
    fun eliminar(clave: Int): Unit {
        // Se calculan las posibles posiciones de la clave
        val indice1 = h1(clave)
        val indice2 = h2(clave)

        // Se elimina la clave de la tabla de hash, si existe en ella
        if (this.tabla1[indice1].obtenerPrimero()?.obtenerClave() == clave) {
            this.tabla1[indice1].eliminarPrimero()
            numElementos--
            claveConocida[clave] = null
        } else if (this.tabla2[indice2].obtenerPrimero()?.obtenerClave() == clave) {
            this.tabla2[indice2].eliminarPrimero()
            numElementos--
            claveConocida[clave] = null
        }
    }

    // existe(clave: Int): Boolean -> Función que verifica si una clave existe en la tabla de hash
    fun existe(clave: Int): Boolean {
        // Se calculan las posibles posiciones de la clave
        val indice1 = h1(clave)
        val indice2 = h2(clave)

        // Se verifica si la clave existe en la tabla de hash
        return (this.tabla1[indice1].obtenerPrimero()?.obtenerClave() == clave) || (this.tabla2[indice2].obtenerPrimero()?.obtenerClave() == clave)
    }

    fun obtenerNumElementos(): Int {
        return this.numElementos
    }

    fun getSize(): Int {
        return this.conocidas.size
    }

    override fun toString(): String {
        var str = ""
        for (i in 0 until this.getSize()) {
            var claveConocida = this.conocidas[i]
            if (claveConocida != null) {
                str += "[${claveConocida}] -> ${this.buscar(claveConocida)}\n"
            } else str += "[] -> []\n"
        }
        return str
    }
}

// createDictionaryCuckoo(): CuckooHashTable -> Función que crea un diccionario vacío basado en cuckoo hashing
fun createDictionaryCuckoo(): CuckooHashTable {
    return CuckooHashTable()
}
