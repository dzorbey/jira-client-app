/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.app.client.Client;
import com.app.client.CloudStorageHelper;
import com.google.api.client.http.HttpResponse;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;

public class IssueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Client client;
	
	private static HttpResponse httpResponse;
	private static JsonReader reader;

	
	public IssueServlet() {
	   client = new Client("","");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/plain");
		
	    URI uriPR =
		        client.getService().getEndpoint(
		            "https://jira-stats-api.appspot.com/stats/issues");

	    Map<String, String> headers = new HashMap<String, String>();
	    
	    headers.put("inputHeaderProject", req.getHeader("inputHeaderProject"));
	    headers.put("inputHeaderBoard", req.getHeader("inputHeaderBoard"));
	    
	    JsonObject jsonObject = executeHttpRequest(uriPR,headers);
	    
	    String jsonResult = jsonObject.toString();
	    
	    //JsonObject tmp = jsonObject.getJsonObject("xata");
	    
	    //System.out.println(tmp.getString("name"));
	    
	    //System.out.println(jsonObject.get("xata"));

	    //String path = writing("selam");
	    //String myresult = readFile();
	    //System.out.println("readFile : " + readFile());
	    //Runtime.getRuntime().exec("java -jar plantuml.1.2019.0.jar issue-relations-created.txt");
	    
	    //"{ \"name\": \"World\" }"
	    //resp.getWriter().println("{ \"name\": \"" +  path   + "\" }");
	    
	    resp.getWriter().println(jsonResult);
	   
	    //resp.getWriter().println(tmp.getString("name"));		
	}
	
	
	
	public String issueTesting() throws IOException {

	   /* URI uriPR =
	        client.getService().getEndpoint(
	            "https://jira-stats-api.appspot.com/stats/issues");

	    Map<String, String> headers = new HashMap<String, String>();
	    
	    headers.put("inputHeaderProject", "ESBEP");
	    headers.put("inputHeaderBoard", "ESBEP BUGS from UAT");
	    
	    JsonObject jsonObject = executeHttpRequest(uriPR,headers);
	   */ //JsonObject tmp = ((JsonObject) jsonObject).getJsonObject("xata");
	    
	    //System.out.println(tmp.getString("name"));
	    
	    //System.out.println(jsonObject.get("xata"));

		
		String targetFileStr = "testing my file";
		CloudStorageHelper helper = new CloudStorageHelper();
		
		 // The name for the new bucket
        String bucketName = "vendor-bucket13";  // "my-new-bucket";

        // Creates the new bucket
        Bucket bucket = helper.getStorage().create(BucketInfo.of(bucketName));
		
        String value = "Hello, World!";
        byte[] bytes = value.getBytes("UTF_8");
        Blob blob = bucket.create("my-first-blob", bytes);
        
        
		BlobId blobId = BlobId.of(bucketName, "my_blob_name");
        //Blob blob = bucket.create("my_blob_name", "a simple blob".getBytes("UTF-8"), "text/plain");
        //Blob blob = bucket.create("my_blob_name", targetFileStr.getBytes("UTF-8"), "text/plain");

		
		
	    //String path = writing("merhaba");
	    //Runtime.getRuntime().exec("java -jar plantuml.1.2019.0.jar issue-relations-created.txt");
	    //System.out.println("readFilePath : " + path);
	    
	    
	    return null;//sonObject.toString();
	}
	
	
	
	
	public String readFile() throws FileNotFoundException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("issue-relations-created.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		}
	}
	
		
	 public String writing(String data) {
		    try {
		      File statText = new File("issue-relations-created.txt");
		      FileOutputStream is = new FileOutputStream(statText);
		      OutputStreamWriter osw = new OutputStreamWriter(is);
		      Writer w = new BufferedWriter(osw);

		      w.write(data);
		      
		      w.close();
		      return statText.getAbsolutePath() + " , : " + statText.getCanonicalPath();
		    } catch (IOException e) {
		      System.err.println("Problem writing to the file issue-relations.txt");
		      return null;
		    }
		    
	 }
	
	
	

	  public JsonObject executeHttpRequest(URI uriPR, Map<String, String> headers) throws IOException {

		    try {
		      httpResponse = client.getService().executeURIHeader(uriPR,headers);
		    } catch (InterruptedException e) {
		      System.out.println("interrupted");
		    }

		    if (httpResponse != null) {
		      reader = Json.createReader(httpResponse.getContent());
		      return reader.readObject();
		    }
		    InputStream stream = new ByteArrayInputStream("".getBytes());
		    reader = Json.createReader(stream);
		    return reader.readObject();

		  }
	
	
	  public JsonObject executeHttpRequest(URI uriPR) throws IOException {

		    try {
		      httpResponse = client.getService().executeURI(uriPR);
		    } catch (InterruptedException e) {
		      System.out.println("interrupted");
		    }

		    if (httpResponse != null) {
		      reader = Json.createReader(httpResponse.getContent());
		      return reader.readObject();
		    }
		    InputStream stream = new ByteArrayInputStream("".getBytes());
		    reader = Json.createReader(stream);
		    return reader.readObject();

		  }
	  
	  
	  
	  
	  
	  
	  
	  
}