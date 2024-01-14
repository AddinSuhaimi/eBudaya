package com.example.ebudaya.Adapters;

// SharedViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> editedName = new MutableLiveData<>();
    private final MutableLiveData<String> editedEmail = new MutableLiveData<>();
    private final MutableLiveData<String> editedBio = new MutableLiveData<>();

    public void setEditedName(String name) {
        editedName.setValue(name);
    }

    public LiveData<String> getEditedName() {
        return editedName;
    }

    public void setEditedEmail(String email) {
        editedEmail.setValue(email);
    }

    public LiveData<String> getEditedEmail() {
        return editedEmail;
    }

    public void setEditedBio(String bio) {
        editedBio.setValue(bio);
    }

    public LiveData<String> getEditedBio() {
        return editedBio;
    }
}
