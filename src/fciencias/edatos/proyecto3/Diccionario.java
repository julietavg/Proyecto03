
package fciencias.edatos.proyecto3;
import java.io.File;
import java.io.FileReader;
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
                return 98034; //294757
            case 'b':
                return 57398;
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
                return 198;
            case 'l':
                return 12500;
            case 'm':
                return 28604;
            case 'n':
                return 5408;
            case 'ñ':
                return 315;
            case 'o':
                return 7840;
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

    public boolean search(String palabra){
        //Buscar en el mapa
        String[] subTab = bank.get(palabra.charAt(0));
        
        try {
            
            System.out.println("Insdice : "+Arrays.binarySearch(subTab, palabra));
        } catch (Exception e) {
            //TODO: handle exception
        }
        return (Arrays.binarySearch(subTab, palabra) >=0) ? true :false; 
    }

        private int getCapacity(char a){
            switch (a) {
                case 'a':
                    return 2947729; //294757
                case 'b':
                    return 172147;
                case 'c':
                    return 155231;
                case 'd':
                    return 107119;
                case 'e':
                    return 282911;
                case 'f':
                    return 45587;
                case 'g':
                    return 39989;
                case 'h':
                    return 33961;
                case 'i':
                    return 57457;
                case 'j':
                    return 16649;
                case 'k':
                    return 571;
                case 'l':
                    return 487469;
                case 'm':
                    return 85817;
                case 'n':
                    return 17167;
                case 'ñ':
                    return 17167;
                case 'o':
                    return 22571;
                case 'p':
                    return 127447;
                case 'q':
                    return 6121;
                case 'r':
                    return 143719;
                case 's':
                    return 83311;
                case 't':
                    return 83639;
                case 'u':
                    return 7019;
                case 'v':
                    return 32099;
                case 'w':
                   return 1597;
                case 'x':
                    return 4597;
                case 'y':
                    return 69191;
                case 'z':
                    return 12347;
                default:
                    return 0;
            }
        
    }
    public static void main(String[] args) {
        Diccionario d = new  Diccionario();
        String[] p = d.readBank('a');
        //System.out.println("\n\tFunciona?" + p.length  );
        //System.out.println("\n\tFunciona?" + p.length + "\t" + p[0] + "\t" + p[1] );
        
        System.out.println("\n\n\tPrueba busqueda: buscar - dfdvdfá \t:  " + d.search("mixturará"));

        
        
    }

}
