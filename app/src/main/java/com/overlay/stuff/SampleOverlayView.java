package com.overlay.stuff;


import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.overlay.share.OverlayService;
import com.overlay.share.OverlayView;

import java.util.Date;

import com.test.overlay.R;

public class SampleOverlayView extends OverlayView {

	private TextView button;
	private int taps, width, height;
	private long time;
	private Date date;
	private boolean lPress = false;
	
	public SampleOverlayView(OverlayService service) {
		super(service, R.layout.overlay, 1);
		date = new Date();
		taps = 0;
		time = date.getTime();
		button.setBackgroundResource(R.drawable.overlay_up);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        width = size.x;
        height = size.y;
	}

	@Override
	protected void onInflateView() {
        button = (TextView) this.findViewById(R.id.textview_info);
	}

	@Override
	protected void refreshViews() {
		/*curX = layoutParams.x;
		curY = layoutParams.y;*/
        //info.setText("W: "+this.getWidth()+"\nH: "+this.getHeight());
        //info.setText(" ");
	}

	@Override
	protected void onTouchEvent_Up(MotionEvent event) {
		//the instant user lifts finger
		long temp = date.getTime();
		if(taps>=2){
            button.setText("!");
			taps=0;
		}
		//info.setText(""+taps);
		if( (temp-time) < 700)	++taps; //milliseconds
		else taps = 1;
		
		//update current time
		time = temp;
		lPress = false;
		layoutParams.x = 0;
		layoutParams.y = 0;
        button.setBackgroundResource(R.drawable.overlay_up);
	}

	@Override
	protected void onTouchEvent_Move(MotionEvent event) {
		if(lPress){
			int newX, newY;
            newX=layoutParams.x + (int)event.getX() - 150;
            newY=layoutParams.y + (int)event.getY() - 150;
            if(-150 > newX || newX > width) newX = 0;
            if(-150 > newY || newY > height) newY = 0;

			updateView(newX, newY);
		}
        taps=0;
	}

	@Override
	protected void onTouchEvent_Press(MotionEvent event) {
		//the instant user touches button
		date = new Date();
        button.setBackgroundResource(R.drawable.overlay_down);
		button.setText("");
	}

	@Override
	public boolean onTouchEvent_LongPress() {
		lPress = true;
		return true;
	}
	/*
	@Override
	public boolean onTouchEvent_Drag(DragEvent d){
		info.setText("DRAG\nEVENT");
		return true;
	}
	*/
	
}
