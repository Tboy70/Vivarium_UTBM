package projet.classesDeBases;

/**********************************************/
/*** CLASSE DU PERSONANGE BIENVEILLANT ELFE ***/
/**********************************************/

public class Elfe extends Bienveillant{

	public Elfe(String n, Arme a, Lieu l) {
		super(n);
		ptVie = ptVieMax = 120;
		typeArme = 1;
		arme = a;
		lieuPrefere = l;
		persoX = l.getX();
		persoY = l.getY();
	}

}
