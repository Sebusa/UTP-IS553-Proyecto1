/*La agenda que hará el trabajo en todo nuestro proyecto.
Se encarga de la gestión de contactos por medio de un menú en consola.
Versión: 1.0*/
package mavenpackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phonebook {
    private List<Contact> contacts = new ArrayList<>();

    String fileName = "Resources\\Datos.txt";
    File dataBase = new File(fileName);

    //Para mostrar los contactos primero debemos extraerlos del archivo
    public void convertData(){
        try {
            Scanner fileReader = new Scanner(dataBase);
            System.out.println("--CONTACTOS DISPONIBLE--");

            this.contacts.clear();

            while(fileReader.hasNext()){
                String[] dataRecolected = fileReader.next().split(";");

                String name = dataRecolected[0];
                String listOfNumbers = dataRecolected[1];
                String email = dataRecolected[2];
                String address = dataRecolected[3];
                String nickname = dataRecolected[4];

                Contact user = new Contact(name,email,address,nickname);

                if(listOfNumbers != ""){
                    String[] userNumbers = listOfNumbers.split(",");
                    for(String number : userNumbers){
                        user.addNumber(number);
                    }
                }

                this.contacts.add(user);
                fileReader.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeData(){

    }

    public void showContact(){
        Screen.cleanScreen();
        if(this.contacts.size() != 0){
            System.out.println("--LISTA DE CONTACTOS--");
            for(Contact user : contacts){
                user.showData();
                System.out.println("________________________");
            }
        }
        else{
            System.out.println("No hay ningún contacto guardado.");
        }
    }

    public void modifyContact(){
        Screen.cleanScreen();
        if(this.contacts.size() != 0){
            Scanner input = new Scanner(System.in);
            
            System.out.println("En este momento hay " + this.contacts.size() + "usuarios en la agenda.");
            System.out.print("Indique la posición a modificar: ");
            int index = input.nextInt();

            Contact user = this.contacts.get(index);

            Boolean flag = true;
            while(flag){
                user.modifyData();

                Screen.cleanScreen();
                System.out.println("¿Deseas modificar más datos al usuario?");
                System.out.println("[1]- Sí.");
                System.out.println("[2]- No.");
                System.out.print("Ingrese una opción: ");
                String option = input.next();

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
    }

    public void addContact(){
        Screen.cleanScreen();
        Scanner input = new Scanner(System.in);

        Contact user = new Contact("","","","");
        Boolean flag = true;
        while(flag){
            user.addData();

            System.out.println("¿Deseas agregar más datos al usuario?");
            System.out.println("[1]- Sí.");
            System.out.println("[2]- No.");
            System.out.print("Ingrese una opción: ");
            String option = input.next();

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

    public void deleteContactData(){
        Screen.cleanScreen();
        if(this.contacts.size() != 0){
            Scanner input = new Scanner(System.in);
            
            System.out.println("En este momento hay " + this.contacts.size() + "usuarios en la agenda.");
            System.out.print("Indique la posición a modificar: ");
            int index = input.nextInt();

            Contact user = this.contacts.get(index);

            Boolean flag = true;
            while(flag){
                user.deleteData();

                Screen.cleanScreen();
                System.out.println("¿Deseas eliminar más datos al usuario?");
                System.out.println("[1]- Sí.");
                System.out.println("[2]- No.");
                System.out.print("Ingrese una opción: ");
                String option = input.next();

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
    }

    public void deleteContact(){
        Screen.cleanScreen();
        if(this.contacts.size() != 0){
            Scanner input = new Scanner(System.in);
            
            System.out.println("En este momento hay " + this.contacts.size() + "usuarios en la agenda.");
            System.out.print("Indique la posición a eliminar: ");
            int index = input.nextInt();
            this.contacts.remove(index);

            input.close();
        }
        else{
            System.out.println("No hay ningún contacto para eliminar.");
        }
    }

    public void verifyNumber(){

    }

    public void searchContact(){
        
    }
}
