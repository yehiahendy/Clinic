package com.example.clinic.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clinic.LoginActivity;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();


    }

    public LiveData<String> getText() {
        return mText;
    }
}


     /* name = root.findViewById(R.id.txtname);
              mail = root.findViewById(R.id.txtMail);
              address = root.findViewById(R.id.txtaddress);
              phone = root.findViewById(R.id.txtphone);
              gender =root.findViewById(R.id.txtgender);
              name.setText("Your Name is : "+ LoginActivity.Name);
              name.setText("Your Address is : "+ LoginActivity.Address);

        name.setText("Your Email is : "+ LoginActivity.Mail);
        name.setText("Your Gender is : "+ LoginActivity.Gender);
        name.setText("Your Phone number is : "+ LoginActivity.Phone);
        name.setText("Your Birth date  is : "+ LoginActivity.Date);*/