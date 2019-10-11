package listasdegrafos;

public class AlgoritmosEmGrafos extends Grafos {

    private final int[] distanciaProfundidade;
    private final int[] verticePredecessor;
    private final int[] distanciasCMC; // CMC-> Caminho Mais Curto
    private final int[] verticeAntecessorCMC;
    
    public AlgoritmosEmGrafos(int vertices) {
        super(vertices);
        distanciaProfundidade = new int[vertices];
        verticePredecessor = new int[vertices];
        distanciasCMC = new int[vertices];
        verticeAntecessorCMC = new int[vertices];
    }

    // faz a busca em profundidade
    private void buscaProfundidade(int vertice) {
        for (int i = 0; i < distanciaProfundidade.length; i++) {
            if (super.matrizAdjacencia[vertice][i] != 0 && distanciaProfundidade[i] == distanciaProfundidade.length) {
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
            distanciaProfundidade[i] = distanciaProfundidade.length;
        }

        distanciaProfundidade[vertice] = 0;
        
        buscaProfundidade(vertice);
    }
    
    // implementa o algoritmo de Dijkstra
    private void caminhoMaisCurto(int verticeInicial) {
        
        
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
            if (i != verticeInicial)
                distanciasCMC[i] = 0;
            else
                distanciasCMC[i] = Integer.MAX_VALUE;
        }
        
        caminhoMaisCurto(verticeInicial);
        
        return distanciasCMC;
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
}
