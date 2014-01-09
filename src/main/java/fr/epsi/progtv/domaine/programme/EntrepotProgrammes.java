package fr.epsi.progtv.domaine.programme;

import java.util.Collections;

import fr.epsi.progtv.domaine.EntrepotAggregats;

public class EntrepotProgrammes extends EntrepotAggregats<Programme> {

	public static EntrepotProgrammes getInstance() {
		if (null == instance) {
			instance = new EntrepotProgrammes();
		}
		return instance;
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
	
	private static EntrepotProgrammes instance;
	
}
