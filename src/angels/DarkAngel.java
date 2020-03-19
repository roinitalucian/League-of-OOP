package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class DarkAngel extends Angel {
    public DarkAngel(final int row, final int col) {
        this.type = AngelType.DarkAngel;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.dealDamage(Constants.D_K);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.dealDamage(Constants.D_P);
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.dealDamage(Constants.D_R);
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.dealDamage(Constants.D_W);
    }
}
