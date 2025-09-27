import EntidadesDoJogo.Inimigo;
import EntidadesDoJogo.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame {
    private JLabel lifeLabel, waveLabel;
    private int basey = 700;
    private List<Inimigo> inimigos;
    private Player player;
    private int currentWave = 1;

    public Interface(Player player) {
        this.player = player;
        // Cria a janela:
        setTitle("Tower Defense");
        setSize(1200, 759);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Fundo
        ImageIcon fundoIcon = new ImageIcon(getClass().getResource("/imagens/imagem2.jpg"));
        JLabel fundoLabel = new JLabel(fundoIcon);
        fundoLabel.setBounds(0, 0, fundoIcon.getIconWidth(), fundoIcon.getIconHeight());
        add(fundoLabel);
        setComponentZOrder(fundoLabel, getContentPane().getComponentCount() - 1); // sempre atrás

        // HUD de vida
        lifeLabel = new JLabel("Vida: " + player.life);
        lifeLabel.setBounds(20, 20, 200, 30);
        lifeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lifeLabel.setForeground(Color.BLACK);
        add(lifeLabel);
        setComponentZOrder(lifeLabel, 0);

        // HUD de wave
        waveLabel = new JLabel("Wave: " + currentWave);
        waveLabel.setBounds(20, 50, 200, 30);
        waveLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(waveLabel);
        setComponentZOrder(waveLabel, 0);

        // Lista de Inimigos
        inimigos = new ArrayList<>();
        Inimigo.Esqueleto esqueleto = new Inimigo.Esqueleto();
        Inimigo.Goblin goblin = new Inimigo.Goblin();
        inimigos.add(esqueleto);
        inimigos.add(goblin);

        for (Inimigo i : inimigos) {
            add(i.sprite); // adiciona o JLabel do inimigo à janela
            setComponentZOrder(i.sprite, 0); // na frente do fundo
        }

        setVisible(true);

        // Timer
        Timer timer = new Timer(50, e -> {
            for (Inimigo i : inimigos) {
                i.updatePosition();
                if (i.posicaoy >= basey) {
                    // Atualiza vida do player
                    player.TakeDamage();
                    lifeLabel.setText("Vida: " + player.life);

                    // Atualiza wave
                    currentWave++;
                    waveLabel.setText("Wave: " + currentWave);

                    // Renasce inimigo individualmente
                    i.resetPosition();

                    // Verifica fim de jogo
                    if (player.life <= 0) {
                        ((Timer) e.getSource()).stop();
                        JOptionPane.showMessageDialog(this,
                                "Fim de Jogo!",
                                "Game Over",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                }
            }
        });
        timer.start();
    }


}
