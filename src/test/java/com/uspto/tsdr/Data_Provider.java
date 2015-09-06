package com.uspto.tsdr;
import org.testng.annotations.DataProvider;

public class Data_Provider {
  @DataProvider(name = "statusTestData")
  public static Object[][] getData(){
	  //rows--> no of iteration
	  //cols --> no of data
	  Object[][] data = new Object[2][4];
	  data[0][0] = "SERIAL_NO";
	  data[0][1] = "78787878";
	  data[0][2] = "MAC MEMPHIS ATHLETIC CAMPUS";
	  data[0][3] = "Abandoned because the applicant failed to respond or filed a late response to an Office action. To view all documents in this file, click on the Trademark Document Retrieval link at the top of this page.";
	  data[1][0] = "SERIAL_NO";
	  data[1][1] = "76716495";
	  data[1][2] = "CIRCLE OF BROTHERS IN ISLAM";
	  data[1][3] = "Application has been published for opposition. The opposition period begins on the date of publication.";
	  return data;
  }
  
}
