import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * http://docs.oracle.com/javase/tutorial/essential/io/fileOps.html
 * The Files class is the other primary entrypoint of the java.nio.file package. 
 * This class offers a rich set of static methods for reading, writing, and manipulating files and directories. 
 * The Files methods work on instances of Path objects.
 */
public class FeaturingNIO2Files {
	public static void main(String[] args) {
		
		//Verifying the Existence of a File or Directory
		Path p1 = Paths.get("C:/cygwin/bin/.");
		Files.exists(p1); 
		Files.notExists(p1);
		//http://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#exists-java.nio.file.Path-java.nio.file.LinkOption...-
		
		
		
		//Checking File Accessibility
		boolean isRegularExecutableFile = Files.isWritable(p1) &
		         Files.isReadable(p1) & Files.isExecutable(p1);    //OTHER WAY: file.canWrite() file.canRead() file.canExecute()
		//http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6203387
		
		
		
		
		
		//Checking Whether Two Paths Locate the Same File
		Path p2 = Paths.get("C:/cygwin/bin/.");
		try {
			if (Files.isSameFile(p1, p2)) {
			    // Logic when the paths locate the same file
			}
		} catch (IOException e) {
		}
		
		
		/*
		 * Deleting a File or Directory
		 * 
		 * You can delete files, directories or links. With symbolic links, the link is deleted and not the target of the link. With directories, the directory must be empty, or the deletion fails.
		 */
		Path p3 = null;
		
		try {
			p3 = Paths.get("/home/logfile");
		    Files.delete(p3);
			Files.deleteIfExists(p3); //deletes the file, but if the file does not exist, no exception is thrown. Failing silently is useful when you have multiple threads deleting files 
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", p3);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", p3);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		
	    //http://www.journaldev.com/861/4-ways-to-copy-file-in-java
		try {
			Files.copy(p1, p2, java.nio.file.StandardCopyOption.REPLACE_EXISTING, java.nio.file.LinkOption.NOFOLLOW_LINKS ); //CopyOption... options
		} catch (IOException e) {
			e.printStackTrace();
		}
//		 copy(InputStream, Path, CopyOptions...);
//		 copy(Path, OutputStream)
		
		
		//You can move a file or directory by using the move(Path, Path, CopyOption...) method.
		try {
			Files.move(p1, p2, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}
		
		
		//Managing Metadata (File and File Store Attributes)
		//http://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html
		//http://stackoverflow.com/questions/1294989/make-a-file-folder-hidden-on-windows-with-java
		
		
		
		
	}
}
