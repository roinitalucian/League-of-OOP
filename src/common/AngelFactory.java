package common;

import angels.Angel;
import angels.DamageAngel;
import angels.DarkAngel;
import angels.Dracula;
import angels.GoodBoy;
import angels.LevelUpAngel;
import angels.LifeGiver;
import angels.SmallAngel;
import angels.Spawner;
import angels.TheDoomer;
import angels.XPAngel;

public final class AngelFactory {
    private static AngelFactory instance = null;

    private AngelFactory() {
    }

    public static AngelFactory getInstance() {
        if (instance == null) {
            instance = new AngelFactory();
        }
        return instance;
    }

    public Angel spawnAngel(final String type, final int row, final int col) {
        switch (type) {
        case "Dracula":
            return new Dracula(row, col);
        case "LifeGiver":
            return new LifeGiver(row, col);
        case "SmallAngel":
            return new SmallAngel(row, col);
        case "Spawner":
            return new Spawner(row, col);
        case "DarkAngel":
            return new DarkAngel(row, col);
        case "GoodBoy":
            return new GoodBoy(row, col);
        case "TheDoomer":
            return new TheDoomer(row, col);
        case "XPAngel":
            return new XPAngel(row, col);
        case "LevelUpAngel":
            return new LevelUpAngel(row, col);
        case "DamageAngel":
            return new DamageAngel(row, col);
        default:
            return null;
        }
    }
}
