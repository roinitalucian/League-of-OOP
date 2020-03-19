package heroes;

import common.Constants;
import common.Race;
import engine.GameManager;
import engine.Locations;

public final class Wizard extends Hero {

    public Wizard(final int row, final int col, final Race race) {
        super(row, col, race);
        this.hp = Constants.W_BASE_HP;
        this.hpBase = hp;
        this.hpPerLevel = Constants.W_HP_PER_LVL;
    }

    public Wizard(final Wizard wizard) {
        super(wizard);
    }

    /**
     * drain.
     * 
     * @return
     */
    @Override
    public int castSpell1(final Hero h) {
        int percent = Constants.W_DRAIN_BASE_PERCENT + this.level * Constants.W_DRAIN_PER_LVL;
        float multiplier = h.getRaceModifier1(this) + angelModifier;
        multiplier *= getLandModifier();
        int dmg = Math.round(percent * multiplier * Math.min(Constants.W_DRAIN_MIN_PERCENT * h.getMaxHp(), h.getHp())
                / Constants.PERCENT);

        h.dealDamage(Math.round(dmg));
        return Math.round(dmg * multiplier / h.getRaceModifier1(this));
    }

    /**
     * deflect.
     * 
     * @return
     */
    @Override
    public int castSpell2(final Hero h) {
        if (h.race == Race.Wizard) {
            return 0;
        }
        float percent = Constants.W_DEFLECT_BASE_PERCENT + this.level * Constants.W_DEFLECT_PER_LVL;
        if (percent > Constants.W_DEFLECT_MAX_PERCENT) {
            percent = Constants.W_DEFLECT_MAX_PERCENT;
        }
        int damageReceived = h.getDamageDealt(this);
        float dmg = percent * damageReceived;
        float multiplier = h.getRaceModifier2(this) + angelModifier;
        multiplier *= getLandModifier();
        h.dealDamage(Math.round(dmg * multiplier));
        return Math.round(dmg * multiplier / h.getRaceModifier2(this));
    }

    @Override
    protected float getLandModifier() {
        if (GameManager.getLocation(row, col) == Locations.Desert) {
            return Constants.W_LAND_BONUS;
        }
        return 1;
    }

    @Override
    protected float getRaceModifier1(final Pyromancer pyromancer) {
        return Constants.BONUS_P_P_1;
    }

    @Override
    protected float getRaceModifier1(final Knight knight) {
        return Constants.BONUS_K_W_1;
    }

    @Override
    protected float getRaceModifier1(final Wizard wizard) {
        return Constants.BONUS_W_W_1;
    }

    @Override
    protected float getRaceModifier1(final Rogue rogue) {
        return Constants.BONUS_R_W_1;
    }

    @Override
    protected float getRaceModifier2(final Pyromancer pyromancer) {
        return Constants.BONUS_P_W_2;
    }

    @Override
    protected float getRaceModifier2(final Knight knight) {
        return Constants.BONUS_K_W_2;
    }

    @Override
    protected float getRaceModifier2(final Wizard wizard) {
        return Constants.BONUS_W_W_2;
    }

    @Override
    protected float getRaceModifier2(final Rogue rogue) {
        return Constants.BONUS_R_W_2;
    }

    @Override
    public Hero clone() {
        return new Wizard(this);
    }

    @Override
    public void applyStrategy() {
        if (getMaxHp() / Constants.MIN_HP_W < hp && hp < getMaxHp() / Constants.MAX_HP_W) {
            hp -= hp / Constants.GIVEUP_HP_W;
            angelModifier += Constants.INC_MOD_W;
        } else if (hp < getMaxHp() / Constants.MIN_HP_W) {
            hp += hp / Constants.INC_HP_W;
            angelModifier -= Constants.GIVEUP_MOD_W;
        }
    }
}
