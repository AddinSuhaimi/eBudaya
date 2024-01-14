package com.example.ebudaya.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.ebudaya.Adapters.SharedViewModel;
import com.example.ebudaya.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private CheckBox ProfileEditCBArt, ProfileEditCBHistory, ProfileEditCBDance, ProfileEditCBFood, ProfileEditCBHistoricalSites;
    EditText ProfileEditName, ProfileEditEmail, ProfileEditBio;
    Button ProfileEditBtnSave;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        ProfileEditName = view.findViewById(R.id.ProfileEditName);
        ProfileEditEmail = view.findViewById(R.id.ProfileEditEmail);
        ProfileEditBio = view.findViewById(R.id.ProfileEditBio);
        ProfileEditBtnSave = view.findViewById(R.id.ProfileEditBtnSave);

        // Initialize checkboxes
        ProfileEditCBArt = view.findViewById(R.id.ProfileEditCBArt);
        ProfileEditCBDance = view.findViewById(R.id.ProfileEditCBDance);
        ProfileEditCBFood = view.findViewById(R.id.ProfileEditCBFood);
        ProfileEditCBHistory = view.findViewById(R.id.ProfileEditCBHistory);
        ProfileEditCBHistoricalSites = view.findViewById(R.id.ProfileEditCBHistoricalSites);

        // Set listeners for each checkbox
        ProfileEditCBArt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedViewModel.setShowButtonArt(isChecked);
            }
        });

        ProfileEditCBDance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedViewModel.setShowButtonDance(isChecked);
            }
        });

        ProfileEditCBFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedViewModel.setShowButtonFood(isChecked);
            }
        });

        ProfileEditCBHistory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedViewModel.setShowButtonHistory(isChecked);
            }
        });

        ProfileEditCBHistoricalSites.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedViewModel.setShowButtonHistoricalSites(isChecked);
            }
        });

        ProfileEditBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedName = ProfileEditName.getText().toString();
                String editedEmail = ProfileEditEmail.getText().toString();
                String editedBio = ProfileEditBio.getText().toString();

                sharedViewModel.setEditedName(editedName);
                sharedViewModel.setEditedEmail(editedEmail);
                sharedViewModel.setEditedBio(editedBio);
                Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}