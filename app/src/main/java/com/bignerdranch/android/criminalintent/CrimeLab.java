package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// We're not using the Context object just yet but we will in Chapter 14.
public class CrimeLab {

    // The current CrimeLab created
    private static CrimeLab sCrimeLab;

    // The list of Crimes this CrimeLab is holding

    // Using List instead of ArrayList because List is more generic than ArrayList in case you want
    //      to use something like LinkedList later.
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {

        // If there already isn't a crime lab created
        if (sCrimeLab == null) {

            // Create a new one using the private constructor
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    // Private constructor. We get a CrimeLab using get().
    private CrimeLab(Context context) {

        // Create a new Crimes list
        mCrimes = new ArrayList<>();

        // Populate the CrimeLab with 100 boring crimes - this is just for now.
        // TODO: Remove this when appropriate
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setSolved(i % 2 == 0);        // Every other crime
            mCrimes.add(crime);                 // Add crime to list
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    // Get a crime by its ID
    public Crime getCrime(UUID id) {

        // Look at every single crime in the mCrimes list
        for (Crime crime : mCrimes) {

            // If the current crime's ID is equal to the queried ID
            if (crime.getId().equals(id)) {

                // Return that crime!
                return crime;
            }
        }

        // We're out of the loop and we didn't find a crime. Return null.
        return null;
    }
}
