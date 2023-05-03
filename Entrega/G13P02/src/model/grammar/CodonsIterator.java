package model.grammar;

import java.util.List;

class CodonsIterator {
	
	private List<Integer> codons;
	private int position;
	private int initial;
	
	CodonsIterator(List<Integer> codons) {
		this.codons = codons;
		position = -1;
		initial = 0;
	}
	
	Integer nextModule(int options) {
		position++;
		if (position == codons.size()) {
			initial++;
			if (initial == codons.size())
				initial = 0;
			position = initial;
		}
		return codons.get(position) % options;
	}
}
