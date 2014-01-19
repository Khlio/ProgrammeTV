package fr.epsi.progtv.domaine.programme;

import java.util.Collections;

import fr.epsi.progtv.domaine.EntrepotAggregats;

public class EntrepotProgrammes extends EntrepotAggregats<Programme> {

	private static class EntrepotProgrammesHolder {
		private static final EntrepotProgrammes INSTANCE = new EntrepotProgrammes();
	}
	
	public static EntrepotProgrammes getInstance() {
		return EntrepotProgrammesHolder.INSTANCE;
	}
	
	@Override
	public void nettoie() {
		super.nettoie();
		Programme.resetId();
	}
	
	@Override
	public void trie() {
		Collections.sort(aggregats);
	}
	
}
