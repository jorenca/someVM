package georgi.gaydarov.gos.gvm.coder;


/**
 * Describes a processor operation ({@link Instruction} and operands).
 * @author Georgi Gaydarov
 *
 */
public class Operation {
	private Instruction instruction;
	private OperandType type1;
	private OperandType type2;
	private short operand1;
	private short operand2;
	
	Operation()
	{
		
	}
	Operation(Instruction instruction) 
	{
		this.instruction = instruction;
	}
	

	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	public OperandType getType1() {
		return type1;
	}
	public void setType1(OperandType type1) {
		this.type1 = type1;
	}
	public OperandType getType2() {
		return type2;
	}
	public void setType2(OperandType type2) {
		this.type2 = type2;
	}
	public short getOperand1() {
		return operand1;
	}
	public void setOperand1(short operand1) {
		this.operand1 = operand1;
	}
	public short getOperand2() {
		return operand2;
	}
	public void setOperand2(short operand2) {
		this.operand2 = operand2;
	}
	/**
	 * Constructs a readable representation of this operation.
	 * 
	 * @return the readable string.
	 */
	public String getReadable()
	{
		StringBuilder resultBuilder = new StringBuilder();
		String instrString = String.format("%s[%s/%s] : ", instruction.toString(), type1.toString(), type2.toString());
		resultBuilder.append(instrString);
		
		String opr = "UNKNOWN";
		switch(type1)
		{
			case L:
				opr = String.format("literal(%d)", (byte)operand1);
				break;
			case A:
			case C:
				opr = String.format("addr(%d)", (short)operand1);
				break;
		}
		resultBuilder.append(opr);
		resultBuilder.append(", ");
		
		opr = "UNKNOWN";
		switch(type2)
		{
			case L:
				opr = String.format("literal(%d)", operand2);
				break;
			case A:
			case C:
				opr = String.format("addr(%d)", operand2);
				break;
		}

		resultBuilder.append(opr);
		
		return resultBuilder.toString();
	}
}
