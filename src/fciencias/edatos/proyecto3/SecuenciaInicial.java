package p3;
import java.io.IOException;
import java.util.Scanner;
/**
 * Clase que leer del archivo del diccionario
 *
 * @author
 */

public class SecuenciaInicial {
    public static Timer timerA;
     
    /**
     * 
     */
    public void jugar(){
        Scanner s = new Scanner(System.in);
        LinkedList<String> ll = new LinkedList<>();
        timerA = new Timer();
        timerA.start();

        while(timerA.isAlive() ) {
         String palabra =  s.nextLine();

      }
    }
            
   
         /**
         * Auxiliar del método main para escoger que tipo de secuencia
         * de palabras quieres
         * @param sc secuencia que escoga el usuario 
         */
	private void secuenciaPalabra(Scanner sc) {
                Inicial inicial = new Inicial();
                String nine = "";
                Scanner scan = new Scanner(System.in);
                SecuenciaInicial jueguito = new SecuenciaInicial();
    while (true) {
            System.out.println("\n\n\t Secuencia inicial de letras \n\tElige con cual te gustaría jugar" +
                            "\n\n\tA.- Secuencia dada por el usuario.\n\n\tB.- Secuencia creada por la computadora.\n\n\tC.- Salir al menu principal.\n\n\t "
                            + "\n\tTypea tu opción:");
            char option = sc.next().toUpperCase().charAt(0);
            sc.nextLine();
            switch (option) {
                 case 'A':
                   do {
                      do {
                       System.out.println("Da una secuencia de 9 LETRAS");
                       nine = scan.nextLine();
                      } while (!inicial.containsLetters(nine));
                    } while (nine.length() != 9);
                     System.out.println("Tu secuencia de letras  " + nine);
                     System.out.println("Listo?? presiona enter"); //jej tengo que checarlo
                     jueguito.jugar();
                            break;
                    case 'B':
                    System.out.println("Secuencia de letras: ");
                    System.out.println("\n" + inicial.generateRandom(9));
                    System.out.println("Listo?? presiona enter"); //jej tengo que checarlo
                    jueguito.jugar();
                            break;
                    case 'C':
                    System.out.println("Saliendo");
                    return;     
                    default:
                            System.out.println("\n\tEscoge A o B");
                            break;
            }
    }
}
    public static void main(String[] args) throws IOException {
        SecuenciaInicial jueguito = new SecuenciaInicial();
        Scanner sc = new Scanner(System.in);

        do { System.out.println(" \t\n Bienvenido/a \n\t ");
            System.out.println(" [1] Jugar \n " 
                    + "[2] Reglas \n "
                    + "[3] Estadisticas del juego \n "
                    + "[4] Salir \n "
                    + "Elige una opcion: ");

           // String nine = "";
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    jueguito.secuenciaPalabra(sc);
                    break;
                case 2:
                    System.out.println(" Reglassss ");
                    break;
                case 3:
                    System.out.println(" Estadisticas ");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida");
            }

        } while (true);

    }
}
