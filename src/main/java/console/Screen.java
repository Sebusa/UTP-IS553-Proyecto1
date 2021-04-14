/*
Clase Screen
Función: Limpiar la consola para el UI de versión en consola.
Versión: 1.0
 */
package console;

public class Screen {
    
    public static void clearScreen(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
