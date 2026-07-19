// ※パッケージ名は Monster.java と同じにする
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonsterTest {

  // toString() のテスト：レア度0なら進化しないのでそのまま表示される
  @Test
  public void testToString() {
    Monster m = new Monster("デュラハン", 0);
    assertEquals("デュラハン:レア度[0]:HP[100]", m.toString());
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
    assertEquals("進化ドラゴン:レア度[3]:HP[250]", m.toString());
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
}
