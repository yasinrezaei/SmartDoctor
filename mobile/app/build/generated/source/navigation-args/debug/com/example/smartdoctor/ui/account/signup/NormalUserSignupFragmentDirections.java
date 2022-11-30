package com.example.smartdoctor.ui.account.signup;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.smartdoctor.R;

public class NormalUserSignupFragmentDirections {
  private NormalUserSignupFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionNormalUserSignupFragmentToDoctorSignupFragment() {
    return new ActionOnlyNavDirections(R.id.action_normalUserSignupFragment_to_doctorSignupFragment);
  }
}
