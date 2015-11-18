package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

// A "pager" is something that handles/pages through "pages", or Fragments in this case.
public class CrimePagerActivity extends FragmentActivity {

    // My ViewPager
    private ViewPager mViewPager;

    // My list of crimes I need to work with
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_pager_activity);

        // Assign widgets
        mViewPager = (ViewPager) findViewById(R.id.crime_pager_activity_view_pager);

        // Get the Crimes I'm working with. The CrimeLab needs context for reasons yet to be known.
        mCrimes = CrimeLab.get(this).getCrimes();

        // Get the support version of the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // My ViewPager needs an adapter. Create an anonymous inner class that overrides FragmentStatePagerAdapter.
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            // Called when my ViewPager needs a new Fragment at a specific position in the list that the adapter is dealing with
            @Override
            public Fragment getItem(int position) {

                // Get the crime from my list of crimes at that position.
                Crime singleCrime = mCrimes.get(position);

                // Create a crimeFragment with the Crime we just got, using its ID.
                return CrimeFragment.newInstance(singleCrime.getId());
            }

            // Called when my ViewPager needs to know how many items are in the data set
            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
    }
}

/*
    FragmentStatePagerAdapter is an "agent" that manages the conversation between ViewPager and your Fragments.
    It uses the FragmentManager to add them to your activity.
 */