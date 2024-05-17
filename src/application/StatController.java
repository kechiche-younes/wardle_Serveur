package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatController {

    @FXML
    public TableView<StatData> tableStat;

    @FXML
    public TableColumn<StatData, String> nom;

    @FXML
    public TableColumn<StatData, String> prenom;

    public void initialize() {
        // Associer les colonnes aux propriétés de la classe StatData
        nom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        prenom.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());

        // Appel de la méthode pour charger les données depuis le fichier
        loadAndDisplayData("src/mots/stat.txt");

        // Sort the data based on the 'nom' column
        sortData();
    }

    private static List<StatData> statDataList = new ArrayList<>();

    public static List<StatData> getStatDataList() {
        return statDataList;
    }
    
    private void loadAndDisplayData(String fileName) {
        List<StatData> statDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Diviser la ligne en mots en utilisant l'espace comme délimiteur
                String[] words = line.split("\\s+");

                // Ajouter les données à la liste
                statDataList.add(new StatData(words[0], words[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la lecture du fichier.");
        }

        // Ajouter la liste des données à la TableView
        tableStat.getItems().addAll(statDataList);
    }

    private void sortData() {
        // Sort the data based on the 'nom' column
        tableStat.getItems().sort(Comparator.comparing(StatData::getNom));
    }
}
