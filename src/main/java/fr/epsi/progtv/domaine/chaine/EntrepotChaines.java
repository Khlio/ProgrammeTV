package fr.epsi.progtv.domaine.chaine;

import java.util.Collections;

import fr.epsi.progtv.domaine.EntrepotAggregats;

public class EntrepotChaines extends EntrepotAggregats<Chaine> {
	
	public static EntrepotChaines getInstance() {
		if (null == instance) {
			instance = new EntrepotChaines();
		}
		return instance;
	}
	
	@Override
	public void trie() {
		Collections.sort(aggregats);
	}
	
	private static EntrepotChaines instance;
	
}
