import EntidadesDoJogo.Inimigo;
import EntidadesDoJogo.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame implements ActionListener {
    private JLabel lifeLabel;
    public java.util.List<Inimigo> inimigos;

    @Override
    public void actionPerformed(ActionEvent e) {
        //player.update();
    }

    //public final Player player;
    public Interface(Player player) {
        Inimigo.Esqueleto esqueleto = new Inimigo.Esqueleto();
        Inimigo.Goblin goblin = new Inimigo.Goblin();
        int basey = 700;
        this.inimigos = new ArrayList<>();


        setTitle("Tower Defense");
        setSize(1200, 759);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // layout absoluto
        setResizable(false);


        // Fundo como JLabel
        ImageIcon fundoIcon = new ImageIcon(getClass().getResource("/imagens/imagem2.jpg"));
        JLabel fundoLabel = new JLabel(fundoIcon);
        fundoLabel.setBounds(0, 0, fundoIcon.getIconWidth(), fundoIcon.getIconHeight());



       /* ImageIcon inimigoIcon = new ImageIcon(getClass().getResource("/imagens/combie.jpg"));
        JLabel inimigoLabel = new JLabel(inimigoIcon);
        inimigoLabel.setBounds(10, 10, inimigoIcon.getIconWidth(), inimigoIcon.getIconHeight());

*/


        //player.load();

        lifeLabel = new JLabel("Vida:" + String.valueOf(player.life));
        lifeLabel.setBounds(20, 20, 200, 30); // posição no topo
        lifeLabel.setFont(lifeLabel.getFont().deriveFont(20f)); // deixa maior
        lifeLabel.setForeground(Color.BLACK); // cor preta

        add(lifeLabel);      // HUD sempre adicionado **antes** do fundo/inimigo
        add(fundoLabel);   // fundo/inimigo por trás
        setComponentZOrder(lifeLabel, 0);      // vida na frente
        setComponentZOrder(fundoLabel, 1);   // fundo atrás

        setVisible(true);


        //player = new Player();
       /* Timer timer = new Timer(350, e -> { // atualiza a cada 1 segundo
            if (Player.isAlive(player.life)) {
                player.TakeDamage();           // diminui a vida
                lifeLabel.setText("Vida: " + player.life); // atualiza o HUD
            } else {
                ((Timer) e.getSource()).stop(); // para o loop
                JOptionPane.showMessageDialog(this, "Game Over!");
            }


        });
        timer.start(); // inicia automático*/



        esqueleto.sprite = new JLabel(esqueleto.iconeRedimensionado);
        esqueleto.sprite.setBounds(esqueleto.posicaox, esqueleto.posicaoy, esqueleto.iconeRedimensionado.getIconWidth(), esqueleto.iconeRedimensionado.getIconHeight());
        add(esqueleto.sprite);
        setComponentZOrder(esqueleto.sprite, 0); // na frente do fundo


        goblin.sprite = new JLabel(goblin.iconeRedimensionado);
        goblin.sprite.setBounds(goblin.posicaox, goblin.posicaoy, goblin.iconeRedimensionado.getIconWidth(), goblin.iconeRedimensionado.getIconHeight());
        add(goblin.sprite);
        setComponentZOrder(goblin.sprite, 0);

        inimigos.add(goblin);
        inimigos.add(esqueleto);


        Timer enemyTimer = new Timer(50, e -> {
            for (Inimigo inimigo : inimigos) {
                inimigo.updatePosition();
                if (inimigo.posicaoy >= basey) {
                    player.TakeDamage();
                    lifeLabel.setText("Vida: " + player.life);
                }
            }
        });
        enemyTimer.start();
    }
}