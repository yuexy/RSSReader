package zq.mdlib.contextmenu;

import android.view.animation.Interpolator;

/**
 * Created by YueXy on 8/21/2015.
 */
public class HesitateInterpolator implements Interpolator
{

	public HesitateInterpolator()
	{
	}

	public float getInterpolation(float t)
	{
		float x = 2.0f * t - 1.0f;
		return 0.5f * (x * x * x + 1.0f);
	}
}