package com.ua.kramarenko104;

import org.apache.log4j.Logger;

import java.sql.*;

public class WhatDoes {

    Connection conn = null;
    Statement st = null;
    private static Logger logger = Logger.getLogger(Who.class);

    public WhatDoes() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //logger.debug("Obtaining connection....");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/malarkey_game?" +  "user=root&password=");
            //logger.debug("Connected");
            st = conn.createStatement();
            initWithValues();
        } catch (SQLException e) {
            e.printStackTrace();}
    }

    public void init(){
        addAction("торгует апельсинами");
        addAction("покупает лотерейный билет");
        addAction("сажает картошку и помидоры");
        addAction("красит губы в алый цвет");
        addAction("стрижется налысо");
        addAction("отжимается 100 раз");
        addAction("поднимает штангу");
        addAction("грызет семечки");
        addAction("рожает пятого ребенка");
        addAction("сутками сидит в позе лотоса");
        addAction("ест ложками красную икру");
        addAction("медитирует");
        addAction("слоняется без дела");
        addAction("не спит по ночам");
        addAction("третирует соседей");
        addAction("заводит трех питбулей");
        addAction("красит волосы в синий цвет");
        addAction("сочиняет стихи");
        addAction("громко и фальшиво поет");
        addAction("ест торт перед сном");
        addAction("сдает бутылки");
    }

    private void initWithValues() throws SQLException {
        //CREATE DATABASE malarkey_game;
        //USE malarkey_game;
        String sqlCreate = "CREATE TABLE IF NOT EXISTS actions (id INT PRIMARY KEY AUTO_INCREMENT, action VARCHAR(30));";
        try {
            st.execute(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlTrancate = "TRUNCATE TABLE actions;";
        try {
            st.execute(sqlTrancate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAction(String verb) {
        String sqlAdd = "INSERT INTO Actions (action) VALUES(?);";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement (sqlAdd );
            statement.setString(1, verb);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAction() {
        String sqlCountRecords = "SELECT COUNT(*) AS count FROM Actions;";
        String sqlSelectAction = "SELECT action FROM Actions WHERE id = ";
        String action = "";
        int countRecord = 0;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sqlCountRecords);
            while (rs.next()) {
                countRecord = rs.getInt("count");
                //logger.debug("There are " + countRecord + " actions saved in database");
            }

            int randomRecordNumber = (int)(Math.random()* countRecord + 1);
            //logger.debug("random action number: " + randomRecordNumber);
            rs = st.executeQuery(sqlSelectAction + randomRecordNumber);
            while (rs.next()) {
                action = rs.getString("action");
            }
            //logger.debug("random action: " + action);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return action;
    }

    public void close() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
