package com.vsplc.android.seekbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
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
public class BasicMainActivity extends Activity {
	
	private SeekBar seekBar;
	private TextView textView;
	private ImageView image;
	Bitmap bitmap;
	private RotateCommand rotateCommand;
	private OnTouchListener m_touchImagListener;
	
	private float m_oldDist = 1f, m_scale, m_oldX = 0, m_oldY = 0, m_dX, m_dY,
			m_posX, m_posY, m_prevX = 0, m_prevY = 0, m_newX, m_newY;
	private Display m_screen;
	
	private int m_Height, m_Width;
	private AbsoluteLayout aLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		aLayout= (AbsoluteLayout)findViewById(R.id.absLayout);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		textView = (TextView) findViewById(R.id.textView1);
		image = (ImageView) findViewById(R.id.image);
				
		bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
		
		rotateCommand = new RotateCommand(0);
		
		m_screen = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		m_Width = m_screen.getWidth();
		m_Height = m_screen.getHeight();
		
		// Initialize the textview with '0'
		textView.setText(seekBar.getProgress() + "/" + seekBar.getMax());
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
				progress = progresValue;
				
				rotateCommand.setAngle(progress);
				image.setImageBitmap(rotateCommand.process(bitmap));
				
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
		
		// Image touch listener to move image onTouch event on screen.
		m_touchImagListener = new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {

				switch (event.getAction() & MotionEvent.ACTION_MASK) {

				case MotionEvent.ACTION_UP:
					Log.v("onTouch : ", "ACTION_UP");
					break;

				case MotionEvent.ACTION_POINTER_UP:
					Log.v("onTouch : ", "ACTION_POINTER_UP");
					break;

				case MotionEvent.ACTION_MOVE:
										
					Log.v("onTouch : ", "ACTION_MOVE");
					
					m_dX = event.getX() - m_oldX;
					m_dY = event.getY() - m_oldY;

					m_posX = m_prevX + m_dX;
					m_posY = m_prevY + m_dY;

					if (m_posX > 0 && m_posY > 0
							&& (m_posX + view.getWidth()) < m_Width
							&& (m_posY + view.getHeight()) < m_Height) 
					{
						
						@SuppressWarnings("deprecation")
						LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,
								(int)event.getX()-view.getWidth()/2,(int)event.getY()-view.getHeight()/2);
						
//						view.setLayoutParams(
//								new AbsoluteLayout.LayoutParams(view.getMeasuredWidth(), view.getMeasuredHeight(),
//										(int) m_posX, (int) m_posY));
//						
						view.setLayoutParams(lp);

						m_prevX = m_posX;
						m_prevY = m_posY;

					}
					break;
					
				case MotionEvent.ACTION_DOWN:

					Log.v("onTouch : ", "ACTION_DOWN");
					
					m_oldX = event.getX();
					m_oldY = event.getY();
					
					Log.v("onTouch : ", "ACTION_DOWN : m_oldX :=>"+m_oldX+"m_oldY :=>"+m_oldY);
					
					break;
				}
				return false;
			}
		};
		
		image.setOnTouchListener(m_touchImagListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
