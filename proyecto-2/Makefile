# Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
# Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	PruebaOrtografia.jar

SRC=	AyudanteOrtografico.kt ConjuntoPalabras.kt PMLI.kt PruebaOrtografia.kt

TEMP=	*.class META-INF

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(TEMP)
