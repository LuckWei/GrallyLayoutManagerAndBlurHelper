package cattt.network.comm.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.FloatRange;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.support.v8.renderscript.ScriptIntrinsicResize;
import android.support.v8.renderscript.Short4;
import android.support.v8.renderscript.Type;
import android.view.View;

public class BlurHelper {
    private static RenderScript rs;

    public static void init(Context context) {
        rs = RenderScript.create(context);
    }

    public static Bitmap blur(Bitmap bitmap, @FloatRange(from = 0f ,to = 1f) float scale, @FloatRange(from = 1f ,to = 25f) float radius, String color) {
        final int width = (int) (bitmap.getWidth() * scale);
        final int height = (int) (bitmap.getHeight() * scale);
        Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Allocation in = Allocation.createFromBitmap(rs, bitmap);
        Type t = Type.createXY(rs, in.getElement(), width, height);
        Allocation tmp1 = Allocation.createTyped(rs, t);
        Allocation tmp2 = Allocation.createTyped(rs, t);
        //缩放
        ScriptIntrinsicResize theIntrinsic = ScriptIntrinsicResize.create(rs);
        theIntrinsic.setInput(in);
        theIntrinsic.forEach_bicubic(tmp1);
        //模糊
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        intrinsicBlur.setRadius(radius);
        intrinsicBlur.setInput(tmp1);
        intrinsicBlur.forEach(tmp2);
//        //蒙版
        ScriptC_tint mScript = new ScriptC_tint(rs);
        mScript.set_maskColor(convertColor2Short4(color));
        mScript.forEach_mask(tmp2, tmp1);
        tmp1.copyTo(outputBitmap);
        bitmap.recycle();
        rs.destroy();
        return outputBitmap;
    }

    public static Bitmap blur(View view, @FloatRange(from = 0f ,to = 2f) float scale, @FloatRange(from = 1f ,to = 25f) float radius, String color) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        final int width = (int) (bitmap.getWidth() * scale);
        final int height = (int) (bitmap.getHeight() * scale);
        Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Allocation in = Allocation.createFromBitmap(rs, bitmap);
        Type t = Type.createXY(rs, in.getElement(), width, height);
        Allocation tmp1 = Allocation.createTyped(rs, t);
        Allocation tmp2 = Allocation.createTyped(rs, t);
        //缩放
        ScriptIntrinsicResize theIntrinsic = ScriptIntrinsicResize.create(rs);
        theIntrinsic.setInput(in);
        theIntrinsic.forEach_bicubic(tmp1);
        //模糊
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        intrinsicBlur.setRadius(radius);
        intrinsicBlur.setInput(tmp1);
        intrinsicBlur.forEach(tmp2);
//        //蒙版
        ScriptC_tint mScript = new ScriptC_tint(rs);
        mScript.set_maskColor(convertColor2Short4(color));
        mScript.forEach_mask(tmp2, tmp1);
        tmp1.copyTo(outputBitmap);
        bitmap.recycle();
        rs.destroy();
        return outputBitmap;
    }

    private static Short4 convertColor2Short4(String color) {
        int c = Color.parseColor(color);
        short b = (short) (c & 0xFF);
        short g = (short) ((c >> 8) & 0xFF);
        short r = (short) ((c >> 16) & 0xFF);
        short a = (short) ((c >> 24) & 0xFF);
        return new Short4(r, g, b, a);
    }
}
