/*
Esta clase brinda las opciones al usuario para
el manejo de la agenda.
Versión: 1.1
*/
package console;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import classes.*;

public class PhonebookMain {
    static Scanner input = new Scanner(System.in);

    //Función para añadir un contacto
    public static void addContact(Phonebook agenda){
        var user = new Contact();

        agenda.clearList();
        ContactMain.addData(user);
        agenda.addContact(user);

        if(agenda.writeOverData(agenda.getFilePath())){
            Screen.clearScreen();
            System.out.println("¡Usuario ingresado!");
        }
        else{
            System.out.println("Ha habido un problema :(");
        }

    }

    //Función para mostrar los contactos registrados
    public static void showContacts(Phonebook agenda){
        agenda.convertData();

        if(agenda.getContactsBookSize() != 0){
            System.out.println("--LISTA DE CONTACTOS--");
            for(Contact user : agenda.getContactsBook()){
                ContactMain.showData(user);
                System.out.println("________________________");
            }
        }
        else{
            System.out.println("No hay ningún contacto guardado.");
        }
    }

    //Función para modificar un contacto
    public static void modifyContact(Phonebook agenda){
        agenda.convertData();

        if(agenda.getContactsBookSize() != 0){
            int index;

            System.out.println("En este momento hay " + agenda.getContactsBookSize() 
                                + " contactos guardados.");            
            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("Ingrese la posición a modificar de la lista: ");
            index = input.nextInt();
            input.nextLine();

            if(index >= 0 && index < agenda.getContactsBookSize()){
                Boolean flag = true;
                String option;
                Contact user = agenda.getContactByIndex(index);

                while(flag){
                    System.out.println("Contacto No. " + index);
                    System.out.println("¿Qué deseas hacer?");
                    System.out.println("[1]- Modificar datos del contacto.");
                    System.out.println("[2]- Eliminar datos del contacto");
                    System.out.println("[3]- Salir de la función.");
                    option = input.next();

                    input.nextLine();
                    Screen.clearScreen();
                    switch(option){
                        case "1":{
                            ContactMain.modifyData(user);
                            break;
                        }
                        case "2":{
                            ContactMain.deleteData(user);
                            break;
                        }
                        case "3":{
                            flag = false;
                            break;
                        }
                        default:{
                            System.out.println("Opción no válida.");
                            break;
                        }
                    }
                }

                agenda.modifyContact(index, user);

                if(agenda.writeNewData(agenda.getFilePath())){
                    Screen.clearScreen();
                    System.out.println("¡Usuario modificado!");
                }
                else{
                    System.out.println("Ha habido un problema :(");
                }

            }
            else{
                System.out.println("Posición no válida.");
            }
        }
        else{
            System.out.println("La agenda está vacía.");
        }
    }

    public static void deleteContact(Phonebook agenda){
        agenda.convertData();

        if(agenda.getContactsBookSize() != 0){
            int index;

            System.out.println("En este momento hay " + agenda.getContactsBookSize() 
                                + " contactos guardados.");
            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("Ingrese la posición a eliminar de la lista: ");
            index = input.nextInt();
            input.nextLine();

            if(index >= 0 && index < agenda.getContactsBookSize()){
                agenda.deleteContact(index);

                if(agenda.writeNewData(agenda.getFilePath())){
                    Screen.clearScreen();
                    System.out.println("¡Usuario eliminado!");
                }
                else{
                    System.out.println("Ha habido un problema :(");
                }

            }
            else{
                System.out.println("Posición no válida.");
            }
        }
        else{
            System.out.println("La agenda está vacía.");
        }
    }

    //Función para buscar un contacto por medio de un criterio de búsqueda
    public static void searchContact(Phonebook agenda){
        String option;

        String wordToSearch = null;
        List<String> sentencesToCompare = new ArrayList<>();
        List<Contact> matchedContacts = new ArrayList<>();

        agenda.convertData();

        System.out.println("Escoge un parámetro de búsqueda");
        System.out.println("[1]- Nombre.");
        System.out.println("[2]- Número de teléfono.");
        System.out.println("[3]- Correo electrónico.");
        System.out.println("[4]- Dirección.");
        System.out.println("[5]- Apodo.");
        System.out.println("[0]- Salir de la función.");
        System.out.print("Ingrese una opción: ");
        option = input.next();

        Screen.clearScreen();

        switch(option){
            case "1":{
                System.out.print("Ingrese el nombre a buscar: ");
                wordToSearch = input.next();

                for(Contact user : agenda.getContactsBook()){
                    sentencesToCompare.add(user.getName());
                }
                break;
            }
            case "2":{
                System.out.println("Ingrese el número telefónico a buscar: ");
                wordToSearch = input.next();

                for(Contact user : agenda.getContactsBook()){
                    for(String number : user.getPhoneNumbers()){
                        sentencesToCompare.add(number);
                    }
                }
                break;
            }
            case "3":{
                System.out.print("Ingrese el correo electrónico a buscar: ");
                wordToSearch = input.next();

                for(Contact user : agenda.getContactsBook()){
                    sentencesToCompare.add(user.getEmail());
                }
                break;
            }
            case "4":{
                System.out.print("Ingrese la dirección a buscar: ");
                wordToSearch = input.next();

                for(Contact user : agenda.getContactsBook()){
                    sentencesToCompare.add(user.getName());
                }
                break;
            }
            case "5":{
                System.out.print("Ingrese el apodo a buscar: ");
                wordToSearch = input.next();

                for(Contact user : agenda.getContactsBook()){
                    sentencesToCompare.add(user.getName());
                }
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Opción no válida.");
                break;
            }
        }

        if(wordToSearch != null){
            for(String sentence : sentencesToCompare){
                if(agenda.searchWord(wordToSearch, sentence)){
                    int index = sentencesToCompare.indexOf(sentence);
                    Contact user = agenda.getContactByIndex(index);

                    matchedContacts.add(user);
                }
            }
        }

        Screen.clearScreen();
        System.out.println("Palabra buscada: " + wordToSearch);

        if(matchedContacts.size() != 0){
            System.out.println("Usuarios que coinciden:");

            for(Contact user : matchedContacts){
                System.out.println("________________");
                ContactMain.showData(user);
            }
        }
        else{
            System.out.println("No hay ningún contacto que coincida con el criterio.");
        }
    }

    //Función para exportar un archivo
    public static void exportDataFile(Phonebook agenda){
        agenda.convertData();

        String fileName;

        System.out.println("Se va a exportar un archivo con los datos de los contactos.");
        System.out.print("Ingrese el nombre del archivo: ");

        fileName = input.next();
        String newFilePath = "resources/user/" + fileName + ".txt";

        System.out.println("Generando archivo...");
        agenda.exportDataFile(newFilePath);
        System.out.println("¡Archivo generado!");
        System.out.println("Lo puedes encontrar en la carpeta de resources," 
                            + " en la subcarpeta user.");
    }

    //Función para importar un archivo
    public static void importFile(Phonebook agenda){
        String file;
        String pathToSearch;

        System.out.println("Recuerda que los archivos que desees importar deben cumplir lo siguiente:");
        System.out.println("1) Deben estar guardados en la carpeta de resources dentro de user.");
        System.out.println("2) Deben cumplir la estructura requerida (componente separados por ;)");
        System.out.println("3) No debe tener números telefónicos repetidos.");
        System.out.println("4) El nombre del archivo debe terminar con la extensión .txt");
        System.out.print("Ingresa el nombre del archivo: ");
        file = input.next();
        
        pathToSearch = "resources/user/" + file;

        agenda.clearList();

        Screen.clearScreen();
        Integer answerExpected = agenda.verifyFile(pathToSearch);
        if(answerExpected == 0){
            System.out.println("¡Archivo cargado correctamente!");
            if(agenda.getContactsBookSize() != 0){
                System.out.println("--LISTA DE CONTACTOS--");
            
                for(Contact user : agenda.getContactsBook()){
                    ContactMain.showData(user);
                    System.out.println("________________________");
                }
            }
            else{
                System.out.println("El archivo está vacío.");
            }
            agenda.clearList();
        }
        else{

            if(answerExpected == 1){
                System.out.println("El archivo no cumple con la estructura.");
            }
            if(answerExpected == 2){
                System.out.println("Hay un número repetido en el archivo.");
            }
            if(answerExpected == 3){
                System.out.println("Un número del archivo no es válido.");
            }
            if(answerExpected == 4){
                System.out.println("El archivo no se ha cargado correctamente.");
            }

            System.out.println("Se cancela la importación del archivo.");

        }
    
    }

}
