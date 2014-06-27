package projet.classesDeBases;

/********************************************/
/*** CLASSE DU PERSONNAGE MALÉFIQUE ORQUE ***/
/********************************************/

public class Orque extends Malefique{
	
	public Orque(String n, Arme a, Lieu l){
		super(n);
		ptVie = ptVieMax = 100;
		typeArme = 0;
		arme = a;
		lieuPrefere = l;
		persoX = l.getX();
		persoY = l.getY();
	}

}
