package br.com.snow.vanderson.snowmovies;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.snow.vanderson.snowmovies.fragments.FavoriteFragment;
import br.com.snow.vanderson.snowmovies.fragments.PopularFragment;
import br.com.snow.vanderson.snowmovies.fragments.TopFragment;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_ITEM = "arg_selected_item";
    private BottomNavigationView mBottomNav;
    private int mSelectedItem;
    private Fragment frag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }

    private void selectFragment(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_top:
                frag = new TopFragment();
                item.setChecked(true);
                TopFragment topFrag = (TopFragment)frag;
                updateToolbarText(item.getTitle(), topFrag.getBack(), topFrag.getFoward());
                break;
            case R.id.navigation_favorite:
                frag = new FavoriteFragment();
                item.setChecked(true);
                //FavoriteFragment favoriteFrag = (FavoriteFragment)frag;
                updateToolbarText(item.getTitle(), null, null);
                break;
            case R.id.navigation_popular:
                frag = new PopularFragment();
                item.setChecked(true);
                PopularFragment popularFrag = (PopularFragment)frag;
                updateToolbarText(item.getTitle(), popularFrag.getBack(), popularFrag.getFoward());
                break;
        }


        // update selected item
        mSelectedItem = item.getItemId();

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_movie, frag, frag.getTag());
            ft.commit();

        }
    }
    private void updateToolbarText(CharSequence title, View.OnClickListener backClick, View.OnClickListener advanceClick) {
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        View mCustomView = getLayoutInflater().inflate(R.layout.action_bar_custom, null);
        getSupportActionBar().setCustomView(mCustomView);

        TextView mTitle = (TextView)mCustomView.findViewById(R.id.txtTitleAction);
        mTitle.setText(title);

        ImageView btnBack = (ImageView) mCustomView.findViewById(R.id.btnLeftActionBar);
        if( backClick != null ){
            btnBack.setOnClickListener(backClick);
        } else {
            btnBack.setVisibility(View.GONE);
        }

        ImageView btnAdvance = (ImageView) mCustomView.findViewById(R.id.btnRightActionBar);
        if( advanceClick != null ){
            btnAdvance.setOnClickListener(advanceClick);
        }else {
            btnAdvance.setVisibility(View.GONE);
        }

    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }



}
