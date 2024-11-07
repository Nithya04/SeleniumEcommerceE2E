package ultilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

//    @DataProvider(name="LoginData")
//    public String[][] getData() throws IOException {
//    	try { System.out.println("DataProvider called"); // Confirmation
//        String path = ".\\testData\\userDetails.xlsx"; // Taking Excel file from testData folder
//        System.out.println("Attempting to load file from: " + path);
//        XLUtility xlutil = new XLUtility(path); // Creating an object for ExcelUtility
//        
//        int totalrows = xlutil.getRowCount("Sheet1");
//       // int totalcols = xlutil.getCellCount("Sheet1", 1);
//        int totalcols=3;
//        String logindata[][] = new String[totalrows][totalcols]; // Created a two-dimensional array to store Excel data
////Reads all data from excel and puts in 2-D Array
//        for(int i = 1; i <= totalrows; i++) { // Read the data from Excel and store it in the array
//            for(int j = 0; j < totalcols; j++) { // i is row, j is column
//            	  logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j );
//            }
//        }
//        System.out.println("Data loaded from Excel: ");
//        return logindata; // Returning two-dimensional array
//    }
//    catch (IOException e) {
//        e.printStackTrace();
//        throw new RuntimeException("Error loading data from Excel file: " + e.getMessage());
//    }
//}
//   
    
    @DataProvider(name = "LoginData1")
    public Object[][] getData1() {
        System.out.println("DataProvider called");

        // Hardcoding the test data directly
        Object[][] logindata = {
            {"UserTest1", "test123", "invalid"},
            {"HgtbWK@gmail.com", "GtKkIE5350592452", "valid"},
            {"UserTest3", "test125", "invalid"}
        };

        // Logging the data to verify it’s being passed correctly
        System.out.println("Data loaded directly: ");
        for (Object[] data : logindata) {
            System.out.println("Email: " + data[0] + ", Password: " + data[1] + ", Expected Result: " + data[2]);
        }

        return logindata;
    
}
}
