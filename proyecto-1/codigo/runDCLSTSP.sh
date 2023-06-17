if [ $# -ne 2 ]; then
    echo "Uso: $0 <archivo de entrada> <archivo de salida>"
    exit 1
fi
INPUT=$(realpath $1)
if [ ! -f $INPUT ]; then
    echo "No existe el archivo de entrada $1"
    exit 1
fi
echo -n "" > $2.out
OUTPUT=$(realpath $2.out)
java -Xss100m -jar DCLSTSP.jar "$INPUT" "$OUTPUT"