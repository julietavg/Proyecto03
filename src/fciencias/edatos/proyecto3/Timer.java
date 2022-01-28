package fciencias.edatos.proyecto3;
/**
 * Modela un temporizador simple
 * @author Vargas Gutiérrez Julieta 318341945
 * @version 1.0 Enero 2022
 * @since EDD2021-1
 */
public class Timer extends Thread {
    @Override
    public void run(){
        System.out.println("\n\n\tIniciando..."
        + "\n\n\tTines 60 s a  partir de ahora");
        
         for(int i = 60; i>0; i--){
            //System.out.println("Restan-- [" + i +"]");
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println(" \n\t\tIT'S OVER!!!-- ");
         System.out.println("Presiona enter para salir ");
    
        }
    }
    