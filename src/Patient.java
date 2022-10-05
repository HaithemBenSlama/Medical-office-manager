public class Patient extends Personne{
    private String dateNaissance;
    private float poids;
    private String sexe;
    
    public Patient(String cin, String nom, String prenom, String email, String tel, String adresse,
            String dateNaissance, float poids,String sexe) {
        super(cin, nom, prenom, email, tel, adresse);
        this.dateNaissance = dateNaissance;
        this.poids = poids;
        this.sexe = sexe;
    }
    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public float getPoids() {
        return poids;
    }
    public void setPoids(float poids) {
        this.poids = poids;
    }
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
    
    
}
