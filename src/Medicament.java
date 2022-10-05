public class Medicament {
    private String ref;
    private String nom;
    private String famille;
	public Medicament(String ref, String nom, String famille) {
		this.ref = ref;
		this.nom = nom;
		this.famille = famille;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
    
    
}
