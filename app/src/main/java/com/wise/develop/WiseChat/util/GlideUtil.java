package com.wise.develop.WiseChat.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class GlideUtil {

    public static void displayImage(Context activity, String url, ImageView imageView) {
        Glide.with(activity).load(url).into(imageView);
    }

    /**
     * 毛玻璃效果
     */
    public static void displayImageTransform(Context activity, String url, ImageView imageView) {
        Glide.with(activity).load(url)
                .bitmapTransform(new BlurTransformation(activity, 50), new CenterCrop(activity))
                .into(imageView);
    }

    /**
     * 圆角效果
     */
    public static void displayRoundImage(Context activity, String url, ImageView imageView, int corner) {
        Glide.with(activity).load(url)
                .transform(new CenterCrop(activity), new CornersTransform(activity, corner))
                .into(imageView);
    }

    /**
     * 圆角效果
     */
    public static void displayRoundImageLocal(Context activity, int path, ImageView imageView) {
        Glide.with(activity).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CornersTransform(activity, 30))
                .into(imageView);
    }

    /**
     * 圆形图片
     */
    public static void displayCircleImage(Context activity, String url, ImageView imageView) {
        Glide.with(activity).load(url).transform(new GlideCircleTransform(activity)).into(imageView);
    }

    /**
     * 圆形图片
     */
    public static void displayCircleImageLocal(Context activity, int path, ImageView imageView) {
        Glide.with(activity).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(activity))
                .crossFade()
                .into(imageView);
    }
}
