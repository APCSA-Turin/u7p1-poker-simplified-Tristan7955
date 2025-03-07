package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // Method to determine the winner between two players based on their hand rankings
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        // Get the ranking of each player's hand using a utility method
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);

        // Compare the rankings to determine the winner
        if (p1Rank > p2Rank) {
            return "Player 1 wins!";
        } else if (p2Rank > p1Rank) {
            return "Player 2 wins!";
        } else {
            // If both players have the same hand ranking, resolve the tie
            return resolveTie(p1, p2, communityCards);
        }
    }

    // Method to resolve a tie between two players by comparing their highest cards
    private static String resolveTie(Player p1, Player p2, List<Card> communityCards) {
        // Combine each player's hand with the community cards
        List<Card> p1Cards = new ArrayList<>(p1.getHand());
        p1Cards.addAll(communityCards);
        List<Card> p2Cards = new ArrayList<>(p2.getHand());
        p2Cards.addAll(communityCards);

        // Compare the cards one by one to determine the winner
        for (int i = 0; i < p1Cards.size(); i++) {
            int p1CardRank = Utility.getRankValue(p1Cards.get(i).getRank()); // Get rank of Player 1's card
            int p2CardRank = Utility.getRankValue(p2Cards.get(i).getRank()); // Get rank of Player 2's card

            // Compare the ranks of the current card for both players
            if (p1CardRank > p2CardRank) {
                return "Player 1 wins!"; // Player 1 has a higher card
            } else if (p2CardRank > p1CardRank) {
                return "Player 2 wins!"; // Player 2 has a higher card
            }
        }

        // If all cards are equal, the game is a tie
        return "Tie!";
    }

    // Method to simulate a game between two players
    public static void play() {
        // Create two players
        Player player1 = new Player();
        Player player2 = new Player();

        // Add cards to Player 1's hand
        player1.addCard(new Card("A", "Hearts")); // Ace of Hearts
        player1.addCard(new Card("K", "Hearts")); // King of Hearts

        // Add cards to Player 2's hand
        player2.addCard(new Card("Q", "Diamonds"));
        player2.addCard(new Card("J", "Diamonds"));

        // Create the community cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("10", "Hearts"));
        communityCards.add(new Card("9", "Hearts"));
        communityCards.add(new Card("8", "Hearts"));
        communityCards.add(new Card("7", "Hearts"));
        communityCards.add(new Card("6", "Diamonds"));

        // Determine the best hand for each player using the community cards
        String p1Hand = player1.playHand(communityCards);
        String p2Hand = player2.playHand(communityCards);

        // Determine the winner of the game
        String result = determineWinner(player1, player2, p1Hand, p2Hand, communityCards);

        // Print the results of the game
        System.out.println("Player 1's Hand: " + player1.getHand() + " - " + p1Hand);
        System.out.println("Player 2's Hand: " + player2.getHand() + " - " + p2Hand);
        System.out.println("Community Cards: " + communityCards);
        System.out.println("Result: " + result);
    }
}