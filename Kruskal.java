import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    // Estrutura para representar uma aresta
    public static class Edge {
        int src, dest;
        double weight;

        public Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Estrutura para representar subconjuntos para o algoritmo Union-Find
    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    // Método para encontrar a raiz de um elemento (com compressão de caminho)
    private int findRoot(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = findRoot(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // Método para unir dois subconjuntos
    private void union(Subset[] subsets, int x, int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else if (subsets[rootX].rank > subsets[rootY].rank) {
            subsets[rootY].parent = rootX;
        } else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Método para executar o algoritmo de Kruskal
    public void runKruskalAlgorithm(int V, List<Edge> edges, GraphPanel graphPanel) {
        List<Edge> result = new ArrayList<>();
        Subset[] subsets = new Subset[V];

        // Inicializar os subconjuntos
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Ordenar as arestas com base no peso
        edges.sort((edge1, edge2) -> Double.compare(edge1.weight, edge2.weight));

        // Processar as arestas em ordem crescente
        for (Edge edge : edges) {
            int x = findRoot(subsets, edge.src);
            int y = findRoot(subsets, edge.dest);

            if (x != y) { // Adicionar aresta se não formar ciclo
                result.add(edge);
                union(subsets, x, y);
                graphPanel.addEdgeToMST(edge); // Atualizar a visualização
                try {
                    Thread.sleep(500); // Pausar para visualizar cada passo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Gravar o resultado no arquivo
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Arestas na MST:\n");
            double totalWeight = 0;
            for (Edge edge : result) {
                writer.write(edge.src + " -- " + edge.dest + " == " + edge.weight + "\n");
                totalWeight += edge.weight;
            }
            writer.write(String.format("Peso total da MST: %.2f%n", totalWeight));
            System.out.println("Resultado salvo em 'output.txt'");
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

    
    // Método para executar o algoritmo de Kruskal sem visualização gráfica
    public void runKruskalAlgorithmNoGraphs(int V, List<Edge> edges) {
        List<Edge> result = new ArrayList<>(); // Lista para armazenar as arestas da MST
        Subset[] subsets = new Subset[V];

        // Inicializar os subconjuntos
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Ordenar as arestas com base no peso
        edges.sort((edge1, edge2) -> Double.compare(edge1.weight, edge2.weight));

        // Processar as arestas em ordem crescente
        for (Edge edge : edges) {
            int x = findRoot(subsets, edge.src);
            int y = findRoot(subsets, edge.dest);

            if (x != y) { // Adicionar aresta se não formar ciclo
                result.add(edge);
                union(subsets, x, y);
            }
        }

        // Gravar o resultado no arquivo
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Arestas na MST:\n");
            double totalWeight = 0;
            for (Edge edge : result) {
                writer.write(edge.src + " -- " + edge.dest + " == " + edge.weight + "\n");
                totalWeight += edge.weight;
            }
            writer.write(String.format("Peso total da MST: %.2f%n", totalWeight));
            System.out.println("Resultado salvo em 'output.txt'");
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

}
