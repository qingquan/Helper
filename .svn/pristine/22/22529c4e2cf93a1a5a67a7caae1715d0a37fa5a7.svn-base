package yl.demo.pathHelper.algrorithm;

import java.util.List;

import yl.demo.pathHelper.db.model.Corner;
import yl.demo.pathHelper.view.MapView;
import android.R.integer;
import android.graphics.Color;
import android.graphics.PointF;

public class MapConstraint {
	public final static int COLOR_PATH = 0xfff2efe9;
	public final static int COLOR_ROOM = 0xffffffd7;
	public final static int COLOR_LIFT = 0;
	public final static int COLOR_OUTSIDE = 0xff3d3d3d;
	public final static int COLOR_NO_ACCESS = 0xff555555;
	public final static int PIXEL_PER_METER = 14;
	
	public static List<Corner> sourceNearCorners;
	public static List<Corner> targetNearCorners;
	public static List<Corner> middleNearCorners;
	
	private final static int THRESHOLD = 10;
	
	public static boolean isPathConnected(int[] pathPixel) {
		int red, green, blue;
		red = green = blue = 0;
		for (int i : pathPixel) {
			red += Color.red(i);
			green += Color.green(i);
			blue += Color.blue(i);
		}
		red = red / pathPixel.length;
		green = green / pathPixel.length;
		blue = blue / pathPixel.length;
		
		if ( Math.abs(red - Color.red(COLOR_PATH)) < THRESHOLD
				&& Math.abs(green - Color.green(COLOR_PATH)) < THRESHOLD
				&& Math.abs(blue - Color.blue(COLOR_PATH)) < THRESHOLD)
			return true;
		return false;
		
	}

}
