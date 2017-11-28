package py.keeporswap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

/**
 * A {@link ReplacementSpan} subclass that surrounds the text in a pink rounded rectangle
 */

public class HighlightSpan extends ReplacementSpan {

    @Override
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int start, int end, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(charSequence, start, end);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        RectF rect = new RectF(x, top+2, x + paint.measureText(charSequence, start, end) + 10, bottom - 10);
        paint.setColor(Color.parseColor("#EA4EAC"));
        canvas.drawRoundRect(rect, 16, 16, paint);
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawText(charSequence, start, end, x, y, paint);
    }
}
