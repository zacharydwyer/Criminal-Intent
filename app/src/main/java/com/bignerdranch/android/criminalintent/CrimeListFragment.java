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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
    private class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Assigned when data (a Crime) is bound to this ViewHolder from bindCrime. Used by onClick to show a toast with the crime's name.
        private Crime mCrime;

        // Widgets inside of this ViewHolder
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        // Called by CrimeAdapter's onCreateViewHolder method.
        public CrimeViewHolder(View itemView) {
            super(itemView);

            // Set the onClickListener for the view, which is built by the CrimeViewHolder, to this - I have implemented View.OnClickListener so I'm using myself.
            itemView.setOnClickListener(this);

            // Wire up the widgets
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_list_item_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_list_item_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.crime_list_item_solved_check_box);

            // This is why ViewHolders are great - calls to findViewById() are expensive. Since onCreateViewHolder
            // is called only 12 or so times in a run to actually create the widgets, onBindViewHolder is called
            // way more times but it only assigns text/other data to these already-created and searched for
            // widgets. So you don't have to create a new widget, just re-use the old one.
        }

        // What happens when this ViewHolder is clicked on? (gets mCrime, which is assigned in bindCrime)
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mCrime.getTitle() + " clicked.", Toast.LENGTH_SHORT).show();
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;             // Assign the gotten crime to this ViewHolder's crime. WHY THO? WHY NOT JUST USE "crime"?
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
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

            // Build a view using the layout you defined, give it the parent for the layout params, and don't actually add it to the parent.
            // !! THIS IS THE ITEMVIEW THAT CRIMEVIEWHOLDER RECEIVES!
            View view = layoutInflater.inflate(R.layout.crime_list_item, parent, false);

            // Create a new ViewHolder using the View you just built. CrimeViewHolder will wire up the widgets.
            return new CrimeViewHolder(view);
        }

        // RecyclerView needs you to update the data of the given ViewHolder. Use the given position to determine what data in the list to give it.
        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position) {

            // Get the crime at the current position
            Crime crime = mCrimes.get(position);

            // Bind the given ViewHolder's data using the crime we just got
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
