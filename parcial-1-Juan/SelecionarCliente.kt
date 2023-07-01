fun busquedaBinaria(A: Array<Int>): Int{
    var i = 0
    var j = A.size - 1
    while(i <= j){
        val m = (i + j)/2
        if(A[m] == m){
            return m
        }else if(A[m] < m){
            i = m + 1
        }else{
            j = m - 1
        }
    }
    return 0
}

fun main(args: Array<String>) {
    val n = args.size
    if (n%2 != 0){
        throw Exception("El número de argumentos debe ser par")
    }
    val suscriptores: Array<Int> = Array(n/2){0}
    val nombres: Array<String> = Array(n/2){""}
    for (i in 0 until n/2){
        suscriptores[i] = args[i*2].toInt()
        nombres[i] = args[i*2 + 1]
    }
    val indice = busquedaBinaria(suscriptores)
    println("${nombres[indice]}")
}