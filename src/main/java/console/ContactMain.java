/*
Esta clase brinda las opciones al usuario para
el manejo de datos de un contacto
Versión: 1.1
*/
package console;

import java.util.Scanner;
import classes.*;

public class ContactMain {
    static Scanner input = new Scanner(System.in);

    //Función para añadir datos a un contacto
    public static void addData(Contact user){
        String newName;
        Boolean nameFlag = true;
        while(nameFlag){
            System.out.print("(Campo obligatorio)Ingresa el nombre: ");
            newName = input.nextLine();

            Screen.clearScreen();
            if(!newName.isBlank()){
                user.setName(newName);
                nameFlag = false;
            }
            else{
                System.out.println("Debes ingresar un nombre.");
            }
        }

        Boolean numberFlag = true;
        int numberOption;
        while(numberFlag){
            addNumber(user);
            if(user.getPhoneNumbersSize() > 0){
                Screen.clearScreen();
                System.out.println("¿Deseas ingresar más números?");
                System.out.println("[1]- Sí.");
                System.out.println("[2]- No.");
                numberOption = input.nextInt();
                if(numberOption != 1){
                numberFlag = false;
                }
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
            user.setEmail(newEmail);
        }

        System.out.println("¿Deseas ingresar una dirección?");
        System.out.println("[1]- Sí.");
        System.out.println("[2]- No.");
        int addressOption;
        addressOption = input.nextInt();
        input.nextLine();
        if(addressOption == 1){
            String newAddress;
            System.out.print("Ingresa la dirección: ");
            newAddress = input.nextLine();
            user.setAddress(newAddress);
        }
    
        System.out.println("¿Deseas ingresar un apodo?");
        System.out.println("[1]- Sí.");
        System.out.println("[2]- No.");
        int nickOption;
        nickOption= input.nextInt();
        input.nextLine();
        if(nickOption == 1){
            String newNickname;
            System.out.print("Ingresa el apodo: ");
            newNickname = input.nextLine();
            user.setNickname(newNickname);
        }

        String newPlace;
        Boolean placeFlag = true;
        while(placeFlag){
            System.out.print("(Campo obligatorio)Ingresa el lugar donde conoció al contacto: ");
            newPlace = input.nextLine();

            Screen.clearScreen();
            if(!newPlace.isBlank()){
                user.setPlace(newPlace);
                placeFlag = false;
            }
            else{
                System.out.println("Debes ingresar un lugar.");
            }
        }

    }

    //Función para mostrar los datos de un usuario
    public static void showData(Contact user){
        System.out.println("Nombre: " + user.getName());

        int i = 1;
        System.out.println("Números telefónicos:");
        for(String number : user.getPhoneNumbers()){
            System.out.println(i + ") " + number);
            i++;
        }

        if(user.getEmail() != null || user.getEmail() != "null"){
            System.out.println("Correo electrónico: " + user.getEmail());
        }

        if(user.getAddress() != null || user.getAddress() != "null"){
            System.out.println("Dirección: " + user.getAddress());
        }

        if(user.getNickname() != null || user.getNickname() != "null"){
            System.out.println("Apodo: " + user.getNickname());
        }

        System.out.println("Lugar de primer encuentro: " + user.getPlace());
    }

    //Función para modificar los datos de un usuario
    public static void modifyData(Contact user){
        String option;

        System.out.println("¿Qué deseas modificar?");
        System.out.println("[1]-Nombre.");
        System.out.println("[2]-Número de teléfono.");
        System.out.println("[3]-Correo electrónico.");
        System.out.println("[4]-Dirección.");
        System.out.println("[5]-Apodo.");
        System.out.println("[6]-Primer lugar de encuentro.");
        System.out.println("[0]-Salir de la función.");
        System.out.print("Ingresa la opción: ");
        option = input.next();
        input.nextLine();

        Screen.clearScreen();
        switch(option){
            case "1": {
                String newName;
                Boolean nameFlag = true;
                while(nameFlag){
                    System.out.print("Ingresa el nuevo nombre: ");
                    newName = input.nextLine();

                    Screen.clearScreen();
                    if(!newName.isBlank()){
                        user.setName(newName);
                        nameFlag = false;
                    }
                    else{
                        System.out.println("Debes ingresar un lugar.");
                    }
                }
                break;
            }
            case "2": {
                modifyNumber(user);
                break;
            }
            case "3": {
                String newEmail;
                System.out.print("Ingresa el nuevo correo electrónico: ");
                newEmail = input.next();
                user.setEmail(newEmail);
                break;
            }
            case "4": {
                String newAddress;
                System.out.print("Ingresa la nueva dirección: ");
                newAddress = input.nextLine();
                user.setAddress(newAddress);
                break;
            }
            case "5": {
                String newNickname;
                System.out.print("Ingresa el nuevo apodo: ");
                newNickname = input.next();
                user.setNickname(newNickname);
                break;
            }
            case "6": {
                String newPlace;
                Boolean placeFlag = true;
                while(placeFlag){
                    System.out.print("Ingresa el nuevo lugar: ");
                    newPlace = input.nextLine();

                    Screen.clearScreen();
                    if(!newPlace.isBlank()){
                        user.setPlace(newPlace);
                        placeFlag = false;
                    }
                    else{
                        System.out.println("Debes ingresar un lugar.");
                    }
                }
                break;
            }
            default: {
                System.out.println("Opción no válida.");
                break;
            }
        }
    }

    //Función para eliminar los datos de un usuario
    public static void deleteData(Contact user){
        String option;

        System.out.println("Ten en cuenta de que no se puede eliminar el nombre"
                            + " ni tampoco el primer lugar de encuentro.");
        System.out.println("¿Qué deseas eliminar?");
        System.out.println("[1]-Número de teléfono.");
        System.out.println("[2]-Correo electrónico.");
        System.out.println("[3]-Dirección.");
        System.out.println("[4]-Apodo.");
        System.out.println("[0]-Salir de la función.");
        System.out.println("Ingresa la opción: ");
        option = input.next();
        input.nextLine();

        Screen.clearScreen();
        switch(option){
            case "1": {
                deleteNumber(user);
                break;
            }
            case "2": {
                user.setEmail(null);
                break;
            }
            case "3": {
                user.setAddress(null);
                break;
            }
            case "4": {
                user.setNickname(null);
                break;
            }
            default: {
                System.out.println("Opción no válida.");
                break;
            }
        }
    }

    //Función para añadir un número
    public static void addNumber(Contact user){
        String number;

        System.out.print("Ingresa un número: ");
        number = input.next();

        try{
            Phonebook agenda = new Phonebook();
            Boolean verifiedNumber = agenda.verifyNumber(number);

            Screen.clearScreen();

            Long.parseLong(number);
            agenda.clearList();

            if(!verifiedNumber){
               user.addNumber(number);
               System.out.println("¡Número agregado!");
            }
            else{
                System.out.println("El número ya está en la agenda. No se puede ingresar.");
            }

        }catch(Exception e){
            System.out.println("El número no es válido.");
        }
    }

    //Función para modificar un número
    public static void modifyNumber(Contact user){
        if(user.getPhoneNumbersSize() != 0){
            System.out.println("Lista de números");
            
            int i = 0;
            for(String number : user.getPhoneNumbers()){
                System.out.println(i + ") " + number);
                i++;
            }

            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("¿Qué número deseas modificar? Ingresa el índice: ");
            int index = input.nextInt();
            input.nextLine();

            if(index >= 0 && index < user.getPhoneNumbersSize()){
                String newNumber;

                System.out.print("Ingresa el nuevo número: ");
                newNumber = input.next();

                try{
                    Phonebook agenda = new Phonebook();

                    Long.parseLong(newNumber);

                    Boolean verifiedNumber = agenda.verifyNumber(newNumber);
                    agenda.getContactsBook().clear();
                
                    Screen.clearScreen();
                    if(!verifiedNumber){
                        user.modifyNumber(index, newNumber);
                        System.out.println("¡Número modificado!");
                    }
                    else{
                        System.out.println("El número ya está en la agenda. No se puede ingresar.");
                    }

                } catch(Exception e){
                    System.out.println("El número ingresado no es válido.");
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

    //Función para eliminar un número
    public static void deleteNumber(Contact user){
        if(user.getPhoneNumbersSize() != 0){
            int i = 0;
            System.out.println("Lista de números");
            for(String number : user.getPhoneNumbers()){
             System.out.println(i + ") " + number);
                i++;
            }

            System.out.println("(Recuerda que el índice comienza desde 0)");
            System.out.print("¿Qué número deseas eliminar? Ingresa el índice: ");
            int index = input.nextInt();
            input.nextLine();
            
            Screen.clearScreen();

            if(index >= 0 && index < user.getPhoneNumbersSize()){
                user.deleteNumber(index);
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

}