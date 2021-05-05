package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import backend.JavaFilesHandler;

import org.junit.jupiter.api.Test;


@RunWith(JUnitPlatform.class)
@SelectClasses({JavaFilesHandler.class})
class JavaFilesHandlerTest {

	@Test
	void test_01() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_1");
		assertEquals(jfh.getNumberOfPackages(), 0);
	}
	
	@Test
	void test_02() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_2");
		assertEquals(jfh.getNumberOfPackages(), 1);
	}
	
	@Test
	void test_03() throws Exception {
		JavaFilesHandler jfh = new JavaFilesHandler("test_subjects/test_subject_3");
		assertEquals(jfh.getNumberOfPackages(), 1);
	}
	
	
	
	

}
