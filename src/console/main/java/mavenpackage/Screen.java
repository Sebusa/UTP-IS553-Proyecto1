/*Clase adicional que permite limpiar la consola.
Versión: 2.0*/
package mavenpackage;

public class Screen {
    public static void clearScreen(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /*public static void clearScreen(){
        System.out.print("\033[H\033[2J");   
        System.out.flush(); 
    }*/
}
