package com.android.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText accountEdit;
    private EditText passworEdit;
    private Button login;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.acount);
        passworEdit = (EditText) findViewById(R.id.password);

        rememberPass = (CheckBox) findViewById(R.id.remember_pass);

        login = (Button) findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password",false);

        if(isRemember){
            //将账号和密码都设置在文本框中
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passworEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //获取输入框中的内容
                String account = accountEdit.getText().toString();
                String password = passworEdit.getText().toString();
                //如果账号是admin,密码是123456就登录成功
                if(account.equals("admin")&&password.equals("123456"))
                {
                    editor = pref.edit();
                    if(rememberPass.isChecked()){//检查复选框有没有被选中
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    //跳转界面
                   // Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                   // startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Account or password is right",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Account or password is invail",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
