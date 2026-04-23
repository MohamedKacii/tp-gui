import javax.swing.SwingUtilities;

public class MainStockApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockModel modele = new StockModel(0, 100);
            StockView vue = new StockView();
            new StockController(modele, vue);
            vue.setVisible(true);
        });
    }
}
