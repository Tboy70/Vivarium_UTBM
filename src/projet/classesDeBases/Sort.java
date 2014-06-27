package projet.classesDeBases;

/******************************/
/*** CLASSE DE L'OBJET SORT ***/
/******************************/

public class Sort extends Objet{
	
	private int degatSort;
	private int coutMana;

	public Sort(String n, int d, int c) {
		super(n);
		this.degatSort = d;
		this.coutMana = c;
	}
	public Sort(Sort s){
		super(s);
		this.degatSort = s.degatSort;
		this.coutMana = s.coutMana;
	}
	
	public int getDegatSort(){
		return degatSort;
	}
	public int getCoutMana(){
		return coutMana;
	}
}
