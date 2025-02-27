    package com.example.project;
import java.util.ArrayList;

public class Utility{
    private static String[] hands = {
        "Royal Flush",
        "Straight Flush",
        "Four of a Kind",
        "Full House",
        "Flush",
        "Straight",
        "Three of a Kind",
        "Two Pair",
        "Pair",
        "High Card"
    };

    private static String[] suits  = {"♠","♥","♣", "♦"};
    private static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public static String[] getRanks(){return ranks;}
    public static String[] getSuits(){return suits;}

    public static int getRankValue(String rank){
        switch(rank){
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
        }
        return -1;
    }

    public static int getHandRanking(String result){
        switch(result){
            case "Royal Flush": return 11;
            case "Straight Flush": return 10;
            case "Four of a Kind": return 9;
            case "Full House": return 8;
            case "Flush": return 7;
            case "Straight": return 6;
            case "Three of a Kind": return 5;
            case "Two Pair": return 4;
            case "A Pair": return 3;
            case "High Card": return 2;
            case "Nothing": return 1;
        }
        return -1;
    }
}