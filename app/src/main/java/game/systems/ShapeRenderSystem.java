package game.systems;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import game.components.DrawableComponent;
import game.components.PositionComponent;
import game.components.ShapeComponent;

/**
 * Render System
 * System which draws Shape Components to the android view.
 * Created by Steven on 7/30/2015.
 */
public class ShapeRenderSystem extends IteratingSystem {
    public static final String TAG = ShapeRenderSystem.class.getSimpleName();
    ComponentMapper<DrawableComponent> drawableMapper;
    ComponentMapper<ShapeComponent> shapeMapper;
    ComponentMapper<PositionComponent> positionMapper;

    private Array<Entity> renderQueue;
    private SurfaceHolder surfaceHolder;

    public ShapeRenderSystem(SurfaceHolder surfaceHolder) {
        super(Family.all(DrawableComponent.class, PositionComponent.class, ShapeComponent.class).get());

        this.surfaceHolder = surfaceHolder;

        this.renderQueue = new Array<>();
        this.drawableMapper = ComponentMapper.getFor(DrawableComponent.class);
        this.shapeMapper = ComponentMapper.getFor(ShapeComponent.class);
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
        Log.d(TAG, "RENDER QUEUE SIZE:" + this.renderQueue.size);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        for (Entity e : this.renderQueue) {
            canvas.drawPath(shapeMapper.get(e).shape.getPath(), p);
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
