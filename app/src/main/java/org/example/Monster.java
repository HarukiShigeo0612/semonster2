package org.example;

public class Monster {
  private String name;
  private int rarity;
  private int hp;
  private int maxHp;

  // コンストラクタ
  public Monster(String name, int rarity) {
    this.name = name;
    this.rarity = rarity;

    // 最大HPを計算し、現在HPを最大HPにする
    this.maxHp = calculateHp();
    this.hp = maxHp;

    // レア度が3以上なら進化
    if (this.rarity >= 3) {
      evolve();
    }

    // レア度が4以上なら超進化
    if (this.rarity >= 4) {
      superEvolve();
    }

  }

  // HPの計算
  private int calculateHp() {
    return 100 + rarity * 50;
  }

  // 進化処理
  private void evolve() {
    this.name = "進化" + this.name;
  }

  // 超進化処理
  private void superEvolve() {
    this.name = "超" + this.name;
  }

  // 召喚処理
  public Monster summonMonster(int id) {
    String[] names = {
        "デュラハン",
        "スライム",
        "ゴブリン",
        "ドラゴン",
        "フェニックス"
    };

    // 範囲外の場合はスライム
    if (id < 0 || id >= names.length) {
      id = 1;
    }

    return new Monster(names[id], id);
  }

  // HPバーを作成する
  public String getHpBar() {
    int barLength = 10;

    // 現在HPの割合から■の数を計算
    int filledLength = hp * barLength / maxHp;

    StringBuilder bar = new StringBuilder("HP[");

    for (int i = 0; i < barLength; i++) {
      if (i < filledLength) {
        bar.append("■");
      } else {
        bar.append("□");
      }
    }

    bar.append("] ");
    bar.append(hp);
    bar.append("/");
    bar.append(maxHp);

    return bar.toString();
  }

  // モンスター情報を表示
  @Override
  public String toString() {
    return name
        + ":レア度[" + rarity + "]"
        + ":" + getHpBar();
  }

  public String getName() {
    return name;
  }

  public int getRarity() {
    return rarity;
  }

  public int getHp() {
    return hp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  // モンスターがダメージを受ける
  public void damage(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("ダメージは0以上である必要があります");
    }

    this.hp -= amount;

    // HPが0未満にならないようにする
    if (this.hp < 0) {
      this.hp = 0;
    }
  }

  // モンスターが生きているか確認する
  public boolean isAlive() {
    return hp > 0;
  }
}
