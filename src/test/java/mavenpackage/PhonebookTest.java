package test.java.mavenpackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import classes.java.mavenpackage.*;

public class PhonebookTest {

    @DisplayName("Prueba unitaria de la clase Phonebook")
    //Prueba de la función de buscar un palabra en la agenda
    @ParameterizedTest()
    @CsvFileSource(resources = "testCases/PhonebookCase.csv")
    void searchWordTest(String name, String number, String email,
                String address, String nickname, String expectedOutput){
        var agenda = new Phonebook();
        var user = new Contact();

        user.setName(name);
        user.addNumber(number);
        user.setEmail(email);
        user.setAddress(address);
        user.setNickname(nickname);

        agenda.addContact(user);

        Boolean expectedAnswer = Boolean.parseBoolean(expectedOutput);
        String wordToSearch = "yahoo";

        assertEquals(expectedAnswer, agenda.searchWord(wordToSearch, user.getEmail()));

    }

    //Prueba de la función de verificar un número
    @ParameterizedTest()
    @CsvFileSource(resources = "testCases/PhonebookCase.csv")
    void verifyNumberTest(String name, String number, String email,
                String address, String nickname, String expectedOutput){
        
        var agenda = new Phonebook();
        var user = new Contact();

        user.setName(name);
        user.addNumber(number);
        user.setEmail(email);
        user.setAddress(address);
        user.setNickname(nickname);

        agenda.addContact(user);

        Boolean expectedAnswer = Boolean.parseBoolean(expectedOutput);

        assertEquals(expectedAnswer, agenda.verifyNumber(number));

    }
    
    @Test
    public void importFileTest(){
        var agenda = new Phonebook();

        String fileToVerify = "resources/user/archivoDePrueba5.txt";
        Integer expectedAnswer = 0;
        assertEquals(expectedAnswer, agenda.verifyFile(fileToVerify));

    }
}
