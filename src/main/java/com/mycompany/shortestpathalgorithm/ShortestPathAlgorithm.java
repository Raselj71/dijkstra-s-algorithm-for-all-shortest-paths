

package com.mycompany.shortestpathalgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ShortestPathAlgorithm {
     static int V;
    
    
       void printSolution(int dest[] ,  Map<Integer,String> parentMap){
           System.out.println("Vertext \t \t Distance From source");
           for(int i=0; i<V; i++){
               System.out.println(i+ "\t \t"+ dest[i]);
           }
           
           for(int i=0; i<parentMap.size(); i++){
                System.out.println("Path " + i + ": " + parentMap.get(i));
           }
           
          
           
           
         
       }   
    
     int minDistance(int dest[], boolean sptSet[]){
         int min=Integer.MAX_VALUE , min_index=-1;
         for(int x=0; x<V; x++){
             if(sptSet[x]==false&& dest[x]<=min){
                 min=dest[x];
                 min_index=x;
             }
         }
         
         return min_index;
    }
    
    void dijkstra(int graph[][], int source){
        
      
       Map<Integer, String> path = new HashMap<>();
        
        int dest[]=new int[V];
        boolean sptset[]=new boolean[V];
        
        for(int i=0; i<V; i++){
            dest[i]=Integer.MAX_VALUE;
            sptset[i]=false;
             path.put(i, i+"");
             
        }
        
        dest[source]=0;
        
        
        for(int count=0; count<V-1; count++){
            int u= minDistance(dest, sptset);
            sptset[u]=true;
            
            for(int v=0; v<V; v++){
                
                if(!sptset[v] && graph[u][v] !=0 && dest[u] !=Integer.MAX_VALUE && dest[u]+ graph[u][v] <dest[v]){
                    dest[v]=dest[u]+graph[u][v];
                    
                      path.put(v, path.get(u) + v);
                  
                    
                }
                
            }
            
        }
        
        printSolution(dest, path);
        
    }
    

    public static void main(String[] args) {
        try {
            
            File file=new File("C:\\Users\\User\\Desktop\\rasel\\dijikstr.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] firstLine = reader.readLine().split(" ");
            int node = Integer.parseInt(firstLine[0]);
            int edge = Integer.parseInt(firstLine[1]);
             V=node;
            
             int[][] metrix= new int[node][node];
            
              for (int i = 0; i < edge; i++) {
                String[] line = reader.readLine().split(" ");
                int source = Integer.parseInt(line[0]);
                int destination = Integer.parseInt(line[1]);
                int weight=Integer.parseInt(line[2]);
                
                metrix[source][destination]=metrix[destination][source]=weight;
                 
               
            }
              
               ShortestPathAlgorithm sh=new ShortestPathAlgorithm();
               sh.dijkstra(metrix, 0);
              
              
             
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
