package com.overlay.stuff;


import com.test.overlay.R;

import com.overlay.share.OverlayService;

import android.app.Notification;
import android.app.PendingIntent;

import android.content.Intent;

public class SampleOverlayService extends OverlayService {

	public static SampleOverlayService instance;

	private SampleOverlayView overlayView;

	@Override
	public void onCreate() {
		super.onCreate();
		
		instance = this;
		
		overlayView = new SampleOverlayView(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (overlayView != null) {
			overlayView.destory();
		}

	}
	
	static public void stop() {
		if (instance != null) {
			instance.stopSelf();
		}
	}
	
	@Override
	protected Notification foregroundNotification(int notificationId) {
		Notification notification;

		notification = new Notification(R.drawable.ic_launcher, getString(R.string.title_notification), System.currentTimeMillis());

		notification.flags = notification.flags | Notification.FLAG_ONGOING_EVENT | Notification.FLAG_ONLY_ALERT_ONCE;

		notification.setLatestEventInfo(this, getString(R.string.title_notification), getString(R.string.message_notification), notificationIntent());

		return notification;
	}


	private PendingIntent notificationIntent() {
		Intent intent = new Intent(this, SampleOverlayHideActivity.class);

		PendingIntent pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		return pending;
	}

}
