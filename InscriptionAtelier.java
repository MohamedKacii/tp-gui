import javax.swing.*;
import java.awt.*;

public class InscriptionAtelier extends JFrame {

  public InscriptionAtelier() {
    setTitle("Inscription à un atelier");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 600);
    setLocationRelativeTo(null);

    setLayout(new BorderLayout(10, 10));

    JLabel titre = new JLabel("Inscription à un atelier universitaire", SwingConstants.CENTER);
    titre.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
    add(titre, BorderLayout.NORTH);

    JPanel panneauCentral = new JPanel();
    panneauCentral.setLayout(new BoxLayout(panneauCentral, BoxLayout.Y_AXIS));

    JPanel panneauFormulaire = new JPanel(new GridLayout(6, 2, 8, 8));
    panneauFormulaire.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));

    panneauFormulaire.add(new JLabel("Nom :"));
    panneauFormulaire.add(new JTextField());

    panneauFormulaire.add(new JLabel("Prénom :"));
    panneauFormulaire.add(new JTextField());

    panneauFormulaire.add(new JLabel("Email :"));
    panneauFormulaire.add(new JTextField());

    panneauFormulaire.add(new JLabel("Niveau :"));
    JComboBox<String> niveauCombo = new JComboBox<>(new String[] { "L1", "L2", "L3", "M1", "M2" });
    panneauFormulaire.add(niveauCombo);

    panneauFormulaire.add(new JLabel("Spécialité :"));
    panneauFormulaire.add(new JTextField());

    panneauCentral.add(panneauFormulaire);

    JPanel panneauModules = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    panneauModules.setBorder(BorderFactory.createTitledBorder("Modules souhaités"));
    panneauModules.add(new JCheckBox("Algorithmique"));
    panneauModules.add(new JCheckBox("Réseaux"));
    panneauModules.add(new JCheckBox("Base de données"));
    panneauModules.add(new JCheckBox("Sécurité informatique"));
    panneauCentral.add(panneauModules);
    panneauCentral.add(Box.createVerticalStrut(10));

    JPanel panneauCommentaire = new JPanel(new BorderLayout());
    panneauCommentaire.setBorder(BorderFactory.createTitledBorder("Commentaire"));
    JTextArea commentaireArea = new JTextArea(4, 30);
    commentaireArea.setLineWrap(true);
    commentaireArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(commentaireArea);
    panneauCommentaire.add(scrollPane, BorderLayout.CENTER);

    panneauCentral.add(panneauCommentaire);
    add(panneauCentral, BorderLayout.CENTER);

    JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    JButton btnEnregistrer = new JButton("Enregistrer");
    JButton btnAnnuler = new JButton("Annuler");
    panneauBoutons.add(btnEnregistrer);
    panneauBoutons.add(btnAnnuler);
    add(panneauBoutons, BorderLayout.SOUTH);

    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new InscriptionAtelier();
    });
  }
}
