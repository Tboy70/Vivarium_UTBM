package projet.classesDeBases;

/***************************************/
/*** CLASSE DE L'OBJET POTION DE VIE ***/
/***************************************/

public class PotionVie extends Objet{
	
	private int recupPV;

	public PotionVie(String n, int rec) {
		super(n);
		this.recupPV = rec;
	}
	public PotionVie(PotionVie p){
		super(p);
		this.recupPV = p.recupPV;
	}

	public void soignerVie(Personnage p)
	{
		if(this.recupPV <= (p.getPDVmax() - p.getPDV()))		
		{
			p.setPDV(p.getPDV() + this.recupPV);
		}
		else 
		{
			p.setPDV(p.ptVieMax);
		}
	}
	public int getRecupPV(){
		return recupPV;
	}
}
