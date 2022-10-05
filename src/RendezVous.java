public class RendezVous {
    private Patient patient;
    private String dateRendezVous;
    private String heure;
    public RendezVous(Patient patient, String dateRendezVous, String heure) {
        this.patient = patient;
        this.dateRendezVous = dateRendezVous;
        this.heure = heure;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getDateRendezVous() {
        return dateRendezVous;
    }
    public void setDateRendezVous(String dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }
    public String getHeure() {
        return heure;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }

    
}
