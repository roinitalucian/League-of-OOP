package angels;

import common.AngelType;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public abstract class Angel {
    protected AngelType type;
    protected int row, col;

    /*
     * Visitor pattern
     */

    public abstract void actOnHero(Knight k);

    public abstract void actOnHero(Pyromancer p);

    public abstract void actOnHero(Rogue r);

    public abstract void actOnHero(Wizard w);

    public final int getRow() {
        return row;
    }

    public final int getCol() {
        return col;
    }

    public final AngelType getType() {
        return type;
    }
}
