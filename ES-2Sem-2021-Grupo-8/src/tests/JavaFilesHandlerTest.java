package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import backend.JavaFilesHandler;
import org.junit.jupiter.api.Test;

@RunWith(JUnitPlatform.class)
@SelectClasses({ JavaFilesHandler.class })
class JavaFilesHandlerTest {
	

	/**
	 * The fist six test cases test the creation of a JavaFilesHandler instance and
	 * its packages from the project path
	 */
	

	/**
	 * Creates a JavaFilesHandler instance of a java project that is empty
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_01() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_1");
		assertEquals(jfh.getProjectName(), "test_subject_1");
		assertEquals(jfh.getNumberOfPackages(), 0);
	}

	/**
	 * Creates a JavaFilesHandler instance of a java project that has one package
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_02() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_2");
		assertEquals(jfh.getProjectName(), "test_subject_2");
		assertEquals(jfh.getNumberOfPackages(), 1);
		assertEquals(jfh.getPackage_list().get(0).getName(), "package_1");
	}

	/**
	 * Creates a JavaFilesHandler instance of a java project that has a default
	 * package
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_03() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_3");
		assertEquals(jfh.getProjectName(), "test_subject_3");
		assertEquals(jfh.getNumberOfPackages(), 1);
		assertEquals(jfh.getPackage_list().get(0).getName(), "default");
	}

	/**
	 * Creates a JavaFilesHandler instance of a java project that has a default
	 * package and a package
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_04() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_4");
		assertEquals(jfh.getProjectName(), "test_subject_4");
		assertEquals(jfh.getNumberOfPackages(), 2);
		assertEquals(jfh.getPackage_list().get(0).getName(), "default");
		assertEquals(jfh.getPackage_list().get(1).getName(), "package_1");
	}

	/**
	 * Creates a JavaFilesHandler instance of a java project that has a package
	 * inside another package
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_05() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_5");
		assertEquals(jfh.getProjectName(), "test_subject_5");
		assertEquals(jfh.getNumberOfPackages(), 2);
		assertEquals(jfh.getPackage_list().get(0).getName(), "package_1.package_2");
		assertEquals(jfh.getPackage_list().get(1).getName(), "package_1");
	}

	/**
	 * Creates a JavaFilesHandler instance of a java project that has a package
	 * inside another package and a default package
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_06() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_6");
		assertEquals(jfh.getProjectName(), "test_subject_6");
		assertEquals(jfh.getNumberOfPackages(), 3);
		assertEquals(jfh.getPackage_list().get(0).getName(), "default");
		assertEquals(jfh.getPackage_list().get(1).getName(), "package_1.package_2");
		assertEquals(jfh.getPackage_list().get(2).getName(), "package_1");
	}
	

	/**
	 * The next six test cases test the creation of a JavaFilesHandler instance and
	 * its classes from the project path
	 */

	
	/**
	 * Creates a JavaFilesHandler instance of a java project that is empty
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_07() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_1");
		assertEquals(jfh.countTotalOfClasses(), 0);
	}
	
	/**
	 * Creates a JavaFilesHandler instance of a java project that has one package with two java classes inside it
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_08() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_2");
		assertEquals(jfh.countTotalOfClasses(), 2);
	}

	/**
	 * Creates a JavaFilesHandler instance of a java project that has a default
	 * package with one java class inside it
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_09() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_3");
		assertEquals(jfh.countTotalOfClasses(), 1);
	}
	
	/**
	 * Creates a JavaFilesHandler instance of a java project that has a package
	 * with one java class and a default package with one java class
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_10() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_4");
		assertEquals(jfh.countTotalOfClasses(), 2);
	}
	
	/**
	 * Creates a JavaFilesHandler instance of a java project that has a package
	 * inside another package with one java class inside the second package
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_11() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_5");
		assertEquals(jfh.countTotalOfClasses(), 1);
	}
	
	/**
	 * Creates a JavaFilesHandler instance of a java project that has a package
	 * inside another package with one java class inside each package and a default
	 * package with another java class
	 * 
	 * @throws Exception when the given folder doesn't exist. This exception is
	 *                   handled by the graphical user interface
	 */
	@Test
	void test_12() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_6");
		assertEquals(jfh.countTotalOfClasses(), 3);
	}


}
