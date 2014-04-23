package georgi.gaydarov.gos.gvm.coder;




import java.util.HashMap;
import java.util.Map;

class InstructionDecoder {
	private static final Map<Byte, Instruction> instructionMap = new HashMap<Byte, Instruction>();
	static
	{
		byte instructionIdIterator = 0x1;
		for(Instruction instruction : Instruction.values())
		{
			instructionMap.put(instructionIdIterator, instruction);
			instructionIdIterator++;
		}
	}
	
	public static Instruction getInstruction(byte code)
	{
		return instructionMap.get(code);
	}
}
