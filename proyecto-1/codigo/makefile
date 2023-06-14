# Proyecto 1 de Algoritmos y Estructuras de Datos II (CI-2692).
# Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	DCLSTSP.jar

SRC=	DCLSTSP.kt

PROGVERIFICADOR=	VerificadorTSP.jar

SRCVERIFICADOR=	VerificadorTSP.kt

all:
	$(KC) $(SRCVERIFICADOR) $(KFLAGS) -d $(PROGVERIFICADOR)
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(PROGVERIFICADOR)