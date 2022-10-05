import java.util.ArrayList;

public class Cabinet {
	private Personne responsable;
	private ArrayList<Patient> patients;
	private ArrayList<RendezVous> rendezvous;
	private ArrayList<Consultation> consultations;
	
	
	public Cabinet() {
		patients = new ArrayList<Patient>();
		rendezvous = new ArrayList<RendezVous>();
		consultations = new ArrayList<Consultation>();
	}


	public Personne getResponsable() {
		return responsable;
	}


	public void setResponsable(Personne responsable) {
		this.responsable = responsable;
	}


	public ArrayList<Patient> getPatients() {
		return patients;
	}



	public ArrayList<RendezVous> getRendezvous() {
		return rendezvous;
	}



	public ArrayList<Consultation> getConsultations() {
		return consultations;
	}


	
	
	public void ajouterPatient(Patient p) {
		patients.add(p);
	}
	
	public void ajouterRendezVous(RendezVous r) {
		rendezvous.add(r);
	}
	
	public void ajouterConsultation(Consultation c) {
		consultations.add(c);
	}
	
	public Patient recherchePatient(String cin) {
		for(Patient i : patients) {
			if(i.getCin().equals(cin)) {
				return i;
			}
		}
		return null;
	}
	
	public void supprimerPatient(String cin) {
		int pos = 0;
		for(Patient p : patients) {
			if(p.getCin().equals(cin)) {
				break;
			}
			pos++;
		}
		
		patients.remove(pos);
	}
	
	public RendezVous rechercheRendezvous(String cinpat, String date,String heure) {
		for(RendezVous r:rendezvous) {
			if(r.getPatient().getCin().equals(cinpat) && r.getDateRendezVous().equals(date) && r.getHeure().equals(heure)) {
				return r;
			}
		}
		return null;
	}
	
	public void supprimerRendezvous(String cin, String date, String heure) {
		int pos = 0;
		for(RendezVous r:rendezvous) {
			if(r.getPatient().getCin().equals(cin) && r.getDateRendezVous().equals(date) && r.getHeure().equals(heure)) {
				break;
			}
			pos++;
		}
		rendezvous.remove(pos);
	}
	
}
