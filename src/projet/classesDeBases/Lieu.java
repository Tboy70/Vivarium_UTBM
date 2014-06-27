package projet.classesDeBases;

/*********************************/
/*** CLASSE GÉNÉRALE DES LIEUX ***/
/*********************************/

public class Lieu {
	
	protected String nomLieu;
	protected float LieuX;
	protected float LieuY;
	
	public Lieu(String n, int x, int y){
		this.nomLieu = n;
		this.LieuX = x;
		this.LieuY = y;
	}
	
	public void reposerPerso(Personnage p){
		if(p.getPDV() < p.getPDVmax())		
		{
			p.setPDV(p.getPDV() + 1);
		}
		// else 
		// {
		// 	//p.setPDV(p.ptVieMax);
		// }
	}
	public float getX(){return this.LieuX;}
	public float getY(){return this.LieuY;}
}
