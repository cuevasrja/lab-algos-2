# Lab Semana 9 Algoritmos y Estructuras de Datos I

## Decodificador de Código Morse

El laboratorio de la semana 9 está enfocado en la implementación de un **árbol de decisión binario** para la decodificación de código morse.

El laboratorio se ejecuta con el siguiente comando:

```bash
./runCodigoMorse.sh <mensaje a descifrar>
```

Es suma importancia que el **mensaje a descifrar esté entre comillas dobles** (**_""_**). De lo contrario el programa no funcionará correctamente.

Por ejemplo:

```bash
./runCodigoMorse.sh ".... --- .-.. .-"
```

Devuelve en la salida estándar:

```bash
'hola'
```

Mientras que:

```bash
./runCodigoMorse.sh .... . .-.. .-.. ---
```

Devuelve en la salida estándar:

```bash
'i i i'
```

Es decir, el programa no es capaz de decodificar el mensaje correctamente. Esto se debe a que el mensaje **no está entre comillas dobles**.

## Integrantes

- **Juan Cuevas** (19-10056).
- **Luis Isea** (19-10175).
