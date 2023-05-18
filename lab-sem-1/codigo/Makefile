# Laboratorio de la semana 1 de Algoritmos y Estructuras de Datos II (CI-2692)
# Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175)

KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	Sortlib.jar

SRC=	Sortlib.kt Main.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)