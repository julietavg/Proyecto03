/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

/**
 *
 * @author julie
 */
public class Timer extends Thread {
    @Override
    public void run(){
         for(int i = 10; i>0; i--){
            System.out.println("Segundos restantes = [" + i +"]");
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println(" its over ");
         System.out.println("Presiona enter para salir ");
    
        }
    }
    
   
    
 

   