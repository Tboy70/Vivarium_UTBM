package projet.classesDeBases;

/*********************************/
/*** CLASSE GÉNÉRALE DES OBJETS **/
/*********************************/

public class Objet {
	
	protected String nomObjet;
	protected float objetX, objetY;
	
	public Objet(String n)
	{
		nomObjet = n;
	}

	public Objet(Objet o)
	{
		this.nomObjet = o.nomObjet;
		this.objetX = o.objetX;
		this.objetY = o.objetY;
	}
	
	/*** ACCESSEURS / MUTATEURS ***/
	
	public float getX(){return this.objetX;}
	public float getY(){return this.objetY;}
	public void setX(float f){this.objetX = f;}
	public void setY(float f){this.objetY = f;}

	public String getNom(){
		return nomObjet;
	}

}
