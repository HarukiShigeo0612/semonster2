// ※パッケージ名は自分のプロジェクトに合わせて変更してください
// gradle init で作った場合、App.java の1行目と同じにする（例: package semonster2;）
package org.example;

public class Monster {
    private String name;   // モンスターの名前
    private int rarity; // レア度（0～4）
    private int hp;

    // コンストラクタ：名前とレア度を受け取る（p.17）
    public Monster(String name, int rarity) {
      this.name = name;
      this.rarity = rarity;
      // レア度からHPを計算する
      this.hp = calculateHp();
      // レア度が3以上のときは進化して名前が変わる（p.17）
      if (this.rarity >= 3) {
        evolve();
      }
    }

    // HPの計算
    private int calculateHp() {
      hp = 100 + rarity * 50;
      return hp;
    }

    // 進化処理：名前の先頭に「進化」を付ける
    private void evolve() {
        this.name = "進化" + this.name;
    }

    // 召喚処理：0～4の数値から対応する名前のMonsterを作る（p.17）
    public Monster summonMonster(int id) {
        String[] names = {"デュラハン", "スライム", "ゴブリン", "ドラゴン", "フェニックス"};
        // 範囲外が来たらとりあえずスライムにしておく
        if (id < 0 || id >= names.length) {
            id = 1;
        }
        return new Monster(names[id], id);
    }

    // 表示処理（p.17）例：「デュラハン:レア度[0]」
    @Override
    public String toString() {
        return name + ":レア度[" + rarity + "]" + ":HP[" + hp + "]";
    }

    // ゲッター（テストや他クラスから使う用）
    public String getName() {
        return name;
    }

    public int getRarity() {
      return rarity;
    }

    public int getHp() {
      return hp;
    }
}
