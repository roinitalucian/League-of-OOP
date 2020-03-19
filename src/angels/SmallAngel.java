package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class SmallAngel extends Angel {

    public SmallAngel(final int row, final int col) {
        this.type = AngelType.SmallAngel;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.increaseModifier(Constants.SA_K_D);
        k.dealDamage(-Constants.SA_K_HP);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.increaseModifier(Constants.SA_P_D);
        p.dealDamage(-Constants.SA_P_HP);
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.increaseModifier(Constants.SA_R_D);
        r.dealDamage(-Constants.SA_R_HP);
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.increaseModifier(Constants.SA_W_D);
        w.dealDamage(-Constants.SA_W_HP);
    }
}
