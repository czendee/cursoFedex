package com.tochi.tochilibros;


import android.view.Menu;


	import java.io.IOException;

	import android.app.Activity;
import android.content.Context;
import android.content.Intent;
	import android.content.pm.ActivityInfo;
	import android.graphics.PixelFormat;
	import android.hardware.Camera;
	import android.os.Bundle;
	import android.view.SurfaceHolder;
	import android.view.SurfaceView;
	import android.view.LayoutInflater;
	import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


	public class AndroidCamera extends Activity implements SurfaceHolder.Callback{

	Camera camera;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	boolean previewing = false;;
	
	LayoutInflater controlInflater = null;
	LayoutInflater trashcontrolInflater = null;
	
	
     ImageButton drag,drag05,drag03;
     
     
	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_camera);
	      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    
	      getWindow().setFormat(PixelFormat.UNKNOWN);
/*
 * logic to decide the controls to display
 * 
 *    early morning   6-9am
 *    morning        10-12
 * 
 *   lunch time   12-3pm
 *   early afternoon 3pm to 6pm
 *   
 *   from office to home  6pm - 8pm
 *   
 *   before bedtime     9pm-midnight
 * 	      
	      Calendar c = Calendar.getInstance();
	      int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

	      if(timeOfDay >= 0 && timeOfDay < 12){
	          Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();        
	      }else if(timeOfDay >= 12 && timeOfDay < 16){
	          Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
	      }else if(timeOfDay >= 16 && timeOfDay < 21){
	          Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
	      }else if(timeOfDay >= 21 && timeOfDay < 24){
	          Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
	      }
*/	      
	      surfaceView = (SurfaceView)findViewById(R.id.camerapreview);
	      surfaceHolder = surfaceView.getHolder();
	      surfaceHolder.addCallback(this);
	      surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	      
	      controlInflater = LayoutInflater.from(getBaseContext());
	      View viewControl = controlInflater.inflate(R.layout.control,null);
	      LayoutParams layoutParamsControl
	       = new LayoutParams(LayoutParams.FILL_PARENT,
	       LayoutParams.FILL_PARENT);
	      this.addContentView(viewControl, layoutParamsControl);
	      

	      trashcontrolInflater = LayoutInflater.from(getBaseContext());
	      View viewTrashControl = trashcontrolInflater.inflate(R.layout.trashcontrol,null);
	      LayoutParams layoutParamsTrashControl
	       = new LayoutParams(LayoutParams.FILL_PARENT,
	       LayoutParams.FILL_PARENT);
	      this.addContentView(viewTrashControl, layoutParamsTrashControl);

	      //boton chamn vista, despliega texto
	      
	      
	      Button  botoncito01= (Button)findViewById(R.id.visioncha);
	      

	      
	      botoncito01.setOnTouchListener(new OnTouchListener() {
	    	  
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	                // TODO Auto-generated method stub

	    	        TextView textView = (TextView) findViewById(R.id.textochaman);
	    	        textView.setText("Amalinalli: esto es un sueño. Debes recordar la luz que vive en ti");
	    	        
//	    	        Toast.makeText(AndroidCamera.this, "Nueva Realidad",		Toast.LENGTH_SHORT).show();
	    	        
	                return false;

	                
	            }
	        });
	      
	      //boton ejercicio del dia,  manda a otra screen
	      
	      
	      Button  botoncito02= (Button)findViewById(R.id.displayVideo);
	      

	      
	      botoncito02.setOnTouchListener(new OnTouchListener() {
	    	  
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	                // TODO Auto-generated method stub
	            	Context context = AndroidCamera.this;
	                Intent i = new Intent(context, VideoDelDiaActivity.class);
	                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                context.startActivity(i);
	                
	                return false;

	                
	            }
	        });
	      
	      //primer personaje
	      
	      
	      drag = (ImageButton)findViewById(R.id.ImageButton02);
	      

	      
	      drag.setOnTouchListener(new OnTouchListener() {
	    	  
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	                // TODO Auto-generated method stub

	                ClipData data = ClipData.newPlainText("", "");
	                View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag);
	                v.startDrag(data, shadow, null, 0);
	                return false;

	                
	            }
	        });

	      drag.setOnDragListener(new View.OnDragListener() {
	          @Override
	          public boolean onDrag(View v, DragEvent event) {
	             switch(event.getAction())
	             {
	                case DragEvent.ACTION_DRAG_STARTED:

	                // Do nothing
	                break;
	                
	                case DragEvent.ACTION_DRAG_ENTERED:

	                break;
	                
	                case DragEvent.ACTION_DRAG_EXITED :
	                	//drag.setVisibility(View.INVISIBLE);
	                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	                            95, 95);
	                    int x_cord = (int) event.getX();
	                    int y_cord = (int) event.getY();
	                    layoutParams.leftMargin = x_cord;
	                    layoutParams.topMargin = y_cord;
	                    v.setLayoutParams(layoutParams);
	                break;
	                
	                case DragEvent.ACTION_DRAG_LOCATION  :
//	                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
	                x_cord = (int) event.getX();
	                y_cord = (int) event.getY();
	                break;
	                
	                case DragEvent.ACTION_DRAG_ENDED   :
//	                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
	                
	                // Do nothing
	                break;
	                
	                case DragEvent.ACTION_DROP:
//	                Log.d(msg, "ACTION_DROP event");
	                
	                // Do nothing
	                break;
	                default: break;
	             }
	             return true;
	          }
	       });
	       
/////////////////////////////////////////77777	      
	      //segundo personaje
	      
	      
	      drag03 = (ImageButton)findViewById(R.id.ImageButton03);
	      

	      
	      drag03.setOnTouchListener(new OnTouchListener() {
	    	  
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	                // TODO Auto-generated method stub

	                ClipData data = ClipData.newPlainText("", "");
	                View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag03);
	                v.startDrag(data, shadow, null, 0);
	                return false;

	                
	            }
	        });

	      drag03.setOnDragListener(new View.OnDragListener() {
	          @Override
	          public boolean onDrag(View v, DragEvent event) {
	             switch(event.getAction())
	             {
	                case DragEvent.ACTION_DRAG_STARTED:

	                // Do nothing
	                break;
	                
	                case DragEvent.ACTION_DRAG_ENTERED:

	                break;
	                
	                case DragEvent.ACTION_DRAG_EXITED :
	                	//drag.setVisibility(View.INVISIBLE);
	                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	                            95, 95);
	                    int x_cord = (int) event.getX();
	                    int y_cord = (int) event.getY();
	                    layoutParams.leftMargin = x_cord;
	                    layoutParams.topMargin = y_cord;
	                    v.setLayoutParams(layoutParams);
	                break;
	                
	                case DragEvent.ACTION_DRAG_LOCATION  :
//	                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
	                x_cord = (int) event.getX();
	                y_cord = (int) event.getY();
	                break;
	                
	                case DragEvent.ACTION_DRAG_ENDED   :
//	                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
	                
	                // Do nothing
	                break;
	                
	                case DragEvent.ACTION_DROP:
//	                Log.d(msg, "ACTION_DROP event");
	                
	                // Do nothing
	                break;
	                default: break;
	             }
	             return true;
	          }
	       });

	      
///////////////////////////////////////////77777
	      //tercero

	      
	      drag05 = (ImageButton)findViewById(R.id.ImageButton04);
	      

	      
	      drag05.setOnTouchListener(new OnTouchListener() {
	    	  
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	                // TODO Auto-generated method stub

	                ClipData data = ClipData.newPlainText("", "");
	                View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag05);
	                v.startDrag(data, shadow, null, 0);
	                return false;

	                
	            }
	        });

	      drag05.setOnDragListener(new View.OnDragListener() {
	          @Override
	          public boolean onDrag(View v, DragEvent event) {
	             switch(event.getAction())
	             {
	                case DragEvent.ACTION_DRAG_STARTED:

	                // Do nothing
	                break;
	                
	                case DragEvent.ACTION_DRAG_ENTERED:

	                break;
	                
	                case DragEvent.ACTION_DRAG_EXITED :
	                	
	                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	                            95, 95);
	                    int x_cord = (int) event.getX();
	                    int y_cord = (int) event.getY();
	                    layoutParams.leftMargin = x_cord;
	                    layoutParams.topMargin = y_cord;
	                    v.setLayoutParams(layoutParams);
	                break;
	                
	                case DragEvent.ACTION_DRAG_LOCATION  :
//	                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
	                x_cord = (int) event.getX();
	                y_cord = (int) event.getY();
	                break;
	                
	                case DragEvent.ACTION_DRAG_ENDED   :
//	                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
	                
	                // Do nothing
	                break;
	                
	                case DragEvent.ACTION_DROP:
//	                Log.d(msg, "ACTION_DROP event");
	                
	                // Do nothing
	                break;
	                default: break;
	             }
	             return true;
	          }
	       });
	      
	      
	   
	      
	      
	      
	  }


	   /**
	     * Launching new activity after click
	     * */ 
	  
	    private void goToButtonClick(View view) {
	    	
//	        Intent i = new Intent(this, EjercicioDiaActivity.class);

//	        startActivity(i);
	        TextView textView = (TextView) findViewById(R.id.textochaman);
	        textView.setText("Amalinalli: esto es un sueño. Debes recordar la luz que vive en ti");
	        
//	        Toast.makeText(AndroidCamera.this, "Nueva Realidad",		Toast.LENGTH_SHORT).show();
	        return;
	    }

		/**
	     * Launching new activity after click
	     * */ 
	    private void goTo08Click(View view) {
//	        Intent i = new Intent(this, EjercicioDiaActivity.class);

//	        startActivity(i);
	        
	        TextView textView = (TextView) findViewById(R.id.textochaman);
	        textView.setText("Amalinalli: esto es un sueño. Debes recordar la luz que vive en ti");

	        return;
	        
	    }

	    
	    
		/**
	     * Launching new activity after click
	     * */ 
	    private void goToButtonDespliegaVideoClick(View view) {
	        Intent i = new Intent(this, EjercicioDiaActivity.class);

	        startActivity(i);
	    }
	    
	    
		/**
	     * Launching new activity after click
	     * */ 
	    private void goToButtonDespliegaTextoClick(View view) {
	        Intent i = new Intent(this, EjercicioDiaActivity.class);

	        startActivity(i);
	    }	    
	    
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
	 int height) {
	// TODO Auto-generated method stub
	if(previewing){
	 camera.stopPreview();
	 previewing = false;
	}

	if (camera != null){
	 try {
	  camera.setPreviewDisplay(surfaceHolder);
	  camera.startPreview();
	  previewing = true;
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 }
	}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	// TODO Auto-generated method stub
	camera = Camera.open();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	// TODO Auto-generated method stub
	camera.stopPreview();
	camera.release();
	camera = null;
	previewing = false;
	}
	}