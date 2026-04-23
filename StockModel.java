public class StockModel {
    int stockInitial;
    int stockCourant;
    int capaciteMax;

    public StockModel(int stockInitial, int capaciteMax) {
        this.stockInitial = stockInitial;
        this.stockCourant = stockInitial;
        this.capaciteMax = capaciteMax;
    }

    public void ajouter(int quantite) throws Exception {
        if (quantite <= 0) {
            throw new Exception("la quantite doit etre > 0");
        }
        if (stockCourant + quantite > capaciteMax) {
            throw new Exception("depassement de capacite " + capaciteMax);
        }
        stockCourant += quantite;
    }

    public void retirer(int quantite) throws Exception {
        if (quantite <= 0) {
            throw new Exception("la quantite doit etre > 0");
        }
        if (stockCourant - quantite < 0) {
            throw new Exception("retrait impossible pas assez de stock");
        }
        stockCourant -= quantite;
    }

    public void reinitialiser() {
        stockCourant = stockInitial;
    }

    public int getStockCourant() {
        return stockCourant;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }
}
