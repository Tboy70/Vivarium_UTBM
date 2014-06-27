package projet.classesDeBases;

import java.util.ArrayList;

/*************************************/
/** CLASSE GÉNÉRALE D'UN PERSONNAGE **/
/*************************************/

public class Personnage {

	protected String nomPerso;
	protected int ptVie, ptVieMax, typeArme;
	protected Arme arme;	// types d'armes : 0=épée, 1=arc, 2=massue, 3=bâton, 4=rocher
	protected Lieu lieuPrefere;
	protected float persoX, persoY;
	protected boolean[] move;	// Gauche, Droite, Haut, Bas
	protected boolean inCombat;
	
	protected ArrayList <Personnage> tabAllies;	// Tableau des alliés potentiels du personnage
	protected boolean leader;
	
	/*** CONSTRUCTEUR ***/

	public Personnage(String n)
	{
		nomPerso = n;
		move = new boolean[4];
		for (int i=0; i<4; i++){ 
			move[i] = false;
		}
		inCombat = false;
		tabAllies = new ArrayList<Personnage>();
		leader = false;
	}
	
	/*** ACCESSEURS / MUTATEURS ***/

	public float getX(){return this.persoX;}
	public float getY(){return this.persoY;}
	public String getNom(){return this.nomPerso;}
	public void setPDV(int pv){this.ptVie = pv;}
	public int getPDV(){return this.ptVie;}
	public int getPDVmax(){return this.ptVieMax;}
	
	public boolean isBien(){return (this instanceof Bienveillant);}
	public boolean isMal(){return (this instanceof Malefique);}
	public boolean isInCombat(){return inCombat;}
	public void setCombat(boolean b){this.inCombat = b;}
	public void setLeader(boolean b){this.leader = b;}
	
	/*** ATTAQUE D'UN PERSONNAGE ***/

	public void attaquer(Personnage p)
	{
		if(this instanceof MageNoir)
		{
			MageNoir m = (MageNoir)this;
			m.lancerSort(p);
		}
		else
		{
			if(p.ptVie>0)
				p.ptVie -= this.arme.getDegat();
			else 
				;
		}
	}

	/*** CHOIX D'UNE DIRECTION ALÉATOIRE ***/

	public void choixDirection(boolean[] m){
		double r = Math.random() * 4;
		if(r>3)
			m[0] = true;	//gauche
		else if(r>2)
			m[1] = true;	//droite
		else if(r>1)
			m[2] = true; 	// haut	
		else
			m[3] = true;	// bas
	}

	/*** UN PERSONNAGE SE DÉPLACE ***/

	public void seDeplacer(Map map, int TileSize){
		if(this.leader || this.tabAllies.size()==0)	// Si le personnage est le leader de son groupe ou qu'il est seul
		{
			if(!move[0] && !move[1] && !move[2] && !move[3]) // s'il n'a pas encore choisi de direction
				choixDirection(move);
			else
			{
				int coordMapX = (int) Math.floor(this.persoX/TileSize);
				int coordMapY = (int) Math.floor(this.persoY/TileSize);
				if(move[0]){
					if(!map.isBlocked(coordMapX - 1, coordMapY)){	//si case gauche libre, deplacer à gauche
						this.persoX -= 1;
					}
					else{
						move[0] = false;
						return;
					}
				}
				if(move[1]){
					if(!map.isBlocked(coordMapX + 1, coordMapY)){	//si case droite libre, deplacer à droite
						this.persoX += 1; 
					}
					else{
						move[1] = false;
						return;
					}
				}
				if(move[2]){
					if(!map.isBlocked(coordMapX, coordMapY -1)){	//si case haut libre, deplacer en haut
						this.persoY -= 1; 
					}
					else{
						move[2] = false;
						return;
					}
				}
				if(move[3]){
					if(!map.isBlocked(coordMapX, coordMapY+1)){ 	//si case bas libre, deplacer en bas
						this.persoY += 1;
					}
					else {
						move[3] = false;
						return;
					}
				}

			}
		} 
		else // Si il fait partie d'un groupe dont il n'est pas le leader, il suit son leader.
		{
			for(int z=0; z<this.tabAllies.size(); z++)
			{
				if(this.tabAllies.get(z).leader)
				{
					this.persoX = this.tabAllies.get(z).persoX;
					this.persoY = this.tabAllies.get(z).persoY;
				}
			}
		}
	}
	
	/*** UN PERSONNAGE RAMASSE UN OBJET ***/

	public boolean ramasserObjet(Objet o)
	{
		if(o instanceof Arme)
		{
			Arme a = (Arme) o;
			if(a.getCategorieArme() == this.typeArme && a.getLevel() > this.arme.getLevel())
		//la catégorie de l'arme convient au personnage et le niveau de l'arme est > a celui de l'arme du perso
			{
				this.arme = a;
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
		else {}
			return false;
		
	}

	/*** MECANISME D'ALLIANCE ENTRE PERSONNAGES ***/

	public void allier(Personnage p)
	{	
		if(this.tabAllies.size() > 2 || p.tabAllies.size() > 2 
			|| (this.tabAllies.size() >0 && p.tabAllies.size() >0))
		{
			return;
		}
		else
		{
			// les perso peuvent s'allier si : 
					//- aucun des deux n'a d'alliance, 
					//- this a maxi 2 alliés et p en a 0
					//- this a 0 allié et p en a maxi 2
			
			if(this.tabAllies.size() == 0 && p.tabAllies.size() == 0)
				this.leader = true; // si aucun des deux n'a d'alliance c'est le perso courant qui devient leader
			
			for(int i=0; i<p.tabAllies.size(); i++) 	// si p a déjà un allié, il faut l'ajouter aussi
			{
				this.tabAllies.add(p.tabAllies.get(i));
			}
			this.tabAllies.add(p);	
			for(int j=0; j<this.tabAllies.size(); j++) // il faut que les alliés soient alliés entre eux
			{
				for(int k=0; k<this.tabAllies.size(); k++)
				{
					if(tabAllies.get(j) != (tabAllies.get(k)) && !tabAllies.get(j).tabAllies.contains(tabAllies.get(k))){	// pas de doublons
						tabAllies.get(j).tabAllies.add(tabAllies.get(k));
					}
				}
			}
			
			p.tabAllies.add(this);
		}
	}
}
