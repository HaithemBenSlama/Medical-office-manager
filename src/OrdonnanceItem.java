public class OrdonnanceItem {
    private Medicament medicament;
    private int qte;
    private int nbrFois;
    public OrdonnanceItem(Medicament medicament, int qte, int nbrFois) {
        this.medicament = medicament;
        this.qte = qte;
        this.nbrFois = nbrFois;
    }
    public Medicament getMedicament() {
        return medicament;
    }
    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
    public int getQte() {
        return qte;
    }
    public void setQte(int qte) {
        this.qte = qte;
    }
    public int getNbrFois() {
        return nbrFois;
    }
    public void setNbrFois(int nbrFois) {
        this.nbrFois = nbrFois;
    }
    
    
}
