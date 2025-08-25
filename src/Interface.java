import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame {

    public Interface() {
        setTitle("Minha Interface");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // layout absoluto
        setResizable(false);
        // Fundo como JLabel
        ImageIcon fundoIcon = new ImageIcon(getClass().getResource("/imagens/imagem2.jpg"));
        JLabel fundoLabel = new JLabel(fundoIcon);
        fundoLabel.setBounds(0, 0, fundoIcon.getIconWidth(), fundoIcon.getIconHeight());
        add(fundoLabel);


        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interface());
    }
}
