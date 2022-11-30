package com.example.smartdoctor.ui.medical_test;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.smartdoctor.R;

public class MedicalTestFragmentDirections {
  private MedicalTestFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMedicalTestFragmentToReserveFragment() {
    return new ActionOnlyNavDirections(R.id.action_medicalTestFragment_to_reserveFragment);
  }
}
