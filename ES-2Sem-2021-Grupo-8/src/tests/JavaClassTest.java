package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import backend.JavaClass;

/**
 * Test Cases that validate the creation and usage of instances of a JavaClass object
 * 
 * @author ES-2Sem-2021-Grupo-8
 * @version 1.0
 *
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({ JavaClass.class })
class JavaClassTest {

	/**
	 * Creates a JavaClass instance of a java class that has 14 lines and 4 methods with the complexity of one each
	 */
	@Test
	void test_01() {
		File file = new File("test_subjects/test_subject_2/src/package_1/FirstClassOfPackageOne.java");
		JavaClass cls = new JavaClass("FirstClassOfPackageOne", file);
		assertEquals(cls.getNOMClass(), 4);
		assertEquals(cls.getLOCClass(), 14);
		assertEquals(cls.getWMCClass(), 4);
	}
	
	/**
	 * Creates a JavaClass instance of a java class that has 5 lines and 1 main method with the complexity of one
	 */
	@Test
	void test_02() {
		File file = new File("test_subjects/test_subject_2/src/package_1/SecondClassOfPackageOne.java");
		JavaClass cls = new JavaClass("SecondClassOfPackageOne", file);
		assertEquals(cls.getNOMClass(), 1);
//		assertEquals(cls.getLOCClass(), 5);
		assertEquals(cls.getWMCClass(), 1);
	}
	
	/**
	 * Creates a JavaClass instance of a java class that has 2 lines and no methods
	 */
	@Test
	void test_03() {
		File file = new File("test_subjects/test_subject_3/src/ClassOfDefaultPackage.java");
		JavaClass cls = new JavaClass("ClassOfDefaultPackage", file);
		assertEquals(cls.getNOMClass(), 0);
//		assertEquals(cls.getLOCClass(), 2);
		assertEquals(cls.getWMCClass(), 0);
	}
	
	/**
	 * Creates a JavaClass instance of a java class that has 20 lines and 3 methods
	 */
	@Test
	void test_04() {
		File file = new File("test_subjects/test_subject_4/src/ClassOfDefaultPackage.java");
		JavaClass cls = new JavaClass("ClassOfDefaultPackage", file);
		assertEquals(cls.getNOMClass(), 3);
		assertEquals(cls.getLOCClass(), 20);
		assertEquals(cls.getWMCClass(), 5);
	}
	
	

}
