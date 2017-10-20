package Main.Modele;

import Main.Modele.Composantes.Aile;
import Main.Modele.Composantes.Metal;
import Main.Modele.Composantes.Moteur;
import Main.Modele.Usines.UsineAvion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BatimentTest {
    Batiment usine;
    int intervalProd;
    int qtyAile,qtyMoteur;
    @Before
    public void setUp() throws Exception {
        intervalProd=10;
        qtyAile=3;
        qtyMoteur=5;
        usine=new UsineAvion(intervalProd,0,null);
        usine.ajouterTypeProduction(Moteur.class.toString(),qtyMoteur);
        usine.ajouterTypeProduction(Aile.class.toString(),qtyAile);
    }
    @Test
    public void extraireSortie_PeutPasProduire_Faux(){
        usine.avancerTour(intervalProd);
        assertNull(usine.extraireSortie());
    }

    //region isInventaireSuffisant
    @Test
    public void isInventaireSuffisant_InventaireSuffisant_True() throws Exception {
        usine.gererAjout(Moteur.class.toString(),qtyMoteur);
        usine.gererAjout(Aile.class.toString(),qtyAile);
        assertTrue(usine.isInventaireSuffisant());
    }
    @Test
    public void isInventaireSuffisant_InventaireInsuffisant_False() throws Exception {
        usine.gererAjout(Moteur.class.toString(),qtyMoteur-2);
        usine.gererAjout(Aile.class.toString(),qtyAile-2);
        assertFalse(usine.isInventaireSuffisant());
    }
    @Test
    public void isInventaireSuffisant_UneEntreeSuffisanteAutreInsuffisante_False() throws Exception {
        usine.gererAjout(Moteur.class.toString(),qtyMoteur-2);
        usine.gererAjout(Aile.class.toString(),qtyAile);
        assertFalse(usine.isInventaireSuffisant());
    }
    //endregion\

    //region ajouterInventaire
    @Test
    public void ajouterInventaire_AjouterUneComposante_Unecomposante(){
        usine.gererAjout(Moteur.class.toString(),qtyMoteur);
        assertEquals(qtyMoteur,usine.getQuantiteInventaire(Moteur.class.toString()));
    }
    @Test
    public void AjouterInventaire_ProductionNonDefinie_RetourneMoinsUn(){
        int ajoutInventaire=4;
        String classeNonSupportee=Metal.class.toString();
        usine.gererAjout(classeNonSupportee,ajoutInventaire);

        assertEquals(-1,usine.getQuantiteInventaire(classeNonSupportee));
    }
    //endregion


}