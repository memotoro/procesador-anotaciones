/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.banco.anotaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 *
 * @author Memo Toro
 */
@SupportedAnnotationTypes({"co.edu.uniandes.banco.anotaciones.Notificacion"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ProcesadorAnotaciones extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        HashMap<String, ClassElement> clases = new HashMap<String, ClassElement>();
        for (Element e : roundEnv.getElementsAnnotatedWith(Notificacion.class)) {
            // Debe ser una clase
            Element classElement = e.getEnclosingElement();
            // Obtener el nombre de la clase incluuido el paquete al que pertence
            String className = ((TypeElement) classElement).getQualifiedName().toString();
            System.out.println(className);
            ClassElement anotaciones = clases.get(className);
            // Si la lista es null entonces es el primero metodo de la clase y se inicializa la lista
            if (anotaciones == null) {
                clases.put(className, new ClassElement(classElement, new ArrayList<Element>()));
            }
            // Agregar la anotacion a la lista de la clase
            clases.get(className).getMethods().add(e);
            for(int i=0;i<clases.get(className).getMethods().size();i++){
                System.out.println(clases.get(className).getMethods().get(i));
            }
        }
        // Cuando se tiene todas las clases con sus metodos se genera la nuevas clases
        Set<String> clazz = clases.keySet();
        for (String idClase : clazz) {
            Element className = clases.get(idClase).getClazz();
            try {
                // Pedir al controlador del ambiente crear un nuevo archivo
                JavaFileObject f = processingEnv.getFiler().createSourceFile(((TypeElement) className).getQualifiedName() + "Extra");
                // Permite escribir mensajes a la consola
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Creando " + f.toUri());
                Writer w = f.openWriter();
                try {
                    PrintWriter pw = new PrintWriter(w);
                    pw.println("/* Clase generada el " + new Date() + "*/");
                    pw.println("package " + ((PackageElement) className.getEnclosingElement()).getQualifiedName() + ";");
                    pw.println("import org.apache.commons.mail.SimpleEmail;");
                    pw.println("public class " + className.getSimpleName() + "Extra extends " + className.getSimpleName() + " {");
                    // Procesar cada uno de los metodos
                    List<Element> metodos = clases.get(idClase).getMethods();
                    for (Element element : metodos) {

                        ExecutableElement method = (ExecutableElement) element;
                        pw.println("    public " + method.getReturnType().toString() + " " + method.getSimpleName() + "(");
                        List<VariableElement> parametros = (List<VariableElement>) method.getParameters();
                        for (int i = 0; i < parametros.size(); i++) {
                            VariableElement variableElement = parametros.get(i);
                            if (i > 0) {
                                pw.print(",");
                            }
                            pw.println(variableElement.asType().toString() + " " + variableElement.getSimpleName());
                        }
                        pw.println(" )   { ");
                        // Invocar la superclase

                        pw.println(" super. " + method.getSimpleName() + "(");
                        // Poner parametros por defecto
                        for (int i = 0; i < parametros.size(); i++) {
                            VariableElement variableElement = parametros.get(i);
                            if (i > 0) {
                                pw.print(",");
                            }
                            pw.println(" " + variableElement.getSimpleName());
                        }
                        pw.println(" );");
                        // Crear codigo de envio
                        //Obtener la anotacion
                        Notificacion notificacion = method.getAnnotation(Notificacion.class);
                        pw.println("  try { SimpleEmail email = new SimpleEmail();");
                        pw.println("email.setHostName(\"smtp.uniandes.edu.co\");");
                 //       pw.println("email.setSSL(true);");
                        String[] usuarios = notificacion.usuarios();
                        for (String string : usuarios) {
                            pw.println("email.addTo(\"" + string + "\", \"" + string + "\");");
                        }
                        pw.println("email.setAuthentication(\"ga.toro53\", \"password\");");
                        pw.println("email.setFrom(\"ga.toro53@uniandes.edu.co\", \"password\");");
                        pw.println("email.setSubject(\" Se ha ejecutado el metodo "+method.getSimpleName()+"\");");
                        pw.println("email.setMsg(\"" + notificacion.mensaje() + "\");");
                        pw.println("email.send(); } catch (Exception ex) {ex.printStackTrace();}");
                        pw.println("}");
                    }
                    pw.println("}");

                    pw.flush();
                } finally {
                    w.close();
                }

            } catch (IOException x) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, x.toString());
            }
        }
        return true;
    }

    public class ClassElement {

        private Element clazz;
        private List<Element> methods;

        public ClassElement(Element clazz, List<Element> methods) {
            this.clazz = clazz;
            this.methods = methods;
        }

        public Element getClazz() {
            return clazz;
        }

        public void setClazz(Element clazz) {
            this.clazz = clazz;
        }

        public List<Element> getMethods() {
            return methods;
        }

        public void setMethods(List<Element> methods) {
            this.methods = methods;
        }

        @Override
        public String toString() {
            return clazz.getSimpleName().toString();
        }
    }
}