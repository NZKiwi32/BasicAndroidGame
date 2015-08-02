package game.systems;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import game.components.DrawableComponent;
import game.components.PositionComponent;

/**
 * Render System
 * System which draws DrawableComponents to the android view.
 * Created by Steven on 7/30/2015.
 */
public class RenderSystem extends IteratingSystem {
    ComponentMapper<DrawableComponent> drawableMapper;
    ComponentMapper<PositionComponent> positionMapper;
    private Array<Entity> renderQueue;
    private SurfaceHolder surfaceHolder;
    private Resources resources;

    public RenderSystem(SurfaceHolder surfaceHolder, Resources resources) {
        super(Family.all(DrawableComponent.class, PositionComponent.class).get());

        this.surfaceHolder = surfaceHolder;
        this.resources = resources;

        this.renderQueue = new Array<>();
        this.drawableMapper =  ComponentMapper.getFor(DrawableComponent.class);
        this.positionMapper =  ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    protected void clearCanvas (Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    protected void drawRenderQueue(Canvas canvas) {
        for ( Entity e : this.renderQueue) {
            canvas.drawBitmap(BitmapFactory.decodeResource(this.resources, drawableMapper.get(e).resourceID), positionMapper.get(e).x, positionMapper.get(e).y, null);
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
