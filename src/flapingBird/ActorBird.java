package flapingBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorBird extends Actor {

	private float setTime;
	private TextureRegion animationRegion;
	private Animation flyingBird;
	private Texture animationBird;
	private float gravity;
	private float birdSpeed;
	public Rectangle shape;
	private Sound cs;
	private int height;
	private int width;

	public ActorBird() {
		super();
		animationBird = new Texture(Gdx.files.internal("birdA.png"));
		int frameW = animationBird.getWidth() / 8;
		int frameH = animationBird.getHeight();
		TextureRegion[][] tmp = TextureRegion.split(animationBird, frameW, frameH);
		flyingBird = new Animation(0.05F, tmp[0]);
		flyingBird.setPlayMode(Animation.PlayMode.LOOP);
		shape = new Rectangle();
		gravity = 2.5F;
		birdSpeed = 0F;
		this.setPosition(50, 200);
		height = 40;
		width = 42;

		cs = Gdx.audio.newSound(Gdx.files.internal("sounds//ansRight.mp3"));
	}

	public void fly() {
		if (Gdx.app.getPreferences("MyPref").getBoolean("isSoundOn", true)) {
			cs.setVolume(cs.play(), 0.1F);
		}
		birdSpeed = -100;
	}

	public void act(float delta) {
		super.act(delta);
		setTime += delta;
		animationRegion = flyingBird.getKeyFrame(setTime);
		birdSpeed += gravity;
		this.setY(this.getY() - birdSpeed * delta);
		shape.set(this.getX(), this.getY() + 5, width, height - 10);
//		if (Gdx.input.isButtonPressed(Buttons.LEFT) && birdSpeed > 0) {
//			fly();
//		}
	}

	public Rectangle getShape() {
		return shape;
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(animationRegion, this.getX(), this.getY(), width, height);
//		batch.draw(animationRegion, this.getX(), this.getY(), 34, 24);
	}
}
