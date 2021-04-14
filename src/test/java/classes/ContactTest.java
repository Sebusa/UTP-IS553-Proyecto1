/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ContactTest {

    @DisplayName("Prueba unitaria de la clase Contact.")
    //Prueba del retorno de los atributos de un contacto
    @ParameterizedTest()
    @CsvFileSource(resources = "testCases/ContactCase.csv")
    void getAttributesTest(String name, String number, String email, 
                String address, String nickname, String expectedResult){
        
        var userTest = new Contact();
        userTest.setName(name);
        userTest.addNumber(number);
        userTest.setEmail(email);
        userTest.setAddress(address);
        userTest.setNickname(nickname);

        assertEquals(expectedResult, userTest.getAttributes());

    }

    //Prueba del manejo de números
    @Test
    public void phoneNumbersTest(){
        var userTest = new Contact();

        userTest.addNumber("3214567586");
        userTest.addNumber("3106105907");
        userTest.modifyNumber(0, "3106105909");
        userTest.deleteNumber(1);

        List<String> numbersExpected = new ArrayList<>();
        numbersExpected.add("3106105909");

        assertEquals(numbersExpected, userTest.getPhoneNumbers());
    }

}
