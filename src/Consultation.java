public class Consultation {
    private String cinPatient;
    private int ordonnance;
    private String passed;
    private String remarques;
    private String date;
    private String heure;
    
    public Consultation(String cinPatient, int ordonnance, String passed, String remarques, String date, String heure) {
		this.cinPatient = cinPatient;
		this.ordonnance = ordonnance;
		this.passed = passed;
		this.remarques = remarques;
		this.date = date;
		this.heure = heure;
	}

	public String getCinPatient() {
		return cinPatient;
	}

	public void setCinPatient(String cinPatient) {
		this.cinPatient = cinPatient;
	}

	public int getOrdonnance() {
		return ordonnance;
	}

	public void setOrdonnance(int ordonnance) {
		this.ordonnance = ordonnance;
	}

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public String getRemarques() {
		return remarques;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}
    
    
    
}
