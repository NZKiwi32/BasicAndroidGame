package game.systems;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import game.components.ImageDrawableComponent;
import game.components.PositionComponent;

/**
 * Render System
 * System which draws Shape Components to the android view.
 * Created by Steven on 7/30/2015.
 */
public class ShapeRenderSystem extends IteratingSystem {
    ComponentMapper<ImageDrawableComponent> drawableMapper;
    ComponentMapper<PositionComponent> positionMapper;

    private Array<Entity> renderQueue;
    private SurfaceHolder surfaceHolder;

    public ShapeRenderSystem(SurfaceHolder surfaceHolder) {
        super(Family.all(ImageDrawableComponent.class, PositionComponent.class).get());

        this.surfaceHolder = surfaceHolder;

        this.renderQueue = new Array<>();
        this.drawableMapper = ComponentMapper.getFor(ImageDrawableComponent.class);
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    protected void clearCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    protected void drawRenderQueue(Canvas canvas) {
        for (Entity e : this.renderQueue) {
           // canvas.drawBitmap(BitmapFactory.decodeResource(this.resources, drawableMapper.get(e).resourceID), positionMapper.get(e).x, positionMapper.get(e).y, null);
        }

        renderQueue.clear();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Canvas canvas = null;
        try {
            canvas = this.surfaceHolder.lockCanvas();

            synchronized (surfaceHolder) {
                this.clearCanvas(canvas);
                this.drawRenderQueue(canvas);
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

}
