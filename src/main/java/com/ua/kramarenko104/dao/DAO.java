package com.ua.kramarenko104.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import org.apache.log4j.Logger;

public class DAO {

    Connection conn;
    Statement st;
    String tableName;
    String fieldName;
    private static Logger logger = Logger.getLogger(DAO.class);

    public DAO(String tableName, String fieldName) {
        this.tableName = tableName;
        this.fieldName = fieldName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/malarkey_game?" +  "user=root&password=");
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();}
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
    public void fillWithValues(String sourceFilePath){
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            String line = "";
            while((line = br.readLine()) != null){
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
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sqlCountRecords);
            while (rs.next()) {
                countRecord = rs.getInt("count");
                //logger.debug("There are " + countRecord + " words saved in database");
            }

            int randomRecordNumber = (int)(Math.random()* countRecord + 1);
            //logger.debug("random result number: " + randomRecordNumber);
            rs = st.executeQuery(sqlSelectWord + randomRecordNumber);
            while (rs.next()) {
                result = rs.getString(fieldName);
            }
            //logger.debug("random result: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addWord(String word) {
        String sqlAdd = "INSERT INTO " + tableName + " (" + fieldName + ") VALUES(?);";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement (sqlAdd );
            statement.setString(1, word);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("SQLException with addWord: " + word);
            e.printStackTrace();
        }
    }


}
