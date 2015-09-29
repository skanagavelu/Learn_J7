import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * https://docs.oracle.com/javase/tutorial/essential/io/fileio.html
 */
public class FeaturingNIO2Path {
	public static void main(String[] args) {
		File f = new File(".");
		String path = f.getAbsolutePath();
		System.out.println(path);

		
        //Previous time
		try {
			path = f.getCanonicalPath();
			System.out.println(path);			
			if((f.exists() && !f.isDirectory()) || f.isFile()) { /* This is a file */ } //Files.exists(path) && Files.isDirectory(Path, LinkOption);
		} catch (IOException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
//		System.out.println(System.getProperty("user.dir")); //user.home is the user's home directory
		
		/*
		 * There are three new packages introduced as part of java7
		 *  java.nio.file
		 *	java.nio.file.attribute
 		 *	java.nio.file.spi
		 */
		
		
		
		/*
		 * What Is a Path? Path class is a programmatic representation of a path
		 * in the file system. A Path instance contains the information used to
		 * specify the location of a file or directory. A Path might consist of
		 * just a single directory or file name.
		 * 
		 * A Path instance reflects the underlying platform. In the Solaris OS,
		 * a Path uses the Solaris syntax (/home/joe/foo) and in Microsoft
		 * Windows, a Path uses the Windows syntax (C:\home\joe\foo).
		 * 
		 * A Path is system dependent. You cannot compare a Path from a
		 * Solaris file system and expect it to match a Path from a Windows file
		 * system, even if the directory structure is identical and both
		 * instances locate the same relative file.
		 */

		/*
		 * Creating PATH
		 */

		Path p1 = Paths.get("/tmp/foo");
		// Path p2 = Paths.get(args[0]);
		Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java")); // [scheme:]scheme-specific-part[#fragment]
		Path p4 = Paths.get(System.getProperty("user.home"), "logs", "foo.log");

		// The Paths.get method is shorthand for the following code:
		Path p5 = FileSystems.getDefault().getPath("/users/sally");

		// Microsoft Windows syntax
		Path p7 = Paths.get("C:\\home\\joe\\foo");
		
		// Solaris syntax
		p7 = Paths.get("/home/joe/foo");

		
		
		/*
		 * NOTE: None of these methods requires that the file corresponding to
		 * the Path exists.
		 */
		System.out.println("\n\nUtility methods on PATH:");
		System.out.println("--------------------------");
		System.out.format("toString     :%s%n", p7.toString());
		System.out.format("getFileName  :%s%n", p7.getFileName());
		System.out.format("getName(0)   :%s%n", p7.getName(0));
		System.out.format("getNameCount :%d%n", p7.getNameCount());
		System.out.format("subpath(0,2) :%s%n", p7.subpath(0, 2));
		System.out.format("getParent    :%s%n", p7.getParent());
		System.out.format("getRoot      :%s%n", p7.getRoot());
		
		

		System.out.println("\n\nRemoving Redundancies:");
		System.out.println("--------------------------");
		/*
		 * Removing Redundancies From a Path 
		 * e.g. 
		 * /home/./joe/foo
		 * /home/sally/../joe/foo
		 * 
		 * It is important to note that normalize doesn't check at the file
		 * system when it cleans up a path.
		 */
		Path p8 = Paths.get("D:/cygwin/bin/../etc").normalize();
		System.out.println("Normalize the redundancies :" + p8);

		
		
		/*
		 * The toRealPath method returns the real path of an existing file. This
		 * method performs several operations in one: 
		 * If true is passed to this method and the file system supports symbolic links, this method resolves any symbolic links in the path. 
		 * If the Path is relative, it returns an absolute path. 
		 * If the Path contains any redundant elements, it returns a path with those elements removed.
		 */
		p8 = Paths.get("C:/cygwin/bin/.");
		try {
			System.out.format("\nToRealPath: %s%n", p8.toRealPath(LinkOption.NOFOLLOW_LINKS));
		} catch (IOException e) {
		}
		p8 = Paths.get("C:/cygwin/bin/../etc");
		try {
			System.out.format("ToRealPath: %s%n", p8.toRealPath());
		} catch (IOException e) {
		}

		
		
		
		Path p9 = Paths.get("/home/logfile");
		// Result is file:///home/logfile
		System.out.format("\nToURI %s%n", p9.toUri());

		Path inputPath = Paths.get("/joe/foo");
		Path fullPath = inputPath.toAbsolutePath();
		System.out.format("\nToAbsolutePath %s%n", fullPath);
		
		

		System.out.println("\n\nJoining & Comparing PATHS:");
		System.out.println("------------------------------");
		// Joining Two Paths
		Path p10 = Paths.get("/home/joe/foo");
		// Result is /home/joe/foo/bar
		System.out.format("RESOLVE:     %s%n", p10.resolve("bar"));


		// Relativize Two Paths
		Path p11 = Paths.get("/home/joe/foo/bar/base");
		Path p12 = Paths.get("/home/joe/sally/jaz");

		System.out.format("RELATIVIZE:  %s%n", p11.relativize(p12));
		System.out.format("RELATIVIZE:  %s%n", p12.relativize(p11));

		System.out.format("RESOLVE:     %s%n",p11.resolve(p12));
		System.out.format("RESOLVE:     %s%n",p12.resolve(p11));

		
		
		// Comparing Two Paths
		Path p13 = Paths.get("/home/joe/foo/bar/base");
		Path p14 = Paths.get("/home/joe/sally/jaz/base");

		if (!p13.equals(p14) || !p13.equals("/home/joe/sally/jaz/base")) {
			System.out.println("\nPATHS are not equal!");
		}
		if (p13.startsWith(p14) || p13.startsWith("/home/joe/")) {
			System.out.println("PATHS are startsWith /home/joe/ .");
		}
		if (p13.endsWith(p14) || p13.endsWith("base")) {
			System.out.println("PATHS are endswith base .");
		}
		
		
		System.out.println("\n\nIterating PATH:");
		System.out.println("-------------------------");
		/*
		 * The Path class implements the Iterable interface. The iterator method
		 * returns an object that enables you to iterate over the name elements
		 * in the path. The first element returned is that closest to the root
		 * in the directory tree. The following code snippet iterates over a
		 * path, printing each name element:
		 */

		Path p15 = Paths.get("/home/joe/foo/bar/base");
		;
		for (Path name : p15) {
			System.out.println(name);
		}

		/*
		 * The Path class also implements the Comparable interface. You can
		 * compare Path objects by using compareTo which is useful for sorting.
		 */
		
		
		//New way of creating File objects
		File file = p8.toFile();

	}
}
