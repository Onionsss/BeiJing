package onionsss.it.smartbeijing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.constant.Constant;
import onionsss.it.smartbeijing.utils.SpUtil;

public class SplashActivity extends AppCompatActivity {

    private ImageView mSplash_iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initAnim();
    }

    private void initAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(-30, 30, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        rotateAnimation.setDuration(800);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(3);
        mSplash_iv_logo.startAnimation(rotateAnimation);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                enterHome();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void enterHome() {
        if(SpUtil.getBoolean(this, Constant.LOAD_SPLASH,false)){
            startActivity(new Intent(this,HomeActivity.class));
        }else{
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
    }

    private void initView() {
        mSplash_iv_logo = (ImageView) findViewById(R.id.splash_iv_logo);
    }
}
