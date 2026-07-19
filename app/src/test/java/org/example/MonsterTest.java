// ※パッケージ名は Monster.java と同じにする
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonsterTest {

  // toString() のテスト：レア度0なら進化しないのでそのまま表示される
  @Test
  public void testToString() {
    Monster m = new Monster("デュラハン", 0);

    assertEquals(
        "デュラハン:レア度[0]:HP[■■■■■■■■■■] 100/100",
        m.toString());
  }

  // summonMonster のテスト：0を渡すとデュラハンが返る
  @Test
  public void testSummonMonster() {
    Monster base = new Monster("スライム", 1);
    Monster summoned = base.summonMonster(0);
    assertEquals("デュラハン", summoned.getName());
    assertEquals(0, summoned.getRarity());
  }

  // 進化のテスト：レア度3以上だと名前の頭に「進化」が付く
  @Test
  public void testEvolve() {
    Monster m = new Monster("ドラゴン", 3);

    assertEquals("進化ドラゴン", m.getName());
    assertEquals(
        "進化ドラゴン:レア度[3]:HP[■■■■■■■■■■] 250/250",
        m.toString());
  }

  // 進化のテスト：レア度4以上だと名前の頭に「超進化」が付く
  @Test
  public void testSuperEvolve() {
    Monster m = new Monster("ドラゴン", 4);

    assertEquals("超進化ドラゴン", m.getName());
    assertEquals(
        "超進化ドラゴン:レア度[4]:HP[■■■■■■■■■■] 300/300",
        m.toString());
  }

  // 進化しないケースのテスト：レア度2以下ならそのまま
  @Test
  public void testNoEvolve() {
    Monster m = new Monster("ゴブリン", 2);
    assertEquals("ゴブリン", m.getName());
  }

  // HP計算のテスト：レア度4ならHPは300になる
  @Test
  public void testHp() {
    Monster m = new Monster("フェニックス", 4);

    assertEquals(300, m.getHp());
  }

  @Test
  public void hpbar() {
    Monster monster = new Monster("デュラハン", 0);

    assertEquals(
        "HP[■■■■■■■■■■] 100/100",
        monster.getHpBar());
  }

  @Test
  public void testMonsterDamage() {
    Monster monster = new Monster("デュラハン", 0);

    monster.damage(30);

    assertEquals(70, monster.getHp());
    assertEquals(
        "HP[■■■■■■■□□□] 70/100",
        monster.getHpBar());
  }

  @Test
  public void testMonsterHpDoesNotGoBelowZero() {
    Monster monster = new Monster("デュラハン", 0);

    monster.damage(150);

    assertEquals(0, monster.getHp());
    assertFalse(monster.isAlive());
  }
}
