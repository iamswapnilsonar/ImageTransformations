package com.vsplc.android.seekbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ChangeImageActivity extends Activity implements
       OnSeekBarChangeListener {
   private int minWidth = 80;
   private ImageView imageView;
   private TextView textview1, textview2;
   Matrix matrix=new Matrix();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       // TODO Auto-generated method stub
       super.onCreate(savedInstanceState);
       setContentView(R.layout.layout_changeimage);

       imageView = (ImageView) findViewById(R.id.imageview3);
       SeekBar seekbar1 = (SeekBar) findViewById(R.id.sbSize);
       SeekBar seekbar2 = (SeekBar) findViewById(R.id.sbRotate);
       textview1 = (TextView) findViewById(R.id.tv1);
       textview2 = (TextView) findViewById(R.id.tv2);

       //Gets the current size of the screen, and set the maximum size of the picture, not more than the screen size
       DisplayMetrics dm = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);
       seekbar1.setMax(dm.widthPixels - minWidth);
       
       seekbar1.setOnSeekBarChangeListener(this);
       seekbar2.setOnSeekBarChangeListener(this);        
   }

   @Override
   public void onProgressChanged(SeekBar seekBar, int progress,
           boolean fromUser) {
       if (seekBar.getId() == R.id.sbSize) {
          
    	   // Set the picture size
           int newWidth=progress+minWidth;
           int newHeight=(int)(newWidth * 3/4);
           imageView.setLayoutParams(new LinearLayout.LayoutParams(newWidth, newHeight));
           textview1.setText("Image: "+newWidth+" image height width: "+newHeight);
           
       } else if (seekBar.getId() == R.id.sbRotate){

    	   // Gets the current to be rotated.
           Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.img_earringfive);           
           
           // Set rotation angle
           matrix.setRotate(progress,30,60);
           
           // Through the angle of rotation of pictures and generate a new picture
           bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
           
           // Binding picture controls to
           imageView.setImageBitmap(bitmap);
           

           textview2.setText(progress+"°");
       }
   }

   @Override
   public void onStartTrackingTouch(SeekBar seekBar) {
       // TODO Auto-generated method stub

   }

   @Override
   public void onStopTrackingTouch(SeekBar seekBar) {
       // TODO Auto-generated method stub

   }

}
