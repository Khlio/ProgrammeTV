package fr.epsi.progtv.domaine;

import java.util.ArrayList;
import java.util.List;

public abstract class EntrepotAggregats<T extends Aggregat> implements Entrepot<T> {

	protected EntrepotAggregats() {
	}
	
	@Override
	public List<T> get() {
		return aggregats;
	}
	
	@Override
	public void ajoute(T aggregat) {
		aggregats.add(aggregat);
	}
	
	@Override
	public void nettoie() {
		aggregats.clear();
	}
	
	@Override
	public void trie() {
	}
	
	protected final List<T> aggregats = new ArrayList<>();
	
}
