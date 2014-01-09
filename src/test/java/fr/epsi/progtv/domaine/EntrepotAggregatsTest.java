package fr.epsi.progtv.domaine;

import org.junit.Test;

public abstract class EntrepotAggregatsTest<T extends Aggregat> {

	@Test
	public abstract void peutRecuperer();
	
	@Test
	public abstract void peutAjouter();
	
	@Test
	public abstract void peutNettoyer();
	
	protected Entrepot<T> entrepot;
	
}
