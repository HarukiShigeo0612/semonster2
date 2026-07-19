package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BattleTest {

    private Battle battle;
    private Player p1;
    private Player p2;

    @Before
    public void setUp() {
        battle = new Battle();
        p1 = new Player("太郎");
        p2 = new Player("花子");
    }

    @Test
    public void testJudgeFirstWins() {
        Monster a = new Monster("ゴブリン", 2);
        Monster b = new Monster("スライム", 1);
        assertEquals(1, battle.judge(a, b));
    }

    @Test
    public void testJudgeSecondWins() {
        Monster a = new Monster("デュラハン", 0);
        Monster b = new Monster("ゴブリン", 2);
        assertEquals(-1, battle.judge(a, b));
    }

    @Test
    public void testJudgeDraw() {
        Monster a = new Monster("スライム", 1);
        Monster b = new Monster("ゴブリン", 1);
        assertEquals(0, battle.judge(a, b));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJudgeWithNull() {
        battle.judge(null, new Monster("スライム", 1));
    }

    @Test
    public void testCalcDamage() {
        Monster winner = new Monster("ゴブリン", 2);
        Monster loser = new Monster("デュラハン", 0);
        assertEquals(20, battle.calcDamage(winner, loser));
    }

    @Test
    public void testTurnPlayer1Wins() {
        p1.addMonster(new Monster("ゴブリン", 2));
        p2.addMonster(new Monster("デュラハン", 0));

        battle.turn(p1, p2, 0, 0);

        assertEquals(100, p1.getHp());
        assertEquals(80, p2.getHp());
    }

    @Test
    public void testTurnPlayer2Wins() {
        p1.addMonster(new Monster("デュラハン", 0));
        p2.addMonster(new Monster("ゴブリン", 2));

        battle.turn(p1, p2, 0, 0);

        assertEquals(80, p1.getHp());
        assertEquals(100, p2.getHp());
    }

    @Test
    public void testTurnDraw() {
        p1.addMonster(new Monster("スライム", 1));
        p2.addMonster(new Monster("ゴブリン", 1));

        String result = battle.turn(p1, p2, 0, 0);

        assertEquals("引き分け", result);
        assertEquals(100, p1.getHp());
        assertEquals(100, p2.getHp());
    }

    @Test
    public void testPlayerIsAlive() {
        assertTrue(p1.isAlive());
        p1.damage(100);
        assertFalse(p1.isAlive());
    }

    @Test
    public void testHpDoesNotGoBelowZero() {
        p1.damage(150);
        assertEquals(0, p1.getHp());
    }
}