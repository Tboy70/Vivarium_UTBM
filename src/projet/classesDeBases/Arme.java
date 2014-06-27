package projet.classesDeBases;

/******************************/
/*** CLASSE DE L'OBJET ARME ***/
/******************************/

public class Arme extends Objet {

	private int degats;
	private int categorieArme;
	private int level;
	
	public Arme(String n, int deg, int cArme, int lvl) {
		super(n);
		this.degats = deg;
		this.categorieArme = cArme;
		this.level = lvl;
	}
	
	public Arme(Arme a){
		super(a);
		this.degats = a.degats;
		this.categorieArme = a.categorieArme;
		this.level = a.level;
	}
	
	public int getDegat(){
		return degats;
	}
	public int getCategorieArme(){
		return categorieArme;
	}
	public int getLevel(){
		return level;
	}
}
