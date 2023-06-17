// Nombre: Luis Isea
// Carnet: 19-10175

fun swap(p: Array<Pair<Int,Int>>, i: Int, j: Int): Unit {
    val temp = p[i]
    p[i] = p[j]
    p[j] = temp
}

fun particionar(datosEstudiantes: Array<Pair<Int,Int>>, inicio: Int, fin: Int): Int {
    val pivote = datosEstudiantes[fin].second
    var i = inicio - 1
    for (j in inicio until fin) {
        if (datosEstudiantes[j].second <= pivote) {
            i++
            swap(datosEstudiantes, i, j)
        }
    }
    swap(datosEstudiantes, i+1, fin)
    return i+1
}

// Se usa quickSort para ordenar los NCF
fun quickSort(datosEstudiantes: Array<Pair<Int,Int>>, inicio: Int, fin: Int): Unit {
    if (inicio < fin) {
        val indiceParticion = particionar(datosEstudiantes, inicio, fin)
        quickSort(datosEstudiantes, inicio, indiceParticion-1)
        quickSort(datosEstudiantes, indiceParticion+1, fin)
    }
}

// En caso que dos estudiantes tengan el mismo NCF, se le otorga el cupo al que tenga menor carnet
fun ordenarMismoNumCredFaltantes(datosEstudiantes: Array<Pair<Int,Int>>): Unit {
    for (i in 0 until datosEstudiantes.size - 1) {
        if (datosEstudiantes[i].second == datosEstudiantes[i+1].second && datosEstudiantes[i].first > datosEstudiantes[i+1].first) {
            swap(datosEstudiantes, i, i+1)
        }
    }
}

// Se le asignan los cupos a los estudiantes
fun asignarCupos(numCupos: Int, datosEstudiantes: Array<Pair<Int,Int>>): Unit {
    val numEstudiantes = datosEstudiantes.size
    if (numCupos >= numEstudiantes) {
        println("TODOS FUERON ADMITIDOS")
    }
    else {
        quickSort(datosEstudiantes, 0, numEstudiantes-1)
        ordenarMismoNumCredFaltantes(datosEstudiantes)
        for (i in 0 until numCupos) {
            println(datosEstudiantes[i].first)
        }
    }
}

fun procesarEntradaDatos(entrada: Array<String>): Array<Pair<Int,Int>> {
    val arregloConParesDeDatos = Array<Pair<Int,Int>>(entrada.size/2) { Pair(0,0) }
    for (i in 0 until (entrada.size/2)) {
        val k = i * 2
        arregloConParesDeDatos[i] = Pair(entrada[k+1].toInt(), entrada[k+2].toInt())
    }
    return arregloConParesDeDatos
}

fun main(Args: Array<String>) {
    val numCupos = Args[0].toInt()
    val datosEstudiantes = procesarEntradaDatos(Args)
    asignarCupos(numCupos, datosEstudiantes)
}