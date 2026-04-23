import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockController {

    StockModel modele;
    StockView vue;

    public StockController(StockModel modele, StockView vue) {
        this.modele = modele;
        this.vue = vue;

        mettreAJourVue();

        this.vue.getBtnAjouter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterStock();
            }
        });

        this.vue.getBtnRetirer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirerStock();
            }
        });

        this.vue.getBtnReinitialiser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reinitialiserStock();
            }
        });
    }

    private void ajouterStock() {
        try {
            int quantite = parseQuantite();
            modele.ajouter(quantite);
            mettreAJourVue();
            vue.afficherMessage("ok ajout fait", false);
            vue.setQuantite("");
        } catch (NumberFormatException e) {
            vue.afficherMessage("entrer un nbr svp", true);
        } catch (Exception e) {
            vue.afficherMessage(e.getMessage(), true);
        }
    }

    private void retirerStock() {
        try {
            int quantite = parseQuantite();
            modele.retirer(quantite);
            mettreAJourVue();
            vue.afficherMessage("ok retrait", false);
            vue.setQuantite("");
        } catch (NumberFormatException e) {
            vue.afficherMessage("entrer un nbr svp", true);
        } catch (Exception e) {
            vue.afficherMessage(e.getMessage(), true);
        }
    }

    private void reinitialiserStock() {
        modele.reinitialiser();
        mettreAJourVue();
        vue.afficherMessage("stock a zero", false);
        vue.setQuantite("");
    }

    private int parseQuantite() throws NumberFormatException, Exception {
        String texte = vue.getQuantite();
        if (texte.isEmpty()) {
            throw new Exception("champ vide");
        }
        return Integer.parseInt(texte);
    }

    private void mettreAJourVue() {
        vue.setStockCourant(modele.getStockCourant());
    }
}
