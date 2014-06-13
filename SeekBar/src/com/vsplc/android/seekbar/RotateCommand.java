package com.vsplc.android.seekbar;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;

public class RotateCommand {
	
	@SuppressWarnings("unused")
	private static final int FULL_ANGLE = 360;
	
	@SuppressWarnings("unused")
	private static final String ID = "com.rec.photoeditor.graphics.commands.RotateCommand";
	
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
		return "com.rec.photoeditor.graphics.commands.RotateCommand";
	}

	public Bitmap process(Bitmap paramBitmap, ImageView view) {
		
		Log.i("Image Processing Command", "com.rec.photoeditor.graphics.commands.RotateCommand : "+ this.angle);
		
		Matrix localMatrix = new Matrix();
		
		localMatrix.postRotate(this.angle);
		
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
