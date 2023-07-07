# Laboratori semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
# Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	CodigoMorse.jar

SRC=	ArbolBinario.kt CodigoMorse.kt PruebaCodigoMorse.kt

TEMP=	*.class META-INF

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(TEMP)
