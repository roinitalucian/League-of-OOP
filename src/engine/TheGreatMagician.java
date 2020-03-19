package engine;

import angels.Angel;
import common.AngelType;
import heroes.Hero;

public final class TheGreatMagician {

    private static TheGreatMagician instance = null;

    private TheGreatMagician() {
    }

    public static TheGreatMagician getInstance() {
        if (instance == null) {
            instance = new TheGreatMagician();
        }
        return instance;
    }

    public void update(final Angel a) {
        System.out.println("Angel " + a.getType()
        + " was spawned at " + a.getRow() + " " + a.getCol());
    }

    public void update(final Angel a, final Hero h) {
        if (a.getType() == AngelType.DamageAngel
                || a.getType() == AngelType.XPAngel
                || a.getType() == AngelType.GoodBoy
                || a.getType() == AngelType.SmallAngel
                || a.getType() == AngelType.LevelUpAngel
                || a.getType() == AngelType.LifeGiver) {
            System.out.println(a.getType() + " helped " + h.getRace() + " " + h.getId());
            return;
        }
        if (a.getType() == AngelType.Spawner) {
            System.out.println(a.getType() + " helped " + h.getRace() + " " + h.getId());
            System.out.println("Player " + h.getRace() + " " + h.getId()
            + " was brought to life by an angel");
            return;
        } else if (a.getType() == AngelType.TheDoomer) {
            System.out.println("TheDoomer hit " + h.getRace() + " " + h.getId());
            System.out.println("Player " + h.getRace() + " " + h.getId()
            + " was killed by an angel");
            return;
        }
        System.out.println(a.getType() + " hit " + h.getRace() + " " + h.getId());
    }

    public void update(final Hero h1, final Hero h2) {
        System.out.println(
                "Player " + h1.getRace() + " " + h1.getId()
                + " was killed by " + h2.getRace() + " " + h2.getId());
    }

    public void update(final int level, final int newLevel, final Hero h) {
        for (int i = level + 1; i <= newLevel; i++) {
            System.out.println(h.getRace() + " " + h.getId() + " reached level " + i);
        }
    }

    public void update(final Hero h, final Angel a) {
        if (!h.isAlive() && a.getType() == AngelType.Dracula) {
            System.out.println("Player " + h.getRace() + " " + h.getId()
            + " was killed by an angel");
        }
    }
}
