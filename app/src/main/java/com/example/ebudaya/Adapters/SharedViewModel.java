package com.example.ebudaya.Adapters;

// SharedViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> editedName = new MutableLiveData<>();
    private final MutableLiveData<String> editedEmail = new MutableLiveData<>();
    private final MutableLiveData<String> editedBio = new MutableLiveData<>();
    private MutableLiveData<Boolean> showButtonArt = new MutableLiveData<>();
    private MutableLiveData<Boolean> showButtonHistory = new MutableLiveData<>();
    private MutableLiveData<Boolean> showButtonDance = new MutableLiveData<>();
    private MutableLiveData<Boolean> showButtonFood = new MutableLiveData<>();
    private MutableLiveData<Boolean> showButtonHistoricalSites = new MutableLiveData<>();

    public void setEditedName(String name) {
        editedName.setValue(name);
    }

    public LiveData<String> getEditedName() {
        return editedName;
    }

    public void setEditedEmail(String email) {editedEmail.setValue(email);}

    public LiveData<String> getEditedEmail() {
        return editedEmail;
    }

    public void setEditedBio(String bio) {
        editedBio.setValue(bio);
    }

    public LiveData<String> getEditedBio() {
        return editedBio;
    }

    public void setShowButtonArt(boolean value) {showButtonArt.setValue(value);}

    public void setShowButtonHistory(boolean value) {
        showButtonHistory.setValue(value);
    }

    public LiveData<Boolean> getShowButtonHistory() {
        return showButtonHistory;
    }

    public LiveData<Boolean> getShowButtonArt() {
        return showButtonArt;
    }

    public void setShowButtonDance(boolean value) {
        showButtonDance.setValue(value);
    }

    public LiveData<Boolean> getShowButtonDance() {
        return showButtonDance;
    }

    public void setShowButtonFood(boolean value) {
        showButtonFood.setValue(value);
    }

    public LiveData<Boolean> getShowButtonFood() {
        return showButtonFood;
    }

    public void setShowButtonHistoricalSites(boolean value) {
        showButtonHistoricalSites.setValue(value);
    }

    public LiveData<Boolean> getShowButtonHistoricalSites() {
        return showButtonHistoricalSites;
    }
}
