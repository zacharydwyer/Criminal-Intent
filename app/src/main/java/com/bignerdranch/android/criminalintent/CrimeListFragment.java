package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CrimeListFragment extends Fragment{

    private RecyclerView mCrimeRecyclerView;            // This fragment contains one widget - a RecyclerView

    private CrimeAdapter mAdapter;                      // The adapter that will work between your data and the RecyclerView

    @Nullable
    @Override
    // When CrimeListFragment is put up on the screen
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create a View from the crime list fragment layout, using the container for layout parameters (when it references layout_width and whatnot), and don't add it to the layout just yet.
        View view = inflater.inflate(R.layout.crime_list_fragment, container, false);

        // Get the RecyclerView from the View we just created - it's in there.
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);

        // RecyclerViews need a layout manager to work with. They handle the positioning of items and define the scrolling behavior.
        // We are using the LinearLayoutManager, which positions items in the list vertically. There are other types of layout managers.
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // So far, creates an Adapter using a new CrimeLab's list of Crimes and assigns that Adapter to the RecyclerView.
        updateUI();

        // Return the view we just built
        return view;
    }

    // This will later on be more complex.
    private void updateUI() {

        // Create a new CrimeLab.
        CrimeLab crimeLab = CrimeLab.get(getActivity());

        // Get the crimes from that CrimeLab.
        List<Crime> crimes = crimeLab.getCrimes();

        // Create a new adapter that handles the crimes List. CrimeAdapter is only equipped to handle List<Crime>
        mAdapter = new CrimeAdapter(crimes);

        // Give the RecyclerView this adapter
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    /* INNER CLASSES THAT SUPPORT THE RECYCLERVIEW */
    private class CrimeViewHolder extends RecyclerView.ViewHolder {

        // Widgets inside of this ViewHolder
        public TextView mTitleTextView;

        // Create a viewHolder. Expects to be given a TextView to work with.
        public CrimeViewHolder(View itemView) {
            super(itemView);

            // Expects the itemView to be a textView.
            mTitleTextView = (TextView) itemView;
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeViewHolder> {

        // Data this Adapter is working with. It's a list
        private List<Crime> mCrimes;

        // Called by you, the programmer
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        // RecyclerView needs a new ViewHolder. Doesn't expect you to assign it any data yet. Just the ViewHolder.
        @Override
        public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Get the layout inflater from the activity
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Build a view using a baked-in layout from the Android library
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            // Create a new ViewHolder using the View you just built.
            return new CrimeViewHolder(view);
        }

        // RecyclerView needs you to update the data of the given ViewHolder. Use the given position to determine what data in the list to give it.
        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position) {

            // Get the crime at the current position
            Crime crime = mCrimes.get(position);

            // Set the text of the CrimeHolder's text view to the title of the crime
            holder.mTitleTextView.setText(crime.getTitle());
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
