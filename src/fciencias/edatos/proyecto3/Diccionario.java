package fciencias.edatos.proyecto3;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.Arrays;
/**
 * Clase que  modela un diccionario en español.
 * @author Reyes Ramos Luz María 318211073
 * @author 
 * @version 1.0 Enero 2022.
 * @since EDD2021-1 
 */
public class Diccionario {

    /** Banco de palabras */
    private Map<Character, String[]> bank;


    //Construir un diccionario
    public Diccionario(){
        builDictionary();
    }

    
    /**
     * Lee un archivo txt  y almacena la informacion obtenida en una tabla hash.
     * @param La letra de la inciañ de las palabras del archivo a leer.
     * @return una tabla hash con las palabras del banco.
     */
    private String[] readBank(char c){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        //AbstractHashMap<String, String> subTab = new AbstractHashMap<>(getCapacity(c));
        String[] subTab = new String[getSize(c)];

        String file = "Dictionary/palabras_"+c + ".txt";

            
        try {
            archivo = new File(file);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            //Reading file
            String line;
            int cont= 0;
            while ((line=br.readLine()) != null) {
                subTab[cont] = line;
                cont++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != fr){
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Arrays.sort(subTab);
        return subTab;

    }

    //fill Map
    private void builDictionary(){
        try {
            char[] abc = new char[]{'a','b','c','d','e','f','g','h','i','j','k',
            'l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
            bank = new AbstractHashMap<>(3119);
            for(int n = 0; n<27; n++){
                bank.put(abc[n], readBank(abc[n]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getSize(char a){
        switch (a) {
            case 'a':
                return 97893; //294757
            case 'b':
                return 17398;
            case 'c':
                return 66785;
            case 'd':
                return 84428;
            case 'e':
                return 94257;
            case 'f':
                return 15190;
            case 'g':
                return 13328;
            case 'h':
                return 11323;
            case 'i':
                return 19094;
            case 'j':
                return 5548;
            case 'k':
                return 197;
            case 'l':
                return 12500;
            case 'm':
                return 28604;
            case 'n':
                return 5407;
            case 'ñ':
                return 314;
            case 'o':
                return 7481;
            case 'p':
                return 42481;
            case 'q':
                return 2041;
            case 'r':
                return 47769;
            case 's':
                return 27716;
            case 't':
                return 27880;
            case 'u':
                return 2323;
            case 'v':
                return 10698;
            case 'w':
                return 41;
            case 'x':
                return 168;
            case 'y':
                return 845;
            case 'z':
                return 4580;
            default:
                return 0;
        }

    }

    public boolean search(String palabra){
        String word = palabra.toLowerCase();
        //Buscar en el mapa
        System.out.println("\n\tCaracter:" + word.charAt(0));
        String[] subTab = bank.get(word.charAt(0));
        
        System.out.println("Indice : "+Arrays.binarySearch(subTab, word));
        return (Arrays.binarySearch(subTab, word) >=0) ? true :false; 
    }

    public static void main(String[] args) {
        Diccionario d = new Diccionario();
        //Pruebas
        String[] p = d.readBank('u');
        //System.out.println("\n\n\tuno existe? \t " + Arrays.binarySearch(p,"uno"));
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                do {
                    System.out.println("\n\n\tPruebas\n"+ 
                    "1.- Buscar palabra.\n\n"+
                    "2.- Salir");
                    int option  = Integer.parseInt(sc.nextLine());
                    switch (option) {
                        case 1:
                            System.out.println("\n\tIngrese una palabra:");
                            String palabra = sc.nextLine();
                            System.out.println("\n\n\tLa palabra " + palabra+ " existe?\t" + d.search(palabra));
                            
                            break;
                    
                        case 2:
                            System.out.println("\n\n\tSaliendo...");
                            return;
                        default:
                            System.out.println("\n\nOpcion invalida");
                            break;
                    }
                } while (true);
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("\n\nEntrada no valida.");
            }

            
        }
        
        
        
        
    }

}
