package org.library_manager.database;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BooksCSVtoMySQL {
    public static void main(String[] args) {
        String csvFile = "dataSet/Books_final_df.csv";

        String table = "books_df"; // table name

        try (Connection connection = new MySQLConnection().manageConnection()) {
            System.out.println("✅ Connected to database!");

            Statement vStatement = connection.createStatement();
            vStatement.execute("SET FOREIGN_KEY_CHECKS=0");
            // Drop old table if exists
            vStatement.executeUpdate("DROP TABLE IF EXISTS " + table);

            // Create table (no text block)
            String createTable =
                    "CREATE TABLE " + table + " (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "Title TEXT," +
                            "Author TEXT," +
                            "Main_Genre VARCHAR(255)," +
                            "Sub_Genre VARCHAR(255)," +
                            "Type TEXT," +
                            "Price TEXT," +
                            "Rating INT," +
                            "No_of_People_rated TEXT," +
                            "URLs TEXT," +
                            "FOREIGN KEY (Main_Genre) REFERENCES Genre_df(Title) ON DELETE CASCADE ON UPDATE CASCADE," +
                            "INDEX (Sub_Genre)" +
                            ")";
            vStatement.executeUpdate(createTable);
            vStatement.execute("SET FOREIGN_KEY_CHECKS=1");
            System.out.println("✅ Table created successfully!");

            // Insert SQL
            String sql = "INSERT INTO " + table +
                    " (Title, Author, Main_genre, Sub_Genre, Type, Price, Rating, No_of_People_rated, URLs )" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql);
                 CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {

                String[] row;
                boolean first = true;
                int counter = 0;

                while ((row = csvReader.readNext()) != null) {
                    if (first) { first = false; continue; } // skip header
                    if (row.length < 10) continue; // skip incomplete lines

                    ps.setString(1, row[1]);
                    ps.setString(2, row[2]);
                    ps.setString(3, row[3]);
                    ps.setString(4, row[4]);
                    ps.setString(5, row[5]);
                    ps.setString(6, row[6]);
                    ps.setString(7, row[7]);
                    ps.setString(8, row[8]);
                    ps.setString(9, row[9]);

                    ps.executeUpdate();
                    counter++;
                }

                System.out.println("✅ " + counter + " rows inserted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

