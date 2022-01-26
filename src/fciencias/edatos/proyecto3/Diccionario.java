package fciencias.edatos.proyecto3;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.Arrays;

/**
 * Clase que modela un diccionario en español.
 * 
 * @author Reyes Ramos Luz María 318211073
 * @author
 * @version 1.0 Enero 2022.
 * @since EDD2021-1
 */
public class Diccionario {

    /** Banco de palabras */
    private Map<Character, String[]> bank;

    private final String file = "Dictionary/diccionarioCompleto.txt";

    private final char[] abc = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    // Construir un diccionario
    public Diccionario() {
        builDictionary();
    }

    /**
     * Lee un archivo txt y almacena la informacion obtenida en una tabla hash.
     * 
     * @return un arreglo con todos lo elementos del archivo
     */

    private String[] readBank() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        // AbstractHashMap<String, String> subTab = new
        // AbstractHashMap<>(getCapacity(c));
        String[] subTab = new String[646615];
        int cont = 0;
        try {
            archivo = new File(file);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Reading file
            String line;

            while (((line = br.readLine()) != null)) {
                subTab[cont] = line;
                cont++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        // Arrays.sort(subTab);
        System.out.println("\ntotal size: " + cont);
        return subTab;

    }

    // fill Map
    private void builDictionary() {
        String[] total = readBank();
        try {

            bank = new AbstractHashMap<>(3119);
            for (int n = 0; n < 26; n++) {
                bank.put(abc[n],builder(abc[n], total));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] builder(char c, String[]bank){
        int size =getSize(c);
        String[]subTab = new String[size];
        //recorrer a la mitad
        int i = Arrays.binarySearch(abc, c);
        char a;
        try {
            if(i>= 0 && i<13){
                int cont =0;
                for(int n=0;n<bank.length;n++){
                    a = fixWord(bank[n]);
                    if(a== abc[i+1]){
                        return subTab;

                    }
                        
                    if(a == c ){
                        subTab[cont] = bank[n];
                        cont++; 
                    }
                    
                    
                }
            }else{
                int cont = size-1;
                for(int m = bank.length-1; m>=0; m--){
                    
                    a = fixWord(bank[m]);
                    if(a == abc[i-1]){
                        return subTab;
                    }
                    if(a == c){
                        subTab[cont]= bank[m];
                        cont--;
                    }
                    
                        
                    
                }
            }

            
            
            

        } catch (Exception e) {

            //TODO: handle exception
        }
        
        return subTab;
    }

    public boolean search(String palabra) {

        // Buscar en el mapa
        System.out.println("\n\tCaracter:" + searchHelper(palabra.charAt(0)));
        String[] subTab = bank.get(searchHelper(palabra.charAt(0)));
        Arrays.sort(subTab);

        System.out.println("Indice : " + Arrays.binarySearch(subTab, palabra));
        return (Arrays.binarySearch(subTab, palabra) >= 0) ? true : false;
    }

    private char searchHelper(char c) {

        if (c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú') {
            switch (c) {
                case 'á':
                    return 'a';
                case 'é':
                    return 'e';
                case 'í':
                    return 'i';
                case 'ó':
                    return 'o';
                case 'ú':
                    return 'u';
                default:
                    break;
            }
        }
        return c;

    }

    private char readHelper(String palabra) {
        char c = palabra.toLowerCase().charAt(0);

        if (c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' || c == 'ñ' || c == 'ǵ'
                || c == 'ṕ') {
            switch (c) {
                case 'á':
                    return 'a';
                case 'é':
                    return 'e';
                case 'í':
                    return 'i';
                case 'ó':
                    return 'o';
                case 'ú':
                    return 'u';
                case 'ñ':
                    return 'n';
                case 'ǵ':
                    return 'g';
                case 'ṕ':
                    return 'p';
                default:
                    break;
            }
        }
        return c;

    }

    private char fixWord(String w) {
        char a = readHelper(w);
        if (a != 'a' && a != 'b' && a != 'c' && a != 'd' && a != 'e' && a != 'f' && a != 'g' && a != 'h' && a != 'i' &&
            a != 'j' && a != 'k' && a != 'l' && a != 'm' && a != 'n' && a != 'o' && a != 'p' && a != 'q' && a != 'r'
            && a != 's' && a != 't' &&
            a != 'u' && a != 'v' && a != 'w' && a != 'x' && a != 'y' && a != 'z') {
        
            if (a == '(') 
                return readHelper(w.charAt(1) + "");
    
            return readHelper(w.charAt(2)+"");
        }
        return a;
    }

    private int getSize(String[] bank, char c) {
        int cont = 0;
        String m = "";
        for (int n = 0; n < bank.length; n++) {

            char a = fixWord(bank[n]);
            if (a == c) {
                cont++;
                m = bank[n];
            }
        }
        // System.out.println("\t"+m);
        return cont;
    }

    private int getSize(char a) {
        switch (a) {
            case 'a':
                return 98033; // 294757
            case 'b':
                return 17398;
            case 'c':
                return 66785;
            case 'd':
                return 84428;
            case 'e':
                return 94307;
            case 'f':
                return 15196;
            case 'g':
                return 13330;
            case 'h':
                return 11323;
            case 'i':
                return 19152;
            case 'j':
                return 5548;
            case 'k':
                return 197;
            case 'l':
                return 12500;
            case 'm':
                return 28604;
            case 'n':
                return 5721;
            case 'o':
                return 7530;
            case 'p':
                return 42484;
            case 'q':
                return 2041;
            case 'r':
                return 47769;
            case 's':
                return 27717;
            case 't':
                return 27880;
            case 'u':
                return 2340;
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

    private int[] sizeHelper(String[] bank) {
        int[] sizes = new int[26];

        for (int n = 0; n < 26; n++) {
            sizes[n] = getSize(bank, abc[n]);
        }
        // System.out.println(sizes.toString());
        return sizes;

    }

    public static void main(String[] args) {
        Diccionario d = new Diccionario();
        // Pruebas
        // String[] p = d.readBank('u');
        // System.out.println("\n\n\tuno existe? \t " + Arrays.binarySearch(p,"uno"));
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
                System.out.println("\n\nOperacion no soportada :v.");
            }

            
        }

    }

}
