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
    Scanner consoleInput = new Scanner(System.in);

    String fileName = "Resources\\Datos.txt";
    String binaryFileName = "Resources\\Datos.dat";
    File dataBase = new File(fileName);

    public void main(){
        Boolean flag = true;

        while(flag){
            Screen.cleanScreen();
        
            System.out.println("--MENÚ DE OPCIONES--");
            System.out.println("[1]- Agregar un contacto.");
            System.out.println("[2]- Modificar un contacto.");
            System.out.println("[3]- Eliminar un contacto.");
            System.out.println("[4]- Mostrar la lista de contactos.");
            System.out.println("[5]- Buscar un contacto.");
            System.out.println("[0]- Salir del programa.");
            System.out.print("Ingrese una opción: ");

            String option = consoleInput.next();
            switch(option){
                case "1":{
                    addContact();
                    break;
                }
                case "2":{
                    modifyContact();
                    break;
                }
                case "3":{
                    deleteContact();
                    break;
                }
                case "4":{
                    showContact();
                    break;
                }
                case "5":{
                    System.out.println("pollo");
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
        }
    }

    //Para archivos de texto
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
        var output = new PrintWriter(fileName);

        for(Contact user : this.contacts){
            output.print(user.getName() + ";");

            if(user.phoneNumbers.size() != 0){
                for(String number : user.phoneNumbers){
                    if(number != user.phoneNumbers.get(user.phoneNumbers.size()-1)){
                        output.print(number + ",");
                    }
                    else{
                        output.print(number + ";");
                    }
                }
            }

            output.print(user.getEmail() + ";");
            output.print(user.getAddress() + ";");
            output.print(user.getNickname());
            output.println("");
        }
        output.close();
    }

    //Para archivos binarios
    public void convertBinaryData(){
        try{
            this.contacts.clear();
            var file = new FileInputStream(binaryFileName);
            var ois = new ObjectInputStream(file);

            while(ois.hasNext()){
                Contact user = ois.readObject();
                this.contacts.add(user);
            }

            ois.close();
        }catch (FileNotFoundException e) {
            System.out.println("¡El fichero no existe!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        };  
    }

    public void writeBinaryData(){
        try{
            var file = new FileInputStream(binaryFileName);
            var oos = new ObjectOutputStream(file);

            for(Contact user : this.contacts){
                oos.writeObject(user);
            }

            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        };
          
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

        this.contacts.add(user);
        writeData();
        //writeBinaryData();

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
