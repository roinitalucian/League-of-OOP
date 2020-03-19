package angels;

import common.AngelType;
import common.Constants;
import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class LevelUpAngel extends Angel {

    public LevelUpAngel(final int row, final int col) {
        this.type = AngelType.LevelUpAngel;
        this.row = row;
        this.col = col;
    }

    public void giveXp(final Hero h) {
        int currentLevel = h.getLevel();
        int xpNextLevel = Constants.XP_LVL1 + currentLevel * Constants.XP_LVL_DIVIDER;
        h.addXp(xpNextLevel - h.getXp());
        h.updateLevel();
    }

    @Override
    public void actOnHero(final Knight k) {
        giveXp(k);
        k.increaseModifier(Constants.LA_K_D);
    }

    @Override
    public void actOnHero(final Pyromancer p) {
        giveXp(p);
        p.increaseModifier(Constants.LA_P_D);
    }

    @Override
    public void actOnHero(final Rogue r) {
        giveXp(r);
        r.increaseModifier(Constants.LA_R_D);
    }

    @Override
    public void actOnHero(final Wizard w) {
        giveXp(w);
        w.increaseModifier(Constants.LA_W_D);
    }

}
