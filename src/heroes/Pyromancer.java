package heroes;

import common.Constants;
import common.OvertimeEffect;
import common.Race;
import engine.GameManager;
import engine.Locations;

public final class Pyromancer extends Hero {

    public Pyromancer(final int row, final int col, final Race race) {
        super(row, col, race);
        this.hp = Constants.P_BASE_HP;
        this.hpBase = hp;
        this.hpPerLevel = Constants.P_HP_PER_LVL;
    }

    public Pyromancer(final Pyromancer pyromancer) {
        super(pyromancer);
    }

    /**
     * fireblast.
     * 
     * @return
     */
    @Override
    public int castSpell1(final Hero h) {
        int dmg = Constants.P_FIREBLAST_BASE_DMG + this.level * Constants.P_FIREBLAST_PER_LVL;
        dmg = Math.round(dmg * getLandModifier());
        float multiplier = h.getRaceModifier1(this) + angelModifier;
        h.dealDamage(Math.round(dmg * multiplier));
        return Math.round(dmg * multiplier / h.getRaceModifier1(this));
    }

    /**
     * ignite.
     * 
     * @return
     */
    @Override
    public int castSpell2(final Hero h) {
        int dmg = Constants.P_IGNITE_BASE_DMG + this.level * Constants.P_IGNITE_PER_LVL;
        h.setOvertimeEffect(OvertimeEffect.DamageOverTime);
        h.setRoundsAffectedByEffect(Constants.P_IGINITE_ROUNDS);
        int dmgOverTime = Constants.P_IGNITE_BASE_DOT + this.level * Constants.P_IGNITE_BASE_DOT;
        float multiplier = h.getRaceModifier2(this) + angelModifier;
        multiplier *= getLandModifier();
        h.setDamageOverTime(Math.round(dmgOverTime * multiplier));
        h.setDotApplier(this);
        h.dealDamage(Math.round(dmg * multiplier));
        return Math.round(dmg * multiplier / h.getRaceModifier2(this));
    }

    @Override
    protected float getLandModifier() {
        if (GameManager.getLocation(row, col) == Locations.Volcanic) {
            return Constants.P_LAND_BONUS;
        }
        return 1;
    }

    @Override
    protected float getRaceModifier1(final Pyromancer pyromancer) {
        return Constants.BONUS_P_P_1;
    }

    @Override
    protected float getRaceModifier1(final Knight knight) {
        return Constants.BONUS_K_P_1;
    }

    @Override
    protected float getRaceModifier1(final Wizard wizard) {
        return Constants.BONUS_W_P_1;
    }

    @Override
    protected float getRaceModifier1(final Rogue rogue) {
        return Constants.BONUS_R_P_1;
    }

    @Override
    protected float getRaceModifier2(final Pyromancer pyromancer) {
        return Constants.BONUS_P_P_2;
    }

    @Override
    protected float getRaceModifier2(final Knight knight) {
        return Constants.BONUS_K_P_2;
    }

    @Override
    protected float getRaceModifier2(final Wizard wizard) {
        return Constants.BONUS_W_P_2;
    }

    @Override
    protected float getRaceModifier2(final Rogue rogue) {
        return Constants.BONUS_R_P_2;
    }

    @Override
    public Hero clone() {
        return new Pyromancer(this);
    }

    @Override
    public void applyStrategy() {
        if (getMaxHp() / Constants.MIN_HP_P < hp && hp < getMaxHp() / Constants.MAX_HP_P) {
            hp -= hp / Constants.GIVEUP_HP_P;
            angelModifier += Constants.INC_MOD_P;
        } else if (hp < getMaxHp() / Constants.MIN_HP_P) {
            hp += hp / Constants.INC_HP_P;
            angelModifier -= Constants.GIVEUP_MOD_P;
        }
    }
}
