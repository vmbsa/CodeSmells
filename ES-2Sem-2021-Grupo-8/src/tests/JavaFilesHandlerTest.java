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
	 * The fist six test cases test the creation of packages from the project path
	 */
	

	// working
	@Test
	void test_01() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_1");
		assertEquals(jfh.getNumberOfPackages(), 0);
	}

	// working
	@Test
	void test_02() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_2");
		assertEquals(jfh.getNumberOfPackages(), 1);
		assertEquals(jfh.getPackage_list().get(0).getName(), "package_1");
	}

	// working
	@Test
	void test_03() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_3");
		assertEquals(jfh.getNumberOfPackages(), 1);
		assertEquals(jfh.getPackage_list().get(0).getName(), "default");
	}

	// working
	@Test
	void test_04() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_4");
		assertEquals(jfh.getNumberOfPackages(), 2);
		assertEquals(jfh.getPackage_list().get(0).getName(), "default");
		assertEquals(jfh.getPackage_list().get(1).getName(), "package_1");
	}

	// working
	@Test
	void test_05() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_5");
		assertEquals(jfh.getNumberOfPackages(), 2);
		assertEquals(jfh.getPackage_list().get(0).getName(), "package_1.package_2");
		assertEquals(jfh.getPackage_list().get(1).getName(), "package_1");
	}

	// working
	@Test
	void test_06() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_6");
		assertEquals(jfh.getNumberOfPackages(), 3);
		assertEquals(jfh.getPackage_list().get(0).getName(), "default");
		assertEquals(jfh.getPackage_list().get(1).getName(), "package_1.package_2");
		assertEquals(jfh.getPackage_list().get(2).getName(), "package_1");
	}

}
