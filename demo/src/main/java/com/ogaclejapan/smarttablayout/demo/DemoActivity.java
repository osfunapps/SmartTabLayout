package com.ogaclejapan.smarttablayout.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class DemoActivity extends AppCompatActivity {

  private static final String KEY_DEMO = "demo";

  public static void startActivity(Context context, Demo demo) {
    Intent intent = new Intent(context, DemoActivity.class);
    intent.putExtra(KEY_DEMO, demo.name());
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);

    Demo demo = getDemo();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(demo.titleResId);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
    tab.addView(LayoutInflater.from(this).inflate(demo.layoutResId, tab, false));

    ViewPager2 viewPager = (ViewPager2) findViewById(R.id.viewpager);
    SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
//    demo.setup(viewPagerTab);

//    FragmentPagerItems pages = new FragmentPagerItems(this);
//    for (int titleResId : demo.tabs()) {
//      pages.add(FragmentPagerItem.of(getString(titleResId), DemoFragment.class));
//    }

    ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(this);

    viewPager.setAdapter(adapter);
    viewPagerTab.setViewPager(viewPager);
    ArrayList<String> f = new ArrayList<String>();
    f.add("tab1", "tab2");
    viewPagerTab.populateTabStrip(f);

  }

  private Demo getDemo() {
    return Demo.valueOf(getIntent().getStringExtra(KEY_DEMO));
  }
}

class ViewPagerFragmentAdapter extends FragmentStateAdapter {
  public ViewPagerFragmentAdapter(@NonNull AppCompatActivity fragmentActivity) {
    super(fragmentActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    return new DemoFragment();
  }


  @Override
  public int getItemCount() {
    return 0;
  }
}