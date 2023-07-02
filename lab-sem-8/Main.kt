// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

fun main(args: Array<String>) {
    // El primer argumento representa el tamaño del arreglo de prueba a crear.
    val n = args[0].toInt()

    // Se crea un arreglo de tamaño n con pares (clave, valor) donde la clave es un entero aleatorio entre 0 y n/3 y el valor es la clave convertida a String.
    val A = Array<Pair<Int, String>>(n) { Pair(0, "") }
    for (i in 0 until n) {
        val clave = (0..n/3).random()
        A[i] = Pair(clave, clave.toString())
    }

    // Contamos cuántas veces aparece cada clave en el arreglo A.
    val contador = Array<Int>(n/3 + 1) { 0 }
    for (i in 0 until n) {
        contador[A[i].first]++
    }

    // Contamos las claves que aparezcan un número impar de veces.
    var j = 0
    for (i in 0 until n/3 + 1) {
        if (contador[i] % 2 == 1) {
            j++
        }
    }

    // Se imprime la información del arreglo A.
    // Y cuántos elementos debería tener cada diccionario tras finalizar la prueba.
    println("\u001b[34mSe creó un arreglo A de tamaño $n con pares (clave, valor).\u001b[0m")
    println("Las claves son números aleatorios entre \u001b[34m0\u001b[0m y \u001b[34m${n}/3.\u001b[0m")
    println("Hay \u001b[34m${j}\u001b[0m claves que aparecen un número impar de veces.")
    println("Por ende, cada diccionario debería tener \u001b[34m${j}\u001b[0m elementos.")
    println("----------------------------------------------------------------\n")

    // Probamos el diccionario basado en una tabla de hash con encadenamiento.
    println("\u001b[36mPrueba del diccionario basado en una tabla de hash con encadenamiento:\u001b[0m")
    println("----------------------------------------------------------------")

    // Se crea un diccionario vacío, basado en una tabla de hash de encadenamiento.
    val dictChaining: HashTableChaining = createDictionaryChaining()

    // Empezamos a medir el tiempo de ejecución.
    var startTime = System.nanoTime()

    // Se realizan las pruebas de las operaciones buscar, agregar y eliminar del diccionario.
    // Se recorre el arreglo A elemento por elemento.
    for (i in 0 until n) {
        // Se busca si la clave del elemento ya existe en el diccionario.
        if (dictChaining.existe(A[i].first)) {
            // Si la clave ya existe, se elimina el par (clave, valor) del diccionario.
            dictChaining.eliminar(A[i].first)
        } else {
            // Si la clave no existe, se agrega el par (clave, valor) en el diccionario.
            dictChaining.agregar(A[i].first, A[i].second)
        }
    }

    // Se termina de medir el tiempo de ejecución.
    var endTime = System.nanoTime()

    // Se calcula el tiempo de ejecución de las inserciones, búsquedas y eliminaciones.
    val tiempoDictChaining = endTime - startTime

    // Se imprime la información del rendimiento del diccionario.
    println("\u001b[32mSe insertaron, buscaron y eliminaron exitosamente los elementos del arreglo A en el diccionario.\u001b[0m")
    println("El tiempo de ejecución de las operaciones fue de: \u001b[33m${tiempoDictChaining/1000000000.0} segundos.\u001b[0m")
    println("El diccionario tiene \u001b[33m${dictChaining.obtenerNumElementos()}\u001b[0m elementos.")
    println("El número de claves conocidas por el diccionario es \u001b[33m${dictChaining.obtenerNumClavesConocidas()}.\u001b[0m")
    println("El factor de carga del diccionario es \u001b[33m${dictChaining.obtenerFactorCarga()}.\u001b[0m\n")

    // Probamos el diccionario basado en una tabla de hash con cuckoo hash.
    println("\u001b[36mPrueba del diccionario basado en una tabla de hash con cuckoo hash:\u001b[0m")
    println("----------------------------------------------------------------")

    // Se crea un diccionario vacío, basado en una tabla de hash con cuckoo hash.
    val dictCuckoo: CuckooHashTable = createDictionaryCuckoo()

    // Empezamos a medir el tiempo de ejecución.
    startTime = System.nanoTime()

    // Se realizan las pruebas de las operaciones buscar, agregar y eliminar del diccionario.
    // Se recorre el arreglo A elemento por elemento.
    for (i in 0 until n) {
        // Se busca si la clave del elemento ya existe en el diccionario.
        if (dictCuckoo.existe(A[i].first)) {
            // Si la clave ya existe, se elimina el par (clave, valor) del diccionario.
            dictCuckoo.eliminar(A[i].first)
        } else {
            // Si la clave no existe, se agrega el par (clave, valor) en el diccionario.
            dictCuckoo.agregar(A[i].first, A[i].second)
        }
    }

    // Se termina de medir el tiempo de ejecución.
    endTime = System.nanoTime()

    // Se calcula el tiempo de ejecución de las inserciones, búsquedas y eliminaciones.
    val tiempoDictCuckoo = endTime - startTime

    // Se imprime la información del rendimiento del diccionario.
    println("\u001b[32mSe insertaron, buscaron y eliminaron exitosamente los elementos del arreglo A en el diccionario.\u001b[0m")
    println("El tiempo de ejecución de las operaciones fue de: \u001b[33m${tiempoDictCuckoo/1000000000.0} segundos.\u001b[0m")
    println("El diccionario tiene \u001b[33m${dictCuckoo.obtenerNumElementos()}\u001b[0m elementos.")
    println("El número de claves conocidas por el diccionario es \u001b[33m${dictCuckoo.obtenerNumClavesConocidas()}.\u001b[0m")
    println("El factor de carga del diccionario es \u001b[33m${dictCuckoo.obtenerFactorCarga()}.\u001b[33m")
}
