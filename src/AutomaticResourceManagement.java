import java.io.IOException;


public class AutomaticResourceManagement {

	/*
	 * 1. Order of close/catch execution
	 * 2. Exception thrown from close() is suppressed
	 * 3. Order of multiple resource declaration
	 * 4. Try without finally?
	 */
	public static void main(String[] args) throws Exception {
		

		try( Resource r = new Resource()){     
			System.out.println("Try completed!");
			throw new Exception("Exception at try!!");
		} 
		catch (Exception e){
			System.out.println("Catch is executed!");
			e.printStackTrace();
		} finally {
			System.out.println("Fianlly");
		}

		
		try (OpenDoor door = new OpenDoor(); //No finally is required?
		     OpenWindow window = new OpenWindow()) {
		}
		
		//try{ } Syntax error, insert "Finally" to complete TryStatement

	}
}

class Resource implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("Close() is invoked!!");
		throw new IOException("Exception at close!!");
	}
	
}




class OpenDoor implements AutoCloseable {
  public OpenDoor() { System.out.println("The door is open.");};
  public void close() throws Exception{
    System.out.println("The door is closed.");
  }
}
	 
	 
class OpenWindow implements AutoCloseable {
  public OpenWindow() { System.out.println("The window is open.");};
  public void close() throws Exception {
    System.out.println("The window is closed.");
  }
}


