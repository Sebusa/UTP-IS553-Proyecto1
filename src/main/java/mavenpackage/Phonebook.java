/*Programa principal del proyecto. Es la agenda que gestiona los contactos y
y ofrece las opciones disponibles que son requerimiento.
Versión: 2.0*/
package mavenpackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Phonebook {
    private List<Contact> contactsBook = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    String dataBaseFile = "dataBase";
    String filePath = "resources/PhonebookData/" + dataBaseFile +".txt";

    public void writeData(String fileName){
        try{
            var fileOutput = new FileWriter(fileName);

            for(Contact user : this.contactsBook){
                fileOutput.write(user.getAttributes() + "\n");
            }

            fileOutput.close();
        } catch(IOException e){
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
    }

    public void convertData(String fileName){
        this.contactsBook.clear();

        try{
            var fileInput = new BufferedReader(new FileReader(fileName));

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

    public void showContacts(){
        Screen.clearScreen();

        convertData(filePath);
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

    public void addContact(){
        var user = new Contact();
        user.addData();

        this.contactsBook.add(user);
        writeData(filePath);
    }

    public void modifyContact(){
        Screen.clearScreen();

        if(this.contactsBook.size() != 0){
            System.out.println("En este momento hay " + this.contactsBook.size() 
                                + " contactos guardados.");
            int index;
            System.out.print("Ingrese la posición a modificar de la lista: ");
            index = input.nextInt();

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
                writeData(filePath);
            }
            else{
                System.out.println("Posición no válida.");
            }
        }
        else{
            System.out.println("La agenda está vacía.");
        }
    }

    public void deleteContact(){
        Screen.clearScreen();

        if(this.contactsBook.size() != 0){
            System.out.println("En este momento hay " + this.contactsBook.size() 
                                + " contactos guardados.");
            int index;
            System.out.print("Ingrese la posición a eliminar de la lista: ");
            index = input.nextInt();

            if(index >= 0 && index < this.contactsBook.size()){
                this.contactsBook.remove(index);
                writeData(filePath);
            }
            else{
                System.out.println("Posición no válida.");
            }
        }
        else{
            System.out.println("La agenda está vacía.");
        }
    }

    public void verifyNumber(){

    }

    public void searchContact(){
        
    }

    public void exportDataFile(){
        Screen.clearScreen();

        convertData(filePath);

        String fileName;
        System.out.println("Se va a importar un archivo con los datos de los contactos.");
        System.out.print("Ingrese el nombre del archivo: ");
        fileName = input.next();

        if(fileName != dataBaseFile){
            System.out.println("Generando archivo...");

            String newFilePath = "resources/user/" + fileName + ".txt";

            var file = new File(newFilePath);
            
            if(!file.exists()){
                try{
                    file.createNewFile();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            writeData(newFilePath);
            System.out.println("¡Archivo generado!");
            System.out.println("Lo puedes encontrar en la carpeta de resources," 
                                + " en la subcarpeta user.");
        }
        else{
            System.out.println("¡Lo siento, no puedes nombrar así el archivo!");
        }
    }
}
