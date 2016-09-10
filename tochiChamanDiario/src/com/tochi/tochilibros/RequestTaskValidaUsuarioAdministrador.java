package com.tochi.tochilibros;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.FileOutputStream.*;

import java.io.FileInputStream.*;


import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context; 
import android.os.AsyncTask;
import android.util.Log; 
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import android.content.Intent;
import java.net.URLEncoder;


class RequestTaskValidaUsuarioAdministrador extends AsyncTask<TextView, String, String>{
	TextView t;
	String result = "fail";
	
	String DuenoId=null;
	
	Activity nextActivity=null;
	
	Context nextC=null;
	
   	 
    String extraMessageUser=null;

	private ProgressDialog pd;
	
	public RequestTaskValidaUsuarioAdministrador(Context va) {
		System.out.println("set the activty in asyncTask - 1");
//	    this.nextActivity = activity;
	    this.nextC = va;
	    System.out.println("set the activty in asyncTask - 2");
	}
	

	@Override
	protected void onPreExecute() {
	    pd = ProgressDialog.show(nextActivity, "Signing in",
	            "Please wait while we are signing you in..");
	}
	
    @Override
    protected String doInBackground(TextView... params) {
    	System.out.println("doInBackground - 1");
//    	this.t = params[0];
    	System.out.println("doInBackground - 2");
    	
    	
		return ValidaDueno(this.DuenoId);
		

    }

	final String ValidaDueno(
			String DuenoId
			) 
	{
		System.out.println("ValidaDueno - 1");
		
		Constants constantValues=new Constants();
		try{
			DuenoId=java.net.URLEncoder.encode(DuenoId, "UTF-8");
		}catch(UnsupportedEncodingException e){
			System.out.println("ValidaDueno - 12 error");
			e.printStackTrace();
		}
		String url = constantValues.URL_GET_USER_ALL_INFO+DuenoId;
		
		
		this.extraMessageUser=DuenoId;
				
		BufferedReader inStream = null;
		System.out.println("ValidaDueno - 2"+url);
		try {
			HttpClient httpClient = new DefaultHttpClient();
			System.out.println("ValidaDueno - 3");
			HttpGet httpRequest = new HttpGet(url);
			System.out.println("ValidaDueno - 4");
			HttpResponse response = httpClient.execute(httpRequest);
			System.out.println("ValidaDueno - 5");
			inStream = new BufferedReader(
				new InputStreamReader(
					response.getEntity().getContent()));
			System.out.println("ValidaDueno - 6");
			StringBuffer buffer = new StringBuffer("");
			String line = "";
			System.out.println("ValidaDueno - 7");
			String NL = System.getProperty("line.separator");
			System.out.println("ValidaDueno - 8");
			while ((line = inStream.readLine()) != null) {
				buffer.append(line + NL);
			}
			System.out.println("ValidaDueno - 9");
			inStream.close();
			System.out.println("ValidaDueno - 10");
			result = buffer.toString();			
			System.out.println("GetSomething - 11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ValidaDueno - 12 error");
			e.printStackTrace();
		} finally {
			System.out.println("ValidaDueno - 13");
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("ValidaDueno - 14");
		 pd.dismiss();
		return result;
	}
	
    @Override
    protected void onPostExecute(String result) {
    	System.out.println("onPostExecute - 1");
        Toast.makeText(nextC, result, Toast.LENGTH_LONG).show();
        System.out.println("onPostExecute - 2");
//        nextActivity.startActivity(new Intent(nextActivity, ListBuildingsActivity.class));
        
        Intent intent2 =new Intent(nextC, ListIdeasActivity.class);
	   	intent2.putExtra("cual_usuario",this.extraMessageUser+"");
        System.out.println("Login activity passing,value:"+this.extraMessageUser); 
                
        nextC.startActivity(intent2);
        
    	System.out.println("onPostExecute - 3");
    }
    

}
