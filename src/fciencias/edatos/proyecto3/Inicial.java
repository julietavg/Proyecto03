package fciencias.edatos.proyecto3;
import java.util.Random;

/**
 * Clase para utilidades de la secuencia inicial
 * @author
 */
public class Inicial extends Thread {

    /**
     * Método booleano que te dice si un string contiene solo letras
     * @param string la cadena a comparar
     * @return true si solo es una cadena de letras
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
     * Genera una secuencia de letras del abecedario sin W X y Q
     *
     * @param len
     * @return la secuencia de letras
     */
    public String generateRandom(int len){
        String chars = "abcdefghijklmnñopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

   
   
    
}
