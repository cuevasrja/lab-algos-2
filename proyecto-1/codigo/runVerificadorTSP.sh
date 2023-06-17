if [ $# -ne 2 ]; then
    echo "Uso: $0 <instancia de TSP> <archivo de solucion>"
    exit 1
fi
$INSTANCIA=$(realpath $1)
$SOLUCION=$(realpath $2)
if [ ! -f $INSTANCIA -o ! -f $SOLUCION ]; then
    echo "No existe el instancia de TSP $1 o el archivo de solucion $2"
    exit 1
fi

java -Xss100m -jar VerificadorTSP.jar $INSTANCIA $SOLUCION