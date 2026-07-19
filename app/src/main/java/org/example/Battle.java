package org.example;

public class Battle {

    // 2体のモンスターの勝敗を判定する
    // 戻り値: 1 = aの勝ち, -1 = bの勝ち, 0 = 引き分け
    public int judge(Monster a, Monster b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("モンスターがnullです");
        }
        if (a.getRarity() > b.getRarity()) {
            return 1;
        } else if (a.getRarity() < b.getRarity()) {
            return -1;
        } else {
            return 0;
        }
    }

    // レア度の差からダメージ量を計算する
    public int calcDamage(Monster winner, Monster loser) {
        if (winner == null || loser == null) {
            throw new IllegalArgumentException("モンスターがnullです");
        }
        return (winner.getRarity() - loser.getRarity()) * 10;
    }

    // 1ターン分の対戦を行い、結果を文字列で返す
    public String turn(Player p1, Player p2, int index1, int index2) {
        Monster m1 = p1.getMonster(index1);
        Monster m2 = p2.getMonster(index2);

        int result = judge(m1, m2);

        if (result == 1) {
            int damage = calcDamage(m1, m2);
            p2.damage(damage);
            return p1.getName() + "の勝ち：" + p2.getName() + "に" + damage + "のダメージ";
        } else if (result == -1) {
            int damage = calcDamage(m2, m1);
            p1.damage(damage);
            return p2.getName() + "の勝ち：" + p1.getName() + "に" + damage + "のダメージ";
        } else {
            return "引き分け";
        }
    }
}