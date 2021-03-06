package com.minhnv.luxuryhomestay.ui.login.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.minhnv.luxuryhomestay.R;
import com.minhnv.luxuryhomestay.ui.base.BaseActivity;
import com.minhnv.luxuryhomestay.utils.AppLogger;
import com.minhnv.luxuryhomestay.utils.CustomToast;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.Random;

public class SignUpActivity extends BaseActivity<SignUpViewModel> implements SignUpNavigator {
    private static final String TAG = "SignUpActivity";
    private EditText username, address, phoneNumber;
    private EditText password;
    private TextView tvBack;
    private SlidrInterface anInterface;


    public static Intent newIntent(Context context) {
        return new Intent(context, SignUpActivity.class);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_sign_up);
        super.onCreate(savedInstanceState);
        viewmodel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
        viewmodel.setNavigator(this);
        initView();
    }

    private void initView() {
        anInterface = Slidr.attach(this);
        password = findViewById(R.id.includePassword);
        address = findViewById(R.id.includeAddress);
        phoneNumber = findViewById(R.id.includeCountMember);

        tvBack = findViewById(R.id.tvBackSignUp);
        tvBack.setOnClickListener(tv -> {
            onBackPressed();
        });

        Button btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(view -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            viewmodel.onSignUp();
        });
    }

    @Override
    public void handleError(Throwable throwable) {
        AppLogger.d("error" + throwable);
    }

    @Override
    public void login() {
        String inputPassword = password.getText().toString().trim();
        int random = new Random().nextInt(10);
        String pass = inputPassword + random;
        String inputAddress = address.getText().toString().trim();
        String inputPhoneNumber = phoneNumber.getText().toString();
        if (viewmodel.isRequestValidate(inputPassword, inputPhoneNumber, inputAddress)) {
            viewmodel.signup(pass, inputPhoneNumber, inputAddress);
            showLoading();
        } else {
            CustomToast.makeText(this,getString(R.string.email_password_valid),Toast.LENGTH_LONG,CustomToast.ERROR).show();
        }
    }

    @Override
    public void onFailed() {
        hideLoading();
        CustomToast.makeText(this,getString(R.string.failed),Toast.LENGTH_LONG,CustomToast.ERROR).show();
    }

    @Override
    public void openSignInActivity() {
        hideLoading();
        setResult(RESULT_OK);
        onBackPressed();
        finish();
        CustomToast.makeText(this,getString(R.string.add_successfully),Toast.LENGTH_LONG,CustomToast.SUCCESS).show();
    }

    @Override
    public void onInsertSuccess() {
        AppLogger.d(TAG, "onInsertSuccess: ");
    }

    @Override
    public void onInsertFailed() {
        AppLogger.d(TAG, "onInsertFailed: ");
    }

    @Override
    public void validatePassword() {
        Toast.makeText(this, "Password phải là số và lớn hơn 5 kí tự", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validatePhoneNumber() {
        Toast.makeText(this, "Số điện thoại sai định dạng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateName() {
        Toast.makeText(this, "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
    }


}
