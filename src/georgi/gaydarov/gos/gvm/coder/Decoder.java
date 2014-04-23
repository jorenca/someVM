package georgi.gaydarov.gos.gvm.coder;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class responsible for decoding binary executables.
 * 
 * @author Georgi Gaydarov
 *
 */
public class Decoder {
	private FileReader reader;
	
	public Decoder(File inFile) throws FileNotFoundException {
		reader = new FileReader(inFile);
	}
	public Decoder(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public Operation getNextOperation() throws IOException
	{
		Operation result = new Operation();
		byte instructionCode = (byte)reader.read();
		Instruction instruction = InstructionDecoder.getInstruction(instructionCode);
		result.setInstruction(instruction);
		
		OperandType operand1type=null, operand2type=null;
		byte operandFormat = (byte)reader.read();
		switch(operandFormat >> 4)
		{
		case 0: operand1type = OperandType.A; 
			break;
		case 1: operand1type = OperandType.C; 
			break;
		case 2: operand1type = OperandType.L; 
			break;
		}
		switch(operandFormat & 15) // magic
		{
		case 0: operand2type = OperandType.A; 
			break;
		case 1: operand2type = OperandType.C; 
			break;
		case 2: operand2type = OperandType.L; 
			break;
		}
		result.setType1(operand1type);
		result.setType2(operand2type);
		
		if(operand1type == OperandType.L)
		{
			byte operand1Value = (byte)reader.read();
			result.setOperand1(operand1Value);
		}
		else
		{
			int operand1Value = reader.read();
			operand1Value = (operand1Value<<8) | reader.read();
			result.setOperand1((short)operand1Value);
		}
		
		if(operand2type == OperandType.L)
		{
			byte operand2Value = (byte)reader.read();
			result.setOperand2(operand2Value);
		}
		else
		{
			int operand2Value = reader.read();
			operand2Value = (operand2Value<<8) | reader.read();
			result.setOperand2((short)operand2Value);
		}
		
		return result;
	}
	
	public boolean hasMore() throws IOException
	{
		return reader.ready();
	}
}
