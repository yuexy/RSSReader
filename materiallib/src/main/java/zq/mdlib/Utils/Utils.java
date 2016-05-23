package zq.mdlib.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by YueXy on 2015/5/8.
 */
public class Utils
{
	public static boolean isLolipop()
	{
		return (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
	}

	public static float dpToPx(float dp, Context context)
	{
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	public static float dpToPx(float dp, Resources resources)
	{
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
		return px;
	}

	public static float pxToDp(float px, Context context)
	{
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	public static int colorWithAlpha(int color, float percent)
	{
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		int alpha = Math.round(percent * 255);

		return Color.argb(alpha, r, g, b);
	}

	//获取相对顶部信息
	public static int getRelativeTop(View view)
	{
		if (view.getId() == android.R.id.content)
			return view.getTop();
		else
			return view.getTop() + getRelativeTop((View) view.getParent());
	}

	public static int getRelativeLeft(View view)
	{
		if (view.getId() == android.R.id.content)
			return view.getLeft();
		else
			return view.getLeft() + getRelativeLeft((View) view.getParent());
	}
}
