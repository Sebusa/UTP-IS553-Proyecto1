/*
Menú alternativo para el uso de la clase Contact.
El propósito de este menú es demostrar la correcta funcionalidad de la clase, así como
tipo "prueba".
Versión: 1.0
*/
package main.java.mavenpackage;

import java.util.Scanner;
import mavenpackage.*;

public class ContactMain {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        Boolean flag = true;
        Contact user = new Contact();
        String option;

        while(flag){
            Screen.clearScreen();
            System.out.println("--MENÚ DE OPCIONES--");
            System.out.println("[1]- Agregar datos.");
            System.out.println("[2]- Modificar datos.");
            System.out.println("[3]- Eliminar datos.");
            System.out.println("[4]- Mostrar datos.");
            System.out.println("[5]- Atributos.");
            System.out.println("[0]- Salir del programa.");
            System.out.print("Ingrese una opción: ");
            option = input.next();

            Screen.clearScreen();
            switch(option){
                case "1":{
                    user.addData();
                    break;
                }
                case "2":{
                    user.modifyData();
                    break;
                }
                case "3":{
                    user.deleteData();
                    break;
                }
                case "4":{
                    user.showData();
                    break;
                }
                case "5":{
                    System.out.println(user.getAttributes());
                    break;
                }
                case "0":{
                    flag = false;
                    break;
                }
                default:{
                    break;
                }
            }
            System.out.print("Presione una tecla para continuar: ");
            input.next();
        }
    }
}
