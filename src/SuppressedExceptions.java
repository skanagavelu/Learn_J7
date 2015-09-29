
/**
 *  only one exception can be thrown by a method (per execution)
 *  but it is possible in the case of a try-with-resources for multiple exceptions
 *  to be thrown (one might be thrown in the try block and another might be thrown 
 *  from the implicit finally provided by the try-with-resources).
 *  
 *  The compiler has to determine which of these to "really" throw.
 *  It chooses to throw the exception raised in the implicit code (the code in the finally block)
 *  rather than the one thrown by the explicit code (the try block).
 *  Therefore the exception(s) thrown in the implicit block are suppressed (ignored).
 *  The only occurs in the case of multiple exceptions.
 * 
 * http://stackoverflow.com/questions/7849416/what-is-a-suppressed-exception/27033358#27033358
 *
 */
public class SuppressedExceptions {
	
	public static void main(String[] args) throws Exception {
		try {
			callTryFinallyBlock();
		} catch (Exception e) {
			e.printStackTrace();
			for(Throwable t: e.getSuppressed())
			{
				//t.printStackTrace();
			}
		}
	}

	private static void callTryFinallyBlock() throws Exception {
		Exception t = null;
		try 
		{
			throw new TryException();
		}
		catch (Exception e) {
			t = e;
		}
		finally
		{
			FinallyException fEx = new FinallyException();
			if(t != null)t.addSuppressed(fEx);
			throw t;
		}
	}
}

class TryException extends Exception {
}

class FinallyException extends Exception {
	FinallyException(Throwable e) {
		super(e);
	}
	FinallyException(){
		
	}
}

