package projet.classesDeBases;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/********************************/
/*** CLASSE QUI GÈRE LA CARTE ***/
/********************************/

public class Map extends TiledMap{

	private boolean blocked[][];
	public final int TILE_SIZE = 16;
	
	public Map( String ref, String URL) throws SlickException{
		
		super(ref); // envoie à TiledMap le .tmx

		/*** on récupère les infos sur les endroits inaccessibles ***/
		blocked = new boolean[width][height];
		
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				for (int l=1; l<2; l++){ // on utilise le deuxieme layer pour le moment
					int id = getTileId(x, y, l); 
					if(id != 0){
						String b = getTileProperty(id, "blocked", "");
						if(b.equals("true")) {blocked[x][y] = true;}
						
					}
				}
			}
		}
	}
	
	public boolean isBlocked(int mapX, int mapY)
	{
		return blocked[mapX][mapY]; 
	} 	
}
