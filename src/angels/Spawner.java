package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Spawner extends Angel {

    public Spawner(final int row, final int col) {
        this.type = AngelType.Spawner;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        if (k.isAlive()) {
            return;
        }
        k.setIsDead(false);
        k.setHp(Constants.S_K);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        if (p.isAlive()) {
            return;
        }
        p.setIsDead(false);
        p.setHp(Constants.S_P);
    }

    @Override
    public void actOnHero(final Rogue r) {
        if (r.isAlive()) {
            return;
        }
        r.setIsDead(false);
        r.setHp(Constants.S_R);
    }

    @Override
    public void actOnHero(final Wizard w) {
        if (w.isAlive()) {
            return;
        }
        w.setIsDead(false);
        w.setHp(Constants.S_W);
    }
}
