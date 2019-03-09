

import java.io.IOException;
import java.sql.SQLException;

import myapp.DemoServlet;
import myapp.IssueServlet;

public class testing {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub

		
		IssueServlet testing = new IssueServlet();
		
		testing.issueTesting();
		//Runtime.getRuntime().exec("java -jar plantuml.1.2019.0.jar issue-relations.txt");
		
	}

}
