package georgi.gaydarov.gos.gvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import georgi.gaydarov.gos.gvm.coder.Instruction;
import georgi.gaydarov.gos.gvm.coder.OperandType;
import georgi.gaydarov.gos.gvm.coder.Operation;

/**
 * Class responsible for behaving as the actual <name to be chosen> machine.
 * 
 * @author Georgi Gaydarov
 *
 */
public class Machine {
	private byte[] cache;
	private byte[] memory;
	private byte cmdRegister;
	private int instructionPointer;
	private Operation[] operations;
	
	private List<Operation> opList = new ArrayList<Operation>();
	
	public Machine(int cacheSize, int memorySize) {
		cache = new byte[cacheSize];
		memory = new byte[memorySize];
		cmdRegister = 0;
		instructionPointer = 0;
	}
	
	public void addOperation(Operation operation)
	{
		opList.add(operation);
	}
	public void loadOperations()
	{
		operations = opList.toArray(new Operation[0]);
	}
	
	public void run()
	{
		while(instructionPointer < operations.length)
		{
			execute(operations[instructionPointer]);
			instructionPointer++;
		}
	}
	
	private void execute(Operation operation)
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
				data = invokeFunction((byte)operand1, data);
				setData(type2, operand2, data);
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
	
	private static byte invokeFunction(byte function, byte data)
	{
		switch(function)
		{
			case 100:
				System.out.println(data);
				return data;
				
			case 101:
				System.out.print(">>");
				int input = Byte.parseByte((new Scanner(System.in)).nextLine());
				return (byte) (input & 0xff);
				
				
		}
		return 0;
	}
}
