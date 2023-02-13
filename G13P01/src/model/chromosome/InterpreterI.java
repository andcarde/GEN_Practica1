package model.chromosome;

import java.util.Map;

public interface InterpreterI {

	Map<String, Object> translate(ChromosomeI chromosome);
}
