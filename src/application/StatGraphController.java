package application;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatGraphController {

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    public void generateGraph() {
        List<Double> values = loadData("src/mots/stat.txt");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, values.get(i)));
        }

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private List<Double> loadData(String fileName) {
        List<Double> values = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.startsWith("null")) {
                    String[] parts = line.split(" ");
                    double value = Double.parseDouble(parts[1]);
                    values.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la lecture du fichier.");
        }

        return values;
    }
}
