package p3;
import java.io.IOException;
import java.util.Scanner;
/**
 * Clase que leer del archivo del diccionario
 *
 * @author
 */
public class SecuenciaInicial {

    public static void main(String[] args) throws IOException {
        Inicial inicial = new Inicial();

        //SOLO ES UN MENU DE PRUEBA
        System.out.println("  useless menu ");
        Scanner sc = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        // String nine = "";

        do {
            System.out.println("[1] Juego con las palabras dadas del usuario \n "
                    + " [2] Juego con secuencia de la computadora \n "
                    + "[3] Verificar si una palabra se encuentra en el diccionario \n "
                    + "[4] Salir \n "
                    + "Elige una opcion: ");

            String nine = "";
            String a;
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    // Por alguna extraña razón no pude poner los "do" juntos pero lo chacaré
                    // TENGO QUE ENSEÑARTE ALGO DE AQUIII
                    do {
                        do {
                            System.out.println("Da una secuencia de 9 LETRAS");
                            nine = scan.nextLine();
                            inicial.splitString(nine);
                            //  while(result.length() < 9){
//                                System.out.println("Insert more character");
//                                a = scan.nextLine();
//                                System.out.println("ahh mm cuantas letras tengo" + nine.length());
                            // }
                        } while (!inicial.containsLetters(nine));
                    } while (nine.length() != 9);

                    break;
                case 2:

                    System.out.println("Secuencia de letras generadas:  ");
                    System.out.println(inicial.generateRandom(9));
                    System.out.println("Secuencia de letras generadas sin letras comunes:  ");
                    System.out.println(inicial.generateRandomPro(9));
                    break;
                case 3:
                    System.out.println(" out of order ");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida");
            }

        } while (true);

    }
}
