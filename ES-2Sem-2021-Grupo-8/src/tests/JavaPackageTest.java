package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import backend.JavaPackage;

import org.junit.jupiter.api.Test;


/**
 * Test Cases that validate the creation and usage of instances of a JavaPackage object
 * 
 * @author ES-2Sem-2021-Grupo-8
 *
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({ JavaPackage.class })
class JavaPackageTest {
	
	
	/**
	 * Creates an instance of a JavaPackage object that is empty
	 */
	@Test
	void test_01() {
		File file = new File("test_subjects/test_subject_7/src/package_1");
		JavaPackage pck = new JavaPackage("package_1", file);
		assertEquals(pck.numberOfClasses(), 0);
		assertEquals(pck.number_of_methods_in_package(), 0);
	}
	
	/**
	 * Creates an instance of a JavaPackage object that has two classes inside 
	 * with a total of five methods
	 */
	@Test
	void test_02() {
		File file = new File("test_subjects/test_subject_2/src/package_1");
		JavaPackage pck = new JavaPackage("package_1", file);
		assertEquals(pck.numberOfClasses(), 2);
		assertEquals(pck.number_of_methods_in_package(), 5);
	}
	
	/**
	 * Creates an instance of a JavaPackage object that has a folder inside and no classes
	 */
	@Test
	void test_03() {
		File file = new File("test_subjects/test_subject_5/src/package_1");
		JavaPackage pck = new JavaPackage("package_1", file);
		assertEquals(pck.numberOfClasses(), 0);
		assertEquals(pck.number_of_methods_in_package(), 0);
	}

}
