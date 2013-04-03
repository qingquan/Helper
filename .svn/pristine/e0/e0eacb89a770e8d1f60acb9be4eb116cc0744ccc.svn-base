package yl.demo.pathHelper.Receiver;

import java.util.HashMap;
import java.util.List;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class WiFiReceiver extends BroadcastReceiver {
	private WifiManager mWifiManager;
	private OnWiFiDataGettedListener mOnWiFiDataGettedListener;
	
	public WiFiReceiver(WifiManager wifiManager) {
		// TODO Auto-generated constructor stub
		mWifiManager = wifiManager;
	}

	public void setWiFiManager(WifiManager wifiManager) {
		mWifiManager = wifiManager;
	}
	
	public void setOnWiFiDataGettedListener(OnWiFiDataGettedListener onWiFiDataGettedListener) {
		mOnWiFiDataGettedListener = onWiFiDataGettedListener;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		List<ScanResult> results = mWifiManager.getScanResults();
		HashMap<String, Double> rssVec = new HashMap<String, Double>();
		for (ScanResult scanResult : results) {
			String mac = scanResult.BSSID;
			mac = mac.replaceAll(":", "");
			double level = Math.pow(10, scanResult.level/10.0);
			rssVec.put(mac, level);
		}
		mOnWiFiDataGettedListener.onWiFiDataGetted(rssVec);
	}
	
	public interface OnWiFiDataGettedListener {
		public void onWiFiDataGetted(HashMap<String, Double> rssVec);
	}

}
