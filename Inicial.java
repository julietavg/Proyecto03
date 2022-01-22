import java.util.Random;
import java.util.Scanner;

/**
 * Clase para utilidades de la secuencia inicial
 * @author
 */
public class Inicial {

    /**
     * Método booleano que te dice si un string contiene solo letras
     *
     * @param string la cadena a comparar
     * @return true si solo es una cadena de letras
     * @return false contiene algún caracter
     */
    public boolean containsLetters(String string) {
        for (int x = 0; x < string.length(); x++) {
            char c = string.charAt(x);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Separa las letras de los caracteres
     *
     * @param str cadena a separar
     */
    public static void splitString(String str) {
        Scanner scan = new Scanner(System.in);
        StringBuffer alpha = new StringBuffer(),
                num = new StringBuffer(), special = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                num.append(str.charAt(i));
            } else if (Character.isAlphabetic(str.charAt(i))) {
                alpha.append(str.charAt(i));
            } else {
                special.append(str.charAt(i));
            }
        }
        System.out.println("De tu cadena las únicas letras son = " + alpha.length());
//        System.out.println(num);
//        System.out.println(special);
    }

    /**
     * Genera una secuencia de letras del abecedario sin W X y Q
     *
     * @param len
     * @return la secuencia de letras
     */
    public String generateRandom(int len) {
        String chars = "abcdefghijklmnñopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Genera una secuencia de letras del abecedario
     *
     * @param len
     * @return la secuencia de letras
     */
    public String generateRandomPro(int len) {
        String chars = "abcdefghijklmnñoprstuvyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
