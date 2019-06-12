package com.example.cdis;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
ImageView imageToUpload;
Button upload,cam;
EditText phoneNum;
TextView displaytxt;
int TAKE_PHOTO_CODE = 0;
final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/CassavaImages/";
private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageToUpload = (ImageView)findViewById(R.id.imagetoUpload);
		upload = (Button)findViewById(R.id.send);
		cam = (Button)findViewById(R.id.camera);
		phoneNum = (EditText)findViewById(R.id.phone);
		displaytxt = (TextView)findViewById(R.id.distxt);
		imageToUpload.setOnClickListener(this);
		upload.setOnClickListener(this);
		cam.setOnClickListener(this);
		
        File newdir = new File(dir);
        newdir.mkdirs();
		    }
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.imagetoUpload:
			
			 break;
		case R.id.camera:
			
			Toast.makeText(getApplicationContext(),"Openinig Camera",Toast.LENGTH_LONG).show();
			String file = dir+phoneNum.getText().toString()+".jpg";
            path = file;
            File newfile = new File(file);
            try {
                newfile.createNewFile();
            }
            catch (IOException e)
            {
            }

            Uri outputFileUri = Uri.fromFile(newfile);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
		       break; 
		       
		case R.id.send:
			if(check(phoneNum.getText().length(),displaytxt.getText().toString())) {
			Toast.makeText(getApplicationContext(),"Opening Browser",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(Intent.ACTION_VIEW, 
				     Uri.parse("http://cdis.000webhostapp.com"));
				startActivity(intent);}
			else {validate();}
				
			 break;
		}
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
            displaytxt.setText(path);
            imageToUpload.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }
	
	private boolean check(int i, String t) {
		if(i==11 && !t.equals("Captured image location"))
			return true;
		else 
			return false;
	}
	
	protected void validate() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setCancelable(false);
		dialog.setTitle("CDIS");
		dialog.setMessage("Enter a phone number or Capture a cassava leaf image");
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int id) {
		        //Action for "Delete".
		    }
		})
		        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		            //Action for "Cancel".
		            }
		        });

		final AlertDialog alert = dialog.create();
		alert.show();
		
	}
	
	}
		


	
		
	

	