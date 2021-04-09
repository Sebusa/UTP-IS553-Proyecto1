/*
Programa principal del proyecto. Es la agenda que gestiona los contactos y
y ofrece las opciones disponibles que son requerimiento.
Versión: 2.0
*/
package mavenpackage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Phonebook {
    Scanner input = new Scanner(System.in);
    //Atributos básicos. Registro de la agenda y el archivo que manejará como base de datos.
    private String filePath = "resources/data/dataBase.txt";
    private List<Contact> contactsBook = new ArrayList<>();

    //Métodos de retorno básicos
    public String getFilePath(){
        return filePath;
    }

    public List<Contact> getContactsBook(){
        return this.contactsBook;
    }

    /*Dos funciones para escribir archivos
    ->writeOverData(): Escribir sobre archivo que ya contiene información.
    ->writeNewData(): Escribir sobre un archivo vacío.*/
    public void writeOverData(String fileName){
        try{
            var fileOutput = new FileWriter(fileName, true);

            for(Contact user : this.contactsBook){
                fileOutput.write(user.getAttributes());
                fileOutput.write("\n");
            }
            fileOutput.close();
        } catch(IOException e){
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
    }

    public void writeNewData(String fileName){
        try{
            var fileOutput = new FileWriter(fileName, false);

            for(Contact user : this.contactsBook){
                fileOutput.write(user.getAttributes() + "\n");
            }
            fileOutput.close();
        } catch(IOException e){
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
    }

    //Función para leer datos de un archivo
    public void convertData(){
        this.contactsBook.clear();

        try{
            var fileInput = new BufferedReader(new FileReader(this.getFilePath()));
            String[] dataRecolected;
            String stringBuffer;

            while((stringBuffer = fileInput.readLine()) != null){
                dataRecolected = stringBuffer.split(";");   
                var user = new Contact();

                user.setName(dataRecolected[0]);
                String[] phoneNumbers = dataRecolected[1].split(",");
                for(String number : phoneNumbers){
                    user.addNumber(number);
                }
                user.setEmail(dataRecolected[2]);
                user.setAddress(dataRecolected[3]);
                user.setNickname(dataRecolected[4]);

                this.contactsBook.add(user);
            }
            fileInput.close();
        } catch(IOException e){
            System.out.println("Archivo no encontrado.");
            e.printStackTrace();
        }
    }

    //Función para mostrar la lista de contactos
    public void showContacts(){
        convertData();

        if(this.contactsBook.size() != 0){
            System.out.println("--LISTA DE CONTACTOS--");
            for(Contact user : this.contactsBook){
                user.showData();
                System.out.println("________________________");
            }
        }
        else{
            System.out.println("No hay ningún contacto guardado.");
        }
    }

    //Función para añadir un contacto
    public void addContact(){
        var user = new Contact();

        this.contactsBook.clear();

        user.addData();
        this.contactsBook.add(user);

        writeOverData(this.getFilePath());
        Screen.clearScreen();
        System.out.println("¡Usuario ingresado!");
    }

    //Función para modificar un contacto
    public void modifyContact(){
        convertData();

        if(this.contactsBook.size() != 0){
            int index;

            System.out.println("En este momento hay " + this.contactsBook.size() 
                                + " contactos guardados.");            
            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("Ingrese la posición a modificar de la lista: ");
            index = input.nextInt();
            input.nextLine();

            if(index >= 0 && index < this.contactsBook.size()){
                Boolean flag = true;
                int option;
                Contact user = this.contactsBook.get(index);

                while(flag){
                    System.out.println("Contacto No. " + index);
                    System.out.println("¿Qué deseas hacer?");
                    System.out.println("[1]- Modificar datos del contacto.");
                    System.out.println("[2]- Eliminar datos del contacto");
                    System.out.println("[3]- Salir de la función.");
                    option = input.nextInt();

                    input.nextLine();
                    Screen.clearScreen();
                    switch(option){
                        case 1:{
                            user.modifyData();
                            break;
                        }
                        case 2:{
                            user.deleteData();
                            break;
                        }
                        case 3:{
                            flag = false;
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                }

                this.contactsBook.set(index, user);

                writeNewData(this.getFilePath());
                Screen.clearScreen();
                System.out.println("¡Usuario modificado!");
            }
            else{
                System.out.println("Posición no válida.");
            }
        }
        else{
            System.out.println("La agenda está vacía.");
        }
    }

    //Función para eliminar un contacto
    public void deleteContact(){
        convertData();

        if(this.contactsBook.size() != 0){
            int index;

            System.out.println("En este momento hay " + this.contactsBook.size() 
                                + " contactos guardados.");
            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("Ingrese la posición a eliminar de la lista: ");
            index = input.nextInt();
            input.nextLine();

            if(index >= 0 && index < this.contactsBook.size()){
                this.contactsBook.remove(index);

                writeNewData(this.getFilePath());
                Screen.clearScreen();
                System.out.println("¡Usuario eliminado!");
            }
            else{
                System.out.println("Posición no válida.");
            }
        }
        else{
            System.out.println("La agenda está vacía.");
        }
    }

    /*Función que verifica si un número ya está registrado.
    Verdadero: Repetido - Falso: No repetido*/
    public Boolean verifyNumber(String number){
        Boolean answer = false;

        convertData();

        for(Contact user : this.contactsBook){
            answer = user.getPhoneNumbers().contains(number);
            if(answer){
                break;
            }
        }
        return answer;
    }

    //Funciones para buscar un contacto bajo un criterio de búsqueda
    public Boolean searchWord(String searchedWord, String sentence){
        Pattern wordPattern = Pattern.compile(searchedWord, Pattern.CASE_INSENSITIVE);
        Matcher wordMatcher = wordPattern.matcher(sentence);
        Boolean wordFound = wordMatcher.find();
        return wordFound;
    }

    //Menú principal de búsqueda
    public void searchContact(){
        String option;

        String wordToSearch = null;
        List<String> sentencesToCompare = new ArrayList<>();
        List<Contact> matchedContacts = new ArrayList<>();

        convertData();

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

                for(Contact user : this.contactsBook){
                    sentencesToCompare.add(user.getName());
                }
                break;
            }
            case "2":{
                System.out.println("Ingrese el número telefónico a buscar: ");
                wordToSearch = input.next();

                for(Contact user : this.contactsBook){
                    for(String number : user.getPhoneNumbers()){
                        sentencesToCompare.add(number);
                    }
                }
                break;
            }
            case "3":{
                System.out.print("Ingrese el correo electrónico a buscar: ");
                wordToSearch = input.next();

                for(Contact user : this.contactsBook){
                    sentencesToCompare.add(user.getEmail());
                }
                break;
            }
            case "4":{
                System.out.print("Ingrese la dirección a buscar: ");
                wordToSearch = input.next();

                for(Contact user : this.contactsBook){
                    sentencesToCompare.add(user.getName());
                }
                break;
            }
            case "5":{
                System.out.print("Ingrese el apodo a buscar: ");
                wordToSearch = input.next();

                for(Contact user : this.contactsBook){
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
                if(searchWord(wordToSearch, sentence)){
                    int index = sentencesToCompare.indexOf(sentence);
                    Contact user = this.contactsBook.get(index);

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
                user.showData();
            }
        }
        else{
            System.out.println("No hay ningún contacto que coincida con el criterio.");
        }
    }

    //Función para permitir el usuario exportar un archivo con los datos de la agenda.
    public void exportDataFile(){
        convertData();
        String fileName;

        System.out.println("Se va a importar un archivo con los datos de los contactos.");
        System.out.print("Ingrese el nombre del archivo: ");

        fileName = input.next();
        String newFilePath = "resources/user/" + fileName + ".txt";

        System.out.println("Generando archivo...");

        var file = new File(newFilePath);
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch(IOException e){
                System.out.println("Hubo un error :(");
                e.printStackTrace();
            }
        }

        writeOverData(newFilePath);

        System.out.println("¡Archivo generado!");
        System.out.println("Lo puedes encontrar en la carpeta de resources," 
                            + " en la subcarpeta user.");
    }

    //Funciones para poder importar archivos externos y verificar si son válidos.
    public void importFile(){
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
        verifyFile(pathToSearch);

        Screen.clearScreen();
        System.out.println("Buscando archivo...");

        if(verifyFile(pathToSearch)){
            System.out.println("¡Archivo cargado correctamente!");

            showImportedData();
            this.contactsBook.clear();
        }
        else{
            System.out.println("El archivo no es válido. Se cancela la importación.");
        }
    }

    //Función que verifica que el archivo importado cumpla con la estructura requerida
    public Boolean verifyFile(String fileName){
        this.contactsBook.clear();
        try{
            var fileToOpen = new BufferedReader(new FileReader(fileName));
            String[] dataRecolected;
            String stringBuffer;

            Boolean fileVerified = true;
            while((stringBuffer = fileToOpen.readLine()) != null){
                if(fileVerified){
                    dataRecolected = stringBuffer.split(";");

                    if(dataRecolected.length != 5){
                        System.out.println("El archivo no cumple con la estructura.");
                        fileVerified = false;
                        break;
                    }
                    else{
                        var user = new Contact();
                        user.setName(dataRecolected[0]);
                        String[] phoneNumbers = dataRecolected[1].split(",");
                        for(String number : phoneNumbers){
                            try{
                                Long.parseLong(number);
                                if(!verifyImportedNumbers(number)){
                                    user.addNumber(number);
                                }
                                else{
                                    System.out.println("Hay un número repetido en el archivo importado.");
                                    fileVerified = false;
                                    break;
                                }
                            }catch(Exception e){
                                System.out.println("Los números registrados no son válidos.");
                                fileVerified = false;
                                break;
                            }
                        }
                        user.setEmail(dataRecolected[2]);
                        user.setAddress(dataRecolected[3]);
                        user.setNickname(dataRecolected[4]);
                        this.contactsBook.add(user);
                    }
                }
            }
            fileToOpen.close();
            return fileVerified;
        }catch(IOException e){
            System.out.println("Archivo no encontrado.");
            return false;
        }
    }

    //Función que verifica los números importados
    public Boolean verifyImportedNumbers(String number){
        Boolean answer = false;
        for(Contact user : this.contactsBook){
            answer = user.getPhoneNumbers().contains(number);
            if(answer){
                break;
            }
        }
        return answer;
    }

    //Si el archivo importado es válido esta función muestra el contenido del archivo
    public void showImportedData(){
        if(this.contactsBook.size() != 0){
            System.out.println("--LISTA DE CONTACTOS--");
            
            for(Contact user : this.contactsBook){
                user.showData();
                System.out.println("________________________");
            }
        }
        else{
            System.out.println("No hay ningún contacto guardado.");
        }
    }
}
