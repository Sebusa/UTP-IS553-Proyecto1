/*
Clase Contact
Función: El manejo de datos privados de un contacto.
Versión: 1.0
 */
package classes;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private List<String> phoneNumbers = new ArrayList<>();
    private String email;
    private String address;
    private String nickname;

    //Constructores y métodos mutadores
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
    
    public Integer getPhoneNumbersSize(){
        return this.phoneNumbers.size();
    }

    //Añadir un número
    public void addNumber(String number){
            this.phoneNumbers.add(number);
    }

    //Modificar un número
    public void modifyNumber(Integer index, String newNumber){
        this.phoneNumbers.set(index,newNumber);
    }

    //Eliminar un número
    public void deleteNumber(int index){
        this.phoneNumbers.remove(index);
    }

    //Función que facilita la escritura de datos por la agenda.
    public String getAttributes(){
        return getName() + ";" + String.join(",",this.phoneNumbers) + ";" 
                + getEmail() + ";" + getAddress() + ";" + getNickname();
    }
    
}
