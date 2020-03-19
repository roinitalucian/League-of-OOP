package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class DamageAngel extends Angel {
    public DamageAngel(final int row, final int col) {
        this.type = AngelType.DamageAngel;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.increaseModifier(Constants.DMG_K_D);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.increaseModifier(Constants.DMG_P_D);
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.increaseModifier(Constants.DMG_R_D);
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.increaseModifier(Constants.DMG_W_D);
    }
}
