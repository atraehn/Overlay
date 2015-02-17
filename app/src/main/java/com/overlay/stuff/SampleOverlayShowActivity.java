package com.overlay.stuff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SampleOverlayShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		startService(new Intent(this, SampleOverlayService.class));
		
		finish();
		
	}
    
}
