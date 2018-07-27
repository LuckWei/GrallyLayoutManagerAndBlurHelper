package cattt.network.comm.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button mBlurBtn;

    public MainActivity() {
        super();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_main);
        mBlurBtn = findViewById(R.id.blur_btn);
        mBlurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleDialogFragment dialog = ExampleDialogFragment.get().setBlur(BlurHelper.blur(getWindow().getDecorView(), 1.0f, 25, "#00ff0000"));
                dialog.show(getSupportFragmentManager(), "AAAAAAAAAAAAAAA");
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        GalleryLayoutManager layoutManager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        GalleryTransformer mScaleTransformer = new GalleryTransformer();
        layoutManager.setItemTransformer(mScaleTransformer);
        layoutManager.attach(recyclerView, 0);
        recyclerView.addItemDecoration(new SpaceItemDecoration(-100));
        MyAdapter adapter = new MyAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final String[] THUMB_ARRAY = {
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/20-%E4%B8%AD%E5%9B%BD%E7%BA%A2.jpg",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E4%B8%AD%E5%9B%BD%E7%BA%A220.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/%E8%A5%BF%E6%B8%B8%E8%AE%B01.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%A4%A7%E5%90%89%E5%A4%A7%E5%88%A9.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180620125747.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E6%97%BA%E6%97%BA.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/10%E5%91%A8%E5%B9%B4.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/20-%E4%B8%AD%E5%9B%BD%E7%BA%A2.jpg",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E4%B8%AD%E5%9B%BD%E7%BA%A220.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/%E8%A5%BF%E6%B8%B8%E8%AE%B01.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%A4%A7%E5%90%89%E5%A4%A7%E5%88%A9.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180620125747.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E6%97%BA%E6%97%BA.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/10%E5%91%A8%E5%B9%B4.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/20-%E4%B8%AD%E5%9B%BD%E7%BA%A2.jpg",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E4%B8%AD%E5%9B%BD%E7%BA%A220.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/%E8%A5%BF%E6%B8%B8%E8%AE%B01.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%A4%A7%E5%90%89%E5%A4%A7%E5%88%A9.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180620125747.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E6%97%BA%E6%97%BA.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/10%E5%91%A8%E5%B9%B4.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/20-%E4%B8%AD%E5%9B%BD%E7%BA%A2.jpg",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E4%B8%AD%E5%9B%BD%E7%BA%A220.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/%E8%A5%BF%E6%B8%B8%E8%AE%B01.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%A4%A7%E5%90%89%E5%A4%A7%E5%88%A9.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180620125747.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E6%97%BA%E6%97%BA.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/10%E5%91%A8%E5%B9%B4.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/20-%E4%B8%AD%E5%9B%BD%E7%BA%A2.jpg",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E4%B8%AD%E5%9B%BD%E7%BA%A220.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/%E8%A5%BF%E6%B8%B8%E8%AE%B01.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%A4%A7%E5%90%89%E5%A4%A7%E5%88%A9.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180620125747.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E6%97%BA%E6%97%BA.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/10%E5%91%A8%E5%B9%B4.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/20-%E4%B8%AD%E5%9B%BD%E7%BA%A2.jpg",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E4%B8%AD%E5%9B%BD%E7%BA%A220.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/%E8%A5%BF%E6%B8%B8%E8%AE%B01.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%A4%A7%E5%90%89%E5%A4%A7%E5%88%A9.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180620125747.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/%E6%97%BA%E6%97%BA.png",
                "http://hcb-cdn.oss-cn-beijing.aliyuncs.com/jkc/10%E5%91%A8%E5%B9%B4.png"
        };

        private Context context;

        private MyAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_child, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyHolder) {
                MyHolder myHolder = (MyHolder) holder;
                GlideApp.with(context).load(THUMB_ARRAY[position]).dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(myHolder.itemView);
            }
        }

        @Override
        public int getItemCount() {
            return THUMB_ARRAY.length;
        }

        private static class MyHolder extends RecyclerView.ViewHolder {

            private ImageView itemView;

            public MyHolder(View itemView) {
                super(itemView);
                this.itemView = (ImageView) itemView;
            }
        }
    }

    private static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = mSpace;
            outRect.right = mSpace;

        }

        private SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }

    private static class GalleryTransformer implements GalleryLayoutManager.ItemTransformer {
        private static final float MIN_SCALE = 0.5f;
        private static final float MAX_ROTATION = 30;
        private static final float TRANSLATION = 0.01f;
        private static final float MIN_ALPHA = 0.9f;


        @Override
        public void transformItem(GalleryLayoutManager layoutManager, View view, float fraction) {
            final float scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(fraction));
            final float rotation = MAX_ROTATION * Math.abs(fraction);
            float alpha = 1 - Math.abs(fraction);
            alpha = alpha < MIN_ALPHA ? MIN_ALPHA : alpha;
            if (fraction > 0 && fraction <= 1) {
                view.setRotationY(-rotation);
            }
            if (fraction < 0 && fraction >= -1) {
                view.setRotationY(rotation);
            }
            if (fraction == 0) {
                view.setElevation(scale - 1);
                view.setAlpha(alpha);
                view.setTranslationX(view.getWidth() * -fraction * TRANSLATION);
                view.setPivotY(0.5f * view.getHeight());
                view.setPivotX(0.5f * view.getWidth());
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setRotationY(0);
            } else {
                view.setElevation(scale - 1);
                view.setAlpha(alpha);
                view.setTranslationX(view.getWidth() * -fraction * TRANSLATION);
                view.setPivotY(0.5f * view.getHeight());
                view.setPivotX(0.5f * view.getWidth());
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        }
    }
}
