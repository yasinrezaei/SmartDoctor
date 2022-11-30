// Generated by view binder compiler. Do not edit!
package com.example.smartdoctor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartdoctor.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TestBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText edtAge;

  @NonNull
  public final EditText edtFullName;

  @NonNull
  public final EditText edtPassword1;

  @NonNull
  public final EditText edtPassword2;

  @NonNull
  public final EditText edtUserName;

  @NonNull
  public final AppCompatButton loginBtn;

  private TestBinding(@NonNull LinearLayout rootView, @NonNull EditText edtAge,
      @NonNull EditText edtFullName, @NonNull EditText edtPassword1, @NonNull EditText edtPassword2,
      @NonNull EditText edtUserName, @NonNull AppCompatButton loginBtn) {
    this.rootView = rootView;
    this.edtAge = edtAge;
    this.edtFullName = edtFullName;
    this.edtPassword1 = edtPassword1;
    this.edtPassword2 = edtPassword2;
    this.edtUserName = edtUserName;
    this.loginBtn = loginBtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TestBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.test, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edtAge;
      EditText edtAge = ViewBindings.findChildViewById(rootView, id);
      if (edtAge == null) {
        break missingId;
      }

      id = R.id.edtFullName;
      EditText edtFullName = ViewBindings.findChildViewById(rootView, id);
      if (edtFullName == null) {
        break missingId;
      }

      id = R.id.edtPassword1;
      EditText edtPassword1 = ViewBindings.findChildViewById(rootView, id);
      if (edtPassword1 == null) {
        break missingId;
      }

      id = R.id.edtPassword2;
      EditText edtPassword2 = ViewBindings.findChildViewById(rootView, id);
      if (edtPassword2 == null) {
        break missingId;
      }

      id = R.id.edtUserName;
      EditText edtUserName = ViewBindings.findChildViewById(rootView, id);
      if (edtUserName == null) {
        break missingId;
      }

      id = R.id.loginBtn;
      AppCompatButton loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      return new TestBinding((LinearLayout) rootView, edtAge, edtFullName, edtPassword1,
          edtPassword2, edtUserName, loginBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}