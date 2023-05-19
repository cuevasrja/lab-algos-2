<<<<<<< HEAD
/*
Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

fun multiplicacionSimpleDeMatrices(A: Array<IntArray>, matrizB: Array<IntArray>): Array<IntArray> {
    val matrizC = Array(A.size) { IntArray(matrizB[0].size) }
=======
/* 
* Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
* Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

/**
* uso: multiplicacionSimpleDeMatrices(A, B)
* Precondición: A y B son matrices cuadradas de enteros
* Postcondición: devuelve la matriz resultante de multiplicar A y B
*/
fun multiplicacionSimpleDeMatrices(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    // Chequear que las matrices sean cuadradas
    if(A.size != B.size) {
        // Lanzar una excepción si no lo son
        throw Exception("Las matrices no son cuadradas")
    }
    // Crear una matriz de ceros del mismo tamaño que A y B
    val C = Array(A.size) { IntArray(B[0].size) }
    // Multiplicar las matrices
>>>>>>> 2fbbc9b168db9a8d54cc765a03f2d87c065099d1
    for (i in A.indices) {
        // Recorrer las filas de A
        for (j in B[0].indices) {
            // Recorrer las columnas de B
            for (k in B.indices) {
                // Recorrer las filas de B y las columnas de A
                C[i][j] += A[i][k] * B[k][j]
                // Sumar el producto de los elementos de A y B
            }
        }
    }
    return C
}

/**
* uso: sumarMatrices(A, B)
* Precondición: A y B son matrices cuadradas de enteros
* Postcondición: devuelve la matriz resultante de sumar A y B
*/
fun sumarMatrices(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    // Chequear que las matrices sean cuadradas
    if(A.size != B.size) {
        // Lanzar una excepción si no lo son
        throw Exception("Las matrices no son cuadradas")
    }
    // Crear una matriz de ceros del mismo tamaño que A y B
    val C = Array(A.size) { IntArray(A[0].size) }
    // Sumar las matrices
    for (i in A.indices) {
        // Recorrer las filas de A
        for (j in A[0].indices) {
            // Recorrer las columnas de A
            C[i][j] = A[i][j] + B[i][j]
            // Sumar los elementos de A y B
        }
    }
    return C
}

/**
* uso: restaMatrices(A, B)
* Precondición: A y B son matrices cuadradas de enteros
* Postcondición: devuelve la matriz resultante de restar A y B
*/
fun restaMatrices(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    // Chequear que las matrices sean cuadradas
    if(A.size != B.size) {
        // Lanzar una excepción si no lo son
        throw Exception("Las matrices no son cuadradas")
    }
    // Crear una matriz de ceros del mismo tamaño que A y B
    val C = Array(A.size) { IntArray(A[0].size) }
    // Restar las matrices
    for (i in A.indices) {
        // Recorrer las filas de A
        for (j in A[0].indices) {
            // Recorrer las columnas de A
            C[i][j] = A[i][j] - B[i][j]
            // Restar los elementos de A y B
        }
    }
    return C
}

/**
* uso: multiplicacionStrassen(A, B)
* Precondición: A y B son matrices cuadradas de enteros
* Postcondición: devuelve la matriz resultante de multiplicar A y B
*/
fun multiplicacionStrassen(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    // Chequear que las matrices sean cuadradas
    if(A.size != B.size) {
        // Lanzar una excepción si no lo son
        throw Exception("Las matrices no son cuadradas")
    }
    // Guardar el tamaño de las matrices
    val n = A.size

    // Crear una matriz de ceros del mismo tamaño que A y B
    val C = Array(n) { IntArray(n) }

    // Multiplicar las matrices
    if (n == 1){
        // Caso base: multiplicar dos matrices de 1x1
        C[0][0] = A[0][0] * B[0][0]
    }
    else if (n%2 != 0){
        // Caso n impar: convertir las matrices en matrices de n+1 x n+1 y multiplicarlas
        // Crear matrices de n+1 x n+1
        val A1 = Array(n+1) { IntArray(n+1) }
        val B1 = Array(n+1) { IntArray(n+1) }

        // Asignar los valores de A y B a las matrices de n+1 x n+1
        for (i in 0 until n) {
            for (j in 0 until n) {
                A1[i][j] = A[i][j]
                B1[i][j] = B[i][j]
            }
        }

        // Multiplicar las matrices de n+1 x n+1
        val C1 = multiplicacionStrassen(A1, B1)
        // Asignar los valores de C1 a C
        for (i in 0 until n) {
            for (j in 0 until n) {
                C[i][j] = C1[i][j]
            }
        }
    }
    else {
        // Caso n par: multiplicar las matrices usando el algoritmo de Strassen

        // Crear submatrices de A y B
        val A11 = Array(n/2) { IntArray(n/2) }
        val A12 = Array(n/2) { IntArray(n/2) }
        val A21 = Array(n/2) { IntArray(n/2) }
        val A22 = Array(n/2) { IntArray(n/2) }
        val B11 = Array(n/2) { IntArray(n/2) }
        val B12 = Array(n/2) { IntArray(n/2) }
        val B21 = Array(n/2) { IntArray(n/2) }
        val B22 = Array(n/2) { IntArray(n/2) }

        // Asignar los valores de A y B a las submatrices
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

        // Calcular los productos de Strassen
        val P1 = multiplicacionStrassen(A11, restaMatrices(B12, B22))
        val P2 = multiplicacionStrassen(sumarMatrices(A11, A12), B22)
        val P3 = multiplicacionStrassen(sumarMatrices(A21, A22), B11)
        val P4 = multiplicacionStrassen(A22, restaMatrices(B21, B11))
        val P5 = multiplicacionStrassen(sumarMatrices(A11, A22), sumarMatrices(B11, B22))
        val P6 = multiplicacionStrassen(restaMatrices(A12, A22), sumarMatrices(B21, B22))
        val P7 = multiplicacionStrassen(restaMatrices(A11, A21), sumarMatrices(B11, B12))

        // Calcular las submatrices de C
        val C11 = sumarMatrices(restaMatrices(sumarMatrices(P5, P4), P2), P6)
        val C12 = sumarMatrices(P1, P2)
        val C21 = sumarMatrices(P3, P4)
        val C22 = restaMatrices(restaMatrices(sumarMatrices(P5, P1), P3), P7)

        // Asignar los valores de las submatrices de C a C
        for (i in 0 until n/2) {
            for (j in 0 until n/2) {
                // Recorrer las filas y columnas de las submatrices
                C[i][j] = C11[i][j]
                C[i][j + n/2] = C12[i][j]
                C[i + n/2][j] = C21[i][j]
                C[i + n/2][j + n/2] = C22[i][j]
            }
        }
    }
    return C
}