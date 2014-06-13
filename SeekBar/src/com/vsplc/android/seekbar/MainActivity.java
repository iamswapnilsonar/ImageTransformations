package com.vsplc.android.seekbar;

import com.example.seekbar.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * Android SeekBar example
 * @author Swapnil Sonar
 * @version 1.0
 * @since June 13 2014
 * 
 */
public class MainActivity extends Activity {
	
	private SeekBar seekBar;
	private TextView textView;
	private ImageView image;
	Bitmap bitmap;
	private RotateCommand rotateCommand;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		textView = (TextView) findViewById(R.id.textView1);
		image = (ImageView) findViewById(R.id.image);
		
		bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
		
		rotateCommand = new RotateCommand(0);
		
		// Initialize the textview with '0'
		textView.setText(seekBar.getProgress() + "/" + seekBar.getMax());
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
				progress = progresValue;
				
				rotateCommand.setAngle(progress);
				image.setImageBitmap(rotateCommand.process(bitmap, image));
				
				textView.setText(progress + "/" + seekBar.getMax());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Do something here, if you want to do anything at the start of
				// touching the seekbar
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value in textview
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
