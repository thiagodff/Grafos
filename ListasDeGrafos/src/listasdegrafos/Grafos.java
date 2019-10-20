
package listasdegrafos;

import java.util.ArrayList;

public class Grafos {
    protected final int numeroVertices;
    protected final int[][] matrizAdjacencia;
    
    public Grafos(int vertices) {
        numeroVertices = vertices;
        matrizAdjacencia = new int[vertices][vertices];
        int i, j;
        
        for(i=0; i<numeroVertices; i++){
            for(j=0; j<numeroVertices; j++){
                matrizAdjacencia[i][j] = 0;
            }
        }
    }
    
    // adiciona uma aresta no grafo com um dado peso
    public void insereAresta(int vertice1, int vertice2, int peso) {
        matrizAdjacencia[vertice1][vertice2] = peso;
    }
    
    // verifica se existe uma aresta no grafo
    public boolean existeAresta(int vertice1, int vertice2) {
        return matrizAdjacencia[vertice1][vertice2] != 0;
    }
    
    // retorna a lista de vertices adjacentes a um determinado vertice v
    ArrayList<Integer> listaDeAdjacencia(int vertice1) {
        ArrayList <Integer> listaAdj = new ArrayList <>();
        
        for(int i=0; i<numeroVertices; i++) {
            if (matrizAdjacencia[vertice1][i] != 0)
                listaAdj.add(i);
        }
        
        return listaAdj;
    }

    // adiciona uma aresta não orientada na matriz de adjacência
    public void insereArestaNaoOrientada(int vertice1, int vertice2, int peso) {
        matrizAdjacencia[vertice1][vertice2] = peso;
        matrizAdjacencia[vertice2][vertice1] = peso;
    }
    // retorna o peso da aresta entre dois vertices contido na matriz de adjacencia
    public int getPeso(int vertice1, int vertice2) {
        return matrizAdjacencia[vertice1][vertice2];
    }
    
    // retorna a quantidade de vertices no grafo
    public int numVertices(){
        return numeroVertices;
    }
}
