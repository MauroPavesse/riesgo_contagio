
package clases;

import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Copy&&Paste
 * 
 */
public class Main {
    
    public static void main(String args[]){
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Ingrese, en numero entero, dejando un espacio:\n(Cantidad de personas) (Cantidad de casos positivos) (Establece una distancia de contagio)");
        String linea = entrada.nextLine();
        String [] separo = linea.split(" ");
  
        int n = Integer.parseInt(separo[0]);
        int m = Integer.parseInt(separo[1]);
        int D = Integer.parseInt(separo[2]);
        
        
        int x[] = new int[n];
        int y[] = new int[n];
        System.out.println("Ingrese las coordenadas para cada persona:");
        for(int i=0; i<n; i++){
            System.out.printf("X: ");
            x[i] = entrada.nextInt();
            System.out.printf("Y: ");
            y[i] = entrada.nextInt();
        }
        
        int[] positivos = new int[m];
        System.out.println("Ingrese indice de las personas positivas: ");
        for(int i=0; i<m; i++){
            positivos[i] = entrada.nextInt();
        }
           
        
        int[] r = riesgo(x, y, D, positivos);
        
        for(int i=0; i<r.length; i++){
            System.out.printf(r[i] + " ");
        }
        
    }
    
    public static int[] riesgo(int[] x, int[] y, int D, int[] positivos){
        
        double distancia;
        int count =0;
        int[] r = new int[x.length+1];
        r[0] = positivos.length;
        
        int[] indices = new int[x.length];
        for(int i=0; i<positivos.length; i++){
            indices[i] = positivos[i];
        }
        int marcador = positivos.length;
        for(int i=marcador; i<indices.length; i++)
            indices[i] = -1;
        
        int[] aux = new int[x.length];
        for(int i=0; i<aux.length; i++)
            aux[i] = -1;
        
        for(int t=1; t<r.length; t++){
            for(int i=0; i<x.length; i++){
                if(eliminarRepetidos(i, indices)){
                    for(int h=0; h<indices.length; h++){
                        if(indices[h] != -1){
                            distancia = Math.sqrt(Math.pow(x[indices[h]]-x[i], 2) + Math.pow(y[indices[h]]-y[i], 2));
                            if(distancia <= D){
                                aux[count] = i;
                                count++;
                                break;
                            }
                        }
                    }
                }
            }
            for(int i=0; i<aux.length; i++){
                if(aux[i] != -1){
                    indices[marcador] = aux[i];
                    marcador++;
                    aux[i] = -1;
                }
            }
            
            
            
            r[t] = count;
            count = 0;
        }
        int sumador = 0;
        for(int i=0; i<r.length; i++){
            sumador += r[i];
        }
        if(sumador != x.length)
            r[r.length-1] = x.length-sumador;
        
        return r;
        
    }
    
    public static boolean eliminarRepetidos(int i, int[] indices){
        for(int j=0; j<indices.length; j++){
            if(indices[j] != -1)
                if(i == indices[j])
                    return false;
        }
        return true;
    }
    
}
        