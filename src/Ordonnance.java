import java.util.ArrayList;

public class Ordonnance  {
    private ArrayList<OrdonnanceItem> items;

    public void ajouterMedicament(OrdonnanceItem o){
        items.add(o);
    }

}
