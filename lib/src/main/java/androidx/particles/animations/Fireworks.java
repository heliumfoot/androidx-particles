package androidx.particles.animations;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import androidx.particles.ParticleSystem;

import java.util.ArrayList;
import java.util.List;

public class Fireworks {
    private List<MultiSystem> systems;
    private long totalDuration;
    private long maxFireworkDuration;
    private Handler handler;

    public Fireworks(ViewGroup parentView, Activity activity, int maxParticles, List<Integer> drawableResIds, long totalDuration, long maxFireworkDuration, int count) {
        this.totalDuration = totalDuration;
        this.maxFireworkDuration = maxFireworkDuration;
        this.systems = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int fireworkDuration = Math.max(new Long(Math.round(Math.random() * maxFireworkDuration)).intValue(),new Long(Math.round(maxFireworkDuration/3.0)).intValue());
            this.systems.add(new MultiSystem(parentView, activity, maxParticles, drawableResIds , fireworkDuration));
        }

        this.handler = new Handler(Looper.getMainLooper());
    }

    public void go() {
        for (int i = 0; i < this.systems.size(); i++) {
            int delay = new Long(Math.round(Math.random() * (this.totalDuration - this.maxFireworkDuration))).intValue();

            final int index = i;
            this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    systems.get(index).go();
                }
            }, delay);
        }

    }


}

class MultiSystem {
    private ViewGroup parentView;

    private List<ParticleSystem> particleSystems;

    public MultiSystem(ViewGroup parentView, Activity activity, int maxParticles, List<Integer> drawableResIds, long duration) {
        this.parentView = parentView;
        this.particleSystems = new ArrayList<>();
        for (Integer drawableResId: drawableResIds) {
            ParticleSystem ps = new ParticleSystem(activity, maxParticles, drawableResId, duration);
            ps.setScaleRange(0.7f, 1.3f);
            ps.setSpeedRange(0.1f, 0.25f);
            ps.setRotationSpeedRange(90, 180);
            ps.setFadeOut(200, new AccelerateInterpolator());
            this.particleSystems.add(ps);
        }
    }

    public void go() {
        int x = new Double(Math.random() * parentView.getWidth()).intValue();
        int y = new Double(Math.random() * parentView.getHeight()).intValue();
        for (ParticleSystem ps: this.particleSystems) {
            ps.oneShot(x, y, 70);
        }

    }
}