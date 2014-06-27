package projet.classesDeBases;

/********************************************/
/*** CLASSE DU PERSONNAGE MALÉFIQUE TROLL ***/
/********************************************/

public class Troll extends Malefique{
	
	public Troll(String n, Arme a, Lieu l){
		super(n);
		ptVie = ptVieMax = 150;
		typeArme = 2;
		arme = a;
		lieuPrefere = l;
		persoX = l.getX();
		persoY = l.getY();
	}
}
