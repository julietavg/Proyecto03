package fciencias.edatos.proyecto3;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Clase que modela el juego de la secuencia de 9
 * letras
 * @author Reyes Ramos Luz María 318211073
 * @author Vargas Gutiérrez Julieta 318341945
 * @version 1.0 Enero 2022
 * @since EDD2021-1
 */

public class Game {

    /** Crónometro del juego */
    public static Timer timerA;

    /** Diccionario */
    private static final Diccionario diccionario = new Diccionario();

    private int[] scoreKeys;

    /** Palabras que existen en el diccionario */
    private String[] validWords;

    /** Puntuación total*/
    private int totalScore;


    /** Número de secuencia de palabras */
    private int secuencia;

    /** Guarda las estadistícas por cada secuencia */
    private LinkedList<String> stadistics;

    /** Ruta de archivo de estadisticas */
    private static final String path = "estadisticas.data";


    private static final String file = "estadisticas.txt";


    /** Contiene todas las palabras con su respectivs puntuacion */
    Map<String, Integer> scores;

    /** Jugador */
    private String player;



     
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
     * Verifica si el arreglo de palabras esta en el diccionario 
     * @param words El arreglo de palabras a verificar
     */
    public void validation(String[] words){
        String data="";
         
        try {
            for(int k = 0; k<words.length;k++){
                String w = words[k];
                if(diccionario.search(w)){
                    data+=w+",";
                }
            }
            validWords = data.split(",");
        } catch (Exception e) {
            System.out.println("\n\tError en la validación");
            System.out.println(e);
            e.printStackTrace();
        }
    
    }

    /**
     * Asigna puntuación a una  palabra valida en el juego
     * del juego.
     * @param palabra Palabra válida.
     * @return puntuación
     */
    public int score(String palabra){
        return (int)Math.pow(palabra.length(), 2);
    }

    private void getScores(){

        try {
            scoreKeys = new int[validWords.length];
            scores = new AbstractHashMap<>();
            System.out.println("\n\n\t\tAsignando puntuación...");
            sleep(600);
            for(int n =0; n<validWords.length;n++ ){
                String word = validWords[n];
                int score = score(word);
                System.out.println("\n\tPalabra:"+word + "\t --- " +score);
                scoreKeys[n] = score;
                scores.put(word,score);
                totalScore+=score;
            }
        
        } catch (Exception e) {
            System.out.println("\n\tOcurrio un error al obtener la puntuación:");
            System.out.println(e);
            e.printStackTrace();
        }
        
    }



    /**
     * Obtiene las 3 mejores puntuaciones por secuencia de palabras.
     * @return Un arreglo con las 3 mejores puntuaciones.
     */
    public int[] getBestScores(){
        int[] best = new int[3];
        try {
            getScores();
            Arrays.sort(scoreKeys);
            for (int i = 0,j=scoreKeys.length-1; i < best.length; i++,j--) {
                best[i] = scoreKeys[j];
                
            }     
        } catch (Exception e) {
            System.out.println("\n\tAlgo inesperado ocurrió al obtener "+
            "las mejores puntuaciones!!");
            System.out.println(e);
            e.printStackTrace();
        }
        return best;
    }


    private void printArray(int[] arr){
        for (int i : arr) {

            System.out.print(i+" ");
        }
    }
    private void printArray(String[] array){
        for (String i : array) {

            if(i == null){
                System.out.print("n,  ");
            }
            System.out.print(i+" ");
        }
    }



    /**
     * Obtiene la puntuación total que se obtuvo en el juego.
     * @return Puntuación total.
     */
    public int getTotalScore(){
        return totalScore;
    }

    /**
     * Muesta las mejores tres puntuaciones obtenidas 
     * en una secuencia.
     * @return Una cadena con las 3 mejores puntuacines.
     */
    public String showBestScore(){
        String estadisticas ="\n\tSecuencia - " + getSecuencia()+
        "\tUser:" + player +"\n\n";
        try {    
            stadistics = new LinkedList<>();
            int[] best = getBestScores();
            String[]keys = showBHelper(best);
            for (int i = 0; i < 3; i++) {
                estadisticas+="\n\tPalabra:" + keys[i] + "\t" + scores.get(keys[i])+ "pts";
            }
            stadistics.add(stadistics.size()-1,estadisticas);
        } catch (Exception e) {
            System.out.println("No se pueden mostrar las estadísticas para esta secuencia.");
            System.out.println(e);
            e.printStackTrace();
        }
        return estadisticas;
    }

   

    /* Axiliar de showBestScore */
    private String[] showBHelper(int[] best ){
        String[] keys = new String[3];
        try {
       
            int cont=0;
            //Ordenar el arreglo de palabras por length
            Arrays.sort(validWords, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2){
                  return Integer.compare(s1.length(),s2.length());
                }
            });
            for (int i = validWords.length-1, j=0; i >= 0; i--,j++) {
                String key = validWords[i];
                int s = scores.get(key);
                if(best[cont] == s){
                    keys[cont] = key;
                    cont++;
                }
                
            }
            
        } catch (Exception e) {
            
        }
        return keys;
    }

    //Obtener todas laa estadisticas
    /**
     * Muestra las estadisticas del juego:
     * Las mejores puntuaciones obtenidas por cada secuencia.
     */
    public void showStadistics(){
        FileInputStream fis =null;
        ObjectInputStream ois =null;
        //Des-serializar el archivo de las estadisticas
    
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            stadistics = (LinkedList<String>)ois.readObject();
            System.out.println(stadistics);
            
        } catch (FileNotFoundException fnfe) {
            System.out.println(stadistics);
        }catch(ClassNotFoundException cnfe){
            System.out.println(cnfe);
            cnfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println(ioe);
            ioe.printStackTrace();
        }finally{
            try {
                if(fis!=null)
                    fis.close();
                if(ois != null)
                    ois.close();              
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

    }


    public void writeStadistics(){
        

        
        try {    
            String[] s = stadistics.toArray();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("\t\tESTADISTICAS\n");
            for(int n=0; n<s.length;n++){
                writer.println(s[n]);
            }
            writer.close();
                
            
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    

 

    /**
     * Obtiene el numero de secuencia de palabras.
     * @return el número de secuencia de palabras.
     */
    public int getSecuencia(){
        return secuencia;
    }

    /**
     * Modifica el numero de secuencia de palabras.
     * @param s Nuevo numero de secuencia.
     */
    public void setSecuencia(int s){
        secuencia =s;
    }

    /**
     * Guarda las estadisticas del juego.
     */
    public void saveStadistics(){
        FileOutputStream fos =null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(stadistics);

            
        } catch (Exception e) {
            System.out.println(e);

        }finally{
            try {
                if(fos!= null)
                    fos.close();
                if(oos!= null)
                    oos.close();
                
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.err.println(e);
        }
    }



    

   
    /**
     * Auxiliar del método main para escoger que tipo de secuencia
     * de palabras quieres
     * @param sc secuencia que escoga el usuario 
     * */
	private void secuenciaPalabra(Scanner sc) {
        Inicial inicial = new Inicial();
        String nine = "";
        Scanner scan = new Scanner(System.in);

        //Identificacion del jugador

        System.out.println("\n\n\tTypea  un identificador de jugador:");
        player = sc.nextLine();
        

        sleep(700);

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
                    jugar();
                    break;
                case 'B':
                    System.out.println("Secuencia de letras: ");
                    System.out.println("\n" + inicial.generateRandom(9));
                    System.out.println("Listo?? presiona enter"); //jej tengo que checarlo
                    jugar();
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

    public void menu(){
        try {
            
            Scanner sc = new Scanner(System.in);
            do { 
                System.out.println(" \t\n Bienvenido/a \n\t ");
                System.out.println(" [1] Jugar \n " 
                    + "[2] Reglas \n "
                    + "[3] Estadisticas del juego \n "
                    + "[4] Salir \n "
                    + "Elige una opcion: ");

                // String nine = "";
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                       secuenciaPalabra(sc);
                       //Despues de que se juega inmediatamente se puntua
                       // y muestra los 3 mejores puntajes
                       //validation(words)
                       //showBestScore()
                       break;
                    case 2:
                       System.out.println(" Reglassss ");
                       //Falta eso xd
                       break;
                    case 3:
                        System.out.println(" \n\tEstadisticas " + 
                        "\n\n\tCargando estadísticas...");
                        sleep(500);
                        //showStadistics();
                        break;
                    case 4:
                        System.out.println("\n\n\t\t\tSaliendo...");
                        //saveStadistics();
                        diccionario.checkFile();
                        sleep(1000);
                        
                        return;
                    default:
                        System.out.println("Opción inválida");
            }

        } while (true);
        } catch (Exception e) {
            System.out.println("\n\n\tOperación no soportada");
        }
    }
    public static void main(String[] args) throws IOException {
        Game jueguito = new Game();
        Game g = new Game();
        Scanner sc = new Scanner(System.in);
        String[] p = {"uhhho", "diablo", "útiiygjl", "inútil","ffgfdf","ofdjdjs",
        "fkndjd", "fjolsmsn", "fifjen", "djwe'fj", "dodsmajs", "mdweklnf w", "kcnd sdns", "dinv ndn",
        "cdvnvn", "fjmforpjf", "kdopw","kfep", "fiffjn", "deofenrf", "comida", "sandía", "ópera", "mar","eslabonaríamos","eslabonas"};
        //g.validation(p);
   
        String[] p2 = {"uno", "diablo", "útiiygjl", "inútil","ffgfdf","ofdjdjs",
        "fkndjd", "fantasma", "moneda", "djwe'fj", "mdweklnf w", "kcnd sdns", "dinv ndn",
        "cdvnvn", "fjmforpjf", "kdopw","carta", "final", "deofenrf", "espachurraríamos", "sandía", "ópera", "mar","eslabonaríamos","eslabonas"};
        g.validation(p2);
        g.printArray(g.validWords);
        g.showBestScore();
        g.showStadistics();
        


        g.saveStadistics();
        g.writeStadistics();

        /*
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
        */

    }
}
