/* 
* Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
* Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

fun multiplicacionSimpleDeMatrices(A: Array<IntArray>, matrizB: Array<IntArray>): Array<IntArray> {
    val matrizC = Array(A.size) { IntArray(matrizB[0].size) }
    for (i in A.indices) {
        for (j in matrizB[0].indices) {
            for (k in matrizB.indices) {
                matrizC[i][j] += A[i][k] * matrizB[k][j]
            }
        }
    }
    return matrizC
}

fun sumarMatrices(A: Array<IntArray>, matrizB: Array<IntArray>): Array<IntArray> {
    val matrizC = Array(A.size) { IntArray(A[0].size) }
    for (i in A.indices) {
        for (j in A[0].indices) {
            matrizC[i][j] = A[i][j] + matrizB[i][j]
        }
    }
    return matrizC
}

fun restaMatrices(A: Array<IntArray>, matrizB: Array<IntArray>): Array<IntArray> {
    val matrizC = Array(A.size) { IntArray(A[0].size) }
    for (i in A.indices) {
        for (j in A[0].indices) {
            matrizC[i][j] = A[i][j] - matrizB[i][j]
        }
    }
    return matrizC
}

fun multiplicacionStrassen(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    if(A.size != B.size) {
        throw Exception("Las matrices no son cuadradas")
    }
    val n = A.size

    val C = Array(n) { IntArray(n) }

    if (n == 1){
        C[0][0] = A[0][0] * B[0][0]
    }
    else if (n%2 != 0){
        val A1 = Array(n+1) { IntArray(n+1) }
        val B1 = Array(n+1) { IntArray(n+1) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                A1[i][j] = A[i][j]
                B1[i][j] = B[i][j]
            }
        }
        val C1 = multiplicacionStrassen(A1, B1)
        for (i in 0 until n) {
            for (j in 0 until n) {
                C[i][j] = C1[i][j]
            }
        }
    }
    else {
        val A11 = Array(n/2) { IntArray(n/2) }
        val A12 = Array(n/2) { IntArray(n/2) }
        val A21 = Array(n/2) { IntArray(n/2) }
        val A22 = Array(n/2) { IntArray(n/2) }

        val B11 = Array(n/2) { IntArray(n/2) }
        val B12 = Array(n/2) { IntArray(n/2) }
        val B21 = Array(n/2) { IntArray(n/2) }
        val B22 = Array(n/2) { IntArray(n/2) }

        for (i in 0 until n/2) {
            for (j in 0 until n/2) {
                A11[i][j] = A[i][j]
                A12[i][j] = A[i][j + n/2]
                A21[i][j] = A[i + n/2][j]
                A22[i][j] = A[i + n/2][j + n/2]

                B11[i][j] = B[i][j]
                B12[i][j] = B[i][j + n/2]
                B21[i][j] = B[i + n/2][j]
                B22[i][j] = B[i + n/2][j + n/2]
            }
        }

        val P1 = multiplicacionStrassen(A11, restaMatrices(B12, B22))
        val P2 = multiplicacionStrassen(sumarMatrices(A11, A12), B22)
        val P3 = multiplicacionStrassen(sumarMatrices(A21, A22), B11)
        val P4 = multiplicacionStrassen(A22, restaMatrices(B21, B11))
        val P5 = multiplicacionStrassen(sumarMatrices(A11, A22), sumarMatrices(B11, B22))
        val P6 = multiplicacionStrassen(restaMatrices(A12, A22), sumarMatrices(B21, B22))
        val P7 = multiplicacionStrassen(restaMatrices(A11, A21), sumarMatrices(B11, B12))

        val C11 = sumarMatrices(restaMatrices(sumarMatrices(P5, P4), P2), P6)
        val C12 = sumarMatrices(P1, P2)
        val C21 = sumarMatrices(P3, P4)
        val C22 = restaMatrices(restaMatrices(sumarMatrices(P5, P1), P3), P7)

        for (i in 0 until n/2) {
            for (j in 0 until n/2) {
                C[i][j] = C11[i][j]
                C[i][j + n/2] = C12[i][j]
                C[i + n/2][j] = C21[i][j]
                C[i + n/2][j + n/2] = C22[i][j]
            }
        }
    }
    return C
}