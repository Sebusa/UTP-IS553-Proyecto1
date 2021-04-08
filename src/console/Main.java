/*Menú principal para el manejo de la agenda telefónica. Ofrece un menú por consola con las opciones disponibles.
Version: 1.0*/
import java.util.Scanner;
import mavenpackage.*;

public class Main{
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
                    agenda.addContact();
                    break;
                }
                case "2":{
                    agenda.modifyContact();
                    break;
                }
                case "3":{
                    agenda.deleteContact();
                    break;
                }
                case "4":{
                    agenda.showContacts();
                    break;
                }
                case "5":{
                    agenda.searchContact();
                    break;
                }
                case "6":{
                    agenda.exportDataFile();
                    break;
                }
                case "7":{
                    agenda.importFile();
                    break;
                }
                case "0":{
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
