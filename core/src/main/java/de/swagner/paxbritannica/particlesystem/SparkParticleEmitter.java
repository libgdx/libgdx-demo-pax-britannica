package de.swagner.paxbritannica.particlesystem;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import de.swagner.paxbritannica.Resources;

public class SparkParticleEmitter extends ParticleEmitter {

	Vector2 particleVelocity = new Vector2();

	public SparkParticleEmitter() {
		super();

		life = 1.0f;
		damping = 5.95f;

		set(Resources.getInstance().spark);
	}

	public void addLaserExplosion(Vector2 position, Vector2 velocity) {
		for (int i = 1; i <= 10; ++i) {
			random.setToRandomDirection().scl((float) Math.sqrt(MathUtils.random()));

			particleVelocity.set(random.x - velocity.x, random.y - velocity.y);
			addParticle(position, particleVelocity, 1f, 1);
		}
	}

}
