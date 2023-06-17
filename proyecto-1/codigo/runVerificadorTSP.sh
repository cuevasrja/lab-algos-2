if [ $# -ne 2 ]; then
    echo "Uso: $0 <instancia de TSP> <archivo de solucion>"
    exit 1
fi

java -Xss100m -jar VerificadorTSP.jar $*