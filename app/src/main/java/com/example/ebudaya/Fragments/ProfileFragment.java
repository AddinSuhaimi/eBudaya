package com.example.ebudaya.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.ebudaya.Adapters.SharedViewModel;
import com.example.ebudaya.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private TextView ProfilePageName, ProfilePageEmail, ProfilePageBio;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        Button BtnEdit = view.findViewById(R.id.ProfilePageButtonEdit);
        BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_editProfileFragment);
            }
        });
//        View.OnClickListener OCLEdit = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.nav_edit_profile);
//            }
//        };
//        BtnEdit.setOnClickListener(OCLEdit);

        sharedViewModel.getShowButtonArt().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean showButton) {
                Button ProfilePageButtonArt = view.findViewById(R.id.ProfilePageButtonArt);
                if (showButton) {
                    // Show ProfilePageButtonArt
                    ProfilePageButtonArt.setVisibility(View.VISIBLE);
                } else {
                    // Hide ProfilePageButtonArt
                    ProfilePageButtonArt.setVisibility(View.GONE);
                }
            }
        });
        sharedViewModel.getShowButtonDance().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean showButton) {
                Button ProfilePageButtonDance = view.findViewById(R.id.ProfilePageButtonDance);
                if (showButton) {
                    // Show ProfilePageButtonDance
                    ProfilePageButtonDance.setVisibility(View.VISIBLE);
                } else {
                    // Hide ProfilePageButtonDance
                    ProfilePageButtonDance.setVisibility(View.GONE);
                }
            }
        });
        sharedViewModel.getShowButtonFood().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean showButton) {
                Button ProfilePageButtonFood = view.findViewById(R.id.ProfilePageButtonFood);
                if (showButton) {
                    // Show ProfilePageButtonFood
                    ProfilePageButtonFood.setVisibility(View.VISIBLE);
                } else {
                    // Hide ProfilePageButtonDance
                    ProfilePageButtonFood.setVisibility(View.GONE);
                }
            }
        });
        sharedViewModel.getShowButtonHistory().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean showButton) {
                Button ProfilePageButtonHistory = view.findViewById(R.id.ProfilePageButtonHistory);
                if (showButton) {
                    // Show ProfilePageButtonHistory
                    ProfilePageButtonHistory.setVisibility(View.VISIBLE);
                } else {
                    // Hide ProfilePageButtonHistory
                    ProfilePageButtonHistory.setVisibility(View.GONE);
                }
            }
        });
        sharedViewModel.getShowButtonHistoricalSites().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean showButton) {
                Button ProfilePageButtonHistoricalSites = view.findViewById(R.id.ProfilePageButtonHistoricalSites);
                if (showButton) {
                    // Show ProfilePageButtonHistoricalSites
                    ProfilePageButtonHistoricalSites.setVisibility(View.VISIBLE);
                } else {
                    // Hide ProfilePageButtonHistoricalSites
                    ProfilePageButtonHistoricalSites.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ProfilePageName = view.findViewById(R.id.ProfilePageName);
        ProfilePageEmail = view.findViewById(R.id.ProfilePageEmail);
        ProfilePageBio = view.findViewById(R.id.ProfilePageBio);
        ImageView ProfilePageImage = view.findViewById(R.id.ProfilePagePicture);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            Uri photoUrl = currentUser.getPhotoUrl();

            ProfilePageName.setText(name);
            ProfilePageEmail.setText(email);
            if (photoUrl != null) {
                Glide.with(this).load(photoUrl).transform(new CircleCrop()).into(ProfilePageImage);
            }
        }

        sharedViewModel.getEditedName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newName) {
                // If newName is not empty, use it; otherwise, use the existing name
                ProfilePageName.setText(!newName.isEmpty() ? newName : currentUser.getDisplayName());
            }
        });
        sharedViewModel.getEditedEmail().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newEmail) {
                // If newEmail is not empty, use it; otherwise, use the existing email
                ProfilePageEmail.setText(!newEmail.isEmpty() ? newEmail : currentUser.getEmail());
            }
        });

        sharedViewModel.getEditedBio().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newBio) {
                ProfilePageBio.setText(newBio);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}