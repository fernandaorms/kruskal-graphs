import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private List<Kruskal.Edge> edges;
    private List<Kruskal.Edge> mstEdges;
    private int V;

    public GraphPanel(int V, List<Kruskal.Edge> edges) {
        this.V = V;
        this.edges = edges;
        this.mstEdges = new ArrayList<>();
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }
    
    public void drawGraph(Graphics g) {
        int radius = 20;

        // Definir um raio para a distribuição circular
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int graphRadius = Math.min(centerX, centerY) - 50; // Raio para distribuir os vértices de forma circular

        // Calcular as coordenadas de cada vértice em uma distribuição circular
        int[] xCoords = new int[V];
        int[] yCoords = new int[V];
        for (int i = 0; i < V; i++) {
            double angle = 2 * Math.PI * i / V;  // Distribuição circular igual
            xCoords[i] = (int) (centerX + graphRadius * Math.cos(angle));
            yCoords[i] = (int) (centerY + graphRadius * Math.sin(angle));
        }

        // Desenhar as arestas
        for (Kruskal.Edge edge : edges) {
            int x1 = xCoords[edge.src];
            int y1 = yCoords[edge.src];
            int x2 = xCoords[edge.dest];
            int y2 = yCoords[edge.dest];

            // Verificar se a aresta faz parte da MST
            if (mstEdges.contains(edge)) {
                g.setColor(Color.RED); // Arestas da MST em vermelho
            } else {
                g.setColor(Color.BLACK); // Outras arestas em preto
            }

            g.drawLine(x1, y1, x2, y2); // Corrigido: removido o deslocamento do raio
        }

        // Desenhar os vértices
        for (int i = 0; i < V; i++) {
            g.setColor(Color.BLUE);
            g.fillOval(xCoords[i] - radius, yCoords[i] - radius, radius * 2, radius * 2);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(i), xCoords[i] - 5, yCoords[i] + 5); // Ajustar posição do texto
        }
    }


    // Método para adicionar arestas à MST visualmente
    public void addEdgeToMST(Kruskal.Edge edge) {
        mstEdges.add(edge);
        repaint();
    }
}
