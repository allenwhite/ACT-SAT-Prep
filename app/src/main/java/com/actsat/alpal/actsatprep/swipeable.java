package com.actsat.alpal.actsatprep;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;


public class swipeable extends AppCompatActivity {

    String getHelp1         = "getHelp";
    String improveScore2    = "improveScore";
    String aboutTheTest3    = "aboutTheTest";

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    String prevButtonTitle, testChosen;
    ArrayList<CharSequence> swipeStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeable);
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.


        Intent intent = getIntent();
        prevButtonTitle = intent.getStringExtra("infoChosen");
        testChosen = intent.getStringExtra("testChosen");

        swipeStrings = new ArrayList<CharSequence>();
        populateWithCorrectData();

        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }

    // Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putCharSequence("text", swipeStrings.get(i));

            // Our object is just an integer :-P
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return swipeStrings.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

    // Instances of this class are fragments representing a single
// object in our collection.
    public static class DemoObjectFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            View rootView = inflater.inflate(
                    R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(args.getCharSequence("text"));

            ((TextView) rootView.findViewById(android.R.id.text1)).setMovementMethod(LinkMovementMethod.getInstance());
            return rootView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipeable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateWithCorrectData() {
        if (prevButtonTitle.equals(getHelp1)){
            setTitle("Where To Go For Help");
            swipeStrings.add(getResources().getText(R.string.whereToGetHelp1));
            swipeStrings.add(getResources().getText(R.string.whereToGetHelp2));
            swipeStrings.add(getResources().getText(R.string.whereToGetHelp3));
            if (testChosen.equals("SAT")){
                swipeStrings.add(getResources().getText(R.string.whereToGetHelp7));
                swipeStrings.add(getResources().getText(R.string.whereToGetHelp8));
                swipeStrings.add(getResources().getText(R.string.whereToGetHelp9));
            }else{
                swipeStrings.add(getResources().getText(R.string.whereToGetHelp4));
                swipeStrings.add(getResources().getText(R.string.whereToGetHelp5));
                swipeStrings.add(getResources().getText(R.string.whereToGetHelp6));
            }
        }else if (prevButtonTitle.equals(improveScore2)){
            setTitle("Tips to Improve Your Score");
            if (testChosen.equals("SAT")){
                swipeStrings.add(getResources().getText(R.string.tips13));
                swipeStrings.add(getResources().getText(R.string.tips14));
                swipeStrings.add(getResources().getText(R.string.tips15));
                swipeStrings.add(getResources().getText(R.string.tips16));
                swipeStrings.add(getResources().getText(R.string.tips17));
                swipeStrings.add(getResources().getText(R.string.tips18));
            }else {
                swipeStrings.add(getResources().getText(R.string.tips1));
                swipeStrings.add(getResources().getText(R.string.tips2));
                swipeStrings.add(getResources().getText(R.string.tips3));
                swipeStrings.add(getResources().getText(R.string.tips4));
                swipeStrings.add(getResources().getText(R.string.tips5));
                swipeStrings.add(getResources().getText(R.string.tips6));
                swipeStrings.add(getResources().getText(R.string.tips7));
                swipeStrings.add(getResources().getText(R.string.tips8));
                swipeStrings.add(getResources().getText(R.string.tips9));
                swipeStrings.add(getResources().getText(R.string.tips10));
                swipeStrings.add(getResources().getText(R.string.tips11));
                swipeStrings.add(getResources().getText(R.string.tips12));
            }
        }else if (prevButtonTitle.equals(aboutTheTest3)){
            setTitle("About the Test");
            if (testChosen.equals("SAT")) {
                swipeStrings.add(getResources().getText(R.string.aboot10));
                swipeStrings.add(getResources().getText(R.string.aboot11));
                swipeStrings.add(getResources().getText(R.string.aboot12));
                swipeStrings.add(getResources().getText(R.string.aboot13));
                swipeStrings.add(getResources().getText(R.string.aboot14));
                swipeStrings.add(getResources().getText(R.string.aboot15));
                swipeStrings.add(getResources().getText(R.string.aboot16));
                swipeStrings.add(getResources().getText(R.string.aboot17));
                swipeStrings.add(getResources().getText(R.string.aboot18));
                swipeStrings.add(getResources().getText(R.string.aboot19));
                swipeStrings.add(getResources().getText(R.string.aboot20));
                swipeStrings.add(getResources().getText(R.string.aboot21));
            }else {
                swipeStrings.add(getResources().getText(R.string.aboot1));
                swipeStrings.add(getResources().getText(R.string.aboot2));
                swipeStrings.add(getResources().getText(R.string.aboot3));
                swipeStrings.add(getResources().getText(R.string.aboot4));
                swipeStrings.add(getResources().getText(R.string.aboot5));
                swipeStrings.add(getResources().getText(R.string.aboot6));
                swipeStrings.add(getResources().getText(R.string.aboot7));
                swipeStrings.add(getResources().getText(R.string.aboot8));
                swipeStrings.add(getResources().getText(R.string.aboot9));
            }
        }
    }
}
