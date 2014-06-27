package projet.classesDeBases;

/************************************************/
/*** CLASSE DU PERSONANGE BIENVEILLANT HUMAIN ***/
/************************************************/

public class Humain extends Bienveillant{

	public Humain(String n, Arme a, Lieu l){
		super(n);
		ptVie = ptVieMax = 100;
		typeArme = 0;
		arme = a;
		lieuPrefere = l;
		persoX = l.getX();
		persoY = l.getY();
	}
}
