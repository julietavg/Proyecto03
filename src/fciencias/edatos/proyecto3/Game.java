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

    /** Arreglo de puntuaciones de palabras validas */
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


    /** Ruta de archivo txt de estadisticas */
    private static final String file = "estadisticas.txt";


    /** Palabras dadas por el usuario */
    LinkedList<String> userWords;


    /** Secuencia */
    String secuenciaCadena;
    

    /**cáracteres  y repeticiones  de la secuencia inicial */
    private Map<Character, Integer> secuenciaRegistro;


    /** Contiene todas las palabras con su respectivs puntuacion */
    Map<String, Integer> scores;

    /** Jugador */
    private String player;



    //Colores
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";


    //----------------------Validación --------------------------------


    // DICCIONARIO
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
        
            
        }
    
    }


    /* Genera registro con los caracteres existentes en la secuenca
     * y su numero de repeticiones en la secuencia */
    private void generateRegistro(String secuencia){
        secuenciaRegistro = new AbstractHashMap<>();
        String secCopy = removeDuplicates(secuencia);
        char[] charArr  = secCopy.toCharArray();
        
        for (int i = 0; i < secCopy.length(); i++) {
            int rep = contarCaracteres(secuencia, secCopy.charAt(i));
            secuenciaRegistro.put(charArr[i], rep);

            
        }

    }

    /* Verifica si una palabra es valida respecto al numero de repeticiones de un 
     * caracter */
    private String verificaRepChar(String word){

        try {
            String copy = removeDuplicates(word);
            for (int i = 0; i < copy.length(); i++) {
                int rep = contarCaracteres(word,copy.charAt(i) );
                int r = secuenciaRegistro.get(copy.charAt(i));
                if(rep > r)
                    return "i";               
            }
        } catch (Exception e) {
        
        }
        return word;
    }

    /* Verifica si una lista de palabras es valida respecto a las repeticiones 
     * de caracteres */
    public LinkedList<String> verificaRep(){

        //String[]  arr = userWords.toArray();
        LinkedList<String> list = new LinkedList<>();

        //Cargar el registro de caracteres
        generateRegistro(secuenciaCadena);

        try {
            for (int i = 0; i < userWords.size(); i++) {
                String s= verificaRepChar(userWords.get(i));
                if( !s.equals("i"))
                    list.add(s);
                
            }
        } catch (Exception e) {
            
        }
        return list;
    }


    /* Remueve los caracteres duplicados en una cadena */
    private String removeDuplicates(String s) {
        StringBuilder noDupes = new StringBuilder(); 
        for (int i = 0; i < s.length(); i++) { 
            String si = s.substring(i, i + 1);
            if (noDupes.indexOf(si) == -1) { 
                noDupes.append(si);
            } 
        } return noDupes.toString(); 
    }
    /* Auxiliar para contar las apariciones de un caracter en una cadena */
    private int contarCaracteres(String cadena, char c){
        int  cont = 0; 
        for (int i = 0; i < cadena.length(); i++) {
            if(c == cadena.charAt(i)){
                cont++;
            }
        }return cont;
    }
    
    
    /* Verificar si la palabra pertenece a la secuencia inicial */
    private String belongSequence(String word){
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char c= word.charAt(i);
            if(c == 'á' || c== 'é' || c=='í'|| c =='ó' || c=='ú')
                continue;
            
            if(!secuenciaCadena.contains(c+""))
                return "N";
            
        }
        return  word;
    }
    public LinkedList<String> belong(){
        LinkedList<String>  l = new LinkedList<>();
        try {
            for (int i = 0; i < userWords.size(); i++) {
                String v = belongSequence(userWords.get(i));
                if(!v.equals("N"))
                    l.add(v);
            }
        } catch (Exception e) {
                
        }
        return l;
    }
    
    /* Eliminar palabras repetidas */
    private String[] deleteDup(String[] array){
        String s ="";
        for (int i = 0; i < array.length; i++) {
            if(s.contains(array[i]))
                continue;
            
            s+=array[i]+",";
        }
        String[] a = s.split(",");
        return a;
    }


    // Validar antes del diccionario

    /**
     * Verifica si las palabras son validas para buscar en el diccionario
     * @return arreglo de Strings
     */
    public String[] verifica(){
        String[] wordsUser = null;
        try {
            //Que pertencezca a la secuencia.
            userWords = belong();
            //Repeticiones
            userWords = verificaRep();
            //La palabras duplicadas no puntuan
            wordsUser  = userWords.toArray();
            wordsUser = deleteDup(wordsUser);
        } catch (Exception e) {
            System.out.println("\n\tNo se puede verficar,ingresaste palabras?( ⚆ _ ⚆ )");
        }

        return wordsUser;
    }







    // ----------------------P U N T U A C I O N -----------------------------

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
            System.out.println("\n\tAl parecer ninguna de las palabras que ingresaste merece ser"+
            "puntuada ¯\\_(ツ)_/¯");
            
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
        String estadisticas ="";
        try {    
            estadisticas ="\n\tSecuencia - " + getSecuencia()+
            "\tUser:" + player +"\n\n";
            int[] best = getBestScores();
            String[]keys = showBHelper(best);
            for (int i = 0; i < 3; i++) {
                estadisticas+="\n\tPalabra:" + keys[i] + "\t" + scores.get(keys[i])+ "pts";
            }
            //stadistics.add(stadistics.size()-1,estadisticas);
        } catch (Exception e) {
            System.out.println("No se pueden mostrar las estadísticas para esta secuencia.ಠ_ಠ");
            
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
            System.out.println("\n\tSi no puntuaste en ninguna no hay mejores puntuaciones lol ¯\\(°_o)/¯!!");
            
        }
        return best;
    }




    // ----------------- utilidades --------------------------

    /* Metodo auxiliar para imprimit un array de Integers */
    private void printArray(int[] arr){
        for (int i : arr) {

            System.out.print(i+" ");
        }
    }
    /* Metodo auxiliar para imprimir un array de Strings */
    private void printArray(String[] array){
        for (String i : array) {

            if(i == null){
                System.out.print("n,  ");
            }
            System.out.print(i+" ");
        }
    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.err.println(e);
        }
    }




    


    // -------------------- Estadisticas --------------------------------------

    private void iniatilizeData(){
        FileInputStream fis =null;
        ObjectInputStream ois =null;
        //Des-serializar el archivo de las estadisticas
    
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            stadistics = (LinkedList<String>)ois.readObject();
            
            
        } catch (FileNotFoundException fnfe) {
            stadistics = new LinkedList<>();
        }catch(ClassNotFoundException cnfe){
        }catch(IOException ioe){
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
    
    
    /**
     * Escribe un archivo txt con las estadisticas por secuencia.
     */
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
        }
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
            }
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

    

    

     //INICIO DE JUEGO ----------- Obtener secuencia

      
    /**
     * Ingreso de palabras por parte del usuario
     */
    public void jugar(){
        Scanner s = new Scanner(System.in);
        userWords = new LinkedList<>();
        timerA = new Timer();
        timerA.start();

        while(timerA.isAlive() ) {
            String palabra =  s.nextLine();
            userWords.add(palabra);
        }
    }

    /**
     * Auxiliar del método main para escoger que tipo de secuencia
     * de palabras quieres
     * @param sc secuencia que escoga el usuario 
     * */
    public void playGame(Scanner sc){

        Inicial inicial = new Inicial();
        String secuencia = "";
        Scanner scan = new Scanner(System.in);

        
        int cont =0;
        

        sleep(700);
        while (true) {
            try {
                System.out.println("\n\n\t Secuencia inicial de letras \n\tElige con cual te gustaría jugar" +
                "\n\n\tA.- Secuencia dada por el usuario.\n\n\tB.- Secuencia creada por la computadora.\n\n\tC.- Salir al menu principal.\n\n\t "
                + "\n\tTypea tu opción:");
                char option = sc.next().toUpperCase().charAt(0);
                sc.nextLine();
                switch (option) {
                    case 'A':
                        do {
                            do {
                                System.out.println(RED+"\n\tDa una secuencia de 9 LETRAS"+RESET);
                                secuencia =scan.nextLine();
                            } while (!inicial.containsLetters(secuencia));
                        } while (secuencia.length()!=9);
                        System.out.println("Tu secuencia de letras  " + secuencia);
                        System.out.println("Listo?? presiona enter"); 
                        secuenciaCadena = secuencia;
                        ++cont;
                        setSecuencia(cont);
                        sleep(500);
                        while (true) {
                            String v = sc.nextLine();
                            if(v.equals("")){
                                break;
                            }
                        }
                        jugar();
                        System.out.println("\n\tLas palabras que ingreso fueron:"+ userWords);

                        String[] words = verifica();
                        validation(words);
                
                        String b = showBestScore();
                        System.out.println(b+ "\tMejores puntuaciones:");
                        if(!b.equals("")){
                            stadistics.add(b);
                        }
                        writeStadistics();
                        
                        
                        break;

                    case 'B':
                        System.out.println("\n\tGenerando secuencia... ");
                        sleep(300);
                        secuenciaCadena = inicial.generateRandom(9);
                        System.out.println("\n\t\t" + secuenciaCadena);
                        System.out.println("Listo?? presiona enter");
                       
                        while (true) {
                            String v = sc.nextLine();
                            if(v.equals("")){
                                break;
                            }
                        }
                        sleep(500);
                        ++cont;
                        setSecuencia(cont);
                        jugar();
                        String[] words1 = verifica();
                        validation(words1);
                        
                        String c = showBestScore();
                        System.out.println(c+ "\tMejores puntuaciones:");
                        if(!c.equals("")){
                            stadistics.add( c);
                        }
                        writeStadistics();
                        break;
                    case 'C':
                        System.out.println("Saliendo--->");
                        writeStadistics();
                        saveStadistics();
                        sleep(600);
                        return;    
                    default:
                        System.out.println("\n\tEscoge A o B");
                        break;
            }
            } catch (Exception e) {
                System.out.println("\n\tFallo con éxito ;)");
            }
        }

    }
   
    
    
	
   

    public void menu(){
        iniatilizeData();
        try {
            
            Scanner sc = new Scanner(System.in);
            do { 
                System.out.println(PURPLE+" \t\n(づ｡◕‿‿◕｡)づ Bienvenido/a \n\t " +RESET);
                System.out.println(" [1] Jugar \n " 
                    + "[2] Reglas \n "
                    + "[3] Estadisticas del juego \n "
                    + "[4] Salir \n "
                    + "Elige una opcion: ");

                // String nine = "";
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1: 
                       //Identificar jugador 
                       System.out.println("\n\n\tTypea  un identificador de jugador:");
                       while (true) {
                           player = sc.nextLine().toUpperCase();
                           if(!player.equals("")){
                               break;
                           }
                       }
                       playGame(sc);
                      
                       break;
                    case 2:
                       System.out.println(RED+" \n\n\t\tR-E-G-L-A-S"+ RESET+"ಠ‿↼\n\n");
                       System.out.println(" ★ Deberas ingresar palabras apartir de una secuencia de letras \n" + 
                        " la mayor cantidad de palabras posibles en un minuto");
                       System.out.println(" \t\n  Puntuación  \t\n ");     
                       System.out.println("\n ★ Las palabras repetidas no púntuan, se descartaran automáticamente\n" + 
                       " ★ Debe ser una palabra del diccionario \n"+
                       " ★ La puntuación es la longitud de la palabra al cuadrado :) \n");
                       sleep(1000);
                       break;
                    case 3:
                        System.out.println(GREEN+" \n\tESTADISTICAS " + RESET+
                        "\n\n\tCargando estadísticas...");
                        sleep(500);
                        System.out.println(stadistics);
                        break;
                    case 4:
                        System.out.println(YELLOW+"\n\n\t\t\tSaliendo..."+RESET);
                        diccionario.checkFile();
                        writeStadistics();
                        saveStadistics();
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
       
        Game game = new Game();
        game.menu();

    }
}
