package com.example.project;

import java.util.ArrayList;
import java.util.Comparator;



public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){     
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        
        sortAllCards();

        return "High Card";
    }

    public void sortAllCards(){
        allCards.sort(Comparator.comparingInt(c -> Utility.getRankValue(c.getRank())));
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<>();
        for (String rank : Utility.getRanks()) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getRank().equals(rank)) count ++;
            }
            frequency.add(count);
        }

        return frequency;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequency = new ArrayList<>();
        for (String suit : Utility.getSuits()) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getSuit().equals(suit)) count ++;
            }
            frequency.add(count);
        }

        return frequency;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
