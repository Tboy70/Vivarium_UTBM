package projet.classesDeBases;

/*********************************************/
/*** CLASSE DU PERSONANGE BIENVEILLANT ENT ***/
/*********************************************/

public class Ent extends Bienveillant{

	public Ent(String n, Arme a, Lieu l){
		super(n);
		ptVie = ptVieMax = 150;
		typeArme = 4;
		arme = a;
		lieuPrefere = l;
		persoX = l.getX();
		persoY = l.getY();
	}
}
