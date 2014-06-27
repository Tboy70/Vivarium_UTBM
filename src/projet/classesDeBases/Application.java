package projet.classesDeBases;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/************************************************************/
/*** CLASSE CONSTITUANT LE POINT D'ENTREE DU JEU (= MAIN) ***/
/************************************************************/

public class Application extends StateBasedGame{
	private Game jeu;
	private AppGameContainer container;

	public Application() {
		super("SmallWorld");
	}

	/*** INITIALISATION DU JEU ***/

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		if (container instanceof AppGameContainer) {
            this.container = (AppGameContainer) container; // on stocke le conteneur du jeu !
        }

        jeu = new Game(); //le jeu en lui même !!

        container.setShowFPS(false); // "false si on ne veut pas voir le nombre de FPS"

		addState(jeu);	//on ajoute le GameState au conteneur !
	}


	/*** LANCEMENT DE LA FENÊTRE DE JEU ***/

	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new Application());
			container.setDisplayMode(1280, 720, false);
			container.setTargetFrameRate(60);
			container.start();
		}                       
        catch (SlickException e) {e.printStackTrace();}  // l'exception de base de slick !!
    }
}