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
        public static final int POISON = 3;  // Torre Cospe Veneno

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
                case 3 -> {
                    return 7.0F; // Projétil de veneno - rápido
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
        // Nova torre: Cospe Veneno
        public static final int POISON = 3;
        public static final int MAX_LEVEL = 3;

        /**
         * Retorna o custo em ouro para construir a torre
         * Cannon: 20, Archer: 15, Wizard: 25, Cospe Veneno: 30
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
                case 3 -> {
                    return 30; // Cospe Veneno
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
                case 3 -> {
                    return "Cospe Veneno";
                }
                default -> {
                    return "";
                }
            }
        }

        /**
         * Retorna o dano inicial da torre
         * Cannon: 15 (alto), Archer: 5 (baixo), Wizard: 0 (não causa dano),
         * Cospe Veneno: 2 (baixo, mas aplica dano contínuo por veneno)
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
                case 3 -> {
                    return 2;  // Cospe Veneno - dano base baixo + dano contínuo
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
                case 3 -> {
                    return 110.0F; // Cospe Veneno - alcance levemente maior
                }
                default -> {
                    return 0.0F;
                }
            }
        }

        /**
         * Retorna o custo do upgrade baseado no nível atual da torre.
         * Nível 2 -> 40% do preço base
         * Nível 3 -> 60% do preço base
         * (Se houvesse nível 4, custaria 100%, mas o limite de nível é 3)
         */
        public static int GetUpgradeCost(int towerType, int currentLevel) {
            int baseCost = GetTowerCost(towerType);
            if (currentLevel >= MAX_LEVEL) {
                return 0;
            }

            float percent;
            if (currentLevel == 1) {
                percent = 0.4F;
            } else if (currentLevel == 2) {
                percent = 0.6F;
            } else {
                percent = 1.0F;
            }

            return Math.round(baseCost * percent);
        }

        /**
         * Retorna o cooldown padrão da torre (em ticks)
         * Cannon: 120 ticks (2 segundos) - muito lento
         * Archer: 25 ticks (~0.4 segundos) - muito rápido
         * Wizard: 40 ticks (~0.67 segundos) - médio
         * Cospe Veneno: 90 ticks (~1.5 segundos)
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
                case 3 -> {
                    return 90.0F;  // Cospe Veneno - atira a cada 1,5s
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
                    return 0.5F;  // Orc - velocidade média (permanece "normal")
                }
                case 1 -> {
                    return 0.8F; // Bat - mais rápido
                }
                case 2 -> {
                    return 0.2F;  // Knight - ainda mais lento (tanque)
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
         * Velocidade escalada por wave: aumenta 5% a cada wave.
         */
        public static float GetSpeedScaled(int enemyType, int waveIndex) {
            float baseSpeed = GetSpeed(enemyType);
            if (waveIndex <= 0) {
                return baseSpeed;
            }
            float multiplier = 1.0F + (waveIndex * 0.05F);
            return baseSpeed * multiplier;
        }

        /**
         * Retorna a vida inicial do inimigo
         * Knight tem muito mais vida que os outros (tipo boss)
         */
        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case 0 -> {
                    return 100; // Orc - vida média (permanece "normal")
                }
                case 1 -> {
                    return 50;  // Bat - menos vida
                }
                case 2 -> {
                    return 350; // Knight - ainda mais resistente
                }
                case 3 -> {
                    return 85;  // Wolf - vida média-baixa
                }
                default -> {
                    return 0;
                }
            }
        }

        /**
         * Vida escalada por wave: aumenta 15% a cada wave.
         */
        public static int GetScaledHealth(int enemyType, int waveIndex) {
            int base = GetStartHealth(enemyType);
            if (waveIndex <= 0) {
                return base;
            }
            float multiplier = 1.0F + (waveIndex * 0.15F);
            return Math.round(base * multiplier);
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
