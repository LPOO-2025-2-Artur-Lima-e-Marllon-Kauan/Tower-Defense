import EntidadesDoJogo.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
    }

    private final Player player;
    public Interface() {
        setTitle("Tower Defense");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // layout absoluto
        setResizable(false);


        // Fundo como JLabel
        ImageIcon fundoIcon = new ImageIcon(getClass().getResource("/imagens/combie.png"));
        JLabel fundoLabel = new JLabel(fundoIcon);
        fundoLabel.setBounds(0, 0, fundoIcon.getIconWidth(), fundoIcon.getIconHeight());
        add(fundoLabel);


        ImageIcon inimigoIcon = new ImageIcon(getClass().getResource("/imagens/imagem2.jpg"));
        JLabel inimigoLabel = new JLabel(inimigoIcon);
        inimigoLabel.setBounds(10, 10, inimigoIcon.getIconWidth(), inimigoIcon.getIconHeight());
        add(inimigoLabel);


        setVisible(true);

        player = new Player();
        player.load();
    }

}