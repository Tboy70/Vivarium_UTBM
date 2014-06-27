package projet.classesDeBases;

/******************************/
/*** CLASSE DU LIEU TAVERNE ***/
/******************************/

public class Taverne extends Lieu{
	
	public Taverne(String n, int x, int y) {
		super(n, x, y);
	}

	public void reposerPerso(Personnage p){
		if(p instanceof MageNoir || p instanceof Humain){
			super.reposerPerso(p);
		}
	}

}
