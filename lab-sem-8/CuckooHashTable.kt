// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

class CuckooHashTable() {
    // conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
    private var conocidas: Array<Int> = Array(7) { 0 }
    // tabla1: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    private var tabla1: Array<CircularList> = Array(7) { CircularList() }
    // tabla2: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    private var tabla2: Array<CircularList> = Array(7) { CircularList() }

    // numElementos: Int -> Número de elementos que hay en la tabla de hash
    var numElementos: Int = 0

    private val A: Double = 0.6180339887

    // Métodos de la clase CuckooHashTable
    // hashFunction(clave: Int): Int -> Función hash que devuelve el índice de la lista en la tabla de hash donde se debe insertar el elemento o donde se encuentra el elemento
    private fun hashFunction(clave: Int): Int {
        val m = this.conocidas.size
        return (m*(A*clave - Math.floor(A*clave))).toInt()
    }
    
    private fun aumentarTablas(): Int {
        return (this.tabla.size + 16) * 3/2
    }

    // rehash(): Unit -> Función que hace rehash de la tabla de hash
    private fun rehash(): Unit {
        // Se duplica el tamaño de las tablas de hash
        val nuevoTamano = this.aumentarTablas()
        this.conocidas = Array(nuevoTamano) { 0 }
        this.tabla1 = Array(nuevoTamano) { CircularList() }
        this.tabla2 = Array(nuevoTamano) { CircularList() }
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
    private fun obtenerFactorCarga(): Double {
        return this.numElementos.toDouble() / this.conocidas.size.toDouble()
    }

    // insertar(clave: Int, String: valor): Boolean -> Función que inserta una clave en la tabla de hash.
    fun insertar(clave: Int, String: valor): Unit {
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
        // Se calculan los hashes de la clave
        val hash1 = hashFunction(clave)
        val hash2 = hashFunction(hash1 xor clave)

        return if (this.tabla1[hash1].obtenerClave() == clave) {
            this.tabla1[hash1].obtenerValor()
        } else if (this.tabla2[hash2].obtenerClave() == clave) {
            this.tabla2[hash2].obtenerValor()
        } else {
            null
        }
    }

    // eliminar(clave: Int): Unit -> Función que elimina una clave de la tabla de hash
    fun eliminar(clave: Int): Unit {
        // Se calculan los hashes de la clave
        val hash1 = hashFunction(clave)
        val hash2 = hashFunction(hash1 xor clave)

        // Se elimina la clave de la tabla de hash
        if (this.tabla1[hash1].obtenerClave() == clave) {
            this.tabla1[hash1].cambiarValor(null)
        } else if (this.tabla2[hash2].obtenerClave() == clave) {
            this.tabla2[hash2].cambiarValor(null)
        }

        // Se disminuye el número de elementos de la tabla de hash
        this.numElementos--
    }

    // existe(clave: Int): Boolean -> Función que verifica si una clave existe en la tabla de hash
    fun existe(clave: Int): Boolean {
        // Se calculan los hashes de la clave
        val hash1 = hashFunction(clave)
        val hash2 = hashFunction(hash1 xor clave)

        return this.tabla1[hash1].obtenerClave() == clave || this.tabla2[hash2].obtenerClave() == clave
    }

    fun obtenerNumElementos(): Int {
        return this.numElementos
    }

    fun obtenerTamano(): Int {
        return this.conocidas.size
    }

    override fun toString(): String {
        var str = ""
        for (i in 0 until this.conocidas.size / 2) {
            val clave1 = this.tabla1[i].obtenerClave()
            val clave2 = this.tabla2[i].obtenerClave()
            if (clave1 != null) {
                str += "$clave1 "
            }
            if (clave2 != null) {
                str += "$clave2 "
            }
        }
        return str
    }
}