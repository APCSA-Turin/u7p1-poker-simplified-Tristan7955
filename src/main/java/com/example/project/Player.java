package com.example.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player {
    private List<Card> hand;
    private List<Card> allCards; // Current community cards + hand

    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public void addCard(Card card) {
        hand.add(card);
        allCards.add(card);
    }

    public String playHand(List<Card> communityCards) {
        allCards = new ArrayList<>(hand);
        allCards.addAll(communityCards);
        sortAllCards();

        List<Integer> rankFrequency = findRankFrequency();
        List<Integer> suitFrequency = findSuitFrequency();

        if (isStraightFlush(rankFrequency, suitFrequency)) {
            return allCards.get(allCards.size() - 1).getRank().equals("A") ? "Royal Flush" : "Straight Flush";
        }

        if (rankFrequency.contains(4)) {
            return "Four of a Kind";
        }

        if (rankFrequency.contains(3) && rankFrequency.contains(2)) {
            return "Full House";
        }

        if (suitFrequency.contains(5)) {
            return "Flush";
        }

        if (isStraight()) {
            return "Straight";
        }

        if (rankFrequency.contains(3)) {
            return "Three of a Kind";
        }

        int pairCount = (int) rankFrequency.stream().filter(count -> count == 2).count();
        if (pairCount >= 2) {
            return "Two Pair";
        }

        if (rankFrequency.contains(2)) {
            return "A Pair";
        }

        return highCardOrNothing() ? "High Card" : "Nothing";
    }

    private boolean isStraightFlush(List<Integer> rankFrequency, List<Integer> suitFrequency) {
        return isStraight() && suitFrequency.contains(5);
    }

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

    private boolean highCardOrNothing() {
        Card highCard = allCards.get(allCards.size() - 1);
        return hand.stream().anyMatch(card -> card.getRank().equals(highCard.getRank()));
    }

    public void sortAllCards() {
        allCards.sort(Comparator.comparingInt(card -> Utility.getRankValue(card.getRank())));
    }

    private List<Integer> findRankFrequency() {
        List<Integer> frequency = new ArrayList<>();
        for (String rank : Utility.getRanks()) {
            int count = (int) allCards.stream().filter(card -> card.getRank().equals(rank)).count();
            frequency.add(count);
        }
        return frequency;
    }

    private List<Integer> findSuitFrequency() {
        List<Integer> frequency = new ArrayList<>();
        for (String suit : Utility.getSuits()) {
            int count = (int) allCards.stream().filter(card -> card.getSuit().equals(suit)).count();
            frequency.add(count);
        }
        return frequency;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}