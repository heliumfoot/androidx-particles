package com.plattysoft.leonids.examples;

import androidx.particles.ParticleSystem;
import androidx.particles.animations.Fireworks;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FireworksExampleActivity extends Activity implements OnClickListener {
	private Fireworks fireworks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_constraint_example);
		findViewById(R.id.button1).setOnClickListener(this);

		ViewGroup parentView = (ViewGroup)this.findViewById(R.id.background_hook);

		this.fireworks = new Fireworks(parentView, this, 100, Arrays.asList(R.drawable.star_pink, R.drawable.star_white) , 3000, 800, 12);

	}

	@Override
	public void onClick(View arg0) {
		this.fireworks.go();
	}

}


