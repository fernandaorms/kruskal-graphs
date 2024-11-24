import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdgeLoader {
    public static List<Kruskal.Edge> loadEdgesFromCSV(String filePath) {
        List<Kruskal.Edge> edges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Ignorar cabeçalho, se existir
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int src = Integer.parseInt(parts[0].trim());
                int dest = Integer.parseInt(parts[1].trim());
                double weight = Double.parseDouble(parts[2].trim());
                edges.add(new Kruskal.Edge(src, dest, weight));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return edges;
    }
}

