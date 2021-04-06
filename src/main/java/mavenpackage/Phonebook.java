/*La agenda que hará el trabajo en todo nuestro proyecto.
Se encarga de la gestión de contactos por medio de un menú en consola.
Versión: 1.0*/
package mavenpackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phonebook{
    private List<Contact> contacts = new ArrayList<>();
    static Scanner consoleInput = new Scanner(System.in);

    String fileName = "resources/datos.txt";

    //Para archivos de texto
    public void convertData(){
        try {
            this.contacts.clear();
            var output = new FileReader(fileName);
            int numberData = output.read();
            String lineText = "";
            while(data != -1){
                String letter = (String)data; 
                lineText.concat(letter);

                String[] data = lineText.split(";");
                Contact user;
                user.setName(data[0]);

                String[] numbers = data[1].split(",");
                for(String number : numbers){
                    user.phoneNumbers.add(number);
                }

                user.setEmail(data[2]);
                user.setAddress(data[3]);
                user.setNickname(data[4]);

                this.contacts.add(user);
            }

        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo.");
            e.printStackTrace();
        }
    }
    
    public void writeData(){
        try{
            var output = new FileWriter(fileName);

            for(Contact user : this.contacts){
                output.write(user.getAttributes() + "\n");
            }
            output.close();
            this.contacts.clear();
        } catch (IOException e) {
            System.out.println("Archivo no encontrado.");
            e.printStackTrace();
        }
    }

    public void showContact(){
        Screen.clearScreen();
        if(this.contacts.size() != 0){
            System.out.println("--LISTA DE CONTACTOS--");
            for(Contact user : this.contacts){
                user.showData();
                System.out.println("________________________");
            }
        }
        else{
            System.out.println("No hay ningún contacto guardado.");
        }

        System.out.print("Presione una tecla para continuar: ");
        String opt = consoleInput.next();
    }

    public void modifyContact(){
        Screen.clearScreen();
        if(this.contacts.size() != 0){
            Scanner input = new Scanner(System.in);
            
            System.out.println("En este momento hay " + this.contacts.size() + "usuarios en la agenda.");
            System.out.print("Indique la posición a modificar: ");
            int index = input.nextInt();

            Contact user = this.contacts.get(index);

            Boolean flag = true;
            while(flag){
                user.modifyData();

                Screen.clearScreen();
                System.out.println("¿Deseas modificar más datos al usuario?");
                System.out.println("[1]- Sí.");
                System.out.println("[2]- No.");
                System.out.print("Ingrese una opción: ");
                String option = input.nextLine();

                switch(option){
                    case "1": break;
                    case "2": {
                        flag = false;
                        break;
                    }
                    default: break;
                } 
            }

            System.out.println("¿Deseas eliminar algún dato del contacto?");
            System.out.println("[1]- Sí.");
            System.out.println("[2]- No.");
            System.out.print("Ingrese una opción: ");
            String opt = consoleInput.next();

            if(opt == "1"){
                deleteContactData(user);
            }

            writeData();
            input.close();
        }
        else{
            System.out.println("No hay ningún contacto para modificar.");
        }
        System.out.print("Presione una tecla para continuar: ");
        String opt = consoleInput.next();
    }

    public void addContact(){
        Screen.clearScreen();
        Contact user = new Contact("","","","");
        user.addData();

        this.contacts.add(user);
        System.out.println(this.contacts.size());
        writeData();
        //writeBinaryData();

        System.out.print("Presione una tecla para continuar: ");
        String opt = consoleInput.next();
    }

    public void deleteContactData(Contact user){
        Screen.clearScreen();
        if(this.contacts.size() != 0){
            Scanner input = new Scanner(System.in);
            
            Boolean flag = true;
            while(flag){
                user.deleteData();

                Screen.clearScreen();
                System.out.println("¿Deseas eliminar más datos al usuario?");
                System.out.println("[1]- Sí.");
                System.out.println("[2]- No.");
                System.out.print("Ingrese una opción: ");
                String option = input.nextLine();

                switch(option){
                    case "1": break;
                    case "2": {
                        flag = false;
                        break;
                    }
                    default: break;
                } 
            }
            input.close();
        }
        else{
            System.out.println("No hay ningún contacto para modificar.");
        }
        System.out.print("Presione una tecla para continuar: ");
        String opt = consoleInput.next();
    }

    public void deleteContact(){
        Screen.clearScreen();
        if(this.contacts.size() != 0){
            Scanner input = new Scanner(System.in);
            
            System.out.println("En este momento hay " + this.contacts.size() + "usuarios en la agenda.");
            System.out.print("Indique la posición a eliminar: ");
            int index = input.nextInt();
            this.contacts.remove(index);

            writeData();
            input.close();
        }
        else{
            System.out.println("No hay ningún contacto para eliminar.");
        }
        System.out.print("Presione una tecla para continuar: ");
        String opt = consoleInput.next();
    }

    public void verifyNumber(){

    }

    public void searchContact(){
        System.out.print("Presione una tecla para continuar: ");
        String opt = consoleInput.next();
    }
}
