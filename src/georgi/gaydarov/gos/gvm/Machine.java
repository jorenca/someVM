package georgi.gaydarov.gos.gvm;

import georgi.gaydarov.gos.gvm.coder.Instruction;
import georgi.gaydarov.gos.gvm.coder.OperandType;
import georgi.gaydarov.gos.gvm.coder.Operation;

public class Machine {
	private byte[] cache;
	private byte[] memory;
	private byte cmdRegister;
	
	public Machine(int cacheSize, int memorySize) {
		cache = new byte[cacheSize];
		memory = new byte[memorySize];
	}
	
	public void execute(Operation operation)
	{
		Instruction instruction = operation.getInstruction();
		OperandType type1 = operation.getType1();
		OperandType type2 = operation.getType2();
		short operand1 = operation.getOperand1();
		short operand2 = operation.getOperand2();
		switch(instruction)
		{
			case MOV:
				byte data = getData(type2, operand2);
				setData(type1, operand1, data);
				break;
				
			case ADD:
				data = getData(type1, operand1);
				data += getData(type2, operand2);
				setData(type1, operand1, data);
				break;
				
			case CALL:
				data = getData(type2, operand2);
				invokeFunction((byte)operand1, data);
				break;
				
		}
	}
	
	private void setData(OperandType space, short address, byte data)
	{
		if(space == OperandType.C)
		{
			cache[address] = data;
		}
		memory[address] = data;
	}
	private byte getData(OperandType space, short address)
	{
		if(space == OperandType.C)
		{
			return cache[address];
		}
		else if(space == OperandType.L)
		{
			return (byte)address;
		}
		return memory[address];
	}
	
	private static void invokeFunction(byte function, byte data)
	{
		switch(function)
		{
			case 100:
				System.out.println(data);
				break;
		}
	}
}
