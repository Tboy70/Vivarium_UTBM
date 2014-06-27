package projet.classesDeBases;

/****************************************/
/*** CLASSE DE L'OBJET POTION DE MANA ***/
/****************************************/

public class PotionMana extends Objet{

	private int recupPM;
	
	public PotionMana(String n, int rec) {
		super(n);
		this.recupPM = rec;
	}
	public PotionMana(PotionMana p)
	{
		super(p);
		this.recupPM = p.recupPM;
	}
	
	public int getRecupPM()
	{
		return recupPM;
	}
	public void soignerMana(MageNoir m)
	{
		if(this.recupPM <= (m.getPtManaMax() - m.getPtMana()))
			m.setPtMana(m.getPtMana() + this.recupPM);
		else 
			m.setPtMana(m.getPtManaMax());
	}
}
