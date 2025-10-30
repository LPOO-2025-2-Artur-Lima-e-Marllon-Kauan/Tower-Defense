//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package helpz;

/**
 * Classe que centraliza todas as constantes do jogo
 * Organizada em classes internas por categoria: Projectiles, Towers, Direction, Enemies, Tiles
 */
public class Constants {
    /**
     * Constantes de projéteis
     * Cada torre dispara um tipo diferente de projétil
     */
    public static class Projectiles {
        public static final int ARROW = 0;   // Archer tower
        public static final int CHAINS = 1;  // Wizard tower
        public static final int BOMB = 2;    // Cannon tower

        /**
         * Retorna a velocidade do projétil baseado no tipo
         * Arrow (Archer) = mais rápido, Bomb (Cannon) = mais lento
         */
        public static float GetSpeed(int type) {
            switch (type) {
                case 0 -> {
                    return 8.0F;
                }
                case 1 -> {
                    return 6.0F;
                }
                case 2 -> {
                    return 4.0F;
                }
                default -> {
                    return 0.0F;
                }
            }
        }
    }

    /**
     * Constantes de torres
     * CANNON: Alto dano, lento, projéteis explosivos
     * ARCHER: Baixo dano, rápido, flechas
     * WIZARD: Sem dano, aplica efeito de lentidão
     */
    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        /**
         * Retorna o custo em ouro para construir a torre
         * Cannon: 20, Archer: 15, Wizard: 25
         */
        public static int GetTowerCost(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return 20; // Cannon
                }
                case 1 -> {
                    return 15; // Archer (mais barata)
                }
                case 2 -> {
                    return 25; // Wizard (mais cara)
                }
                default -> {
                    return 0;
                }
            }
        }

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

        /**
         * Retorna o dano inicial da torre
         * Cannon: 15 (alto), Archer: 5 (baixo), Wizard: 0 (não causa dano)
         */
        public static int GetStartDmg(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return 15; // Cannon - alto dano
                }
                case 1 -> {
                    return 5;  // Archer - baixo dano
                }
                case 2 -> {
                    return 0;  // Wizard - apenas aplica slow
                }
                default -> {
                    return 0;
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

        /**
         * Retorna o cooldown padrão da torre (em ticks)
         * Cannon: 120 ticks (2 segundos) - muito lento
         * Archer: 25 ticks (~0.4 segundos) - muito rápido
         * Wizard: 40 ticks (~0.67 segundos) - médio
         */
        public static float GetDefaultCooldown(int towerType) {
            switch (towerType) {
                case 0 -> {
                    return 120.0F; // Cannon - lento mas poderoso
                }
                case 1 -> {
                    return 25.0F;  // Archer - ataques rápidos
                }
                case 2 -> {
                    return 40.0F;  // Wizard - cooldown médio
                }
                default -> {
                    return 0.0F;
                }
            }
        }
    }

    /**
     * Direções de movimento
     * Usadas por inimigos para navegar no mapa
     */
    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    /**
     * Constantes de inimigos
     * Cada tipo tem características diferentes de vida, velocidade e recompensa
     */
    public static class Enemies {
        public static final int ORC = 0;    // Média vida, média velocidade
        public static final int BAT = 1;    // Baixa vida, rápido
        public static final int KNIGHT = 2; // Alta vida, lento (boss)
        public static final int WOLF = 3;   // Média vida, muito rápido

        /**
         * Retorna a recompensa em ouro ao derrotar o inimigo
         * Knight dá a maior recompensa por ser o mais forte
         */
        public static int GetReward(int enemyType) {
            switch (enemyType) {
                case 0 -> {
                    return 5;  // Orc
                }
                case 1 -> {
                    return 5;  // Bat
                }
                case 2 -> {
                    return 25; // Knight - recompensa alta
                }
                case 3 -> {
                    return 10; // Wolf
                }
                default -> {
                    return 0;
                }
            }
        }

        /**
         * Retorna a velocidade de movimento do inimigo (pixels por tick)
         * Wolf é o mais rápido, Knight é o mais lento
         */
        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case 0 -> {
                    return 0.5F;  // Orc - velocidade média
                }
                case 1 -> {
                    return 0.65F; // Bat - rápido
                }
                case 2 -> {
                    return 0.3F;  // Knight - lento (tanque)
                }
                case 3 -> {
                    return 0.75F; // Wolf - mais rápido
                }
                default -> {
                    return 0.0F;
                }
            }
        }

        /**
         * Retorna a vida inicial do inimigo
         * Knight tem muito mais vida que os outros (tipo boss)
         */
        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case 0 -> {
                    return 100; // Orc - vida média
                }
                case 1 -> {
                    return 60;  // Bat - baixa vida
                }
                case 2 -> {
                    return 250; // Knight - muito resistente (boss)
                }
                case 3 -> {
                    return 85;  // Wolf - vida média-baixa
                }
                default -> {
                    return 0;
                }
            }
        }
    }

    /**
     * Tipos de tiles do mapa
     * WATER: decorativo
     * GRASS: onde torres podem ser construídas
     * ROAD: caminho por onde inimigos andam
     */
    public static class Tiles {
        public static final int WATER_TILE = 0; // Decorativo
        public static final int GRASS_TILE = 1; // Área construível
        public static final int ROAD_TILE = 2;  // Caminho dos inimigos
    }
}
