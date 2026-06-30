package de.swagner.paxbritannica.bomber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import de.swagner.paxbritannica.Ship;
import de.swagner.paxbritannica.Targeting;
import de.swagner.paxbritannica.frigate.Frigate;

public class BomberAI {
	private static final float APPROACH_DISTANCE = 210;
    private static final float COOLDOWN_DURATION = 0.6f;
    private static final float MAX_SHOTS = 4;

	private float cooldown_timer = 0;
	private float shots_counter = MAX_SHOTS;
	private int approach_sign = 1;
	float delta;

	// 0 = approach
	// 1 = turn
	// 2 = shoot
	// 3 = move_away
	private int state = 0;

	//recycle
	final Vector2 target_direction = new Vector2();

	public Ship target;

	private final Bomber bomber;

	public BomberAI(Bomber bomber) {
		this.bomber = bomber;
	}

	public void retarget() {
		target = Targeting.getNearestOfType(bomber, 2);
		if (target == null) {
			target = Targeting.getNearestOfType(bomber, 3);
		}
	}

	public void reviseApproach() {
        approach_sign = MathUtils.randomSign();
    }

	public void update() {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

		if (target == null || !target.alive || MathUtils.random() < 0.005f) {
			Ship old_target = target;
			retarget();
			if (old_target != null && target != null && old_target.id != target.id) {
				reviseApproach();
			}
		}

		if (target != null) {
			float target_distance = target.collisionCenter.dst(bomber.collisionCenter);
			target_direction.set(target.collisionCenter).sub(bomber.collisionCenter).nor();

			float unit_factor;
			if (target instanceof Frigate) {
				unit_factor = 0.6f;
			} else {
				unit_factor = 1;
			}

			if (target_distance > (APPROACH_DISTANCE + 50) * unit_factor) {
				state = 0;
			}

			// approach
			if (state == 0) {
				bomber.goTowards(target.collisionCenter, true);
				if (target_distance < APPROACH_DISTANCE * unit_factor) {
					reviseApproach();
					state = 1;
				}
			}
			// turn
			else if (state == 1) {
				bomber.turn(-approach_sign);
				bomber.thrust(unit_factor * 0.75f);
				if (target_direction.dot(bomber.facing) < 0.5f) {
					state = 2;
				}
			}
			// shoot
			else if (state == 2) {
				bomber.turn(approach_sign * 0.05f);
				bomber.thrust(unit_factor * 0.75f);

				cooldown_timer -= delta;
				if (cooldown_timer <= 0) {
					bomber.shoot(approach_sign);
					cooldown_timer = COOLDOWN_DURATION;
					shots_counter -= 1;

					if (shots_counter == 0) {
						shots_counter = MAX_SHOTS;
						state = 3;
					}
				}
			}
			// move_away
			else if (state == 3) {
				bomber.goAway(target.collisionCenter, true);
			}
		}
	}
}
