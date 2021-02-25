/* 
   Programa de generación de 680 cartones para un único sorteo.
   
   Author : Pablo Courault.
   
   Creado : 15/11/2013.
   Created : 2013.


   Versión : 1.1.3

*/

import java.util.StringTokenizer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;
 
public class cartones680
   
   {
     
     /* El vector cgenerados se utiliza para generar las combinaciones;
        es el núcleo de todo el sistema */
      
     LinkedList<String> cgenerados = new LinkedList<String>(); 
     

     /* se define un vector con los 90 números que luego serán tomados aleatoriamente para generar 
        las particiones o lotes con los que se generarán las combinaciones.
        Ej. de aquí se armarán aleatoriamente 5 lotes de 17 elementos para generar un total de
        680 cartones que pueden participar en un sorteo.
        La idea es que esto sea la base para luego poder configurar cómo se arman los lotes,
        según las necesidades de cada sorteo */
     
     ArrayList<Integer> vectorbolillas = new ArrayList<Integer>();

     
     /* se crean 5 vectores que tomarán 17 elementos de vectorbolillas, para luego
        mediante mapeo utilizando las combinaciones generadas, arrojarán los distintos
        mini-lotes de 136 cartones. Esos 5 vectores de 136 cartones cada uno; conforman
        el lote total de 680 cartones */
     
     ArrayList<Integer> loteaa = new ArrayList<Integer>();
     ArrayList<Integer> loteab = new ArrayList<Integer>();
     ArrayList<Integer> loteac = new ArrayList<Integer>();
     ArrayList<Integer> lotead = new ArrayList<Integer>();
     ArrayList<Integer> loteae = new ArrayList<Integer>();
     

     /* se define el arreglo que contendrá el total de cartones generados a través del uso de las combinaciones
        calculadas (vector cgenerados) y la extrapolación para cada uno de los 5 lotes calculados */

     int cartonessorteo[][] = new int [680][17]; 


     /* se define un arreglo StringTokenizer para sacar los elementos de las combinaciones generadas, 
        tomando como separador los espacios en blanco */

     // StringTokenizer stk = new StringTokenizer();

     
     /* las variables 'x' e 'y' se utilizan simplemente para tabular la salida del programa (filas,columnas) */
     
     int x;
     int y;

     int i;
     int n;
     int m;

       
     /* las siguientes variables se utilizan para como índices para las extrapolaciones de los distintos lotes */

     int mloteb;
     int mlotec;
     int mloted;
     int mlotee;

     int puntero;
     
     int contador;
     
     /* se usan para el simulador de sorteo */
     
     int numbolilla;
     int bolilla;
     

     /* la variable 'conjunto' se utiliza para indicar la cantidad de números sobre los
        que se calcularan tuplas indicadas por el tamaño de la variable 'numerosporcarton' */
   
     int conjunto;
     int numerosporcarton;
     

     /* la variable 'continuar' se utiliza para leer si el usuario desea seguir ejecutando
         el programa o finalizar el mismo */
         
     String continuar;
     String nuevabolilla;
   

     /* la variable 'tamanio' obtiene/guarda el total de tuplas generadas */
     
     int tamanio;
     
     /* declaración de la variable de tipo FileOutputStream para grabar en archivo los cartones */

     FileOutputStream archivosalida;

      /* declaración del string para grabar en el archivo */

      String datossalida = new String();
      
     
     /* declaración de variables varias para pruebas, borrar al terminar o formalizar su declaración */


    
     public cartones680()
        
        {

         /* carga de las bolillas de vectorbolillas */
          
         for (i=0; i<90; i++)
             {
              vectorbolillas.add(i+1); 
             } 
         
         System.out.println("\n");
         System.out.println(" Programa de generación de lote de 680 cartones \n");

         continuar = "s";
         nuevabolilla = "y";
         
         Scanner entrada = new Scanner(System.in);
         
         while (!continuar.equalsIgnoreCase("n"))
         
           {
            y = 0;
            x = 0;
            tamanio = 0;
            conjunto = 17;
            numerosporcarton=15;
            numbolilla=0;
            

            
            /* inicializo los minilotes para cada ejecución del programa */

            loteaa.clear();
            loteab.clear();
            loteac.clear();
            lotead.clear();
            loteae.clear();
            

            /* se mezclan aleatoriamente los números, para que luego, las series de 
               cartones que se generan nunca sean las mismas */

            Collections.shuffle(vectorbolillas);

            System.out.println(" Se arma en forma aleatoria la matriz de bolillas..." + "\n");
            System.out.println(vectorbolillas + "\n");
            
         
            /* se hace la carga de los números que irán en cada una de las 5 series de 17 números */

            for (i=0; i<17; i++)
                {
                loteaa.add(vectorbolillas.get(i));
                }        
           
            for (i=17; i<34; i++)
                {
                loteab.add(vectorbolillas.get(i));
                }        
           
            for (i=34; i<51; i++)
                {
                loteac.add(vectorbolillas.get(i));
                }        
          
            for (i=51; i<68; i++)
                {
                lotead.add(vectorbolillas.get(i));
                }        
      
            for (i=68; i<85; i++)
                {
                loteae.add(vectorbolillas.get(i));
                } 


            System.out.println("\n");
            System.out.println(" Procesando...\n");
           
            System.out.println(" Se generan las series..." + "\n");
            
            System.out.println(" Lote AA : " + loteaa + "\n");
            System.out.println(" Lote AB : " + loteab + "\n");
            System.out.println(" Lote AC : " + loteac + "\n");
            System.out.println(" Lote AD : " + lotead + "\n");
            System.out.println(" Lote AE : " + loteae + "\n");


            /* se llama al método 'Cartones' que calcula las tuplas (combinaciones) a partir de los parámetros ingresados */
            
            cgenerados = Cartones(numerosporcarton,conjunto);
         
            /* se almacena en la variable 'tamanio' la cantidad de tuplas generadas */
                     
            tamanio = cgenerados.size();
         

            /* rutina para la impresión de cartones */

            System.out.println("\n Se generan combinaciones de "+conjunto+" elementos tomados de a "+numerosporcarton+" elementos.");
            System.out.println("\n La cantidad generada es de : "+tamanio+" cartones.\n");
            
           
            /* mientras haya elementos para imprimir , imprimir 
            
            while (x < tamanio)
              
              {
               
             /*   el condicional establece la cantidad de columnas de la impresión de salida 
               
               if (y < 4)
               
                  {
                   System.out.print("   ["+cgenerados.get(x)+"]");
                   y++;
                   x++;
                  }
                  
               else
               
                  {
                   System.out.println("   ["+cgenerados.get(x)+"]");
                   y = 0;
                   x++;
                  }        
               } 
            
            */

            System.out.println("\n");
            System.out.println(" Comienzo del proceso de extrapolación...\n");

            
            /* ********
            /* Aquí arranca el proceso de extrapolación 
            /* y guarda en el lote de cartones final (cartonessorteo) */           
            /* ********

            /* *********************************************************** */   
            /* En primer lugar se procesa las combinaciones para el loteaa */
            /* *********************************************************** */

            
            System.out.println(" En primer lugar se extrapola el lote : loteaa...\n");

            for (m=0; m<136; m++)
                {
                 StringTokenizer stk = new StringTokenizer(cgenerados.get(m)," ");
                
                 // System.out.println("\n Valores de loteaa carton serie nº : " + m ); 

                 for(n=0; n<16; n++)
                    {
                     if ( n==0)
                        {
                         cartonessorteo[m][n] = m;
                        }
                    else
                      {
                        puntero = Integer.valueOf((String) stk.nextElement()); 
                        
                        puntero = puntero - 1 ; 

                        // System.out.println(" Valores de puntero : " + puntero);

                        // System.out.println("Cantidad de Tokens : " + stk.countTokens());

                        // System.out.print(" " + loteaa.get(puntero));
                        
                        cartonessorteo[m][n] = loteaa.get(puntero);
                      } 
                    }

                   // System.out.println("\n");
                  } 


            System.out.println("\n");
            
            

            /* ************************************************************* */
            /* En segundo lugar se procesan las combinaciones para el loteab */
            /* ************************************************************* */
            

            System.out.println(" En segundo lugar se extrapola el lote : loteab...\n");

            for (m=0; m<136; m++)
                
               {
                 StringTokenizer stk = new StringTokenizer(cgenerados.get(m)," ");
                
                 mloteb = m + 136;

                 // System.out.println("\n Valores de loteab carton serie nº : " + mloteb ); 

                 for(n=0; n<16; n++)
                    {
                     if ( n==0)
                        {
                         cartonessorteo[mloteb][n] = mloteb;
                        }
                    else
                      {
                        puntero = Integer.valueOf((String) stk.nextElement()); 
                        
                        puntero = puntero - 1 ; 
                       
                        // System.out.print(" " + loteab.get(puntero));
                        
                        cartonessorteo[mloteb][n] = loteab.get(puntero);
                      } 
                    }

                   // System.out.println("\n");
                  
                } 

            
            System.out.println("\n");
            
            
            /* ************************************************************ */
            /* En tercer lugar se procesan las combinaciones para el loteac */
            /* ************************************************************ */
            
            System.out.println(" En tercer lugar se extrapola el lote : loteac...\n");

            for (m=0; m<136; m++)
                
               {
                 StringTokenizer stk = new StringTokenizer(cgenerados.get(m)," ");
                
                 mlotec = m + 272;

                 // System.out.println("\n Valores de loteac carton serie nº : " + mlotec ); 

                 for(n=0; n<16; n++)
                    {
                     if ( n==0)
                        {
                         cartonessorteo[mlotec][n] = mlotec;
                        }
                    else
                      {
                        puntero = Integer.valueOf((String) stk.nextElement()); 
                        
                        puntero = puntero - 1 ; 
                       
                        // System.out.print(" " + loteac.get(puntero));
                        
                        cartonessorteo[mlotec][n] = loteac.get(puntero);
                      } 
                    }

                   // System.out.println("\n");
                  
                } 

            System.out.println("\n");


 
            /* ************************************************************ */
            /* En cuarto lugar se procesan las combinaciones para el lotead */
            /* ************************************************************ */
            
            System.out.println(" En cuarto lugar se extrapola el lote : lotead...\n");

            for (m=0; m<136; m++)
                
               {
                 StringTokenizer stk = new StringTokenizer(cgenerados.get(m)," ");
                
                 mloted = m + 408;

                 // System.out.println("\n Valores de lotead carton serie nº : " + mloted ); 

                 for(n=0; n<16; n++)
                    {
                     if ( n==0)
                        {
                         cartonessorteo[mloted][n] = mloted;
                        }
                    else
                      {
                        puntero = Integer.valueOf((String) stk.nextElement()); 
                        
                        puntero = puntero - 1 ; 
                       
                        // System.out.print(" " + lotead.get(puntero));
                        
                        cartonessorteo[mloted][n] = lotead.get(puntero);
                      } 
                    }

                  //  System.out.println("\n");
                  
                } 

            System.out.println("\n");


 
            /* ************************************************************ */
            /* En quinto lugar se procesan las combinaciones para el loteae */
            /* ************************************************************ */
            
            System.out.println(" En quinto lugar se extrapola el lote : loteae...\n");

            for (m=0; m<136; m++)
                
               {
                 StringTokenizer stk = new StringTokenizer(cgenerados.get(m)," ");
                
                 mlotee = m + 544;

                 // System.out.println("\n Valores de loteae carton serie nº : " + mlotee ); 

                 for(n=0; n<16; n++)
                    {
                     if ( n==0)
                        {
                         cartonessorteo[mlotee][n] = mlotee;
                        }
                    else
                      {
                        puntero = Integer.valueOf((String) stk.nextElement()); 
                        
                        puntero = puntero - 1 ; 
                       
                        // System.out.print(" " + loteae.get(puntero));
                        
                        cartonessorteo[mlotee][n] = loteae.get(puntero);
                      } 
                    }

                   // System.out.println("\n");
                  
                } 

 
            System.out.println("\n");


            /* Impresión del  lote final de cartones para control (cartonessorteo) */           

            System.out.println(" Impresión del lote final de cartones para control...\n");
            
            try
            {
           
            /* se crea el archivo a través del constructor de la clase FileOutputStream */

            archivosalida = new FileOutputStream("/Users/admin/Downloads/cartones680.txt");

            /* se graban los datos de los cartones generados (cartonessorteo) */
          
                for (m=0; m<680; m++)
                    { 

                    for(n=0; n<16; n++)
                       {

                        if (n==0)
                           {
                           datossalida = datossalida + " Nº cartón : " + cartonessorteo[m][n] + "  - ";
                           } 
                       
                        else
                           {
                           datossalida = datossalida + "  " + cartonessorteo[m][n];
                           }

                        }                     
                        /* el método write de la clase FileOutputStream espera un array de bytes; 
                       por lo tanto se utiliza el método getBytes() de la clase String para 
                       poder obtenerlo */
                     
                     datossalida = datossalida + "\r";
                     archivosalida.write(datossalida.getBytes());
                     datossalida = "\r";
                     archivosalida.write(datossalida.getBytes());
                     datossalida =" ";
                    
                   }
            
                /* se cierra el archivo */

               archivosalida.close();
      
          }
            

          catch(IOException ex)

        {
         /* tanto el constructor como los métodos write y close, lanzan una excepción
            del tipo IOException */
         
         System.out.println("Error : " + ex.getMessage());
        
        }
     
         System.out.print("\n");
         System.out.print(" Comienzo de sorteo...\n");
         System.out.print("\n");
         System.out.print(" ¿Extraer bolilla ? (y/n) : ");
         
         Collections.shuffle(vectorbolillas);
         
          while (!nuevabolilla.equalsIgnoreCase("n"))
                 {
                 
                  bolilla = vectorbolillas.get(numbolilla);
                    
                  System.out.print(" La bolilla extraída es la número : " + bolilla + "   Cantidad bolillas extraídas : " + numbolilla + "\n");       
                      
                  numbolilla = numbolilla + 1;
                  
                  for (m=0; m<680; m++)
                      {              
                       
                       for(n=1; n<16; n++)
                       {

                        if (cartonessorteo[m][n] == bolilla)
                           {
                           cartonessorteo[m][16]++;
                           } 
                       
                        if (cartonessorteo[m][16] == 15)
                        
                          {
                            System.out.print(" ¡ Hay cartón ganador ! ");
                            System.out.print("\n");
                            System.out.print(" El número de serie del cartón ganador es : " + m + "\n");
                                
                            nuevabolilla = "n";                        
                          }
                                              
                         }
                    }                                 
                  
                  
                  System.out.print(" ¿Extraer bolilla ? (y/n) : ");
                  nuevabolilla = entrada.next();
                 }
             
     
            
            System.out.print(" ¿Desea generar nuevos cartones? (s/n) : ");
            continuar = entrada.next();
         
            System.out.println("\n");
        

            }
         
            entrada.close();            
        }
        
   
   /* método main de la clase GeneradorCartones */  
     
   public static void main(String[] args)
        { 
         new cartones680();
        }
 
       
        /* el método 'bitprint' es el encargado real de hacer los cálculos de las tuplas */
        
        public static String bitprint(int u)
           {
           
             String s= "";
             
             /* el operador '>>=' produce un traslado a la derecha con asignación,
                lo que en el método manual se denominó como "corredera" */
             
             /* el bucle genera tuplas que se van desplazando mientras queden elementos
                disponibles en el conjunto 'u' */
             
             for(int n= 1; u>0; ++n , u>>= 1)
                  
                  /* la comprobación de si n < 10 , no tiene incidencia en los cálculos,
                     se hace solamente a los efectos de tabular la salida en columnas,
                     insertando espacios en blanco para números de un solo caracter. */
                  
                  if (n < 10)
                     {
                      if((u & 1) > 0) s+= " " + n + " ";
                     }
                  else
                     {
                       if((u & 1) > 0) s+= n + " ";
                     }
                     
             return s;
             
           }
 
        
        public static int cuentaelementos(int u)
           {
           
            int n;
            
            /* Pone el último conjunto de bits en cero. */
            
            for(n= 0; u > 0; ++n, u&= (u - 1));
            return n;
            
           }
 
        
        /* Los parámetros del método generarCartones son : 
           -> numerosporcarton = cantidad de números del cartón (ej. 15);
           -> conjunto =  cantidad de números disponibles para armar un cartón (ej. 90) */
           
        public LinkedList<String> Cartones(int numerosporcartonlc, int conjuntolc)
           {
           
            LinkedList<String> s= new LinkedList<String>();
                
            for(int u= 0; u < 1 << conjuntolc ;u++)
                   
                if(cuentaelementos(u) == numerosporcartonlc) 
                
                  {
                   s.push(bitprint(u));
                  }                     
                   
            Collections.sort(s);
    
            return s;   
                         
           }
   }
