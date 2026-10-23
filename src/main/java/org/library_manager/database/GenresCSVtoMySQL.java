package org.library_manager.database;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

public class GenresCSVtoMySQL {
    public static void main(String[] args) {
        String csvFile = "dataSet/Genre_df.csv";
        String table = "Genre_df";

        try (Connection connection = new MySQLConnection().manageConnection()) {
            System.out.println("✅ Connected to database!");

            Statement vStatement = connection.createStatement();

            // 1. Reset Table
            vStatement.execute("SET FOREIGN_KEY_CHECKS=0");
            vStatement.executeUpdate("DROP TABLE IF EXISTS " + table);

            String createTable = "CREATE TABLE " + table + " (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "Title VARCHAR(255) NOT NULL UNIQUE," +
                    "Number_of_Sub_Genre TEXT," +
                    "URLs TEXT" +
                    ")";
            vStatement.executeUpdate(createTable);
            vStatement.execute("SET FOREIGN_KEY_CHECKS=1");
            System.out.println("✅ Table created!");

            String sql = "INSERT INTO " + table + " (Title, Number_of_Sub_Genre, URLs) VALUES (?, ?, ?)";

            // 2. UTILISATION DU PARSEUR RFC4180 (Le Standard Excel)
            // Ce parseur gère parfaitement les "Title, With, Commas"
            RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();

            try (PreparedStatement ps = connection.prepareStatement(sql);
                 CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                         .withCSVParser(rfc4180Parser) // On utilise le parser robuste
                         .withSkipLines(1)
                         .build()) {

                String[] row;
                int counter = 0;
                int errorCounter = 0;

                while ((row = csvReader.readNext()) != null) {
                    // DEBUG SPECIAL : Si le parseur échoue encore, on tente un découpage manuel
                    // (C'est une sécurité, normalement le RFC4180 suffit)
                    if (row.length == 1 && row[0].contains(",")) {
                        // Fallback très basique si jamais le fichier est corrompu
                        // row = row[0].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                        // Mais voyons d'abord si le RFC4180 fonctionne seul.
                    }

                    if (row.length < 4) {
                        System.err.println("❌ Ligne ignorée (Taille " + row.length + "): " + Arrays.toString(row));
                        errorCounter++;
                        continue;
                    }

                    try {
                        // Nettoyage des données (trim) et insertion
                        ps.setString(1, row[1].trim());
                        ps.setString(2, row[2].trim());
                        ps.setString(3, row[3].trim());

                        ps.executeUpdate();
                        counter++;
                    } catch (Exception ex) {
                        System.err.println("⚠️ Erreur SQL sur '" + row[1] + "': " + ex.getMessage());
                        errorCounter++;
                    }
                }

                System.out.println("------------------------------------------------");
                System.out.println("✅ TERMINÉ : " + counter + " lignes insérées.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}