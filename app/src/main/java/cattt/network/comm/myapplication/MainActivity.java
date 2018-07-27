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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button mBlurBtn;

    public MainActivity(){
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
                switch (position) {
                    case 0:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#2196F3"));
                        break;
                    case 1:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#673AB7"));
                        break;
                    case 2:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#009688"));
                        break;
                    case 3:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#607D8B"));
                        break;
                    case 4:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#F44336"));
                        break;
                    case 5:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#A43436"));
                        break;
                    case 6:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#3F4643"));
                        break;
                    case 7:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#44336F"));
                        break;
                    case 8:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#600988"));
                        break;
                    case 9:
                        myHolder.itemView.setBackgroundColor(Color.parseColor("#7673AB"));
                        break;
                }
                myHolder.textView.setText("" + (++position));
            }
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        private static class MyHolder extends RecyclerView.ViewHolder {

            private View itemView;
            private TextView textView;

            public MyHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                textView = itemView.findViewById(R.id.pager_textview);
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
                view.setElevation(scale-1);
                view.setAlpha(alpha);
                view.setTranslationX(view.getWidth() * -fraction * TRANSLATION);
                view.setPivotY(0.5f * view.getHeight());
                view.setPivotX(0.5f * view.getWidth());
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setRotationY(0);
            } else {
                view.setElevation(scale-1);
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
