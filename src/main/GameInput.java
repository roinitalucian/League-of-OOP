package main;

import java.util.List;

import angels.Angel;

public final class GameInput {
    private final int n, m;
    private final Character[][] map;
    private final List<Character> heroTypes;
    private final List<Integer> heroRows;
    private final List<Integer> heroCols;
    private final List<String> moves;
    private final List<List<Angel>> angels;

    public GameInput(final int n, final int m, final Character[][] map, final List<Character> heroTypes,
            final List<Integer> heroRows, final List<Integer> heroCols, final List<String> moves,
            final List<List<Angel>> angels) {
        this.n = n;
        this.m = m;
        this.map = map;
        this.heroTypes = heroTypes;
        this.heroRows = heroRows;
        this.heroCols = heroCols;
        this.moves = moves;
        this.angels = angels;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Character[][] getMap() {
        return map;
    }

    public List<Character> getHeroTypes() {
        return heroTypes;
    }

    public List<Integer> getHeroRows() {
        return heroRows;
    }

    public List<Integer> getHeroCols() {
        return heroCols;
    }

    public List<String> getMoves() {
        return moves;
    }

    public List<Angel> getAngelsInRound(final int i) {
        return angels.get(i);
    }
}
