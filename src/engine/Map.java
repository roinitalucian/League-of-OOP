package engine;

public final class Map {
    private static Map instance = null;

    private static Character[][] map;

    private Map() {
        map = GameManager.getGameInput().getMap();
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public Character getTerrain(final int row, final int col) {
        return map[row][col];
    }
}
