package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class GoodBoy extends Angel {

    public GoodBoy(final int row, final int col) {
        this.type = AngelType.GoodBoy;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.increaseModifier(Constants.GB_K_D);
        k.dealDamage(-Constants.GB_K_HP);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.increaseModifier(Constants.GB_P_D);
        p.dealDamage(-Constants.GB_P_HP);
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.increaseModifier(Constants.GB_R_D);
        r.dealDamage(-Constants.GB_R_HP);
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.increaseModifier(Constants.GB_W_D);
        w.dealDamage(-Constants.GB_W_HP);
    }
}
