package heroes;

import common.Constants;
import common.OvertimeEffect;
import common.Race;
import engine.Locations;
import engine.GameManager;

public final class Rogue extends Hero {

    private int currentRound;

    public Rogue(final int row, final int col, final Race race) {
        super(row, col, race);
        this.hp = Constants.R_BASE_HP;
        this.hpBase = hp;
        this.hpPerLevel = Constants.R_HP_PER_LVL;
        this.currentRound = 0;
    }

    public Rogue(final Rogue rogue) {
        super(rogue);
    }

    /**
     * backstab.
     *
     * @return
     */
    @Override
    public int castSpell1(final Hero h) {
        currentRound++;
        int dmg = Constants.R_BACKSTAB_BASE_DMG + this.level * Constants.R_BACKSTAB_PER_LVL;
        float multiplier = h.getRaceModifier1(this) + angelModifier;
        multiplier *= getLandModifier();
        if (currentRound % Constants.R_BACKSTAB_CRITICAL_CHANCE == 1
                && GameManager.getLocation(row, col) == Locations.Woods) {
            multiplier *= Constants.R_BACKSTAB_CRITICAL_MULTIPLIER;
        }
        h.dealDamage(Math.round(dmg * multiplier));
        return Math.round(dmg * (multiplier - angelModifier) / h.getRaceModifier1(this));
    }

    /**
     * paralysis.
     *
     * @return
     */
    @Override
    public int castSpell2(final Hero h) {
        int dmg = Constants.R_PARALYSIS_BASE_DMG + this.level * Constants.R_PARALYSIS_PER_LVL;
        float multiplier = h.getRaceModifier2(this);
        for (Float f : angelModifierList) {
            multiplier += f;
        }
        multiplier *= getLandModifier();
        h.setOvertimeEffect(OvertimeEffect.Both);
        h.setRoundsAffectedByEffect(Constants.R_PARALYSIS_ROUNDS);
        if (GameManager.getLocation(row, col) == Locations.Woods) {
            h.setRoundsAffectedByEffect(Constants.R_PARALYSIS_ROUNDS_WOODS);
        }
        h.setDamageOverTime(Math.round(dmg * multiplier));
        h.setDotApplier(this);
        h.dealDamage(Math.round(dmg * multiplier));
        for (Float f : angelModifierList) {
            multiplier -= f;
        }
        return Math.round(dmg * multiplier / h.getRaceModifier2(this));
    }

    @Override
    protected float getLandModifier() {
        if (GameManager.getLocation(row, col) == Locations.Woods) {
            return Constants.R_LAND_BONUS;
        }
        return 1;
    }

    @Override
    protected float getRaceModifier1(final Pyromancer pyromancer) {
        return Constants.BONUS_P_R_1;
    }

    @Override
    protected float getRaceModifier1(final Knight knight) {
        return Constants.BONUS_K_R_1;
    }

    @Override
    protected float getRaceModifier1(final Wizard wizard) {
        return Constants.BONUS_W_R_1;
    }

    @Override
    protected float getRaceModifier1(final Rogue rogue) {
        return Constants.BONUS_R_R_1;
    }

    @Override
    protected float getRaceModifier2(final Pyromancer pyromancer) {
        return Constants.BONUS_P_R_2;
    }

    @Override
    protected float getRaceModifier2(final Knight knight) {
        return Constants.BONUS_K_R_2;
    }

    @Override
    protected float getRaceModifier2(final Wizard wizard) {
        return Constants.BONUS_W_R_2;
    }

    @Override
    protected float getRaceModifier2(final Rogue rogue) {
        return Constants.BONUS_R_R_2;
    }

    @Override
    public void resetAfterSimulation() {
        this.currentRound--;
    }

    @Override
    public Hero clone() {
        return new Rogue(this);
    }

    @Override
    public void applyStrategy() {
        if (getMaxHp() / Constants.MIN_HP_R < hp && hp < getMaxHp() / Constants.MAX_HP_R) {
            hp -= hp / Constants.GIVEUP_HP_R;
            angelModifier += Constants.INC_MOD_R;
        } else if (hp < getMaxHp() / Constants.MIN_HP_R) {
            hp += hp / Constants.INC_HP_R;
            angelModifier -= Constants.GIVEUP_MOD_R;
        }
    }
}
