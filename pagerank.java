import java.util.Arrays;

public class PageRank {
    static final double DAMPING_FACTOR = 0.85;
    static final double THRESHOLD = 0.0001;

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 0},
            {0, 0, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 0}
        };
        int numPages = graph.length;
        double[] rank = new double[numPages];
        Arrays.fill(rank, 1.0 / numPages);

        boolean converged;
        do {
            double[] newRank = new double[numPages];
            converged = true;
            for (int i = 0; i < numPages; i++) {
                double sum = 0;
                for (int j = 0; j < numPages; j++) {
                    if (graph[j][i] == 1) {
                        int outLinks = Arrays.stream(graph[j]).sum();
                        sum += rank[j] / outLinks;
                    }
                }
                newRank[i] = (1 - DAMPING_FACTOR) / numPages + DAMPING_FACTOR * sum;
                if (Math.abs(newRank[i] - rank[i]) > THRESHOLD) {
                    converged = false;
                }
            }
            rank = newRank;
        } while (!converged);

        System.out.println("Final PageRanks:");
        for (int i = 0; i < numPages; i++) {
            System.out.println("Page " + i + ": " + rank[i]);
        }
    }
}
