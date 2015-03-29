Small project that contains classes implementing JEE annotations processing. The aim is to declare new annotations as you want, taking into account the standards and specifications for declaring custom annotations in JEE.

Either methods, classes or properties are annotated at deployment, compilation or runtime; the container can perform introspection and find the notes and develop the work you want done.

In this particular case, there is a range of annotation with class and methods scope. When the method is invoked at runtime, the container looks for all the classes in the virtual machine JVM that are annotated with that specific annotation and creates and compiles a runtime class that sends an email through smtp. This class is only created and compiled at runtime, the code does not exist explicitly in a class, is part of the job log processor.



Pequeño proyecto que contiene clases que implementan el procesamiento de anotaciones JEE. El objetivo es declarar las propias anotaciones que uno quiera, teniendo en cuenta los estándares y especificaciones para la declaración de anotaciones personalizadas en JEE.

Posteriormente anotar ya sea metodos, clases o propiedades para que en momento de despliegue, compilación o runtine el contenedor pueda realizar el trabajo de introspección y encontrar las anotaciones y elaborar el trabajo que uno quiera que se haga.

En este caso en particular, se encuentra una anotación con alcance de Clase y que se agrega a los métodos de clase, una vez se invoca dicho método en Runtime, el contenedor encuentra todas las clases que en la maquina virtual JVM estan anotadas con dicha anotación personalizada y crea y compila una clase de runtime que envía un correo electrónico vía smtp. Dicha clase solo se crea y se compila en Runtime, en código no existe explicitamente una clase de envío, es parte del trabajo del procesador de anotaciones.