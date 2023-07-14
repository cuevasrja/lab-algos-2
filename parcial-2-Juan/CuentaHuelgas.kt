// Class: Nodo(d: Int)
// @param d: Día de huelga
class Nodo(val d: Int){
    val dia = d
    var next: Nodo? = null

    fun cambiarNext(nodo: Nodo?) {
        this.next = nodo
    }

    init{
        if (this.dia < 0) throw IllegalArgumentException("Los días deben ser mayores a 0")
    }
}

// Class: Sindicato(periodicidad: Int, limiteDias: Int) 
// Es una lista enlazada de días de huelga de un sindicato
// @param periodicidad: Cada cuantos días se va a hacer huelga
// @param limiteDias: Hasta que día se va a hacer huelga
class Sindicato(val periodicidad: Int, val limiteDias: Int){
    private var sentinel: Nodo = Nodo(0)

    // diasHuelga() devuelve un arreglo con los días de huelga sin contar los dias sabados (6 % 7 = 6) y domingos (7 % 7 = 0)
    fun diasHuelga(): Array<Int> {
        var diasHuelga = Array<Int>(0) {0}
        var nodoActual = this.sentinel.next
        // Recorremos la lista enlazada
        while (nodoActual != null) {
            // Si el día no es sábado ni domingo, lo agregamos al arreglo
            if (nodoActual.dia % 7 != 6 && nodoActual.dia % 7 != 0) {
                diasHuelga += nodoActual.dia
            }
            nodoActual = nodoActual.next
        }
        return diasHuelga
    }

    // agregarDiasHuelga() agrega los días de huelga a la lista enlazada
    fun agregarDiasHuelga() {
        // Agregamos el primer día de huelga
        var dia = this.periodicidad
        // Agregamos los demás días de huelga
        while (dia <= this.limiteDias) {
            // Si el día es múltiplo de la periodicidad y no es el primer día, lo agregamos a la lista
            if (dia % this.periodicidad == 0 && dia > 1){
                agregarAlFinal(dia)
            }
            dia += this.periodicidad
        }
    }

    // agregarAlFinal(dia: Int) agrega un nodo al final de la lista enlazada
    private fun agregarAlFinal(dia: Int) {
        val nuevoNodo = Nodo(dia)
        var nodoActual = this.sentinel
        // Recorremos la lista enlazada hasta llegar al último nodo
        while (nodoActual.next != null) {
            nodoActual = nodoActual.next!!
        }
        // Agregamos el nuevo nodo al final de la lista
        nodoActual.cambiarNext(nuevoNodo)
        nuevoNodo.cambiarNext(null)
    }
}

// Class: CuentaHuelgas(numSindicatos: Int, periodicidades: Array<Int>, limiteDias: Int)
// Es un TDA que cuenta los días de huelga de varios sindicatos, sin contar los días sabados (6 % 7 = 6) y domingos (7 % 7 = 0)
// Guarda cada sindicato en un arreglo de sindicatos y cada sindicato es una lista enlazada de días de huelga
// @param numSindicatos: Número de sindicatos
// @param periodicidades: Arreglo con las periodicidades de cada sindicato
// @param limiteDias: Hasta que día se va a hacer huelga
// @param sindicatos: Arreglo de sindicatos
class CuentaHuelgas(val numSindicatos: Int, val periodicidades: Array<Int>, val limiteDias: Int) {
    private var sindicatos: Array<Sindicato?> = Array<Sindicato?>(this.numSindicatos) {null}

    // diasHuelga() devuelve el número de días de huelga
    fun diasHuelga(): Int {
        var diasHuelga = Array<Int>(0) {0}
        // Agregamos los días de huelga de cada sindicato
        for (i in 0 until this.numSindicatos) {
            diasHuelga += sindicatos[i]!!.diasHuelga()
        }
        // Eliminar duplicados
        diasHuelga = eliminarRepetidos(diasHuelga)
        // Devolver el tamaño del arreglo
        return diasHuelga.size
    }

    // eliminarRepetidos(diasHuelga: Array<Int>) elimina los días de huelga repetidos
    private fun eliminarRepetidos(diasHuelga: Array<Int>): Array<Int> {
        // Creamos un arreglo sin repetidos
        var diasHuelgaSinRepetidos = Array<Int>(0) {0}
        // Iteramos sobre el arreglo de días de huelga
        for (i in 0 until diasHuelga.size) {
            // Si el día de huelga no está en el arreglo sin repetidos, lo agregamos
            if (!diasHuelgaSinRepetidos.contains(diasHuelga[i])) {
                diasHuelgaSinRepetidos += diasHuelga[i]
            }
        }
        return diasHuelgaSinRepetidos
    }

    // Inicializamos los sindicatos
    init {
        // Iteramos sobre el número de sindicatos
        for (i in 0 until this.numSindicatos) {
            // Creamos un sindicato con la periodicidad y el límite de días
            sindicatos[i] = Sindicato(periodicidades[i], limiteDias)
            // Agregamos los días de huelga
            sindicatos[i]!!.agregarDiasHuelga()
        }
    }
}

fun main(args: Array<String>) {
    // Tomamos de la línea de comandos los argumentos
    // args[0] = limiteDias
    // args[1] = numSindicatos
    // args[2..n] = periodicidades
    val limiteDias = args[0].toInt()
    val numSindicatos = args[1].toInt()
    val periodicidades = Array<Int>(numSindicatos) {0}
    // Agregamos las periodicidades a un arreglo
    for (i in 0 until numSindicatos) {
        periodicidades[i] = args[i + 2].toInt()
        // Verificamos que las periodicidades sean mayores a 0
        if (periodicidades[i] < 1) throw IllegalArgumentException("Las periodicidades deben ser mayores a 0")
    }
    val cuentaHuelgas = CuentaHuelgas(numSindicatos, periodicidades, limiteDias)
    println("En $limiteDias días, habrá ${cuentaHuelgas.diasHuelga()} días de huelga")
}