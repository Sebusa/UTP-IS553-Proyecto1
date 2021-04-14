/*
Menú principal para la gestión de la agenda.
Esta es la versión de consola.
Versión: 1.0
*/
package console;

import java.util.Scanner;
import classes.*;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        Boolean flag = true;
        Phonebook agenda = new Phonebook();
        String option;

        while(flag){
            Screen.clearScreen();
            System.out.println("--MENÚ DE OPCIONES--");
            System.out.println("[1]- Agregar un contacto.");
            System.out.println("[2]- Modificar un contacto.");
            System.out.println("[3]- Eliminar un contacto.");
            System.out.println("[4]- Mostrar lista de contactos.");
            System.out.println("[5]- Buscar un contacto.");
            System.out.println("[6]- Exportar un archivo de datos.");
            System.out.println("[7]- Importar un archivo de datos.");
            System.out.println("[0]- Salir del programa.");
            System.out.print("Ingrese una opción: ");
            option = input.next();

            Screen.clearScreen();
            switch(option){
                case "1":{
                    PhonebookMain.addContact(agenda);
                    break;
                }
                case "2":{
                    PhonebookMain.modifyContact(agenda);
                    break;
                }
                case "3":{
                    PhonebookMain.deleteContact(agenda);
                    break;
                }
                case "4":{
                    PhonebookMain.showContacts(agenda);
                    break;
                }
                case "5":{
                    PhonebookMain.searchContact(agenda);
                    break;
                }
                case "6":{
                    PhonebookMain.exportDataFile(agenda);
                    break;
                }
                case "7":{
                    PhonebookMain.importFile(agenda);
                    break;
                }
                case "0":{
                    System.out.println("Saliendo del programa...");
                    flag = false;
                    break;
                }
                default:{
                    System.out.println("Opción no válida.");
                    break;
                }
            }
            System.out.print("Presione una tecla para continuar: ");
            input.next();
        }
    }

}