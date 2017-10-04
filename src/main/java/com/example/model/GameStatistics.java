package com.example.model;


import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class GameStatistics {
    // attributes
    public static int GLOBAL_WINS = 0;
    public static int LOCAL_WINS = 0;
    public static int TOTAL_PLAYS = 0;
    public static int LOCAL_PLAYS = 0;

    // getters and setters

    public static int getGlobalWins() {
        return GLOBAL_WINS;
    }

    public static void setGlobalWins(int globalWins) {
        GLOBAL_WINS = globalWins;
        try {
            updateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLocalWins() {
        return LOCAL_WINS;
    }

    public static void setLocalWins(int localWins) {
        LOCAL_WINS = localWins;
    }

    public static int getLocalPlays() {
        return LOCAL_PLAYS;
    }

    public static void setLocalPlays(int localPlays) {
        LOCAL_PLAYS = localPlays;
    }

    public static int getTotalPlays() {
        return TOTAL_PLAYS;
    }

    public static void setTotalPlays(int totalPlays) {
        TOTAL_PLAYS = totalPlays;
        try {
            updateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getGlobalAverageWins() throws java.lang.ArithmeticException{
        return (((double)GLOBAL_WINS/(double)TOTAL_PLAYS)*100);
    }

    public static double getLocalAverageWins(){
        return (((double)LOCAL_WINS/(double)LOCAL_PLAYS)*100);
    }

    public static void updateFile() throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/globalStats.json");
        JSONObject object = new JSONObject();
        object.put("globalWins", GLOBAL_WINS);
        object.put("globalGames", TOTAL_PLAYS);
        fw.write(object.toJSONString());
        fw.close();
    }
}
