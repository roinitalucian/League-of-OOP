package heroes;

import common.Constants;
import common.OvertimeEffect;
import common.Race;
import engine.GameManager;
import engine.Locations;

public final class Knight extends Hero {

    public Knight(final int row, final int col, final Race race) {
        super(row, col, race);
        this.hp = Constants.K_BASE_HP;
        this.hpBase = hp;
        this.hpPerLevel = Constants.K_HP_PER_LVL;
    }

    public Knight(final Knight knight) {
        super(knight);
    }

    /**
     * execute.
     *
     * @return
     */
    @Override
    public int castSpell1(final Hero h) {
        int hpLimit = Constants.K_EXECUTE_BASE_HP + this.level * Constants.K_EXECUTE_HP_PER_LVL;
        if (hpLimit > Constants.K_EXECUTE_MAX_HP) {
            hpLimit = Constants.K_EXECUTE_MAX_HP;
        }
        if (h.getHp() < h.getMaxHp() * hpLimit / Constants.PERCENT) {
            int oldHp = h.getHp();
            h.dealDamage(h.getHp());
            return oldHp;
        }
        int dmg = Constants.K_EXECUTE_BASE_DMG + this.level * Constants.K_EXECUTE_PER_LVL;
        float multiplier = h.getRaceModifier1(this) + angelModifier;
        if (h.getRace() == Race.Knight) {
            multiplier -= angelModifier;
        }
        multiplier *= getLandModifier();
        h.dealDamage(Math.round(dmg * multiplier));
        return Math.round(dmg * multiplier / h.getRaceModifier1(this));
    }

    /**
     * slam.
     *
     * @return
     */
    @Override
    public int castSpell2(final Hero h) {
        int dmg = Constants.K_SLAM_BASE_DMG + this.level * Constants.K_SLAM_PER_LVL;
        float multiplier = h.getRaceModifier2(this) + angelModifier;
        multiplier *= getLandModifier();
        h.dealDamage(Math.round(dmg * multiplier));
        h.setOvertimeEffect(OvertimeEffect.Snare);
        h.setRoundsAffectedByEffect(Constants.K_SLAM_ROUNDS);
        return Math.round(dmg * multiplier / h.getRaceModifier2(this));
    }

    @Override
    protected float getLandModifier() {
        if (GameManager.getLocation(row, col) == Locations.Land) {
            return Constants.K_LAND_BONUS;
        }
        return 1;
    }

    @Override
    protected float getRaceModifier1(final Pyromancer pyromancer) {
        return Constants.BONUS_P_K_1;
    }

    @Override
    protected float getRaceModifier1(final Knight knight) {
        return Constants.BONUS_K_K_1;
    }

    @Override
    protected float getRaceModifier1(final Wizard wizard) {
        return Constants.BONUS_W_K_1;
    }

    @Override
    protected float getRaceModifier1(final Rogue rogue) {
        return Constants.BONUS_R_K_1;
    }

    @Override
    protected float getRaceModifier2(final Pyromancer pyromancer) {
        return Constants.BONUS_P_K_2;
    }

    @Override
    protected float getRaceModifier2(final Knight knight) {
        return Constants.BONUS_K_K_2;
    }

    @Override
    protected float getRaceModifier2(final Wizard wizard) {
        return Constants.BONUS_W_K_2;
    }

    @Override
    protected float getRaceModifier2(final Rogue rogue) {
        return Constants.BONUS_R_K_2;
    }

    @Override
    public Hero clone() {
        return new Knight(this);
    }

    @Override
    public void applyStrategy() {
        if (getMaxHp() / Constants.MIN_HP_K < hp && hp < getMaxHp() / Constants.MAX_HP_K) {
            hp -= hp / Constants.GIVEUP_HP_K;
            angelModifier += Constants.INC_MOD_K;
        } else if (hp < getMaxHp() / Constants.MIN_HP_K) {
            hp += hp / Constants.INC_HP_K;
            angelModifier -= Constants.GIVEUP_MOD_K;
        }
    }
}
