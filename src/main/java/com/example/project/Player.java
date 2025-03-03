package com.example.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player {
    private List<Card> hand; // Represents the player's hand of cards
    private List<Card> allCards; // Represents the player's hand plus the community cards

    // Constructor to initialize the player's hand and allCards list
    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    // Getter for the player's hand
    public List<Card> getHand() {
        return hand;
    }

    // Getter for allCards (community cards + hand)
    public List<Card> getAllCards() {
        return allCards;
    }

    // Method to add a card to the player's hand and allCards list
    public void addCard(Card card) {
        hand.add(card);
        allCards.add(card);
    }

    // Method to determine the best hand the player can make with the community cards
    public String playHand(List<Card> communityCards) {
        allCards = new ArrayList<>(hand); // Reset allCards to the player's hand
        allCards.addAll(communityCards); // Add community cards to allCards
        sortAllCards(); // Sort allCards by rank

        List<Integer> rankFrequency = findRankFrequency(); // Get frequency of each rank
        List<Integer> suitFrequency = findSuitFrequency(); // Get frequency of each suit

        // Check for the best possible hand in descending order of strength
        if (isStraightFlush(rankFrequency, suitFrequency)) {
            // The ? : operator is a ternary operator. It works like a compact if-else statement.
            // If the condition (allCards.get(allCards.size() - 1).getRank().equals("A")) is true,
            // it returns "Royal Flush"; otherwise, it returns "Straight Flush".
            return allCards.get(allCards.size() - 1).getRank().equals("A") ? "Royal Flush" : "Straight Flush";
        }

        if (hasFourOfAKind(rankFrequency)) {
            return "Four of a Kind";
        }

        if (hasFullHouse(rankFrequency)) {
            return "Full House";
        }

        if (hasFlush(suitFrequency)) {
            return "Flush";
        }

        if (isStraight()) {
            return "Straight";
        }

        if (hasThreeOfAKind(rankFrequency)) {
            return "Three of a Kind";
        }

        int pairCount = countPairs(rankFrequency); // Count the number of pairs
        if (pairCount >= 2) {
            return "Two Pair";
        }

        if (hasPair(rankFrequency)) {
            return "A Pair";
        }

        // If no other hand is found, check if the player has a high card or nothing
        return highCardOrNothing() ? "High Card" : "Nothing";
    }

    // Method to check if the hand is a straight flush
    private boolean isStraightFlush(List<Integer> rankFrequency, List<Integer> suitFrequency) {
        return isStraight() && hasFlush(suitFrequency); // Must be both a straight and a flush
    }

    // Method to check if the hand is a straight
    private boolean isStraight() {
        for (int i = 0; i <= allCards.size() - 5; i++) {
            int currentRank = Utility.getRankValue(allCards.get(i).getRank());
            boolean isStraight = true;
            for (int j = 1; j < 5; j++) {
                int nextRank = Utility.getRankValue(allCards.get(i + j).getRank());
                if (nextRank != currentRank + j) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand has a high card or nothing
    private boolean highCardOrNothing() {
        Card highCard = allCards.get(allCards.size() - 1); // Get the highest card
        for (Card card : hand) {
            if (card.getRank().equals(highCard.getRank())) {
                return true; // Player has the high card
            }
        }
        return false; // Player has nothing
    }

    // Method to sort all cards by rank
    public void sortAllCards() {
        allCards.sort(Comparator.comparingInt(card -> Utility.getRankValue(card.getRank())));
    }

    // Method to find the frequency of each rank in the allCards list
    private List<Integer> findRankFrequency() {
        List<Integer> frequency = new ArrayList<>();
        for (String rank : Utility.getRanks()) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getRank().equals(rank)) {
                    count++;
                }
            }
            frequency.add(count);
        }
        return frequency;
    }

    // Method to find the frequency of each suit in the allCards list
    private List<Integer> findSuitFrequency() {
        List<Integer> frequency = new ArrayList<>();
        for (String suit : Utility.getSuits()) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getSuit().equals(suit)) {
                    count++;
                }
            }
            frequency.add(count);
        }
        return frequency;
    }

    // Method to count the number of pairs in the rank frequency list
    private int countPairs(List<Integer> rankFrequency) {
        int pairCount = 0;
        for (int count : rankFrequency) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount;
    }

    // Method to check if the hand has four of a kind
    private boolean hasFourOfAKind(List<Integer> rankFrequency) {
        for (int count : rankFrequency) {
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand has a full house
    private boolean hasFullHouse(List<Integer> rankFrequency) {
        boolean hasThree = false;
        boolean hasTwo = false;
        for (int count : rankFrequency) {
            if (count == 3) {
                hasThree = true;
            } else if (count == 2) {
                hasTwo = true;
            }
        }
        return hasThree && hasTwo;
    }

    // Method to check if the hand has a flush
    private boolean hasFlush(List<Integer> suitFrequency) {
        for (int count : suitFrequency) {
            if (count >= 5) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand has three of a kind
    private boolean hasThreeOfAKind(List<Integer> rankFrequency) {
        for (int count : rankFrequency) {
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand has a pair
    private boolean hasPair(List<Integer> rankFrequency) {
        for (int count : rankFrequency) {
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    // Override toString method to return the player's hand as a string
    @Override
    public String toString() {
        return hand.toString();
    }
}