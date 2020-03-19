package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class XPAngel extends Angel {

    public XPAngel(final int row, final int col) {
        this.type = AngelType.XPAngel;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.addXp(Constants.XPA_K);
        k.updateLevel();
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.addXp(Constants.XPA_P);
        p.updateLevel();
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.addXp(Constants.XPA_R);
        r.updateLevel();
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.addXp(Constants.XPA_W);
        w.updateLevel();
    }

}
