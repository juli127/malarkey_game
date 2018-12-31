package com.ua.kramarenko104.dao;

import java.io.*;
import java.sql.*;

import org.apache.log4j.Logger;

public class DAO implements Closeable {

    private static Logger logger = Logger.getLogger(DAO.class);
    private static String mySQLdriver = "com.mysql.cj.jdbc.Driver";
    private static String myDatabaseAccess = "jdbc:mysql://localhost/malarkey_game?" + "user=root&password=";

    private Connection conn;
    private Statement st;
    private String tableName;
    private String fieldName;

    public DAO(String tableName, String fieldName) {
        this.tableName = tableName;
        this.fieldName = fieldName;

        try {
            Class.forName(mySQLdriver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(myDatabaseAccess);
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // prepare database table for filling out with words
    public void init() {
        //CREATE DATABASE malarkey_game;
        //USE malarkey_game;
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id INT PRIMARY KEY AUTO_INCREMENT, " + fieldName + " VARCHAR(50) NOT NULL UNIQUE);";
        try {
            st.execute(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlTrancate = "TRUNCATE TABLE " + tableName + ";";
        try {
            st.execute(sqlTrancate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // read words from the source file
    // and fill the corresponding database table with them
    public void fillWithValues(String sourceFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                addWord(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomWord() {
        String sqlCountRecords = "SELECT COUNT(*) AS count FROM " + tableName + ";";
        String sqlSelectWord = "SELECT " + fieldName + " FROM " + tableName + " WHERE id = ";
        String result = "";
        int countRecord = 0;

        // find out count of records in table
        try (ResultSet rs = st.executeQuery(sqlCountRecords)) {
            while (rs.next()) {
                countRecord = rs.getInt("count");
                //logger.debug("There are " + countRecord + " words saved in database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int randomRecordNumber = (int) (Math.random() * countRecord + 1);
        //logger.debug("random result number: " + randomRecordNumber);

        // get value from table by random position
        try (ResultSet rs =st.executeQuery(sqlSelectWord + randomRecordNumber)){
            while (rs.next()) {
                result = rs.getString(fieldName);
            }
            //logger.debug("random result: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addWord(String word) {
        String sqlAdd = "INSERT INTO " + tableName + " (" + fieldName + ") VALUES(?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlAdd)) {
            ps.setString(1, word);
            ps.executeUpdate();
        } catch (SQLException e) {
            //logger.debug("SQLException with addWord: " + word);
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
