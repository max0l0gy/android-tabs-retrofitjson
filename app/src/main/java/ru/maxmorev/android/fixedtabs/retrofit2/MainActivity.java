package ru.maxmorev.android.fixedtabs.retrofit2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rick.a1402retrofitjson.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentPosts(), getResources().getString(R.string.tab_posts));
        adapter.addFragment(new FragmentUsers(), getResources().getString(R.string.tab_users));
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);

        ViewPager.OnAdapterChangeListener listener = new ViewPager.OnAdapterChangeListener(){

            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter1) {

            }
        };
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                FragmentTab fragment = (FragmentTab)adapter.getItem(position);
                if(!fragment.isDataLoaded()){
                    fragment.loadData();
                }


            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();


    }
}
