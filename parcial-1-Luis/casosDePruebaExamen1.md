# Casos de prueba de la preguna 1

Se compila con el comando:

> kotlinc SeleccionarCliente.kt

## Caso de prueba 1

Se ejecuta el comando:

> kotlin SeleccionarClienteKt -6 "Ana" -4 "Lola " -3 "Petra" -1 "Maria" 0 "Carlos" 1 "Julian" 2 "Rodrigo" 3 "Luis" 4 "Angela" 5 "Pepe" 10 "Carlota" 12 "Carme" 23 "Sonia" 45 "Sofia"

Respuesta Correcta: Carlota

## Caso de prueba 2

Se ejecuta el comando:

> ./prueba2_pregunta1.sh

Respuesta correcta: 316

# Casos de prueba de la preguna 2

Se compila con el comando:

> kotlinc AsignarCupo.kt

## Caso de prueba 1

Se ejecuta el comando:

> kotlin AsignarCupoKt 3 999 2 992 10 991 3 998 1 980 4 970 8 974 8 965 7

Respuesta correcta:
998
999
991

## Caso de prueba 2

> kotlin AsignarCupoKt 4 999 2 992 10 991 3 998 1 980 4 970 8 974 8 965 7 990 4 965 4 927 4 940 5

Respuesta correcta:
998
999
991
927
