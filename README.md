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
 ┣━ EntidadesDoJogo/         # Classes que representam inimigos, torres, etc
 ┃   ┣━ Inimigo.java         # Classe base para os inimigos
 ┃   ┣━ Player.java          # Classe para as ações do player
   

 ┣━ Map/                     # Classes relacionadas ao mapa do jogo
 ┃   ┣━ Mapa.java             
 ┃   ┣━ Tile.java            
 ┃   ┗━ Position.java        

 ┣━ imagens/                 # Imagens usadas no jogo

 ┣━ Fase.java                # Geração das fases

 ┣━ GameLoop.java            # Loop de gameplay

 ┣━ InicioDeGame.java        

 ┣━ Interface.java           # Elementos que compõe a interface do jogo
 
 ┣━ Main.java                # Classe principal que inicia o jogo

```

### Autores
* Artur Lima Cardoso
* Marllon Kauan
