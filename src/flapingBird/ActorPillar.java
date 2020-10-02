package flapingBird;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPillar extends Actor {

	private Rectangle upPillarShape;
	private Rectangle downPillarShape;
	private Texture texture;
	private boolean scoreLock;
	private Random rand;
	public boolean gameover;

	public int score;
	private int speed;
	private int dis;
	private ActorBird ActorBird;

	private Sprite upPillar, downPillar;
	private flapingBird fb;

	private int height;
	private int width;

	public ActorPillar(ActorBird haha, flapingBird flapingBird) {
		fb = flapingBird;
		ActorBird = haha;
		gameover = false;
		rand = new Random();
		dis = 100;
		upPillarShape = new Rectangle();
		downPillarShape = new Rectangle();

		speed = 100;

		texture = new Texture("upPillar.png");
		upPillar = new Sprite(texture);
		texture = new Texture("downPillar.png");
		downPillar = new Sprite(texture);
		upPillar.setPosition(200, 250);
		downPillar.setPosition(200, -150);

		width = 52;
		height = 320;
	}

	public void act(float delta) {
		if (upPillar.getX() < -width) {
			scoreLock = true;
			upPillar.setY(rand.nextInt(240) + 180);
			downPillar.setY(upPillar.getY() - 326 - dis);
			upPillar.setX(290);
			downPillar.setX(290);
		}

		upPillar.translateX(-speed * delta);
		downPillar.translateX(-speed * delta);
		if (upPillar.getX() < ActorBird.getX() && scoreLock) {
			fb.addScore();
			Gdx.app.log("Score", Integer.toString(score));

//			Gdx.app.log("Speed", Integer.toString(speed));
			scoreLock = false;
		}
		upPillarShape.set(upPillar.getX(), upPillar.getY(), width, height);
		downPillarShape.set(downPillar.getX(), downPillar.getY(), width, height);
		if (upPillarShape.overlaps(ActorBird.getShape()) || downPillarShape.overlaps(ActorBird.getShape())
				|| (ActorBird.getY() > 512 || ActorBird.getY() < 0)) {
			gameover = true;
			Gdx.app.log("Haha", "GameOver");
		}

	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
//		batch.draw(texture, x, y, width, height);

		batch.draw(upPillar, upPillar.getX(), upPillar.getY(), 52, 320);

		batch.draw(downPillar, downPillar.getX(), downPillar.getY(), width, height);
	}

}
