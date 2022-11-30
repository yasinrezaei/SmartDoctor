package com.example.smartdoctor.ui.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.smartdoctor.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileFragmentToMedicalTestFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_medicalTestFragment);
  }
}
