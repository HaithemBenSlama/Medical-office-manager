public class Secretaire extends Personne{
    private String dateNaissance;
    private String rppsDocteur;
    private String sexe;

    public Secretaire() { super(); }

	

    public Secretaire(String cin, String nom, String prenom, String email, String tel, String adresse, String dateNaissance, String rppsDocteur, String sexe) {
		super(cin, nom, prenom, email, tel, adresse);
		this.dateNaissance = dateNaissance;
		this.rppsDocteur = rppsDocteur;
		this.sexe = sexe;
	}



	public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

	public String getRppsDocteur() {
		return rppsDocteur;
	}

	public void setRppsDocteur(String rppsDocteur) {
		this.rppsDocteur = rppsDocteur;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
    
    
    
    
}
