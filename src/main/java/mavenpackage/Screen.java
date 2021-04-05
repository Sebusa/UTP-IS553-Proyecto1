/*Clase adicional que permite limpiar la consola*/
package mavenpackage;

public class Screen {
    public static void cleanScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
