package listasdegrafos;

import java.util.ArrayList;
import javafx.util.Pair;

public class AlgoritmosEmGrafos extends Grafos {

    private final int[] distanciaProfundidade; // guarda a distancia busca em profundidade
    private final int[] verticePredecessor;
    private final int[] distanciasCMC; // CMC-> Caminho Mais Curto
    private final int[] verticeAntecessorCMC;
    private final int[] verticeConhecido;
    private final ArrayList < Pair < Integer, Integer >> arestasArvoreGeradoraMinima; 
    private final int[] verticeAntecessorAGM;
    private final int[] distanciasAGM;
    
    public AlgoritmosEmGrafos(int vertices) {
        super(vertices);
        distanciaProfundidade = new int[vertices];
        verticePredecessor = new int[vertices];
        
        distanciasCMC = new int[vertices];
        verticeAntecessorCMC = new int[vertices];
        
        verticeConhecido = new int[vertices];
        
        verticeAntecessorAGM = new int[vertices];
        arestasArvoreGeradoraMinima = new ArrayList <>();
        distanciasAGM = new int[vertices];
    }

    // faz a busca em profundidade
    private void buscaProfundidade(int vertice) {
        for (int i = 0; i < distanciaProfundidade.length; i++) {
            if (super.matrizAdjacencia[vertice][i] != 0 && distanciaProfundidade[i] == Integer.MAX_VALUE) {
                verticePredecessor[i] = vertice;
                distanciaProfundidade[i] = distanciaProfundidade[vertice] + super.getPeso(vertice, i);
                buscaProfundidade(i);
            }
        }

    }

    // inicia a busca em profundidade
    public void iniciaBuscaEmProfundidade(int vertice) {
        for (int i = 0; i < verticePredecessor.length; i++) {
            verticePredecessor[i] = -1;
        }

        verticePredecessor[vertice] = vertice;

        for (int i = 0; i < distanciaProfundidade.length; i++) {
            distanciaProfundidade[i] = Integer.MAX_VALUE;
        }

        distanciaProfundidade[vertice] = 0;
        
        buscaProfundidade(vertice);
    }
    
    // implementa o algoritmo de Dijkstra
    private void caminhoMaisCurto(int verticeInicial) {
        int proxVertice = Integer.MAX_VALUE;
        verticeConhecido[verticeInicial] = 1;
        
        for (int i = 0; i < distanciasCMC.length; i++) {
            if (super.matrizAdjacencia[verticeInicial][i] != 0 && i != verticeInicial){
                if (distanciasCMC[i] > (distanciasCMC[verticeInicial] + super.getPeso(verticeInicial, i))) {
                    distanciasCMC[i] = distanciasCMC[verticeInicial] + super.getPeso(verticeInicial, i);
                    verticeAntecessorCMC[i] = verticeInicial;
                }
            }
            if (proxVertice > distanciasCMC[i] && i != verticeInicial && verticeConhecido[i]==0) {
                proxVertice = i;
            }
        }
        
        if (proxVertice != Integer.MAX_VALUE) {
            caminhoMaisCurto(proxVertice);
        }
    }

    // calcula o caminho minimo entre dois vertices do grafo
    public int iniciaCaminhoMaisCurto(int verticeInicial, int verticeFinal) {
        iniciaCaminhoMaisCurto(verticeInicial);
        return distanciasCMC[verticeFinal];
    }

   // calcula o caminho mais curto entre o vertice inicial e todos os outros
    public int[] iniciaCaminhoMaisCurto(int verticeInicial) {
        for(int i=0; i<distanciasCMC.length; i++) {
            verticeAntecessorCMC[i] = -1;
            distanciasCMC[i] = Integer.MAX_VALUE;
            verticeConhecido[i] = 0;
        }
        
        distanciasCMC[verticeInicial] = 0;
        caminhoMaisCurto(verticeInicial);
        
        return distanciasCMC;
    }
    
    // Implementação da arvore geradora - Algoritmo de Prim
    private int arvoreGeradoraMinima(int vertice) {
        int peso = 0;
        int proxVertice = vertice;
        verticeConhecido[vertice] = 1;
        
        for(int i=0; i<verticeAntecessorAGM.length; i++) {
            if (super.matrizAdjacencia[vertice][i] != 0 && verticeConhecido[i] != 1) {
                verticeAntecessorAGM[i] = vertice;
                distanciasAGM[i] = super.matrizAdjacencia[vertice][i];                
                if (proxVertice == vertice) {
                    proxVertice = i;
                    peso = distanciasAGM[i];
                }                
                if (distanciasAGM[proxVertice] > distanciasAGM[i]) {
                    proxVertice = i;
                    peso = distanciasAGM[i];
                }
            }
        }
        if (proxVertice != vertice) {
            //arestasArvoreGeradoraMinima.add(new Pair(vertice, proxVertice));
            peso += arvoreGeradoraMinima(proxVertice);
        } else {
            for (int i=0; i<verticeConhecido.length; i++){
                if (verticeConhecido[i] == 0 && distanciasAGM[i] != Integer.MAX_VALUE) {
                    if (proxVertice == vertice) {
                        proxVertice = i;
                        peso = distanciasAGM[i];
                    } else {
                        if(distanciasAGM[proxVertice] > distanciasAGM[i]) {
                            proxVertice = i;
                            peso = distanciasAGM[i];
                        }
                    }                        
                }
            }
        }
        
        if (proxVertice != vertice) {
            arestasArvoreGeradoraMinima.add(new Pair(verticeAntecessorAGM[proxVertice], proxVertice));
            peso += arvoreGeradoraMinima(proxVertice);
        }
        
        return peso;
    }
    
    // Inicia o metodo que ira encontrar a arvore geradora minima
    public int iniciaArvoreGeradoraMinima(int vertice) {
        for(int i=0; i<distanciasAGM.length; i++) {
            verticeAntecessorAGM[i] = -1;
            verticeConhecido[i] = 0;
            distanciasAGM[i] = Integer.MAX_VALUE;
        }
        distanciasAGM[vertice] = 0;
        
        return arvoreGeradoraMinima(vertice);
    }
       
    public int[] getVerticeAntecessorCMC() {
        return verticeAntecessorCMC;
    }
    
    public int[] getDistanciaProfundidade() {
        return distanciaProfundidade;
    }

    public int[] getVerticePai() {
        return verticePredecessor;
    }
    
    public ArrayList<Pair<Integer, Integer>> getArestasAGM() {
        return arestasArvoreGeradoraMinima;
    }

    public int[] getVerticeAntecessorAGM() {
        return verticeAntecessorAGM;
    }
}