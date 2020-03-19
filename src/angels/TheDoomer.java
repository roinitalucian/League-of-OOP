package angels;

import common.AngelType;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class TheDoomer extends Angel {

    public TheDoomer(final int row, final int col) {
        this.type = AngelType.TheDoomer;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actOnHero(final Knight k) {
        k.dealDamage(k.getHp());
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        p.dealDamage(p.getHp());
    }

    @Override
    public void actOnHero(final Rogue r) {
        r.dealDamage(r.getHp());
    }

    @Override
    public void actOnHero(final Wizard w) {
        w.dealDamage(w.getHp());
    }

}
