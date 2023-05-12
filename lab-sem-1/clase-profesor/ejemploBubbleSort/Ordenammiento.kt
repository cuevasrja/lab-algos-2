fun bubbleSort(A: Array<Int>) {
    for (i in 0 until A.size) {
    	for (j in (A.size - 1) downTo (i+1)) {
	    if ( A[j] < A[j-1] ) {
	       var tmp = A[j]
	       A[j] = A[j-1]
	       A[j-1] = tmp
	    }
	}
    }
}
