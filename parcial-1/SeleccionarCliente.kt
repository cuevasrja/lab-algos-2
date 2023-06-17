// Nombre: Luis Isea
// Carnet: 19-10175

// Se usa insertionSort dentro de bucketSort para ordenar los buckets
fun insertionSort(datosClientes: Array<Pair<Int,String>>): Unit {
    for (i in 1 until datosClientes.size) {
        val cliente = datosClientes[i]
        var j = i - 1
        while (j >= 0 && datosClientes[j].first > cliente.first) {
            datosClientes[j+1] = datosClientes[j]
            j--
        }
        datosClientes[j+1] = cliente
    }
}

// Se ordenan los numeros de suscripción de los clientes con bucketSort
// El peor caso de bucketSort es de O(n)
fun bucketSort(datosClientes: Array<Pair<Int,String>>): Unit {
    val buckets = Array<Array<Pair<Int,String>>>(10) { Array<Pair<Int,String>>(0) { Pair(0,"") } }
    // Añadir los elementos a los buckets
    for (cliente in datosClientes) {
        val numId = cliente.first
        val numIdValAbsoluto = Math.abs(numId)
        val bucketIndex = numIdValAbsoluto % 10
        buckets[bucketIndex].plus(cliente)
    }
    // Ordenar los buckets con Insertion Sort
    for (bucket in buckets) {
        insertionSort(bucket)
    }
    // Unir los buckets en un solo arreglo
    for (i in 0 until buckets.size) {
        val bucket = buckets[i]
        for (j in 0 until bucket.size) {
            val cliente = bucket[j]
            datosClientes[i*bucket.size + j] = cliente
        }
    }
}

// Se encuentra el cliente con menor número de suscripción
fun encontrarClienteConMenorId(datosClientes: Array<Pair<Int,String>>): String {
    bucketSort(datosClientes)
    val clienteConMenorId = datosClientes[0].second
    return clienteConMenorId
}

// Se encuentra el cliente ganador
fun encontrarClienteGanador(datosClientes: Array<Pair<Int,String>>): String {
    for (i in 0 until datosClientes.size) {
        var numId: Int = datosClientes[i].first.toInt()
        if (numId == i) {
            return datosClientes[i].second
        }
    }
    return encontrarClienteConMenorId(datosClientes)
}

// Se procesan los datos de entrada con los datos de los clientes
fun procesarEntradaDatos(entrada: Array<String>): Array<Pair<Int,String>> {
    val arregloConParesDeDatos = Array<Pair<Int,String>>(entrada.size/2) { Pair(0,"") }
    for (i in 0 until entrada.size/2) {
        val k = i * 2
        arregloConParesDeDatos[i] = Pair(entrada[k].toInt(), entrada[k+1])
    }
    return arregloConParesDeDatos
}

fun main(Args: Array<String>) {
    val datosClientes = procesarEntradaDatos(Args)
    println(encontrarClienteGanador(datosClientes))
}