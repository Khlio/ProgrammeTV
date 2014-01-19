package fr.epsi.progtv.domaine.chaine;

import java.util.Collections;

import fr.epsi.progtv.domaine.EntrepotAggregats;

public class EntrepotChaines extends EntrepotAggregats<Chaine> {
	
	private static class EntrepotChainesHolder {
		private static final EntrepotChaines INSTANCE = new EntrepotChaines();
	}
	
	public static EntrepotChaines getInstance() {
		return EntrepotChainesHolder.INSTANCE;
	}
	
	@Override
	public void trie() {
		Collections.sort(aggregats);
	}
	
}
