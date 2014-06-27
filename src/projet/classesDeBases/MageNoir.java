package projet.classesDeBases;

/************************************************/
/*** CLASSE DU PERSONNAGE MALÉFIQUE MAGE NOIR ***/
/************************************************/

public class MageNoir extends Malefique{
	
	private int ptMana, ptManaMax;
	private Sort sort;
	
	public MageNoir(String n, Arme a, Lieu l, Sort s){
		super(n);
		ptVie = ptVieMax = 60;
		typeArme = 3;
		arme = a;
		lieuPrefere = l;
		persoX = l.getX();
		persoY = l.getY();
		ptMana = ptManaMax = 100;
		sort = s;
	}
	
	/***  METHODE POUR LANCER UN SORT ***/

	public void lancerSort(Personnage p){
		if(p.ptVie>0)
		{
			if(this.ptMana>= this.sort.getCoutMana())	// Si assez de point de mana -> sort
			{
				if(this.sort.getCoutMana() == 50)		// sort = vampirisme
				{
					if(this.ptVie + p.getPDV()/2 <= this.ptVieMax)
						this.ptVie += p.getPDV()/2;
					else 
						this.ptVie = ptVieMax;

					p.ptVie -= p.ptVie/2;
					this.ptMana = this.ptMana - this.sort.getCoutMana();
				}
				else if(this.sort.getCoutMana() == 70)		// sort = mort instant
				{
					p.ptVie = 0;
					this.ptMana = this.ptMana - this.sort.getCoutMana();
				}
				else 
				{
					p.ptVie -= this.sort.getDegatSort();
					this.ptMana = this.ptMana - this.sort.getCoutMana();
				}
			}
			else{	// Sinon -> Arme classique
				p.ptVie -= this.arme.getDegat();
			}
		}
	}
	
	/*** ACCESSEURS / MUTATEURS ***/

	public Sort getSort(){
		return this.sort;
	}
	public int getPtMana(){
		return this.ptMana;
	}
	public int getPtManaMax(){
		return this.ptManaMax;
	}
	public void setPtMana(int m){
		this.ptMana = m;
	}
	public void setPtManaMax(int m){
		this.ptManaMax = m;
	}
	
	/*** RAMASSER UN OBJET EN ÉTANT MAGE ***/
	
	public boolean ramasserObjet(Objet o)
	{
		if(o instanceof Arme)
		{
			Arme a = (Arme) o;
			if(a.getCategorieArme() == this.typeArme && a.getLevel() > this.arme.getLevel())
			{
				this.arme = a;
				if(a.getLevel() == 1)
					this.ptMana = this.ptManaMax = 120;	// bâton de l'érudit
				else 
					this.ptMana = this.ptManaMax = 150;	// baguette de sureau
				
				return true;
			}
		}
		else if(o instanceof PotionVie)
		{
			PotionVie pv = (PotionVie) o;
			if(this.ptVieMax > this.ptVie)			// si p a perdu de la vie
			{
				pv.soignerVie(this);				// soigner p
				return true;
			}
		} 
		else if(o instanceof Sort)
		{
			Sort s = (Sort) o;
			if(s.getCoutMana() > this.sort.getCoutMana())
			{
				this.sort = s;
				return true;
			}
		}
		else 
		{
			PotionMana pm = (PotionMana) o;
			if(this.ptManaMax > this.ptMana)
			{
				pm.soignerMana(this);
				return true;
			}
		}
		return false;
	}
}
