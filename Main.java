import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Criando uma instância da classe KruskalsMST
        Kruskal kruskal = new Kruskal();

        // Kruskal com as arestas padrão e visualização gráfica
        int V = 10;
    	List<Kruskal.Edge> graphEdges = loadDefaultEdges();

        // Criando o painel de gráficos
        GraphPanel graphPanel = new GraphPanel(V, graphEdges);
        JFrame frame = new JFrame("Visualização do Algoritmo de Kruskal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphPanel);
        frame.pack();
        frame.setVisible(true);

        kruskal.runKruskalAlgorithm(V, graphEdges, graphPanel);
        

        // // Kruskal com as arestas do arquivo CSV sem visualização gráfica
        // int V = 7605; // Número total de vértices do arquivo CSV
    	// String filePath = "C:\\Users\\orfer\\OneDrive\\Área de Trabalho\\Ifes\\kruskal-graphs\\BTCAlphaNet.csv";
        // List<Kruskal.Edge> graphEdges = EdgeLoader.loadEdgesFromCSV(filePath);

        // kruskal.runKruskalAlgorithmNoGraphs(V, graphEdges);
    }
    
    public static List<Kruskal.Edge> loadDefaultEdges() {
        List<Kruskal.Edge> graphEdges = new ArrayList<>(List.of(
            new Kruskal.Edge(0, 1, 1.0),
            new Kruskal.Edge(0, 2, 2.3),
            new Kruskal.Edge(0, 3, 1.5),
            new Kruskal.Edge(0, 4, 2.9),
            new Kruskal.Edge(0, 5, 3.8),
            new Kruskal.Edge(1, 2, 1.2),
            new Kruskal.Edge(1, 3, 2.4),
            new Kruskal.Edge(1, 6, 3.5),
            new Kruskal.Edge(1, 7, 4.1),
            new Kruskal.Edge(2, 3, 2.1),
            new Kruskal.Edge(2, 4, 1.9),
            new Kruskal.Edge(2, 8, 3.2),
            new Kruskal.Edge(2, 9, 2.6),
            new Kruskal.Edge(3, 4, 1.8),
            new Kruskal.Edge(3, 5, 2.2),
            new Kruskal.Edge(3, 6, 3.0),
            new Kruskal.Edge(4, 5, 1.1),
            new Kruskal.Edge(4, 7, 2.7),
            new Kruskal.Edge(4, 8, 3.4),
            new Kruskal.Edge(5, 6, 2.5),
            new Kruskal.Edge(5, 9, 3.3),
            new Kruskal.Edge(6, 7, 1.4),
            new Kruskal.Edge(6, 8, 2.8),
            new Kruskal.Edge(6, 9, 1.6),
            new Kruskal.Edge(7, 8, 3.1),
            new Kruskal.Edge(7, 9, 2.3),
            new Kruskal.Edge(8, 9, 1.7),
            new Kruskal.Edge(0, 6, 3.9),
            new Kruskal.Edge(1, 8, 2.0)
        ));

        return graphEdges;
    }

}
