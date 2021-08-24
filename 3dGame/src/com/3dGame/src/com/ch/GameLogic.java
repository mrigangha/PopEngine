package com.ch;

import com.PopEngine.*;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL33.*;

public class GameLogic implements IGameLogic {
    private Renderer renderer;
    private Shader shader;
    private Camera camera;
    private Entity entity;
    private Entity entity2;
    float x,y;

    public GameLogic()
    {
        x=0;
        y=0;
    }
    @Override
    public void InitGame() {
        camera=new Camera(new Vector2f());
        float[] vertices= {
                -50.5f, 50.5f,0.0f,
                -50.5f,  -50.5f, 0.0f,
                50.5f,  -50.5f,0.0f ,
                50.5f, 50.5f, 0.0f,
        };


        int [] indices= {
                0,1,3,
                3,1,2
        };

        float[] tc={
                1,0,
                0,1,
                1,1,
                0,0
        };

        camera.adjustOrtho();
        shader=new DefaultShader();
        renderer=new Renderer();
        entity=new Entity(ResourcePool.CreateMesh(vertices,indices,tc,"res/Texture/arrow.png"));
        entity.getPosition().x=30;
        entity.getPosition().y=30;
        entity.setColor(new Vector4f(0,1,0,1));
        entity2=new Entity(ResourcePool.CreateMesh(vertices,indices,tc,"res/Texture/arrow.png"));
        entity2.getPosition().x=300;
        entity2.getPosition().y=300;
        entity2.setColor(new Vector4f(0,1,0,1));
        renderer.submit(entity2);
        renderer.submit(entity);
    }

    @Override
    public void UpdateGameState(double dt) {
        entity.getPosition().x+=dt;
        entity.getPosition().y+=dt;
    }

    @Override
    public void render(Window window) {
        window.ClearColor(1,0,0,1);
        renderer.render(camera,shader);
    }

    @Override
    public void CleanUp() {
        shader.CleanUp();
    }
}
