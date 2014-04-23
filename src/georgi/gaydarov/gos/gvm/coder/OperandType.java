package georgi.gaydarov.gos.gvm.coder;


/**
 * Describes the instruction operand types.
 * 
 * @author Georgi Gaydarov
 *
 */
public enum OperandType
{
	/**
	 * Address in the memory.
	 */
	A,
	/**
	 * Address in the cache.
	 */
	C,
	/**
	 * Literal value.
	 */
	L;
}
