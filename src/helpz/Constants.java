//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package helpz;

public class Constants {
    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 1;
        public static final int CHAINS = 2;

        public static float GetSpeed(int type) {
            switch (type) {
                case 0 -> {
                    return 3.0F;
                }
                case 1 -> {
                    return 1.0F;
                }
                case 2 -> {
                    return 2.0F;
                }
                default -> {
                    return 0.0F;
                }
            }
        }
    }

    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static String GetName(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return "Cannon";
                }
                case 1 -> {
                    return "Archer";
                }
                case 2 -> {
                    return "Wizard";
                }
                default -> {
                    return "";
                }
            }
        }

        public static float GetStartDmg(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return 25.0F;
                }
                case 1 -> {
                    return 15.0F;
                }
                case 2 -> {
                    return 5.0F;
                }
                default -> {
                    return 0.0F;
                }
            }
        }

        public static float GetDefaultRange(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return 100.0F;
                }
                case 1 -> {
                    return 100.0F;
                }
                case 2 -> {
                    return 100.0F;
                }
                default -> {
                    return 0.0F;
                }
            }
        }

        public static float GetDefaultCooldown(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return 10.0F;
                }
                case 1 -> {
                    return 10.0F;
                }
                case 2 -> {
                    return 10.0F;
                }
                default -> {
                    return 0.0F;
                }
            }
        }
    }

    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Enemies {
        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;
        public static final int WOLF = 3;

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case 0 -> {
                    return 0.5F;
                }
                case 1 -> {
                    return 0.65F;
                }
                case 2 -> {
                    return 0.3F;
                }
                case 3 -> {
                    return 0.75F;
                }
                default -> {
                    return 0.0F;
                }
            }
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case 0 -> {
                    return 100;
                }
                case 1 -> {
                    return 60;
                }
                case 2 -> {
                    return 250;
                }
                case 3 -> {
                    return 85;
                }
                default -> {
                    return 0;
                }
            }
        }
    }

    public static class Tiles {
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }
}
