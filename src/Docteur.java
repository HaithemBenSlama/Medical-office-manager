public class Docteur extends Personne{
    private String specialite;
    private String numeroRPPS;
    public Docteur(String cin, String nom, String prenom, String email, String tel, String adresse, String specialite,
            String numeroRPPS) {
        super(cin, nom, prenom, email, tel, adresse);
        this.specialite = specialite;
        this.numeroRPPS = numeroRPPS;
    }
    public Docteur() {
		// TODO Auto-generated constructor stub
	}
	public String getSpecialite() {
        return specialite;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public String getNumeroRPPS() {
        return numeroRPPS;
    }
    public void setNumeroRPPS(String numeroRPPS) {
        this.numeroRPPS = numeroRPPS;
    }

}
