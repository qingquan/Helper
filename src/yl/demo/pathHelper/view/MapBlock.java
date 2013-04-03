package yl.demo.pathHelper.view;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * 地图块，地图中显示的一个个小区域
 * @author jackqdyulei
 *
 */
public class MapBlock {
	private Bitmap mMapBlockBitmap;
	private float mLeft;
	private float mTop;
	private boolean hasBitmap;
	private int mX;
	private int mY;
	private float mWidth;
	private float mHeight;
	private String mFilePath;

	/**
	 * 构造地图块的基本信息
	 * @param left    距离(0,0)点的横向偏移
	 * @param top	      距离(0,0)点的纵向偏移
	 * @param x		      在整个地图矩阵中的横向位置
	 * @param y		      在整个地图矩阵中的纵向位置
	 * @param filePath   该地图块本地文件名，用于构建bitmap
	 */
	public MapBlock(float left, float top, int x, int y, String filePath) {
		// TODO Auto-generated constructor stub
		mLeft = left;
		mTop = top;
		mX = x;
		mFilePath = filePath;
	}

	public float getLeft() {
		return mLeft;
	}

	public float getTop() {
		return mTop;
	}
	
	public int getX() {
		return mX;
	}
	
	public int getY() {
		return mY;
	}

	/**
	 * 判断该地图块是否位于屏幕显示位置
	 * @param rectf  屏幕显示区域的范围
	 * @return
	 */
	public boolean isOverLap(RectF rectf) {
		if ( mLeft + mWidth * 1.5f < rectf.left || mLeft - mWidth * 0.5f > rectf.right )
			return false;
		if ( mTop + mHeight * 1.5f < rectf.top || mTop - mWidth * 0.5f > rectf.bottom )
			return false;
		return true;
	}

	public synchronized boolean hasMapBitmap() {
		return hasBitmap && mMapBlockBitmap != null;
	}
	
	public void setBitmapFinished() {
		hasBitmap = true;
	}

	public void putMapBitmap(Bitmap bitmap) {
		mMapBlockBitmap = bitmap;
	}

	public void destoryMapBitmap() {
		hasBitmap = false;
		if ( mMapBlockBitmap!= null && !mMapBlockBitmap.isRecycled() ) {
			mMapBlockBitmap.recycle();
			System.gc();
		}
		mMapBlockBitmap = null;
	}

	public Bitmap getMapBitmap() {
		return mMapBlockBitmap;
	}
	
	public void setFilePath(String filePath) {
		mFilePath = filePath;
	}
	
	public String getFilePath() {
		return mFilePath;
	}
	
	public void setWidth(float width) {
		mWidth = width;
	}
	
	public void setHeight(float height) {
		mHeight = height;
	}

}
