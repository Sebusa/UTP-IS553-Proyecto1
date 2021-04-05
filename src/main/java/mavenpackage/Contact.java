package mavenpackage;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private List<String> phoneNumbers = new ArrayList();
    private String email;
    private String address;
    private String nickname;

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

    public void showData(){

    }

    public void modifyData(){

    }

    public void addData(){

    }

    public void deleteData(){

    }

    public void addNumber(){

    }
    public void modifyNumber(){

    }
    public void deleteNumber(){

    }

}
