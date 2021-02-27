# dam2-psp-ejemplo-cliente-servidor

## TCP

Ejemplo sencillo de cliente servidor usando Java y sockets TCP.

A continuación se muestra un diagrama de secuencia que muestra la interacción entre cliente y servidor.

```
|---------|                           |----------|
| Cliente |                           | Servidor |
|---------|                           |----------|
     |              new Socket()            |
     |=====================================>|
     |                                      |
     |              accepted                |
     |<- - - - - - - - - - - - - - - - - - -|
     |                                      |
     |              write("patata")         |
     |=====================================>|
     |                                      |
     |                                      |
     |     write("tú si que eres patata")   |
     |<=====================================|
     |                                      |
     |                                      |

```

## UDP

Ejemplo sencillo de cliente servidor usando Java y sockets UDP.

A continuación se muestra un diagrama de secuencia que muestra la interacción entre cliente y servidor.

```
|---------|                           |----------|
| Cliente |                           | Servidor |
|---------|                           |----------|
     |                                      |
     |=====||                               |
     |     || new Socket()                  |
     |<====||                               |
     |                                      |
     |                                      |
     |              send("patata")          |
     |=====================================>|
     |                                      |
     |                                      |
     |     send("tú si que eres patata")    |
     |<=====================================|
     |                                      |
     |                                      |

```
