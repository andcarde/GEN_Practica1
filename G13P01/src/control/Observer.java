package control;

import java.util.List;

import model.chromosome.ChromosomeI;

public interface Observer {

	void updateGenerationAverage(List<Double> generationAverage);
	void updateGenerationLeaders(List<Double> generationLeaders);
	void updateIntergenerationLeader(ChromosomeI intergenerationLeader);
}
