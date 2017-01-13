package com.sdwfqin.mvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sdwfqin.mvpdemo.R;
import com.sdwfqin.mvpdemo.model.User;
import com.sdwfqin.mvpdemo.interactor.UserLoginInteractorImpl;
import com.sdwfqin.mvpdemo.presenter.UserLoginPresenterImpl;
import com.sdwfqin.mvpdemo.presenter.UserLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserLogenActivity extends AppCompatActivity implements UserLoginView {

    @BindView(R.id.user)
    EditText mUser;
    @BindView(R.id.pass)
    EditText mPass;
    @BindView(R.id.id_pb_loading)
    ProgressBar mIdPbLoading;
    private UserLoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_userlogen);
        ButterKnife.bind(this);

        mUserLoginPresenter = new UserLoginPresenterImpl(this, new UserLoginInteractorImpl());
    }

    @OnClick({R.id.btn_login, R.id.btn_clean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mUserLoginPresenter.validateCredentials(mUser.getText().toString(), mPass.getText().toString());
                break;
            case R.id.btn_clean:
                mUserLoginPresenter.clean();
                break;
        }
    }

    @Override
    public void clearUserName() {
        mUser.setText("");
    }

    @Override
    public void clearPassword() {
        mPass.setText("");
    }

    @Override
    public void showLoading() {
        mIdPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mIdPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getName() +
                " 登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mUserLoginPresenter.onDestroy();
        super.onDestroy();
    }
}
