package projet.classesDeBases;

/****************************/
/*** CLASSE DU LIEU FORET ***/
/****************************/

public class Foret extends Lieu{

	public Foret(String n, int x, int y) {
		super(n, x, y);
	}

	public void reposerPerso(Personnage p){
		if(p instanceof Ent || p instanceof Elfe)
			super.reposerPerso(p);
	}
}
