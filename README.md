# Proyecto I
Primer proyecto para la materia de programación IV. El proyecto consiste de una agenda telefónica creada aplicando los conocimientos adquiridos en la materia.

## Especifaciones
El proyecto fue creado con la herramienta _Maven_ con __JDK 15__ y el manejo de pruebas unitarias __JUnit 5__.

## Estructura del proyecto
El proyecto está distribuido en tres carpetas fundamentalmente:
* ___lib___:
    Esta carpeta recolecta los archivos necesarios que aseguran el buen funcionamiento del proyecto.
* ___Resources___:
    Esta carpeta conforma los recursos utilizados por el programa en caso necesario. Se conforma por:
    * __Datos__:
        La base de datos que maneja el programa para la recolección de contactos.
    * __Diagrama UML__: El modelo de dato en UML del proyecto.
    * __POM__: 
        El archivo .xml que viene con la estructura del proyecto incorporado por _Maven_.
* ___src___:
    Esta carpeta contiene los programas _.java_ del proyecto. Se conforma por:
    * __main__:
        La carpeta que contiene los programas principales.
    * __test__: La carpeta que contiene las pruebas unitarias de cada programa.