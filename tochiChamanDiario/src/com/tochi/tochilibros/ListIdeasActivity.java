package com.tochi.tochilibros;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;

import java.util.ArrayList; 

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





import android.os.Bundle; 
import android.app.Activity; 
import android.content.res.TypedArray; 
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
import android.view.View; 
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemClickListener; 
import android.widget.Button;
import android.widget.GridView; 
import android.widget.ImageView;
import android.widget.Toast;


/**
 * 
 * @author javatechig {@link http://javatechig.com}
 * 
 */ 
public class ListIdeasActivity extends Activity {
	private GridView gridView;
  
	public  String role="ADMIN";
	public String urlEmpleado="http://mexico.brinkster.net/sitetochimobil/historias";
	public String empleadoId="ninguno";
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listideas);
		
		//i.putExtra("cual_usuario",message+"");
		Bundle extras = getIntent().getExtras();
	    String  cualesBuldings=null;
	    cualesBuldings="ninguno";
		if (extras != null) {
		    String value = extras.getString("cual_usuario");
		    System.out.println("usuario activity initial step 1,value:"+value);
            
		    if(value.equalsIgnoreCase("BORITI")){
		    	cualesBuldings=",1,2,3";
		    	urlEmpleado=urlEmpleado+".html";
		    }else if(value.equalsIgnoreCase("ARIS")){
		    	cualesBuldings=",1,3,5";
		    	urlEmpleado=urlEmpleado+".html";
		    }else{
		    	cualesBuldings="todos";
		    	this.role="VER";
		    	urlEmpleado=urlEmpleado+value+".html";
		    }
		    empleadoId=value;
		    //getdata for this user
		    //second value is image_users index
		    //third value is the image_building index
		    //third value is the image_recamaras array number

		}

		
   
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl(urlEmpleado);


	}

	
/*
 * 
 * 	original (non-Javadoc)
 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
 * 	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listideas);
		
		//i.putExtra("cual_usuario",message+"");
		Bundle extras = getIntent().getExtras();
	    String  cualesBuldings=null;
	    cualesBuldings="ninguno";
		if (extras != null) {
		    String value = extras.getString("cual_usuario");
		    System.out.println("usuario activity initial step 1,value:"+value);
            
		    if(value.equalsIgnoreCase("BORITI")){
		    	cualesBuldings=",1,2,3";
		    }else if(value.equalsIgnoreCase("ARIS")){
		    	cualesBuldings=",1,3,5";
		    }else{
		    	cualesBuldings="todos";
		    	this.role="VER";
		    }
		    //getdata for this user
		    //second value is image_users index
		    //third value is the image_building index
		    //third value is the image_recamaras array number

		}

		
   
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl("http://mexico.brinkster.net/sitetochimobil/historias.html");


	}
 */

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
 
        
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	System.out.println("paso click gral");
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_search:
            // search action
        	System.out.println("paso click 1");
            return true;
        case R.id.action_location_found:
        	System.out.println("paso click 2");
            // location found
            LocationFound();
            return true;
        case R.id.action_refresh:
            // refresh
//        	goToNewEdificioDetail();
            return true;
        case R.id.action_help:
            // help action
//        	goToListaEdificiosEditar();
            return true;
        case R.id.action_check_updates:
            // check for updates action
//        	PantallaAdministacion();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }


    
    /**
     * Launching new activity
     * */
    private void LocationFound() {
//        Intent i = new Intent(ListBuildingsActivity.this, BuildingsActivity.class);
//        startActivity(i);
        
    }

    /**
     * Launching new activity to display the edificios a editar
     * */ 
    
    public void goToSalir(View view) {
//        finish();
//        System.exit(0);
        Intent i = new Intent(this, EjercicioDiaActivity.class);
	   	i.putExtra("cual_usuario",this.empleadoId+"");
        System.out.println("Lista Ideas activity passing,value:"+this.empleadoId); 
        startActivity(i);
    }
    
    public void goToVisionChaman(View view) {
      Intent i = new Intent(this, AndroidCamera.class);
	   	i.putExtra("cual_usuario",this.empleadoId+"");
        System.out.println("Lista Ideas activity passing,value:"+this.empleadoId); 

      startActivity(i);
      
        
    }    
    
    
    public void goToHome(View view) {
        Intent i = new Intent(this, ListIdeasActivity.class);
	   	i.putExtra("cual_usuario",this.empleadoId+"");
        System.out.println("Lista Ideas activity passing,value:"+this.empleadoId); 
        startActivity(i);
        
          
      }  
    

}
