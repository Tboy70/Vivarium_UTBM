package projet.classesDeBases;

/*** CLASSE DU LIEU GROTTE ***/

public class Grotte extends Lieu{
	
	public Grotte(String n, int x, int y) {
		super(n, x, y);
	}

	public void reposerPerso(Personnage p){
		if(p instanceof Troll || p instanceof Orque)
			super.reposerPerso(p);
	}
}
