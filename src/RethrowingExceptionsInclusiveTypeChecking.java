import java.util.ArrayList;
import java.util.List;

/*
 * http://stackoverflow.com/questions/26207241/jdk-7-catching-multiple-exception-types-and-rethrowing-exceptions-with-improved
 * http://stackoverflow.com/questions/19913834/rethrowing-an-exception-why-does-the-method-compile-without-a-throws-clause
 * 
 * 1. e is final
 * 2. IOException | Exception
 */

public class RethrowingExceptionsInclusiveTypeChecking {
	
	/*
	 * JAVA 6 Possible ways
	 * 
	 public void rethrowException(String exceptionName) throws IOException, SQLException{
    	try {
      		if (exceptionName.equals("First")) {
        		throw new IOException();
      		} else {
        		throw new SQLException();
      		}
    	} catch (IOExceptione) {
      		throw e;
    	}catch (SQLException) {
      		throw e;
    	}
  	}
  
  	public void rethrowException(String exceptionName) throws Exception {
    	try {
      		if (exceptionName.equals("First")) {
        		throw new IOException();
      		} else {
        		throw new SQLException();
      		}
    	} catch (Exception e) {
      			throw e;
    	}
  	}
	 */
	
	public static void main(String[] args) {
		try {
			rethrowException("First");
		} catch (IOException   e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void rethrowException(String exceptionName)
			throws IOException, SQLException { //Exception {
		try {
			if (exceptionName.equals("First")) {
				throw new IOException();
			} else {
				throw new SQLException();
			}
		} catch (Exception e) { //e is final by default, to make InclusiveTypeChecking
			// e = new Exception();
			throw e; //InclusiveTypeChecking by casting Exception to IOException/SQLException
		} 
	}
	
	
	static class IOException extends Exception {
	}

	static class SQLException extends Exception {
	}
	

	
	
	/*
	 * VALID JAVA7 http://stackoverflow.com/questions/19913834/rethrowing-an-exception-why-does-the-method-compile-without-a-throws-clause
	 * 
	 * Since there is no real through from TRY block at compile time.
	 * 
	 * public void throwsOrNotThrowsThatsTheQuestion() {
    	try {

        	// Any processing //error: unreported exception Exception; must be caught or declared to be thrown

    	} catch (Exception e) {
        	throw e;
    	}
	  }
	 */
	
}
