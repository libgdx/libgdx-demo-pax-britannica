package de.swagner.paxbritannica.particlesystem;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import de.swagner.paxbritannica.Resources;

public class SparkParticleEmitter extends ParticleEmitter {

	Vector2 particleVelocity = new Vector2();

	public SparkParticleEmitter() {
		super();

		life = 1.0f;
		damping =5.95f;

		set(Resources.getInstance().spark);
	}

	public void addLaserExplosion(Vector2 position, Vector2 velocity) {
		for (int i = 1; i <= 10; ++i) {
			random.set(MathUtils.cos(((MathUtils.random() * MathUtils.PI2) * (float) Math.sqrt(MathUtils.random()))),
                (MathUtils.sin(MathUtils.random() * MathUtils.PI2) * (float) Math.sqrt(MathUtils.random())));

			particleVelocity.set(-velocity.x + random.x, -velocity.y + random.y);
			addParticle(position, particleVelocity, 1f, 1);
		}
	}

}
