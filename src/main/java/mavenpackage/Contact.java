/*Está clase representa a un contacto que será manejado por la agenda.
Un contacto podrá añadir, modificar o eliminar sus propios datos en caso de ser necesario.
Versión: 1.0*/
package mavenpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contact {
    Scanner input = new Scanner(System.in);

    private String name;
    private List<String> phoneNumbers = new ArrayList<>();
    private String email;
    private String address;
    private String nickname;


    //Constructor y métodos mutadores.
    public Contact(String name, String email, String address, String nickname) {
        this.setName(name);
        this.setEmail(email);
        this.setAddress(address);
        this.setNickname(nickname);
    }    

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Funciones básicas que brinda un contacto.
    public void showData(){
        cleanScreen();

        System.out.println("Nombre: " + getName());
        System.out.println("\nNúmeros telefónicos:\n");
        showNumbers();
        System.out.println("Correo electrónico: " + getEmail());
        System.out.println("\nDirección: " + getAddress());
        System.out.println("\nApodo: " + getNickname());
    }

    public void addData(){
        cleanScreen();

        Integer option;
        System.out.println("¿Qué deseas agregar?\n");

        System.out.println("[1]-Nombre.");
        System.out.println("[2]-Número de teléfono.");
        System.out.println("[3]-Correo electrónico.");
        System.out.println("[4]-Dirección.");
        System.out.println("[5]-Apodo.");
        System.out.println("[Cualquier otra tecla]-Salir de la función.");

        System.out.println("Ingresa la opción: ");
        option = input.nextInt();

        switch(option){
            case 1: {
                String newName;
                System.out.println("Ingresa el nombre: ");
                newName = input.nextLine();
                setName(newName);
                break;
            }
            case 2: {
                addNumber();
                break;
            }
            case 3: {
                String newEmail;
                System.out.println("Ingresa el correo electrónico: ");
                newEmail = input.nextLine();
                setEmail(newEmail);
                break;
            }
            case 4: {
                String newAddress;
                System.out.println("Ingresa la dirección: ");
                newAddress = input.nextLine();
                setAddress(newAddress);
                break;
            }
            case 5: {
                String newNickname;
                System.out.println("Ingresa el apodo: ");
                newNickname = input.nextLine();
                setNickname(newNickname);
                break;
            }
            default: break;
        }
    }

    public void modifyData(){
        cleanScreen();

        Integer option;
        System.out.println("¿Qué deseas modificar?\n");

        System.out.println("[1]-Nombre.");
        System.out.println("[2]-Número de teléfono.");
        System.out.println("[3]-Correo electrónico.");
        System.out.println("[4]-Dirección.");
        System.out.println("[5]-Apodo.");
        System.out.println("[Cualquier otra tecla]-Salir de la función.");

        System.out.println("Ingresa la opción: ");
        option = input.nextInt();

        switch(option){
            case 1: {
                String newName;
                System.out.println("Ingresa el nombre: ");
                newName = input.nextLine();
                setName(newName);
                break;
            }
            case 2: {
                modifyNumber();
                break;
            }
            case 3: {
                String newEmail;
                System.out.println("Ingresa el correo electrónico: ");
                newEmail = input.nextLine();
                setEmail(newEmail);
                break;
            }
            case 4: {
                String newAddress;
                System.out.println("Ingresa la dirección: ");
                newAddress = input.nextLine();
                setAddress(newAddress);
                break;
            }
            case 5: {
                String newNickname;
                System.out.println("Ingresa el apodo: ");
                newNickname = input.nextLine();
                setNickname(newNickname);
                break;
            }
            default: break;
        }
    }

    public void deleteData(){
        cleanScreen();

        Integer option;
        System.out.println("¿Qué deseas eliminar?\n");

        System.out.println("[1]-Número de teléfono.");
        System.out.println("[2]-Correo electrónico.");
        System.out.println("[3]-Dirección.");
        System.out.println("[4]-Apodo.");
        System.out.println("[Cualquier otra tecla]-Salir de la función.");

        System.out.println("Ingresa la opción: ");
        option = input.nextInt();

        switch(option){
            case 1: {
                deleteNumber();
                break;
            }
            case 2: {
                setEmail("");
                break;
            }
            case 3: {
                setAddress("");
                break;
            }
            case 4: {
                setNickname("");
                break;
            }
            default: break;
        }
    }

    //Funciones adicionales para el manejo de la lista de números del contacto.
    public void addNumber(){
        cleanScreen();

        String number;
        System.out.println("Ingresa el número: ");
        number = input.nextLine();
        this.phoneNumbers.add(number);
        System.out.println("¡Número agregado!");

    }
    
    public void modifyNumber(){
        cleanScreen();

        if(this.phoneNumbers.size() != 0){
            System.out.println("Lista de números");
            showNumbers();
            System.out.println("¿Qué número deseas modificar: ");
            int option = input.nextInt();

            if(option >= 0 && option < this.phoneNumbers.size()){
                String number;
                System.out.println("Ingresa el número: ");
                number = input.nextLine();
                this.phoneNumbers.set(option, number);
                System.out.println("¡Número modificado!");
            }
            else{
                System.out.println("Indice fuera del rango.");
            }
        }
        else{
            System.out.println("La lista de números está vacía.");
        }
    }
    
    public void deleteNumber(){
        cleanScreen();
        
        if(this.phoneNumbers.size() != 0){
            System.out.println("Lista de números");
            showNumbers();
            System.out.println("¿Qué número deseas eliminar?: ");
            int option = input.nextInt();

            if(option >= 0 && option < this.phoneNumbers.size()){
                this.phoneNumbers.remove(option);
                System.out.println("¡Número eliminado!");
            }
            else{
                System.out.println("Indice fuera del rango.");
            }
        }
        else{
            System.out.println("La lista de números está vacía.");
        }
    }
    
    public void showNumbers(){
        if(this.phoneNumbers.size() != 0){
            for(int i = 0; i < this.phoneNumbers.size(); i++){
                System.out.println(i+1 + ")" + this.phoneNumbers.get(i) + "\n");
            }
        }
        else{
            System.out.println("La lista de números está vacía.");
        }
    }

    //función adicional para limpiar la pantalla de la consola.
    public void cleanScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
