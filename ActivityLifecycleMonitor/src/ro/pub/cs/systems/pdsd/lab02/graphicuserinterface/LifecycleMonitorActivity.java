package ro.pub.cs.systems.pdsd.lab02.graphicuserinterface;

import ro.pub.cs.systems.pdsd.lab02.R;
import ro.pub.cs.systems.pdsd.lab02.general.Constants;
import ro.pub.cs.systems.pdsd.lab02.general.Utilities;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

public class LifecycleMonitorActivity extends Activity {
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	private Bundle _myBundle = new Bundle();
	
	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		@SuppressWarnings("all")
		public void onClick(View view) {
			EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
			EditText passwordEditText = (EditText)findViewById(R.id.password_edit_text);
			if (getResources().getString(R.string.ok_button_content).equals(((Button)view).getText().toString())) {

				LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  		
				
				if (Utilities.allowAccess(getApplicationContext(), usernameEditText.getText().toString(), passwordEditText.getText().toString())) {
				    View popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_success, null);  
				    final PopupWindow popupWindow = new PopupWindow(popupContent, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
				             
				    Button dismissButton = (Button)popupContent.findViewById(R.id.dismiss_button);
				    dismissButton.setOnClickListener(new Button.OnClickListener(){	
				    	@Override
				    	public void onClick(View view) {
				    		popupWindow.dismiss();
				    	}
				    });
					popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);	
				} else {
				    View popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_fail, null);  
				    final PopupWindow popupWindow = new PopupWindow(popupContent, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
				             
				    Button dismissButton = (Button)popupContent.findViewById(R.id.dismiss_button);
				    dismissButton.setOnClickListener(new Button.OnClickListener(){	
				    	@Override
				    	public void onClick(View view) {
				    		popupWindow.dismiss();
				    	}
				    });
					popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);					
				}
			}
			if (getResources().getString(R.string.cancel_button_content).equals(((Button)view).getText().toString())) {
				usernameEditText.setText(getResources().getString(R.string.empty));
				passwordEditText.setText(getResources().getString(R.string.empty));
			}
		}
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_lifecycle_monitor);
        
        Button okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        Button cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);
        
        _myBundle = savedInstanceState;
        if(savedInstanceState == null){
        	Log.d(Constants.TAG, "onCreate() method was invoked for the first time");
        }
        else{
        	onRestoreInstanceState(savedInstanceState);
        	
        	Log.d(Constants.TAG, "onCreate() method was invoked again");
        }
    }    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onStart() {
      super.onStart();
      Log.d(Constants.TAG, "onStart() method was invoked");
    }
   
    @Override
    protected void onResume() {
      super.onResume();
      Log.d(Constants.TAG, "onResume() method was invoked");
    }
   
    @Override
    protected void onPause() {
      super.onPause();
      Log.d(Constants.TAG, "onPause() method was invoked");
    }
   
    @Override
    protected void onStop() {
      super.onStop();
      Log.d(Constants.TAG, "onStop() method was invoked");
    }
   
    @Override
    protected void onDestroy() {
      super.onDestroy();
      Log.d(Constants.TAG, "onDestroy() method was invoked");
    }
   
    @Override
    protected void onRestart() {
      super.onRestart();
      Log.d(Constants.TAG, "onRestart() method was invoked");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
    	super.onSaveInstanceState(savedInstanceState);
    	
    	CheckBox checkBox = (CheckBox)findViewById(R.id.remember_me_checkbox);
    	boolean isChecked = checkBox.isChecked();

		EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
		EditText passwordEditText = (EditText)findViewById(R.id.password_edit_text);
		
    	if(isChecked){
    		Log.d(Constants.TAG, "onSavedInstance() method was invoked with check " + passwordEditText.getText().toString() );
    		savedInstanceState.putString(Constants.USERNAME_TAG, usernameEditText.getText().toString());
    		savedInstanceState.putString(Constants.PASSWORD_TAG, passwordEditText.getText().toString());
    	}
    	else{
    		Log.d(Constants.TAG, "onSavedInstance() method was invoked");
    	}
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
	     
        CheckBox checkBox = (CheckBox)findViewById(R.id.remember_me_checkbox);
	  	boolean isChecked = checkBox.isChecked();
	  	
	  	if(savedInstanceState == null)
	  		return;
	  	
	  	String pass = savedInstanceState.getString(Constants.PASSWORD_TAG);
	  	
	  	String usernameEditText = (String)savedInstanceState.get(Constants.USERNAME_TAG);
	  	String passwordEditText = (String)savedInstanceState.get(Constants.PASSWORD_TAG);
			
	  	if(isChecked){
	  		Log.d(Constants.TAG, "onRestoreInstanceState() method was invoked with check "+ pass);
	  		//savedInstanceState.putString(Constants.USERNAME_EDIT_TEXT, usernameEditText);
	  		//savedInstanceState.putString(Constants.PASSWORD_EDIT_TEXT, passwordEditText);
	  		
	  		EditText usernameEditText2 = (EditText)findViewById(R.id.username_edit_text);
			EditText passwordEditText2 = (EditText)findViewById(R.id.password_edit_text);
			
			usernameEditText2.setText(usernameEditText);
			passwordEditText2.setText(passwordEditText);
	  	}
	  	else{
	  		Log.d(Constants.TAG, "onRestoreInstanceState() method was invoked");
	  	}
    }
    
}
