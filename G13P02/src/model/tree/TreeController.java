package model.tree;

public class TreeController {
	
	public Double getValue(Double x, ArithmeticNode node) {
		if (node.isLeaf()) {
			try {
				return Double.parseDouble(node.getFruit());
			} catch (Exception e) {
				return x;
			}
		}
		else {
			Double left = getValue(x, node.getLeftBranch());
			Double right = getValue(x, node.getRightBranch());
			switch(node.getKnot()) {
				
			}
			return left;
		}
	}
}
