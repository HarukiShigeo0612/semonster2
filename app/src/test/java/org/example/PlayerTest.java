package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("太郎");
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("太郎", player.getName());
        assertEquals(0, player.getDeckSize());
    }

    @Test
    public void testAddSingleMonster() {
        Monster monster = new Monster("スライム", 1);
        player.addMonster(monster);

        assertEquals(1, player.getDeckSize());
        assertEquals(monster, player.getMonster(0));
    }

    @Test
    public void testAddEightMonsters() {
        for (int i = 0; i < 8; i++) {
            player.addMonster(new Monster("スライム", 1));
        }
        assertEquals(8, player.getDeckSize());
    }

    @Test
    public void testGetMonsterByIndex() {
        Monster m1 = new Monster("デュラハン", 0);
        Monster m2 = new Monster("ゴブリン", 2);

        player.addMonster(m1);
        player.addMonster(m2);

        assertEquals(m1, player.getMonster(0));
        assertEquals(m2, player.getMonster(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMonsterIndexOutOfBounds() {
        player.addMonster(new Monster("スライム", 1));
        player.getMonster(5);
    }

    @Test
    public void testToString() {
        player.addMonster(new Monster("デュラハン", 0));
        player.addMonster(new Monster("ゴブリン", 2));

        String result = player.toString();

        assertTrue(result.contains("Deck:太郎"));
        assertTrue(result.contains("デュラハン:レア度[0]"));
        assertTrue(result.contains("ゴブリン:レア度[2]"));
    }

    @Test
    public void testEvolvedMonsterInDeck() {
        // レア度3以上は進化して名前が変わる
        player.addMonster(new Monster("ドラゴン", 3));

        String result = player.toString();
        assertTrue(result.contains("進化ドラゴン:レア度[3]"));
    }

    @Test
    public void testToStringWithFullDeck() {
        for (int i = 0; i < 8; i++) {
            player.addMonster(new Monster("スライム", 1));
        }

        String result = player.toString();
        String[] lines = result.split("\n");

        assertEquals(8, player.getDeckSize());
        assertEquals(9, lines.length);  // "Deck:太郎" + 8体
    }

    @Test
    public void testMultiplePlayers() {
        Player player2 = new Player("花子");

        player.addMonster(new Monster("スライム", 1));
        player2.addMonster(new Monster("ゴブリン", 2));
        player2.addMonster(new Monster("デュラハン", 0));

        assertEquals(1, player.getDeckSize());
        assertEquals(2, player2.getDeckSize());
        assertEquals("太郎", player.getName());
        assertEquals("花子", player2.getName());
    }

    @Test
    public void testGetDeckReturnsCopy() {
        player.addMonster(new Monster("スライム", 1));
        player.getDeck().add(new Monster("ゴブリン", 2));

        assertEquals(1, player.getDeckSize());
    }
}