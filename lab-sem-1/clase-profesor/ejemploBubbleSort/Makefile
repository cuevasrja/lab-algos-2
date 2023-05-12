KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	PruebaOrdenamiento.jar

SRC=	Ordenammiento.kt Main.kt

all:	
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
