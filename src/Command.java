
public class Command {
	private byte cmd;
	public Command(byte cmd)
	{
		this.cmd = cmd;
	}
	public byte c()
	{
		return cmd;
	}
	
	private Object[] params;
	private Command setParams(Object[] params)
	{
		this.params = params;
		return this;
	}
	
	public Command pAA(int addr1, int addr2)
	{
		return setParams(new Object[]{(short)addr1, (short)addr2});
	}
	public Command pAL(int addr, int literal)
	{
		return p((short)addr, (byte)literal);
	}
	public Command pA(int addr)
	{
		return p((short)addr);
	}
	

	private Command p(short addr, byte literal)
	{
		return setParams(new Object[]{addr, literal});
	}
	private Command p(short addr)
	{
		return setParams(new Object[]{addr});
	}
	
	public Object[] p()
	{
		return params;
	}
}
