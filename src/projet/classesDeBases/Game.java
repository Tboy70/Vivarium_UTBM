package projet.classesDeBases;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;  
import org.newdawn.slick.Graphics;  
import org.newdawn.slick.SlickException;  
import org.newdawn.slick.state.BasicGameState;  
import org.newdawn.slick.state.StateBasedGame;  
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;  
import org.newdawn.slick.Input;  

/******************************************/
/*** CLASSE QUI GÈRE LE JEU EN LUI-MÊME ***/
/******************************************/

public class Game extends BasicGameState{  
	
    public static final int ID = 1;       
    private Input input;  
    private int mapX = 0, mapY = 0;			// on affiche la map depuis le coin en haut à gauche	 

    private Image persoH, persoO, persoT, persoM, persoEn, persoEl, coffre; // Déclaration des images des personnages

    private Map carte;  // Déclaration de la carte									

    /*********** LISTE DES ARMES / SORTS **************/

    Arme epe1 = new Arme("épée émoussée", 20, 0, 0);
    Arme epe2 = new Arme("épée longue", 40, 0, 1);
    Arme epe3 = new Arme("Excalibur", 75, 0, 2);
    Arme arc1 = new Arme("arc de chasse", 15, 1, 0);
    Arme arc2 = new Arme("arc long", 30, 1, 1);
    Arme arc3 = new Arme("arc elfique", 50, 1, 2);
    Arme mas1 = new Arme("gourdin", 40, 2, 0);
    Arme mas2 = new Arme("massue", 45, 2, 1);
    Arme mas3 = new Arme("massue à pointes", 50, 2, 2);
    Arme bat1 = new Arme("Bâton de l'apprenti", 5, 3, 0);
	Arme bat2 = new Arme("Bâton de l'érudit", 10, 3, 1); 	// PM = 100
	Arme bat3 = new Arme("Baguette de sureau", 15, 3, 2);	// PM = 150
    Arme ent1 = new Arme("Corps à corps", 40, 4, 0);
    Arme ent2 = new Arme("rondins", 45, 4, 1);
    Arme ent3 = new Arme("rochers", 50, 4, 2);

    Sort sor1 = new Sort("boule de feu", 35, 20);
    Sort sor2 = new Sort("éclair", 45, 30);
    Sort sor3 = new Sort("soin", 50, 40);
	Sort sor4 = new Sort("vampirisme", 0, 50);			// DEGATS = pdvEnemi/2
	Sort sor5 = new Sort("mort instantannée", 0, 70);	// DEGATS = pdvEnemi

    PotionVie pv1 = new PotionVie("Potion mineure", 20);
    PotionVie pv2 = new PotionVie("Potion moyenne", 50);
    PotionVie pv3 = new PotionVie("Potion majeure", 100);
    PotionMana pm1 = new PotionMana("Potion de mana", 30);

    /**************************************************/

	ArrayList <Lieu> tabLieu = new ArrayList<Lieu>();				// Liste de lieux
	ArrayList <Personnage> tabPerso = new ArrayList<Personnage>();	// Liste de personnages
	ArrayList <Objet> tabObjet = new ArrayList<Objet>(); 			// Liste d'objets

	/*** Compteur de personnages ***/

	int nbHum = 0;
    int nbOrq = 0;
    int nbTro = 0;
    int nbMag = 0;
    int nbEnt = 0;
    int nbElf = 0;

    /*** Déclaration des lieux ***/

    Taverne tav1, tav2; // 2 tavernes -> Une pour humain et une pour mage noir
    Grotte gro1;
    Foret for1;

    /*** Déclaration des personnages de départ ***/

    Humain h1, h2, h3;
    Orque o1, o2, o3;
    Troll t1, t2, t3;
    MageNoir m1, m2, m3;
    Ent en1, en2, en3;
    Elfe el1, el2, el3;

    String str = "";
    String str2 = "";

    /*** Pour ce qui sera interface graphique ***/

    boolean bouton_ajout_perso = false;
    boolean bouton_ajout_objet = false;
    boolean demande_pos_objet = false;
    String str_demande_pos_objet = "Cliquez sur l'endroit où vous\nsouhaitez ajouter un objet";
    boolean pos_objet_incorrect = false;
    String str_pos_objet_incorrect = "Vous devez cliquer\nsur un chemin !";
    int objet_a_ajouter = 0;

    int perso_select = 0;
    int objet_select = 0;

    int sourisX = 0;
    int sourisY = 0;

    @Override  
    public int getID(){
        return ID;
    }  


    /*** INITIALISATION DU JEU ***/

    public void init(GameContainer container, StateBasedGame game) throws SlickException  
    {  
        /*** On récupère l'instance du gestionnaire d'entrées ***/ 

        input = container.getInput();  

        /*** Récuperation des ressources (images des personnages, map en XML ...) ***/

        persoH = new Image("res/perso.png"); 
        persoO = new Image("res/orc16x16.png");
        persoT = new Image("res/Troll23x32.png");
        persoM = new Image("res/mage16x24.png");
        persoEn = new Image("res/ent23x32.png");
        persoEl = new Image("res/elfe16x20.png");
        coffre = new Image("res/coffre16x16.png");
      //carte = new Map("res/Level_1.tmx",""); 
        carte = new Map("res/Level_2.tmx","");

        /*** Instanciations ***/

        tav1 = new Taverne("TAVERNE B", 48, 112);			// ATTENTION VALEURS ARIBTRAIRES 
        tav2 = new Taverne("TAVERNE M", 848, 448);
        gro1 = new Grotte("GROTTE", 848, 64);
        for1 = new Foret("FORET", 144,464);
        tabLieu.add(tav1);
        tabLieu.add(for1);
        tabLieu.add(tav2);
        tabLieu.add(gro1);

        h1 = new Humain("Jack", epe1, tav1); 				tabPerso.add(h1); nbHum++;
        h2 = new Humain("Thorin", epe1, tav1); 				tabPerso.add(h2); nbHum++;
        h3 = new Humain("Marc", epe1, tav1); 				tabPerso.add(h3); nbHum++;
        o1 = new Orque("Lurtz", epe1, gro1); 				tabPerso.add(o1); nbOrq++;
        o2 = new Orque("Gorbak", epe1, gro1); 				tabPerso.add(o2); nbOrq++;
        o3 = new Orque("Loktar", epe1, gro1); 				tabPerso.add(o3); nbOrq++;
        t1 = new Troll("Rasalgul", mas1, gro1); 			tabPerso.add(t1); nbTro++;
        t2 = new Troll("Larbi", mas1, gro1); 				tabPerso.add(t2); nbTro++;
        t3 = new Troll("Gargantua", mas1, gro1); 			tabPerso.add(t3); nbTro++;
        m1 = new MageNoir("Gandalf", bat1, tav2, sor1);		tabPerso.add(m1); nbMag++;
        m2 = new MageNoir("Merlin", bat1, tav2, sor1);		tabPerso.add(m2); nbMag++;
        m3 = new MageNoir("Sarouman", bat1, tav2, sor1);	tabPerso.add(m3); nbMag++;
        en1 = new Ent("Smoag", ent1, for1);					tabPerso.add(en1); nbEnt++;
        en2 = new Ent("Flower", ent2, for1);				tabPerso.add(en2); nbEnt++;
        en3 = new Ent("Peace", ent3, for1);					tabPerso.add(en3); nbEnt++;
        el1 = new Elfe("Legolas", arc1, for1);				tabPerso.add(el1); nbElf++;
        el2 = new Elfe("Idril", arc1, for1);				tabPerso.add(el2); nbElf++;
        el3 = new Elfe("Feanor", arc1, for1);				tabPerso.add(el3); nbElf++;

        /*** Génération de 30 objets ***/

        for (int k=0; k<30; k++)		
        {
            tabObjet.add(choixObjetRandom());		
            int coordMapX = 0;
            int coordMapY = 0;

            while(carte.isBlocked(coordMapX, coordMapY))	// placement des objets ALÉATOIREMENT sur la carte
            {
            	// on réduit les possibilités de placement aléatoire pour l'accélérer
                tabObjet.get(k).objetX = (float)Math.random() * 800 + 48;
                tabObjet.get(k).objetY = (float)Math.random() * 400 + 64;
                
                coordMapX = (int) Math.floor(tabObjet.get(k).getX()/carte.TILE_SIZE);
                coordMapY = (int) Math.floor(tabObjet.get(k).getY()/carte.TILE_SIZE);
            }
        }
    }  


    /*** METHODE DE RENDU ***/

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException { 

        carte.render(mapX, mapY); // affichage de la carte


        /************** DESSINS DES LIEUX ***************************/

        for(int j=0; j<tabLieu.size(); j++){
            Lieu l = tabLieu.get(j);
            g.setColor(Color.yellow);
            g.drawOval(l.getX(),l.getY(), carte.TILE_SIZE, carte.TILE_SIZE);
        }

        /****************** DESSINS DES OBJETS  **********************/

        for(int k=0; k<tabObjet.size(); k++){
            Objet o = tabObjet.get(k);
            g.drawImage(coffre,o.getX(),o.getY());
        }

        /************** DESSINS DES PERSONNAGES *********************/

        g.setColor(Color.black);
        for(int i=0; i<tabPerso.size(); i++){
            Personnage p = tabPerso.get(i);
            g.setColor(Color.red);

            /*** Dessine suivant le type de personnage ***/ 

            if(p instanceof Humain){
                persoH.draw(p.getX()-carte.TILE_SIZE/2,p.getY()-carte.TILE_SIZE/2); 
            }
            else if(p instanceof Orque){
                persoO.draw(p.getX()-carte.TILE_SIZE/2,p.getY()-carte.TILE_SIZE/2);
            }
            else if(p instanceof MageNoir){
                persoM.draw(p.getX()-carte.TILE_SIZE/2,p.getY()-carte.TILE_SIZE/2);
            }
            else if(p instanceof Ent){
                persoEn.draw(p.getX()-carte.TILE_SIZE/2,p.getY()-carte.TILE_SIZE/2);
            }
            else if(p instanceof Elfe){
                persoEl.draw(p.getX()-carte.TILE_SIZE/2,p.getY()-carte.TILE_SIZE/2);
            }
            else {
                persoT.draw(p.getX()-carte.TILE_SIZE/2,p.getY()-carte.TILE_SIZE/2);
            }

            /*** En cas d'alliance ***/

            if(p.leader){   // Si le personnage est leader
                String lead = p.getNom();
                g.drawString("alliance", p.getX()-carte.TILE_SIZE/2 - 5, p.getY()-carte.TILE_SIZE/2 - 60);
                String allies = p.getNom()+" ";
                for(int z=0; z<p.tabAllies.size(); z++){
                    allies += p.tabAllies.get(z).getNom()+" ";
                }

                g.drawString("leader : "+lead+"\n"+allies, p.getX()-carte.TILE_SIZE/2 - 5, p.getY()-carte.TILE_SIZE/2 - 40);
            }
        }

        /*************** AFFICHAGE DE L'INTERFACE UTILISATEUR  ****************/

        g.setColor(Color.black);
        g.fillRect(1000, 0, 280, 720);
        g.setColor(Color.white);
        g.drawString("NB Personnages : "+tabPerso.size(), 1015, 10);
        g.drawString("Humains : "+nbHum+"\n" +
           "Orques : "+nbOrq+"\n" +
           "Mages Noirs : "+nbMag+"\n" +
           "Ents : "+nbEnt+"\n" +
           "Elfes : "+nbElf+"\n" +
           "Troll : "+nbTro, 1015, 30);
        g.setColor(Color.yellow);
    	g.drawString(str, 1006, 150); 	// Ecrit "X attaque Y"   
    	g.drawString(str2, 1006, 170); 	// Ecrit "X ramasse Z"
    	
        /************** BOUTONS D'AJOUT *****************/

        g.setColor(Color.white);
        g.drawRect(1013, 200, 124, 30);
        g.drawString("Ajouter Perso", 1017, 205);
        g.fillRect(1150, 200, 124, 30);
        g.setColor(Color.black);
        g.drawString("Ajouter objet", 1154, 205);

        g.setColor(Color.white);

        /*** Dessin des boutons d'ajout de personnages ***/

        if(bouton_ajout_perso){
	    	//ajout humain
            g.drawRect(1070, 245, 130, 35);
            persoH.draw(1080, 255);
            g.drawString("Humain",1115,253);
	    	//ajout elfe
            g.drawRect(1070, 290, 130, 35);
            persoEl.draw(1083, 298);
            g.drawString("Elfe", 1115, 298);
	    	//ajout Ent
            g.drawRect(1070, 335, 130, 35);
            persoEn.draw(1077, 337);
            g.drawString("Ent", 1115, 343);
	    	//ajout orque
            g.drawRect(1070, 380, 130, 35);
            persoO.draw(1082, 390);
            g.drawString("Orque", 1115, 390);
	    	//ajout troll
            g.drawRect(1070, 425, 130, 35);
            persoT.draw(1077, 427);
            g.drawString("Troll", 1115, 432);
	    	//ajout MageNoir
            g.drawRect(1070, 470, 130, 35);
            persoM.draw(1082, 475);
            g.drawString("Mage noir", 1115, 480);
        }

        /*** Dessin d'ajout des boutons d'objet ***/

        if(bouton_ajout_objet)
        {
    		//ajout arme
            g.fillRect(1070, 245, 130, 28);
            g.setColor(Color.black);
            g.drawString("Arme",1112,250);
	    	//ajout potion de vie
            g.setColor(Color.white);
            g.fillRect(1070, 285, 130, 28);
            g.setColor(Color.black);
            g.drawString("Potion de vie", 1075, 290);
	    	//ajout Potion de mana
            g.setColor(Color.white);
            g.fillRect(1070, 325, 130, 28);
            g.setColor(Color.black);
            g.drawString("Potion de mana", 1071, 330);
	    	//ajout sort
            g.setColor(Color.white);
            g.fillRect(1070, 365, 130, 28);
            g.setColor(Color.black);
            g.drawString("Sort", 1114, 370);
            g.setColor(Color.white);
        }
        if(demande_pos_objet)
        {
            g.drawString(str_demande_pos_objet, 1005, 400);
        }
        if(pos_objet_incorrect)
        {
            g.setColor(Color.red);
            g.drawString(str_pos_objet_incorrect, 1005, 440);
            g.setColor(Color.white);
        }

        /*** Indication pour avoir les données sur un personnage ou un objet précis ***/

        g.drawString("Appuyez sur les flèches\n<- et -> pour obtenir les\ncaractéristiques d'un\n" +
           "personnage précis", 1022, 515);
        g.setColor(Color.cyan); 

        Personnage pers;
        if(tabPerso.size()!=0)
        {
            pers = tabPerso.get(perso_select);
            if(pers instanceof Humain || pers instanceof Orque)
                g.drawRect(pers.getX()-1-carte.TILE_SIZE/2,pers.getY()-1-carte.TILE_SIZE/2,
                  persoEl.getWidth(),persoEl.getHeight());
            else if(pers instanceof Troll || pers instanceof Ent)
                g.drawRect(pers.getX()-1-carte.TILE_SIZE/2,pers.getY()-1-carte.TILE_SIZE/2,
                  persoEn.getWidth()+1, persoEn.getHeight()+1);
            else if(pers instanceof Elfe || pers instanceof MageNoir)
                g.drawRect(pers.getX()-1-carte.TILE_SIZE/2,pers.getY()-1-carte.TILE_SIZE/2,
                  persoM.getWidth(), persoM.getHeight()+1);
            else {}

                g.drawString(pers.getNom()+"\nArme : "+pers.arme.getNom()+"\nPV restants : "+pers.getPDV(), 1022, 600);

            if(pers instanceof MageNoir)    // Si c'est un mage noir, une ligne en plus
            {
                MageNoir mag = (MageNoir) pers;
                g.drawString("PM restants : "+mag.getPtMana()+"\nSort : "+mag.getSort().getNom(), 1022, 655);
            }
        }

        /*** ADDITIONNEL ***/

        if(tabObjet.size() != 0){
            Objet obj = tabObjet.get(objet_select);
            g.drawRect(obj.getX()-1,obj.getY()-1,
               coffre.getWidth(),coffre.getHeight());
            g.drawString(obj.getNom(), obj.getX() - 8, obj.getY() - 20);
        }
    }  

    /*** METHODE DE RAFRAICHISSEMENT DE LA CARTE ***/

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {  

        /**** DETECTION CLIC SUR BOUTONS ****/

        sourisX = Mouse.getX();
        sourisY = Mouse.getY();

        /*** Bouton Ajout Personnage ***/

        if((sourisX>1013 && sourisX<1137) && (sourisY>488 && sourisY<519)){
            if(input.isMousePressed(0)){
                bouton_ajout_perso = true;
                bouton_ajout_objet = false;
                demande_pos_objet = false;
                pos_objet_incorrect = false;
            }
        }

        /*** Bouton Ajout Objet ***/

        if((sourisX>1150 && sourisX<1274) && (sourisY>488 && sourisY<519)){
            if(input.isMousePressed(0)){
                bouton_ajout_perso = false;
                bouton_ajout_objet = true;
            }
        }

        if(bouton_ajout_perso){

    	   // ajouterPerso : 0=Hum, 1=Elf, 2=Ent, 3=Orq, 4=Tro, default=Mag

    	   // Bouton Ajout Humain

            if((sourisX>1070 && sourisX<1200) && (sourisY>437 && sourisY<473)){
                if(input.isMousePressed(0)){
                    ajouterPerso(0);
                }
            }

	    	// Bouton Ajout Elf

            if((sourisX>1070 && sourisX<1200) && (sourisY>387 && sourisY<427)){
                if(input.isMousePressed(0)){
                    ajouterPerso(1);
                }
            }

	    	// Bouton Ajout Ent

            if((sourisX>1070 && sourisX<1200) && (sourisY>347 && sourisY<382)){
                if(input.isMousePressed(0)){
                    ajouterPerso(2);
                }
            }

	    	// Bouton Ajout Orque

            if((sourisX>1070 && sourisX<1200) && (sourisY>303 && sourisY<337)){
                if(input.isMousePressed(0)){
                    ajouterPerso(3);
                }
            }

	    	// Bouton Ajout Troll

            if((sourisX>1070 && sourisX<1200) && (sourisY>258 && sourisY<292)){
                if(input.isMousePressed(0)){
                    ajouterPerso(4);
                }
            }

	    	// Bouton Ajout Mage noir

            if((sourisX>1070 && sourisX<1200) && (sourisY>213 && sourisY<247)){
                if(input.isMousePressed(0)){
                    ajouterPerso(5);
                }
            }
        }

        if(bouton_ajout_objet)
        {
    		// Bouton Ajout Arme

            if((sourisX>1070 && sourisX<1200) && (sourisY>445 && sourisY<473)){
                if(input.isMousePressed(0)){
                    demande_pos_objet = true;
                    objet_a_ajouter = 1;
                }

            }

	    	// Bouton Ajout Potion de vie

            if((sourisX>1070 && sourisX<1200) && (sourisY>405 && sourisY<432)){
                if(input.isMousePressed(0)){
                    demande_pos_objet = true;
                    objet_a_ajouter = 2;
                }
            }

	    	// Bouton Ajout Potion de mana

            if((sourisX>1070 && sourisX<1200) && (sourisY>366 && sourisY<393)){
                if(input.isMousePressed(0)){
                    demande_pos_objet = true;
                    objet_a_ajouter = 3;
                }
            }

	    	// Bouton Ajout Sort

            if((sourisX>1070 && sourisX<1200) && (sourisY>325 && sourisY<352)){
                if(input.isMousePressed(0)){
                    demande_pos_objet = true;
                    objet_a_ajouter = 4;
                }
            }

            /*** Pour positionner l'objet sur la carte ***/

            if(demande_pos_objet)
            {
                if(input.isMousePressed(0))
                {
                	// il faut faire attention ici, les coordonnées sur l'axe Y sont inversées
                	// entre les coordonnées de la souris et les coordonnées sur l'écran
                    if(carte.isBlocked(sourisX/carte.TILE_SIZE, (720-sourisY)/carte.TILE_SIZE))
                    {
                        pos_objet_incorrect = true;
                    }
                    else 
                    {
                        pos_objet_incorrect = false;
                        ajouterObjet(objet_a_ajouter, sourisX/carte.TILE_SIZE, (720-sourisY)/carte.TILE_SIZE);
                    }
                }	    		
            }
        }


        /*** Appuie sur échap = quitter le jeu ***/

        if ( input.isKeyPressed(Input.KEY_ESCAPE))  
        {  
            System.exit(0);   
        }  

        /*** Interactions entre personnages ***/

        for(int i=0; i<tabPerso.size(); i++)			//pour chaque perso de la liste : 
        {
            Personnage p1 = tabPerso.get(i);

            for(int j=0; j<tabPerso.size(); j++)
            {
                if(!tabPerso.get(j).equals(p1))
                {
                    Personnage p2 = tabPerso.get(j);
                    if(distance(p1.getX(),p1.getY(),p2.getX(),p2.getY()) <= 1.5*carte.TILE_SIZE) // P1 et P2 proches
                    {
    				    if(p1.isBien() && p2.isMal() || p1.isMal() && p2.isBien()) // P1 et P2 sont ennemis
    				    {
                            str = p1.getNom()+" ATTAQUE "+p2.getNom();
                            p1.attaquer(p2);
                            if(p2.getPDV() <= 0) 
                            {
                                if(p2.getClass() == Orque.class) nbOrq--;
                                else if(p2.getClass() == Humain.class) nbHum--; 
                                else if(p2.getClass() == Elfe.class) nbElf--;
                                else if(p2.getClass() == Ent.class) nbEnt--;
                                else if(p2.getClass() == Troll.class) nbTro--;
                                else nbMag--;

                                if(p2.leader)   // Si le personnage mort est le leader d'un groupe
                                {
                                    p2.setLeader(false);
                                    p2.tabAllies.get(0).setLeader(true); // on nomme un autre perso leader
                                }

                                tabPerso.remove(j);
                                perso_select--;	// a voir : selon le perso supprimé de la liste, on ne devrait pas
                            }					// systématiquement decrémenter cet indice
                        }

                        else if(p1.isBien() && p2.isBien() || p1.isMal() && p2.isMal()) // Si les deux personnages sont du même camp
                        {
                            double random = Math.random();
                            if(random<0.000075) // valeur très faible car verif très souvent exécutée en 1 seconde
                                p1.allier(p2);  // Les deux personnages s'allient
                        }
                    }
                }
            }

            if(!p1.isInCombat())	// perso pas en combat : il peut donc se déplacer
                p1.seDeplacer(carte, carte.TILE_SIZE);

            for(int y=0; y<p1.tabAllies.size(); y++)
            {
            	// si un personnage mort fait partie d'un groupe, 
            	// il faut penser à l'en supprimer
                if(p1.tabAllies.get(y).getPDV() <= 0)
                    p1.tabAllies.remove(y);
            }
            // si un personnage n'a plus de groupe (ses alliés sont morts) 
            if(p1.tabAllies.size() == 0)
                p1.leader = false;

            /*** interactions perso / objet ***/

            for(int z=0; z<tabObjet.size(); z++)
            {
                Objet o = tabObjet.get(z);

                /*** perso proche d'un objet ***/

                if (distance(p1.getX(), p1.getY(), o.getX(), o.getY()) <= carte.TILE_SIZE)
                {
                    boolean resultat = p1.ramasserObjet(o);
                    if(resultat)
                    {
                        str2 = p1.getNom()+" RAMASSE "+o.getNom();
                        tabObjet.remove(z);
                        objet_select--;		// même remarque que sur l'indice perso_select
                    }
              //       else 
              //       {
        			   // //str2 = p1.getNom()+" LAISSE "+o.getNom();
              //       }
                }
            }

            /*** interaction perso / lieu ***/

            for(int a=0; a<tabLieu.size(); a++)
            {
                Lieu l = tabLieu.get(a);

                /*** perso proche d'un lieu ***/

                if (distance(p1.getX(), p1.getY(), l.getX(), l.getY()) <= carte.TILE_SIZE )
                {
                    l.reposerPerso(p1);        		   
                }
            }
        }

        if(perso_select < 0)
            perso_select = 0;
        else if(perso_select >= tabPerso.size())
            perso_select = tabPerso.size() - 1;
        else {

        } // il faut être prudent lors de la manipulation de ces indices, pour ne pas sélectionner un personnage
          // ou un objet qui serait en dehors de la liste de Personnage et de la liste d'Objet
        
        if(objet_select < 0)
            objet_select = 0;
        else if(objet_select >= tabObjet.size())
            objet_select = tabObjet.size() - 1;
        else {

        }

        /*** GESTION DES FLÈCHES POUR SÉLECTIONNER UN PERSO ***/

        if ( input.isKeyPressed(Input.KEY_RIGHT))  
        {  
            perso_select++;
            if(perso_select>=tabPerso.size())
                perso_select = 0;        	
        }  
        else if ( input.isKeyPressed(Input.KEY_LEFT))  
        {  
            perso_select--;
            if(perso_select<0)
                perso_select = tabPerso.size()-1;
        }  
        else if ( input.isKeyPressed(Input.KEY_UP))  
        {  
            objet_select++;
            if(objet_select>=tabObjet.size())
                objet_select = 0; 
        } 
        else if ( input.isKeyPressed(Input.KEY_DOWN))  
        {  
            objet_select--;
            if(objet_select<0)
                objet_select = tabObjet.size()-1;
        } 
        else { 
        } // on relâche la touche  

    }  

    /*** TOUS LES PERSONNAGES ET LES OBJETS SONT SUPPRIMÉS ***/

    public void armageddon (){			// méthode utilisée lors du développement, peut toujours servir
        for (int i=0; i<tabPerso.size(); ++i){
            tabPerso.remove(i);
        }
        for (int j = 0; j<tabObjet.size(); j++){
            tabObjet.remove(j);
        }
    }

    /*** CHOISIT UN OBJET À PLACER SUR LA CARTE ALÉATOIREMENT ***/

    private Objet choixObjetRandom() {
        double rand = Math.random() * 4;
        if(rand>2.5)
            return armeRandom();            // Une arme au hasard
        else if(rand>2)
            return sortRandom();            // idem
        else if(rand>0.5)
            return potionVieRandom();        // idem
        else
            return new PotionMana(pm1); 	// il n'y a qu'une potion de mana
    }

    /*** CHOISIT UNE ARME AU HASARD ***/

    private Arme armeRandom()
    {
        double rand = Math.random() * 10;

        /*** Renvoie les armes de puissance 2 et 3 car de base, les personnages ont des armes de puissance 1 **/
        if(rand>9) return new Arme(epe2);
        else if(rand>8) return new Arme(epe3);
        else if(rand>7) return new Arme(arc2);
        else if(rand>6) return new Arme(arc3);
        else if(rand>5) return new Arme(mas2);
        else if(rand>4) return new Arme(mas3);
        else if(rand>3) return new Arme(bat2);
        else if(rand>2) return new Arme(bat3);
        else if(rand>1) return new Arme(ent2);
        else return new Arme(ent3);
    }

    /*** CHOISIT UN SORT AU HASARD ***/

    private Sort sortRandom()
    {
        double rand = Math.random() * 4;

        /*** Renvoie un sort de puissance > 1 car le mage noir à forcément celui de puissance = 1 de base ***/

        if(rand>3) return new Sort(sor2);
        else if(rand>2) return new Sort(sor3);
        else if(rand>1) return new Sort(sor4);
        else return new Sort(sor5);
    }

    /*** CHOISIT UNE POTION DE VIE AU HASARD ***/
    private PotionVie potionVieRandom()
    {
        double rand = Math.random() * 3;
        if(rand>2) return new PotionVie(pv1);
        else if(rand>1) return new PotionVie(pv2);
        else return new PotionVie(pv3);
    }

    /*** METHODE D'AJOUT DE PERSONNAGES ***/

    private void ajouterPerso(int perso)
    {
        switch(perso)
        {
            case 0: 
            Humain h = new Humain("Humain", epe1, tav1);
            nbHum++;
            tabPerso.add(h);
            break;
            case 1:
            Elfe e = new Elfe("Elfe", arc1, for1);
            nbElf++;
            tabPerso.add(e);
            break;
            case 2:
            Ent a = new Ent("Ent", ent1, for1);
            nbEnt++;
            tabPerso.add(a);
            break;
            case 3:
            Orque o = new Orque("Orque", epe1, gro1);
            nbOrq++;
            tabPerso.add(o);
            break;
            case 4:
            Troll t = new Troll("Troll", mas1, gro1);
            nbTro++;
            tabPerso.add(t);
            break;
            default:
            MageNoir m = new MageNoir("Mage", bat1, tav2, sor1);
            nbMag++;
            tabPerso.add(m);
            break;
        }
    }

    /*** METHODES D'AJOUT D'OBJETS ***/

    private void ajouterObjet(int objet, int posX, int posY)
    {
        Objet o;
        switch(objet)
        {
            case 1:
            o = armeRandom();
            // toujours faire attention avec l'axe Y et la position de la souris
            o.objetX = sourisX;o.objetY = 720-sourisY;tabObjet.add(o);
            break;
            case 2:
            o = potionVieRandom();
            o.objetX = sourisX;o.objetY = 720-sourisY;tabObjet.add(o);
            break;
            case 3: 
            o = new PotionMana(pm1);
            o.objetX = sourisX;o.objetY = 720-sourisY;tabObjet.add(o);
            break;
            case 4:
            o = sortRandom();
            o.objetX = sourisX;o.objetY = 720-sourisY;tabObjet.add(o);
            break;
            default:
            break;
        }

        pos_objet_incorrect = false;
        demande_pos_objet = false;
    }

    /*** CALCUL DE LA DISTANCE ENTRE DEUX POINTS ***/
    public double distance(float xa, float ya, float xb, float yb){
        return Math.sqrt( (Math.pow(xb-xa,2)) + (Math.pow(yb-ya,2)) );
    }

}  