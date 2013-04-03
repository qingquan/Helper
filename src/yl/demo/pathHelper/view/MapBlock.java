package yl.demo.pathHelper.view;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * ��ͼ�飬��ͼ����ʾ��һ����С����
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
	 * �����ͼ��Ļ�����Ϣ
	 * @param left    ����(0,0)��ĺ���ƫ��
	 * @param top	      ����(0,0)�������ƫ��
	 * @param x		      ��������ͼ�����еĺ���λ��
	 * @param y		      ��������ͼ�����е�����λ��
	 * @param filePath   �õ�ͼ�鱾���ļ��������ڹ���bitmap
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
	 * �жϸõ�ͼ���Ƿ�λ����Ļ��ʾλ��
	 * @param rectf  ��Ļ��ʾ����ķ�Χ
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
