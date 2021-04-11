package classes.java.mavenpackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Phonebook {
    private String filePath = "resources/data/dataBase.txt";
    private List<Contact> contactsBook = new ArrayList<>();

    //Métodos de retorno básicos
    public String getFilePath(){
        return filePath;
    }

    public List<Contact> getContactsBook(){
        return this.contactsBook;
    }
    public Integer getContactsBookSize(){
        return this.contactsBook.size();
    }

    //Función para retornar un contacto de la agenda por medio de un índice
    public Contact getContactByIndex(int index){
        return this.getContactsBook().get(index);
    }

    //Vaciar la lista de contactos
    public void clearList(){
        this.contactsBook.clear();
    }

    /*Dos funciones para escribir archivos
    ->writeOverData(): Escribir sobre archivo que ya contiene información.
    ->writeNewData(): Escribir sobre un archivo vacío.*/
    public Boolean writeOverData(String fileName){
        Boolean answer;
        try{
            var fileOutput = new FileWriter(fileName, true);

            for(Contact user : this.contactsBook){
                fileOutput.write(user.getAttributes());
                fileOutput.write("\n");
            }
            fileOutput.close();
            answer = true;
        } catch(IOException e){
            answer = false;
            e.printStackTrace();
        }
        return answer;
    }

    public Boolean writeNewData(String fileName){
        Boolean answer;
        try{
            var fileOutput = new FileWriter(fileName, false);

            for(Contact user : this.contactsBook){
                fileOutput.write(user.getAttributes() + "\n");
            }
            fileOutput.close();
            answer = true;
        } catch(IOException e){
            answer = false;
            e.printStackTrace();
        }
        return answer;
    }

    //Función para leer datos de un archivo
    public void convertData(){
        this.clearList();

        try{
            var fileInput = new BufferedReader(new FileReader(this.getFilePath()));
            String[] dataRecolected;
            String stringBuffer;

            while((stringBuffer = fileInput.readLine()) != null){
                dataRecolected = stringBuffer.split(";");   
                var user = new Contact();

                user.setName(dataRecolected[0]);
                String[] phoneNumbers = dataRecolected[1].split(",");
                for(String number : phoneNumbers){
                    user.addNumber(number);
                }
                user.setEmail(dataRecolected[2]);
                user.setAddress(dataRecolected[3]);
                user.setNickname(dataRecolected[4]);

                this.addContact(user);
            }
            fileInput.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //añadir un contacto
    public void addContact(Contact user){
        this.contactsBook.add(user);
    }

    //modificar un contacto
    public void modifyContact(int index, Contact modifiedContact){
        this.getContactsBook().set(index, modifiedContact);
    }

    //eliminar un contacto
    public void deleteContact(int index){
        this.getContactsBook().remove(index);
    }

    /*Función que verifica si un número ya está registrado.
    Verdadero: Repetido - Falso: No repetido*/
    public Boolean verifyNumber(String number){
        Boolean answer = false;

        convertData();

        for(Contact user : this.getContactsBook()){
            answer = user.getPhoneNumbers().contains(number);
            if(answer){
                break;
            }
        }
        return answer;
    }

    //Función para buscar un contacto bajo un criterio de búsqueda
    public Boolean searchWord(String searchedWord, String sentence){
        Pattern wordPattern = Pattern.compile(searchedWord, Pattern.CASE_INSENSITIVE);
        Matcher wordMatcher = wordPattern.matcher(sentence);
        Boolean wordFound = wordMatcher.find();
        return wordFound;
    }

    //Función para permitir el usuario exportar un archivo con los datos de la agenda.
    public Boolean exportDataFile(String newFilePath){
        Boolean answer;

        var file = new File(newFilePath);
        if(!file.exists()){
            try{
                file.createNewFile();
                answer = true;
            } catch(IOException e){
                answer = false;
                e.printStackTrace();
            }
        }
        else{
            answer = true;
        }

        writeOverData(newFilePath);

        return answer;
    }

    //Funciones para poder importar archivos externos y verificar si son válidos.
    //Función que verifica que el archivo importado cumpla con la estructura requerida
    public Integer verifyFile(String fileName){
        Integer answer = 0;//Archivo bien cargado
        this.clearList();

        try{
            var fileToOpen = new BufferedReader(new FileReader(fileName));
            String[] dataRecolected;
            String stringBuffer;

            Boolean fileVerified = true;
            while((stringBuffer = fileToOpen.readLine()) != null){
                if(fileVerified){
                    dataRecolected = stringBuffer.split(";");

                    if(dataRecolected.length != 5){
                        fileVerified = false;
                        answer = 1;//No cumple la estructura
                        break;
                    }
                    else{
                        var user = new Contact();
                        user.setName(dataRecolected[0]);
                        String[] phoneNumbers = dataRecolected[1].split(",");
                        for(String number : phoneNumbers){
                            try{
                                Long.parseLong(number);
                                if(!verifyImportedNumbers(number)){
                                    user.addNumber(number);
                                }
                                else{
                                    fileVerified = false;
                                    answer = 2;//Número repetido
                                    break;
                                }
                            }catch(Exception e){
                                fileVerified = false;
                                answer = 3;//Número no válido
                                break;
                            }
                        }
                        user.setEmail(dataRecolected[2]);
                        user.setAddress(dataRecolected[3]);
                        user.setNickname(dataRecolected[4]);
                        this.addContact(user);
                    }
                }
            }
            fileToOpen.close();
        }catch(IOException e){
            answer = 4;//Archivo no cargado correctamente.
        }

        return answer;
    }

    //Función que verifica los números importados
    public Boolean verifyImportedNumbers(String number){
        Boolean answer = false;
        for(Contact user : this.contactsBook){
            answer = user.getPhoneNumbers().contains(number);
            if(answer){
                break;
            }
        }
        return answer;
    }

}