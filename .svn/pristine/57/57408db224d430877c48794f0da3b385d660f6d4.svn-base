package yl.demo.pathHelper.Animation;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class ComeAndGoLeftAnimtion extends AnimationSet {

	public ComeAndGoLeftAnimtion(boolean shareInterpolator) {
		super(shareInterpolator);
		// TODO Auto-generated constructor stub
		TranslateAnimation shiftRightAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.5f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
		shiftRightAnimation.setDuration(1000);
		
		AlphaAnimation alphain = new AlphaAnimation(0.7f, 1.0f);
		alphain.setDuration(1000);

		addAnimation(alphain);
		addAnimation(shiftRightAnimation);
		
		setFillAfter(true);
		setFillBefore(true);
		setFillEnabled(true);
		setInterpolator(new OvershootInterpolator());
	}
	
}