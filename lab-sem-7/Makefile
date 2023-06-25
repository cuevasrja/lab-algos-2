# Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
# Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	Main.jar

SRC=	Cola.kt ListaCircular.kt Nodo.kt Pila.kt Main.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
