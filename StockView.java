import javax.swing.*;
import java.awt.*;

public class StockView extends JFrame {

    JLabel lblStockCourant;
    JTextField txtQuantite;
    JButton btnAjouter;
    JButton btnRetirer;
    JButton btnReinitialiser;
    JLabel lblMessage;

    public StockView() {
        setTitle("Gestion de stock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titre = new JLabel("gestion de stock ", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        titre.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(titre, BorderLayout.NORTH);

        JPanel panneauCentral = new JPanel();
        panneauCentral.setLayout(new BoxLayout(panneauCentral, BoxLayout.Y_AXIS));

        JPanel panneauInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblStockCourant = new JLabel("stock courant: 0");
        lblStockCourant.setFont(new Font("Arial", Font.BOLD, 14));
        panneauInfos.add(lblStockCourant);
        panneauCentral.add(panneauInfos);

        JPanel panneauSaisie = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panneauSaisie.add(new JLabel("quantite:"));
        txtQuantite = new JTextField(10);
        panneauSaisie.add(txtQuantite);
        panneauCentral.add(panneauSaisie);

        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAjouter = new JButton("Ajouter");
        btnRetirer = new JButton("Retirer");
        btnReinitialiser = new JButton("Reinitialiser");
        panneauBoutons.add(btnAjouter);
        panneauBoutons.add(btnRetirer);
        panneauBoutons.add(btnReinitialiser);
        panneauCentral.add(panneauBoutons);

        add(panneauCentral, BorderLayout.CENTER);

        JPanel panneauBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblMessage = new JLabel(" ");
        lblMessage.setForeground(Color.BLUE);
        panneauBas.add(lblMessage);
        add(panneauBas, BorderLayout.SOUTH);
    }

    public void setStockCourant(int stock) {
        lblStockCourant.setText("stock: " + stock);
    }

    public String getQuantite() {
        return txtQuantite.getText().trim();
    }

    public void setQuantite(String text) {
        txtQuantite.setText(text);
    }

    public void afficherMessage(String msg, boolean isErr) {
        lblMessage.setText(msg);
        if (isErr) {
            lblMessage.setForeground(Color.RED);
        } else {
            lblMessage.setForeground(Color.GREEN);
        }
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnRetirer() {
        return btnRetirer;
    }

    public JButton getBtnReinitialiser() {
        return btnReinitialiser;
    }
}
