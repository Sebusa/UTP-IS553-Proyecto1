/*Clase adicional que permite limpiar la consola*/
package mavenpackage;

public class Screen {
    public static void clearScreen(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
