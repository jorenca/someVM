import java.util.LinkedList;
import java.util.List;


public class VMMain {

	private static final byte MOV = 0;
	private static final byte DIV = 1;
	private static final byte PRINT = 2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Command> program = new LinkedList<Command>();
		
		program.add(new Command(MOV).pAL(0, 100));
		program.add(new Command(MOV).pAL(1, 5));
		program.add(new Command(DIV).pAA(0, 1));
		program.add(new Command(PRINT).pA(0));
		execute(program);

	}
	
	public static byte[] memory = new byte[2048];
	public static void execute(List<Command> program)
	{
		for(Command c : program)
		{
			switch(c.c())
			{
			case MOV:
				memory[(short)c.p()[0]] = (byte)c.p()[1];
				break;
			case PRINT:
				System.out.println(memory[(short)c.p()[0]]);
				break;
			case DIV:
				memory[(short)c.p()[0]] /= memory[(short)c.p()[1]];
				break;
			}
		}
	}

}
