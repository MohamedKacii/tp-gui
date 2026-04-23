import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulateurCommande extends JFrame {

    private JTextField txtArticle;
    private JTextField txtPrix;
    private JTextField txtQuantite;
    private JCheckBox chkFidele;
    private JComboBox<String> comboLivraison;
    private JTextArea txtResultat;

    public SimulateurCommande() {
        setTitle("Mini simulateur de commande");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 520);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel titre = new JLabel("Simulateur de commande", SwingConstants.CENTER);
        titre.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
        add(titre, BorderLayout.NORTH);

        JPanel panneauCentral = new JPanel();
        panneauCentral.setLayout(new BoxLayout(panneauCentral, BoxLayout.Y_AXIS));

        // Zone du formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(5, 2, 8, 8));
        panneauFormulaire.setBorder(BorderFactory.createTitledBorder("Informations de la commande"));

        panneauFormulaire.add(new JLabel("Article :"));
        txtArticle = new JTextField();
        panneauFormulaire.add(txtArticle);

        panneauFormulaire.add(new JLabel("Prix unitaire :"));
        txtPrix = new JTextField();
        panneauFormulaire.add(txtPrix);

        panneauFormulaire.add(new JLabel("quantite :"));
        txtQuantite = new JTextField();
        panneauFormulaire.add(txtQuantite);

        panneauFormulaire.add(new JLabel("Options :"));
        chkFidele = new JCheckBox("client fidele (-10%)");
        panneauFormulaire.add(chkFidele);

        panneauFormulaire.add(new JLabel("Livraison :"));
        comboLivraison = new JComboBox<>(new String[] { "Standard", "Express" });
        panneauFormulaire.add(comboLivraison);

        panneauCentral.add(panneauFormulaire);
        panneauCentral.add(Box.createVerticalStrut(10));

        // Zone des boutons
        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnCalculer = new JButton("Calculer");
        JButton btnVider = new JButton("Vider");
        panneauBoutons.add(btnCalculer);
        panneauBoutons.add(btnVider);
        panneauCentral.add(panneauBoutons);

        panneauCentral.add(Box.createVerticalStrut(10));

        // Zone de resultat
        JPanel panneauResultat = new JPanel(new BorderLayout());
        panneauResultat.setBorder(BorderFactory.createTitledBorder("resultat final"));
        txtResultat = new JTextArea(6, 30);
        txtResultat.setEditable(false);
        txtResultat.setLineWrap(true);
        txtResultat.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtResultat);
        panneauResultat.add(scrollPane, BorderLayout.CENTER);

        panneauCentral.add(panneauResultat);

        add(panneauCentral, BorderLayout.CENTER);

        // evenements
        btnCalculer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculerMontant();
            }
        });

        btnVider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viderChamps();
            }
        });

        setVisible(true);
    }

    private void calculerMontant() {
        String article = txtArticle.getText().trim();
        String prixStr = txtPrix.getText().trim();
        String quantiteStr = txtQuantite.getText().trim();

        // verif des champs vides
        if (article.isEmpty() || prixStr.isEmpty() || quantiteStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "remplir tout svp",
                    "erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double prixUnitaire = Double.parseDouble(prixStr);
            int quantite = Integer.parseInt(quantiteStr);

            if (prixUnitaire < 0) {
                JOptionPane.showMessageDialog(this, "prix negatif interdit",
                        "erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (quantite <= 0) {
                JOptionPane.showMessageDialog(this, "quantite doit etre > 0",
                        "erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Appliquer les calculs
            double montantBase = prixUnitaire * quantite;
            double remise = 0;

            if (chkFidele.isSelected()) {
                remise = montantBase * 0.10;
            }

            double fraisLivraison = 0;
            String livraisonStr = (String) comboLivraison.getSelectedItem();
            if ("Express".equals(livraisonStr)) {
                fraisLivraison = 300;
            }

            double montantTotal = montantBase - remise + fraisLivraison;

            // Affichage du resultat final
            StringBuilder sb = new StringBuilder();
            sb.append("recap commande :\n\n");
            sb.append(" - Article: ").append(article).append("\n");
            sb.append(" - Montant de base : ").append(String.format("%.2f", montantBase)).append(" DA\n");
            if (remise > 0) {
                sb.append(" - Remise (10%) : -").append(String.format("%.2f", remise)).append(" DA\n");
            }
            sb.append(" - Frais de livraison (").append(livraisonStr).append(") : +")
                    .append(String.format("%.2f", fraisLivraison)).append(" DA\n");
            sb.append("--------------------------------------------------\n");
            sb.append("Montant Total : ").append(String.format("%.2f", montantTotal)).append(" DA");

            txtResultat.setText(sb.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "format incorrect.",
                    "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viderChamps() {
        txtArticle.setText("");
        txtPrix.setText("");
        txtQuantite.setText("");
        chkFidele.setSelected(false);
        comboLivraison.setSelectedIndex(0);
        txtResultat.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimulateurCommande();
        });
    }
}
