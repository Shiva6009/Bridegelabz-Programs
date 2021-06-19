package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class nameandemailvalidation {

	Validation obj = new Validation();
	
	// 	1,2,4,5,6,7,8,9,10,11,13   3,12
  @Test
  public void TestcaseforFirstnameandLastname()
  {
	  Assert.assertEquals(true, obj.FirstnameandLastnameValidation("Kumar"));
  }
  
  @Test
  public void TestcaseforPhonenumber()
  {
	  assertTrue(obj.PhonenumberValidation("91 9589364557"));
  }
  
  @Test
  public void TestcaseforPassword()
  {
	  assertTrue(obj.PasswordValidation("%Siva1197@"));
  }
  
  @Test
  public void TestcaseforEmail()
  {
	  assertTrue(obj.EmailValidation("abc+100@gmail.com"));
  }
  
}


