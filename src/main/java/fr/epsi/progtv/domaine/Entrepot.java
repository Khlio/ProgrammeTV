package fr.epsi.progtv.domaine;

import java.util.List;

public interface Entrepot<T extends Aggregat> {

	List<T> get();
	
	void ajoute(T aggregat);
	
	void nettoie();
	
	void trie();
	
}
