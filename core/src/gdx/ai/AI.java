package gdx.ai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class AI extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    ShapeRenderer SR;
    float fUserX, fUserY, fEnX = 0, fEnY = 0, fEnRadX, fEnRadY, fHealth;
    boolean isNear = false, isHit;
    int nDeadTime = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        SR = new ShapeRenderer();
        fUserX = Gdx.graphics.getWidth() / 2 - 25;
        fUserY = Gdx.graphics.getHeight() / 2 - 25;
        fEnRadX = fEnX - 100;
        fEnRadY = fEnY - 100;
        fHealth = Gdx.graphics.getWidth();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player();
        enemy();
        health();
        hit();
        reset();
        playerMove();
        enemyMove();
    }

    public void player() {
        SR.begin(ShapeType.Filled);
        SR.setColor(Color.BLACK);
        SR.rect(fUserX, fUserY, 50, 50);
        SR.end();
    }

    public void enemyHitRadius() {
        SR.begin(ShapeType.Line);
        SR.setColor(Color.RED);
        SR.ellipse(fEnRadX, fEnRadY, 250, 250);
        SR.end();
    }

    public void enemy() {
        SR.begin(ShapeType.Filled);
        SR.setColor(Color.CYAN);
        SR.rect(fEnX, fEnY, 50, 50);
        SR.end();
        //enemyHitRadius();
    }

    public void enemyMove() {
        initate();
        if (isNear == true && isHit == false) {
            if (fEnX > fUserX) {
                fEnX--;
                fEnRadX--;
            } else if (fEnX < fUserX) {
                fEnX++;
                fEnRadX++;
            }
            if (fEnY > fUserY) {
                fEnY--;
                fEnRadY--;
            } else if (fEnY < fUserY) {
                fEnY++;
                fEnRadY++;
            }
        }
    }

    public void initate() {
        if (fUserX >= fEnRadX && fUserX <= fEnRadX + 250
                && fUserY >= fEnRadY && fUserY <= fEnRadY + 250) {
            isNear = true;
        }
    }

    public void hit() {
        if (fUserX + 50 >= fEnX && fUserX <= fEnX + 50
                && fUserY + 50 >= fEnY && fUserY <= fEnY + 50) {
            fHealth -= 50;
            isHit = true;
        }
    }

    public void reset() {
        if (isHit == true) {
            fEnX = 0;
            fEnY = 0;
            fEnRadX = fEnX - 100;
            fEnRadY = fEnY - 100;
            isNear = false;
            nDeadTime++;
            if (nDeadTime == 600) {
                nDeadTime = 0;
                isHit = false;
            }
        }
    }

    public void health() {
        SR.begin(ShapeType.Filled);
        SR.setColor(Color.RED);
        SR.rect(0, Gdx.graphics.getHeight() - 25, fHealth, 25);
        SR.end();
    }

    public void playerMove() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            fUserY += 2;
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                fUserX -= 2;
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                fUserX += 2;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            fUserY -= 2;
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                fUserX -= 2;
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                fUserX += 2;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            fUserX -= 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            fUserX += 2;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
