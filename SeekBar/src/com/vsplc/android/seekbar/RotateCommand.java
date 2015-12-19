package com.vsplc.android.seekbar;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;

public class RotateCommand {
	
	@SuppressWarnings("unused")
	private static final int FULL_ANGLE = 360;
	
	@SuppressWarnings("unused")
	private static final String ID = "com.vsplc.android.seekbar.RotateCommand";
	
	private int angle = 0;

	public RotateCommand() {
	}

	public RotateCommand(int paramInt) {
		setAngle(paramInt);
	}

	public int getAngle() {
		return this.angle;
	}

	public String getId() {
		return "com.vsplc.android.seekbar.RotateCommand";
	}

	public Bitmap process(Bitmap paramBitmap) {
		
		Matrix localMatrix = new Matrix();
		
		localMatrix.postRotate(this.angle);
		
		Log.v("RotateCommand", "Bitmap : "+paramBitmap);
		
		return Bitmap.createBitmap(paramBitmap, 0, 0,
				paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
		
//		try {
//			Thread.sleep(300L);
//			return Bitmap.createBitmap(paramBitmap, 0, 0,
//					paramBitmap.getWidth(), paramBitmap.getHeight(),
//					localMatrix, true);
//		} catch (InterruptedException localInterruptedException) {
//			while (true)
//				localInterruptedException.printStackTrace();
//		}
	}

	public void setAngle(int paramInt) {
		int i = paramInt % 360;
		if (i < 0)
			i += 360;
		this.angle = i;
	}
}
