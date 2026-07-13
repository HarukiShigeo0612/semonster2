package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Monster> deck;

    public Player(String name) {
        this.name = name;
        this.deck = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMonster(Monster monster) {
        deck.add(monster);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Monster getMonster(int index) {
        if (index < 0 || index >= deck.size()) {
            throw new IndexOutOfBoundsException("インデックスが範囲外です");
        }
        return deck.get(index);
    }

    public List<Monster> getDeck() {
        return new ArrayList<>(deck);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck:").append(name).append("\n");
        for (Monster monster : deck) {
            sb.append(monster.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}