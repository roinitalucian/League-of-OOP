package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Dracula extends Angel {

    public Dracula(final int row, final int col) {
        this.type = AngelType.Dracula;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.increaseModifier(Constants.D_K_D);
        k.dealDamage(-Constants.D_K_HP);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.increaseModifier(Constants.D_P_D);
        p.dealDamage(-Constants.D_P_HP);
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.increaseModifier(Constants.D_R_D);
        r.dealDamage(-Constants.D_R_HP);
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.increaseModifier(Constants.D_W_D);
        w.dealDamage(-Constants.D_W_HP);
    }
}
