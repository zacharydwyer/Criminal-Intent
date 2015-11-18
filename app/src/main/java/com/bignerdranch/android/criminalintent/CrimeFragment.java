package com.bignerdranch.android.criminalintent;

    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v4.app.Fragment;
    import android.text.Editable;
    import android.text.TextWatcher;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.EditText;

    import java.text.DateFormat;
    import java.text.FieldPosition;
    import java.text.ParsePosition;
    import java.util.Date;
    import java.util.UUID;

// A controller that interacts with model and view objects. Like you would with a traditional Activity.
public class CrimeFragment extends Fragment {

    // Bundle key
    private static final String ARG_CRIME_ID = "crime_id";

    // Each CrimeFragment has an associated crime.
    private Crime mCrime;

    // Widgets inside of this fragment
    private EditText mCrimeTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    // Used to create a new CrimeFragment. Should be called every time by CrimeActivity when it wants to create a Fragment.
    public static CrimeFragment newInstance(UUID crimeID) {
        Bundle argumentsBundle = new Bundle();

        argumentsBundle.putSerializable(ARG_CRIME_ID, crimeID);

        CrimeFragment fragment = new CrimeFragment();

        fragment.setArguments(argumentsBundle);

        return  fragment;
    }

    // This method is called by the parent activity's FragmentManager
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Crime ID that created our parent activity (which created us).
        // Retrieve it from the parent activity's Intent that was used to start it.
        // UUID crimeID = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);

        // Retrieve the singleton CrimeLab we have. It needs context (the activity) for some reason.
        // CrimeLab retrievedCrimeLab = CrimeLab.get(getActivity());

        // Set this Fragment's crime to the CrimeLab's crime that has the ID we just got.
        // mCrime = retrievedCrimeLab.getCrime(crimeID);

        // ABOVE CODE IS NO LONGER NEEDED //

        // Get the crimeID from the arguments bundle
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        // Assign a crime from the crime lab (a singleton - to get it, use "get") to this fragment depending on the ID - again, it needs context, for some reason
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    // LayoutInflater is provided for you so you can inflate your layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create the fragment view. Needs the root element (container) and whether to attach this view to the activity.
        View fragmentView = inflater.inflate(R.layout.crime_fragment, container, false);

        // Add handle to widgets - just like in Activity.onCreate() but now you call View.findViewById.
        //   In Activity.findViewById() it's a convenience method that calls View.findViewById behind the scenes.
        //   The Fragment class doesn't have a corresponding convenience method, so you have to call the real thing.
        mCrimeTitleField = (EditText) fragmentView.findViewById(R.id.crime_title_field);
        mDateButton = (Button) fragmentView.findViewById(R.id.crime_date_button);
        mSolvedCheckBox = (CheckBox) fragmentView.findViewById(R.id.crime_solved_checkbox);

        // Set properties
        mCrimeTitleField.setText(mCrime.getTitle());    // mCrime is assigned from onCreate
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        // Create a medium DateFormat formatter
        DateFormat mediumDF = DateFormat.getDateInstance(DateFormat.MEDIUM);

        // Use it to format the date and set the text of the button and disable it for now
        mDateButton.setText(mediumDF.format(mCrime.getDate()));
        mDateButton.setEnabled(false);

        // Add listeners
        mCrimeTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Set the title of the Crime object to whatever the user has typed in
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Doing nothing with this
            }
        });

        return fragmentView;
    }
}

// Nullable means that this method is permitted to return null. This is because fragments can
//   lack a UI.

// onCreateView is a method that expects a view that is created from the fragment xml layout.
// It is called by the hosting activity - it needs this view so it can be used to draw it in the Activity.
// It is public as opposed to protected because it needs to be called by the hosting activity.
// This is also where you wire up your widgets. This is because your widgets aren't even available in onCreate() -
//   they become available after the layout is inflated to become a view.

// You don't inflate  the fragment's view in onCreate().

// To attach an arguments bundle, you create the fragment, add the bundle, and then add it to the activity. Using Fragment.setArguments(Bundle)

/*
onCreate()
The system calls this when creating the fragment. Within your implementation,
you should initialize essential components of the fragment that you want to retain
when the fragment is paused or stopped, then resumed.

onCreateView()
The system calls this when it's time for the fragment to draw its user interface for the first time.
To draw a UI for your fragment, you must return a View from this method that is
the root of your fragment's layout.
You can return null if the fragment does not provide a UI.

onPause()
The system calls this method as the first indication that the user is
leaving the fragment (though it does not always mean the fragment is being destroyed).

This is usually where you should commit any changes that should be persisted beyond the current user session
(because the user might not come back).



Fragments cannot put their own views on the screen.


 */
