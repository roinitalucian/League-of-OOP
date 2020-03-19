package heroes;

import java.util.ArrayList;
import java.util.List;

import common.Constants;
import common.OvertimeEffect;
import common.Race;
import engine.TheGreatMagician;

public abstract class Hero {
    protected int row;
    protected int col;
    protected int hp;
    protected int hpPerLevel;
    protected int xp;
    protected int level;
    protected final Race race;
    protected OvertimeEffect overtimeEffect;
    protected int roundsAffectedByEffect;
    protected int damageOverTime;
    protected int hpBase;
    protected boolean isDead;
    protected Hero dotApplier;
    protected float angelModifier = 0;
    private boolean canMove = true;
    private int id = -1;
    private static int currentId = 0;
    protected List<Float> angelModifierList = new ArrayList<>();

    public Hero(final int row, final int col, final Race race) {
        this.row = row;
        this.col = col;
        this.race = race;
        this.level = 0;
        this.roundsAffectedByEffect = 0;
        this.isDead = false;
        this.dotApplier = null;
        this.id = currentId;
        currentId++;
    }

    public Hero(final Hero h) {
        this.row = h.row;
        this.col = h.col;
        this.hp = h.hp;
        this.hpPerLevel = h.hpPerLevel;
        this.xp = h.xp;
        this.level = h.level;
        this.race = h.race;
        this.overtimeEffect = h.overtimeEffect;
        this.roundsAffectedByEffect = h.roundsAffectedByEffect;
        this.damageOverTime = h.damageOverTime;
        this.hpBase = h.hpBase;
        this.isDead = h.isDead;
        this.dotApplier = h.dotApplier;
    }

    public abstract int castSpell1(Hero h);

    public abstract int castSpell2(Hero h);

    /*
     * Double Dispatch methods
     */
    protected abstract float getRaceModifier1(Pyromancer pyromancer);

    protected abstract float getRaceModifier1(Knight knight);

    protected abstract float getRaceModifier1(Wizard wizard);

    protected abstract float getRaceModifier1(Rogue rogue);

    protected abstract float getRaceModifier2(Pyromancer pyromancer);

    protected abstract float getRaceModifier2(Knight knight);

    protected abstract float getRaceModifier2(Wizard wizard);

    protected abstract float getRaceModifier2(Rogue rogue);

    protected abstract float getLandModifier();

    public final void attack(final Hero h) {
        if (!h.isAlive() || isDead) {
            return;
        }

        castSpell1(h);
        castSpell2(h);

    }

    public final void updateXp(final Hero h) {
        if (!h.isAlive()) {
            TheGreatMagician.getInstance().update(h, this);
            if (isAlive()) {
                xp += Math.max(0, Constants.MIN_XP_EARNED
                        - (this.level - h.getLevel()) * Constants.XP_LVL_MULTIPLIER);
                updateLevel();
            }
        }
    }

    public final void move(final char direction) {
        char dir = direction;
        if (hp < 0) {
            return;
        }
        if (roundsAffectedByEffect > 0) {
            roundsAffectedByEffect--;
            if (overtimeEffect == OvertimeEffect.DamageOverTime
                    || overtimeEffect == OvertimeEffect.Both) {
                hp -= damageOverTime;
                if (hp <= 0 && this.hasSamePositionAs(dotApplier)) {
                    return;
                }
            }

            canMove = true;
            if (overtimeEffect == OvertimeEffect.Snare
                    || overtimeEffect == OvertimeEffect.Both) {
                dir = '_';
                canMove = false;
            }
        }

        if (roundsAffectedByEffect == 0) {
            overtimeEffect = OvertimeEffect.None;
        }

        if (canMove) {
            applyStrategy();
        }

        switch (dir) {
        case 'U':
            row--;
            break;
        case 'D':
            row++;
            break;
        case 'L':
            col--;
            break;
        case 'R':
            col++;
            break;
        default:
            break;
        }
    }

    public final void dealDamage(final int dmg) {
        hp -= dmg;
    }

    public final void updateLevel() {
        int newLevel = level;
        if (xp >= Constants.XP_LVL1) {
            newLevel = (xp - Constants.MIN_XP_EARNED) / Constants.XP_LVL_DIVIDER;
        }
        if (newLevel != level) {
            TheGreatMagician.getInstance().update(level, newLevel, this);
            level = newLevel;
            hp = getMaxHp();
        }
    }

    public final int getHp() {
        return this.hp;
    }

    public final int getXp() {
        return this.xp;
    }

    public final int getMaxHp() {
        return hpBase + (level * hpPerLevel);
    }

    public final void setOvertimeEffect(final OvertimeEffect overtimeEffect) {
        this.overtimeEffect = overtimeEffect;
    }

    public final void setRoundsAffectedByEffect(final int r) {
        this.roundsAffectedByEffect = r;
    }

    public final void setDamageOverTime(final int dmg) {
        this.damageOverTime = dmg;
    }

    public final int getLevel() {
        return level;
    }

    /*
     * used for calculating the damaged deflected by Wizard.
     */
    protected final int getDamageDealt(final Wizard wizard) {
        Wizard copy = new Wizard(wizard);
        copy.setHp(copy.getMaxHp());
        int damageDealt = this.castSpell1(copy) + this.castSpell2(copy);
        if (this.race == Race.Rogue) {
            this.resetAfterSimulation();
        }
        return damageDealt;
    }

    public final boolean isAlive() {
        return hp > 0;
    }

    public final boolean hasSamePositionAs(final Hero h2) {
        if (this == h2) {
            return false;
        }
        return (this.col == h2.getCol() && this.row == h2.getRow());
    }

    public final int getCol() {
        return col;
    }

    public final int getRow() {
        return row;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder("");
        if (race == Race.Pyromancer) {
            sb.append('P');
        } else if (race == Race.Knight) {
            sb.append('K');
        } else if (race == Race.Wizard) {
            sb.append('W');
        } else {
            sb.append('R');
        }

        if (!isAlive()) {
            sb.append(" dead");
            return sb.toString();
        }

        sb.append(" " + level + " " + xp + " " + hp + " " + row + " " + col);
        return sb.toString();
    }

    public final void setIsDead(final boolean isDead) {
        this.isDead = isDead;
    }

    public final boolean getIsDead() {
        return isDead;
    }

    public final void addXp(final int xpToAdd) {
        this.xp += xpToAdd;
    }

    public final void setDotApplier(final Hero h) {
        this.dotApplier = h;
    }

    /**
     * it is used to get to the initial state after Wizard uses Deflect.
     */
    public void resetAfterSimulation() {
        return;
    }

    public final boolean hasPosition(final int y, final int x) {
        return (this.col == y && this.row == x);
    }

    public final Race getRace() {
        return race;
    }

    public final OvertimeEffect getOvertimeEffect() {
        return overtimeEffect;
    }

    @SuppressWarnings("deprecation")
    public final void increaseModifier(final float x) {
        angelModifierList.add(new Float(x));
        angelModifier += x;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final int getId() {
        return id;
    }

    public abstract Hero clone();

    public abstract void applyStrategy();
}
