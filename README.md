## Tower Defense em Java - Poli - UPE
### 🎮 Sobre o Projeto

Este projeto é um jogo do gênero Tower Defense, desenvolvido inteiramente em Java como parte da disciplina de Linguagem de Programação Orientada a Objetos (LPOO) da Escola Politécnica de Pernambuco.

O objetivo do jogo é impedir que inimigos cheguem ao final do caminho, posicionando torres estratégicas que atacam automaticamente.

### Funcionalidades

* Inimigos com diferentes níveis de dificuldade;

*  Torres com diferentes tipos de ataque;

*  Sistema de economia (para poder colocar outras torres e balanceamento do jogo);

* Aumento progressivo da dificuldade em waves.


### Tecnologias Utilizadas

☕ Java

🎨 JPanel / JFrame (usado para interface gráfica)

🔗 Paradigma Orientado a Objetos (herança, polimorfismo, encapsulamento)

### Estrutura do Projeto
```
src/
 ┣━ enemies/                     # Classes dos inimigos
 ┃   ┣━ Bat.java
 ┃   ┣━ Cavaleiro.java
 ┃   ┣━ Enemy.java               # Classe base dos inimigos
 ┃   ┣━ Lobo.java
 ┃   ┗━ Orc.java

 ┣━ eventos/
 ┃   ┗━ Wave.java                # Representa uma wave de inimigos

 ┣━ helpz/                       # Classes auxiliares do projeto
 ┃   ┣━ Constants.java
 ┃   ┣━ ImgFix.java
 ┃   ┣━ LoadSave.java            # Carrega e salva sprites/dados
 ┃   ┗━ Utilz.java

 ┣━ inputs/
 ┃   ┣━ KeyboardListener.java
 ┃   ┗━ MyMouseListener.java

 ┣━ main/
 ┃   ┣━ Game.java
 ┃   ┣━ GameScreen.java
 ┃   ┣━ GameStates.java
 ┃   ┗━ Render.java

 ┣━ managers/                    # Gerenciadores do jogo
 ┃   ┣━ EnemyManager.java
 ┃   ┣━ ProjectileManager.java
 ┃   ┣━ TileManager.java
 ┃   ┣━ TowerManager.java
 ┃   ┗━ WaveManager.java

 ┣━ objects/                     # Entidades principais do mapa
 ┃   ┣━ PathPoint.java
 ┃   ┣━ Projectile.java
 ┃   ┣━ Tile.java
 ┃   ┗━ Tower.java

res/
 ┣━ new_level.txt                # Dados do mapa
 ┣━ old_spriteatlas.png
 ┣━ sprite.png
 ┣━ spriteatlas.png

 ┣━ scenes/                      # Telas do jogo
 ┃   ┣━ Editing.java
 ┃   ┣━ GameOver.java
 ┃   ┣━ GameScene.java
 ┃   ┣━ Menu.java
 ┃   ┣━ Playing.java
 ┃   ┣━ SceneMethods.java
 ┃   ┗━ Settings.java

 ┗━ ui/                           # Elementos da interface
     ┣━ ActionBar.java
     ┣━ Bar.java
     ┣━ MyButton.java
     ┗━ Toolbar.java


```

### Autores
* Artur Lima Cardoso
* Marllon Kauan
