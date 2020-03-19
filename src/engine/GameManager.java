package engine;

import java.util.ArrayList;
import java.util.List;

import angels.Angel;
import common.AngelType;
import common.Race;
import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.GameInput;

public final class GameManager {
    private static ArrayList<Hero> heroList = new ArrayList<Hero>();
    private static GameInput gameInput;

    public GameManager(final GameInput gameInput) {
        GameManager.gameInput = gameInput;
        int i = 0;
        for (Character c : gameInput.getHeroTypes()) {
            switch (c) {
            case 'W':
                heroList.add(new Wizard(gameInput.getHeroRows().get(i),
                        gameInput.getHeroCols().get(i), Race.Wizard));
                break;
            case 'K':
                heroList.add(new Knight(gameInput.getHeroRows().get(i),
                        gameInput.getHeroCols().get(i), Race.Knight));
                break;
            case 'P':
                heroList.add(new Pyromancer(gameInput.getHeroRows().get(i),
                        gameInput.getHeroCols().get(i),
                        Race.Pyromancer));
                break;
            case 'R':
                heroList.add(new Rogue(gameInput.getHeroRows().get(i),
                        gameInput.getHeroCols().get(i), Race.Rogue));
                break;
            default:
                break;
            }
            i++;
        }
    }

    public void startGame() {
        boolean[] wasAlive = new boolean[heroList.size()];
        boolean[] diedOfDot = new boolean[heroList.size()];
        int currentRound = 0;
        for (String s : gameInput.getMoves()) {
            System.out.println("~~ Round " + (currentRound + 1) + " ~~");

            for (int i = 0; i < s.length(); i++) {
                wasAlive[i] = heroList.get(i).isAlive();
                heroList.get(i).move(s.charAt(i));
                diedOfDot[i] = !heroList.get(i).isAlive();
            }

            for (int i = 0; i < gameInput.getN(); i++) {
                for (int j = 0; j < gameInput.getM(); j++) {
                    Hero h1 = null;
                    Hero h2 = null;
                    for (Hero h : heroList) {
                        if (h.hasPosition(i, j) && h.isAlive()) {
                            h1 = h;
                            break;
                        }
                    }

                    for (Hero h : heroList) {
                        if (h.hasPosition(i, j) && h != h1 && h.isAlive()) {
                            h2 = h;
                            break;
                        }
                    }

                    if (h1 == null || h2 == null) {
                        continue;
                    }
                    if (wasAlive[heroList.indexOf(h2)] && diedOfDot[heroList.indexOf(h2)]) {
                        break;
                    }
                    if (wasAlive[heroList.indexOf(h1)] && diedOfDot[heroList.indexOf(h1)]) {
                        break;
                    }

                    if (h1.getRace() == Race.Wizard) {
                        h1.attack(h2);
                        h2.attack(h1);
                    } else {
                        h2.attack(h1);
                        h1.attack(h2);
                    }
                    h1.updateXp(h2);
                    h2.updateXp(h1);
                }
            }

            for (Hero h : heroList) {
                h.setIsDead(!h.isAlive());
            }
            List<Angel> angels = gameInput.getAngelsInRound(currentRound);
            for (Angel a : angels) {
                TheGreatMagician.getInstance().update(a);
                for (Hero h : heroList) {
                    if (h.hasPosition(a.getCol(), a.getRow())) {
                        if ((!h.isAlive() && a.getType() != AngelType.Spawner)
                                || (h.isAlive() && a.getType() == AngelType.Spawner)) {
                            continue;
                        }
                        if (!h.isAlive() && a.getType() == AngelType.TheDoomer) {
                            continue;
                        }
                        TheGreatMagician.getInstance().update(a, h);
                        if (h.getRace() == Race.Knight) {
                            a.actOnHero((Knight) h);
                        } else if (h.getRace() == Race.Pyromancer) {
                            a.actOnHero((Pyromancer) h);
                        } else if (h.getRace() == Race.Rogue) {
                            a.actOnHero((Rogue) h);
                        } else {
                            a.actOnHero((Wizard) h);
                        }
                        TheGreatMagician.getInstance().update(h, a);
                    }
                }
            }
            currentRound++;
            System.out.println("");
        }
        System.out.println("~~ Results ~~");
        printLeaderBoard();

    }

    public static ArrayList<Hero> getHeroList() {
        return heroList;
    }

    public static GameInput getGameInput() {
        return gameInput;
    }

    public static Locations getLocation(final int row, final int col) {
        switch (Map.getInstance().getTerrain(row, col)) {
        case 'L':
            return Locations.Land;
        case 'V':
            return Locations.Volcanic;
        case 'D':
            return Locations.Desert;
        case 'W':
            return Locations.Woods;
        default:
            return null;
        }
    }

    public void printLeaderBoard() {
        for (Hero h : heroList) {
            System.out.println(h);
        }
    }

}
