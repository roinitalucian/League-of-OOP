package angels;

import common.AngelType;
import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class LifeGiver extends Angel {

    public LifeGiver(final int row, final int col) {
        this.type = AngelType.LifeGiver;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.dealDamage(-Constants.LG_K);
        if (k.getHp() > k.getMaxHp()) {
            k.setHp(k.getMaxHp());
        }
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.dealDamage(-Constants.LG_P);
        if (p.getHp() > p.getMaxHp()) {
            p.setHp(p.getMaxHp());
        }
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.dealDamage(-Constants.LG_R);
        if (r.getHp() > r.getMaxHp()) {
            r.setHp(r.getMaxHp());
        }
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.dealDamage(-Constants.LG_W);
        if (w.getHp() > w.getMaxHp()) {
            w.setHp(w.getMaxHp());
        }
    }
}
