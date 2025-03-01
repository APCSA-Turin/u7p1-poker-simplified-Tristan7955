package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);

        if (p1Rank > p2Rank) {
            return "Player 1 wins!";
        } else if (p2Rank > p1Rank) {
            return "Player 2 wins!";
        } else {
            return resolveTie(p1, p2, communityCards);
        }
    }

    private static String resolveTie(Player p1, Player p2, List<Card> communityCards) {
        List<Card> p1Cards = new ArrayList<>(p1.getHand());
        p1Cards.addAll(communityCards);
        List<Card> p2Cards = new ArrayList<>(p2.getHand());
        p2Cards.addAll(communityCards);

        p1Cards.sort((c1, c2) -> Utility.getRankValue(c2.getRank()) - Utility.getRankValue(c1.getRank()));
        p2Cards.sort((c1, c2) -> Utility.getRankValue(c2.getRank()) - Utility.getRankValue(c1.getRank()));

        for (int i = 0; i < p1Cards.size(); i++) {
            int p1CardRank = Utility.getRankValue(p1Cards.get(i).getRank());
            int p2CardRank = Utility.getRankValue(p2Cards.get(i).getRank());
            if (p1CardRank > p2CardRank) {
                return "Player 1 wins!";
            } else if (p2CardRank > p1CardRank) {
                return "Player 2 wins!";
            }
        }

        return "Tie!";
    }

    public static void play() {
        Player player1 = new Player();
        Player player2 = new Player();
        
        player1.addCard(new Card("A", "Hearts"));
        player1.addCard(new Card("K", "Hearts"));
        player2.addCard(new Card("Q", "Diamonds"));
        player2.addCard(new Card("J", "Diamonds"));
        
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("10", "Hearts"));
        communityCards.add(new Card("9", "Hearts"));
        communityCards.add(new Card("8", "Hearts"));
        communityCards.add(new Card("7", "Hearts"));
        communityCards.add(new Card("6", "Diamonds"));
        
        String p1Hand = player1.playHand(communityCards);
        String p2Hand = player2.playHand(communityCards);
        
        String result = determineWinner(player1, player2, p1Hand, p2Hand, communityCards);
        
        System.out.println("Player 1's Hand: " + player1.getHand() + " - " + p1Hand);
        System.out.println("Player 2's Hand: " + player2.getHand() + " - " + p2Hand);
        System.out.println("Community Cards: " + communityCards);
        System.out.println("Result: " + result);
    }
}