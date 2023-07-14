# Parcial 2 de Algoritmos y Estructuras de Datos II (CI-2692).
# Autor: Luis Isea (19-10175).

KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	CuentaHuelgas.jar

SRC=	CuentaHuelgas.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
