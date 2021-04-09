/*Está clase representa a un contacto que será manejado por la agenda.
Un contacto podrá añadir, modificar o eliminar sus propios datos en caso de ser necesario.
Versión: 2.0*/
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

    //Constructores y métodos mutadores
    public Contact(String name, String email, String address, String nickname) {
        this.setName(name);
        this.setEmail(email);
        this.setAddress(address);
        this.setNickname(nickname);
    }    

    public Contact(){
        this.setName(null);
        this.setEmail(null);
        this.setAddress(null);
        this.setNickname(null);
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

    public List<String> getPhoneNumbers(){
        return this.phoneNumbers;
    }

    //Funciones básicas para la clase Contact
    public void addData(){
        String newName;
        System.out.print("Ingresa el nombre: ");
        newName = input.nextLine();
        setName(newName);

        Boolean flag = true;
        int numberOption;
        while(flag){
            addNumber();
            System.out.println("¿Deseas ingresar más números?");
            System.out.println("[1]- Sí.");
            System.out.println("[2]- No.");
            numberOption = input.nextInt();
            if(numberOption != 1){
                flag = false;
            }
        }

        System.out.println("¿Deseas ingresar un correo electrónico?");
        System.out.println("[1]- Sí.");
        System.out.println("[2]- No.");
        int emailOption;
        emailOption = input.nextInt();
        if(emailOption == 1){
            String newEmail;
            System.out.print("Ingresa el correo electrónico: ");
            newEmail = input.next();
            setEmail(newEmail);
        }

        System.out.println("¿Deseas ingresar una dirección?");
        System.out.println("[1]- Sí.");
        System.out.println("[2]- No.");
        int addressOption;
        addressOption = input.nextInt();
        if(addressOption == 1){
            String newAddress;
            System.out.print("Ingresa la dirección: ");
            newAddress = input.nextLine();
            setAddress(newAddress);
        }
    
        System.out.println("¿Deseas ingresar un apodo?");
        System.out.println("[1]- Sí.");
        System.out.println("[2]- No.");
        int nickOption;
        nickOption= input.nextInt();
        if(nickOption == 1){
            String newNickname;
            System.out.print("Ingresa el apodo: ");
            newNickname = input.next();
            setNickname(newNickname);
        }
    }

    public void showData(){
        System.out.println("Nombre: " + getName());
        System.out.println("Números telefónicos:");
        showNumbers();
        if(getEmail() != null || getEmail() != "null"){
            System.out.println("Correo electrónico: " + getEmail());
        }
        if(getAddress() != null || getAddress() != "null"){
            System.out.println("Dirección: " + getAddress());
        }
        if(getNickname() != null || getNickname() != "null"){
            System.out.println("Apodo: " + getNickname());
        }
    }

    public void modifyData(){
        int option;
        System.out.println("¿Qué deseas modificar?");
        System.out.println("[1]-Nombre.");
        System.out.println("[2]-Número de teléfono.");
        System.out.println("[3]-Correo electrónico.");
        System.out.println("[4]-Dirección.");
        System.out.println("[5]-Apodo.");
        System.out.println("[0]-Salir de la función.");
        System.out.print("Ingresa la opción: ");
        option = input.nextInt();

        Screen.clearScreen();
        switch(option){
            case 1: {
                String newName;
                System.out.print("Ingresa el nombre: ");
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
                System.out.print("Ingresa el correo electrónico: ");
                newEmail = input.next();
                setEmail(newEmail);
                break;
            }
            case 4: {
                String newAddress;
                System.out.print("Ingresa la dirección: ");
                newAddress = input.nextLine();
                setAddress(newAddress);
                break;
            }
            case 5: {
                String newNickname;
                System.out.print("Ingresa el apodo: ");
                newNickname = input.next();
                setNickname(newNickname);
                break;
            }
            default: {
                System.out.println("Opción no válida.");
                break;
            }
        }
    }

    public void deleteData(){
        int option;
        System.out.println("¿Qué deseas eliminar?");
        System.out.println("[1]-Número de teléfono.");
        System.out.println("[2]-Correo electrónico.");
        System.out.println("[3]-Dirección.");
        System.out.println("[4]-Apodo.");
        System.out.println("[0]-Salir de la función.");
        System.out.println("Ingresa la opción: ");
        option = input.nextInt();

        Screen.clearScreen();
        switch(option){
            case 1: {
                deleteNumber();
                break;
            }
            case 2: {
                setEmail(null);
                break;
            }
            case 3: {
                setAddress(null);
                break;
            }
            case 4: {
                setNickname(null);
                break;
            }
            default: {
                System.out.println("Opción no válida.");
                break;
            }
        }
    }

    //Funciones para el manejo de números telefónicos para el contacto
    public void addNumber(){
        String number;
        System.out.print("Ingresa el número: ");
        number = input.next();

        Phonebook agenda = new Phonebook();
        Boolean verifiedNumber = agenda.verifyNumber(number);
        agenda.getContactsBook().clear();
        if(!verifiedNumber){
            this.phoneNumbers.add(number);
            System.out.println("¡Número agregado!");
        }
        else{
            System.out.println("El número ya está en la agenda. No se puede ingresar.");
        }
    }

    public void addNumber(String number){
            this.phoneNumbers.add(number);
    }

    public void showNumbers(){
        int i = 1;
        for(String number : this.phoneNumbers){
            System.out.println(i + ") " + number);
            i++;
        }
    }

    public void modifyNumber(){
        if(this.phoneNumbers.size() != 0){
            System.out.println("Lista de números");
            showNumbers();
            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("¿Qué número deseas modificar? Ingresa el índice: ");
            int index = input.nextInt();

            if(index >= 0 && index < this.phoneNumbers.size()){
                String number;
                System.out.print("Ingresa el número: ");
                number = input.next();

                Phonebook agenda = new Phonebook();
                Boolean verifiedNumber = agenda.verifyNumber(number);
                agenda.getContactsBook().clear();
                
                Screen.clearScreen();
                if(!verifiedNumber){
                    this.phoneNumbers.set(index, number);
                    System.out.println("¡Número modificado!");
                }
                else{
                    System.out.println("El número ya está en la agenda. No se puede ingresar.");
                }
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
        if(this.phoneNumbers.size() != 0){
            System.out.println("Lista de números");
            showNumbers();
            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("¿Qué número deseas eliminar? Ingresa el índice: ");
            int index = input.nextInt();
            if(index >= 0 && index < this.phoneNumbers.size()){
                this.phoneNumbers.remove(index);
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

    //Función que facilita la escritura de datos por la agenda.
    public String getAttributes(){
        return getName() + ";" + String.join(",",this.phoneNumbers) + ";" 
                + getEmail() + ";" + getAddress() + ";" + getNickname();
    }
}
