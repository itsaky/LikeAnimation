package com.itsaky.instalike;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.graphics.PorterDuff;

public class MainActivity extends AppCompatActivity 
{
	private ImageView mLike;
	private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		mLike = findViewById(R.id.like);

		mLike.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					animateLike();
				}
			});
    }

	private void animateLike()
	{
		isLiked = !isLiked;

		ObjectAnimator xAnim = new ObjectAnimator();
		xAnim.setTarget(mLike);
		xAnim.setDuration(450);
		xAnim.setPropertyName("scaleX");
		xAnim.setFloatValues(0.8f, 1.1f, 0.9f, 1.0f);

		ObjectAnimator yAnim = new ObjectAnimator();
		yAnim.setTarget(mLike);
		yAnim.setDuration(450);
		yAnim.setPropertyName("scaleY");
		yAnim.setFloatValues(0.8f, 1.1f, 0.9f, 1.0f);
		
		/*
		* 0.8f = start with smaller image
		* 1.1f = make it little bigger than original
		* 0.8f = make it smaller than original
		* 1.0f = restore its size to original
		*/

		AnimatorSet anim = new AnimatorSet();
		anim.play(xAnim).with(yAnim);
		anim.addListener(new Animator.AnimatorListener(){

				@Override
				public void onAnimationStart(Animator p1)
				{
					/**
					* 	if(isLiked)
					* 	{
					*		mLike.setImageResource(R.drawable.ic_liked);
					* 	} else {
					*		mLike.setImageResource(R.drawable.ic_unliked);
					* 	}
					*/
					
					mLike.setImageResource(isLiked ? R.drawable.ic_liked : R.drawable.ic_unliked);
					
					/*
					* Set color as you wish
					* This is required because 
					* 	ic_liked icon is white in color
					* This way, you can apply any color to it...
					*/
					
					int colorLiked = Color.parseColor("#f44336");
					int colorUnliked = Color.BLACK;
					int color = isLiked ? colorLiked : colorUnliked;
					
					mLike.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
				}
				
				// No need of these callbacks
				@Override public void onAnimationEnd(Animator p1){}
				@Override public void onAnimationCancel(Animator p1){}
				@Override public void onAnimationRepeat(Animator p1){}
			});
				
		anim.start();
	}

}
