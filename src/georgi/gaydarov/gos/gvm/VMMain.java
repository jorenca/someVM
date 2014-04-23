package georgi.gaydarov.gos.gvm;

import georgi.gaydarov.gos.gvm.coder.Decoder;
import georgi.gaydarov.gos.gvm.coder.Operation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class VMMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Decoder decoder = new Decoder("out.gex");
		Machine machine = new Machine(1024, 16000);
		
		while(decoder.hasMore())
		{
			Operation op = decoder.getNextOperation();
			System.out.println(op.getReadable());
			machine.execute(op);
		}
	}
	

}
