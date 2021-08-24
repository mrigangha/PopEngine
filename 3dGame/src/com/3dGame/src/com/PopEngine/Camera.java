package com.PopEngine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Matrix4f ProjectionMatrix,viewMatrix;
    private Vector2f position;

    public Camera(Vector2f pos)
    {
        position=pos;
        ProjectionMatrix=new Matrix4f();
        viewMatrix=new Matrix4f();
    }

    public void adjustOrtho()
    {
        ProjectionMatrix.identity();
        ProjectionMatrix.ortho(0.0f,32.0f*40.0f,0.0f,32.0f* 21.0f,0.0f,100.0f);
    }

    public void adjustPrespective()
    {
        float FOV=(float)Math.toRadians(60.0f);
        float Z_NEAR=0.01f;
        float Z_FAR=1000.f;
        float aspectRatio=(float)Window.getWidth()/Window.getHeight();

        ProjectionMatrix=new Matrix4f().identity().perspective(FOV,aspectRatio,Z_NEAR,Z_FAR);

    }

    public Matrix4f getViewMatrix() {
        Vector3f camerafront=new Vector3f(0.0f,0.0f,-1.0f);
        Vector3f cameraUp =new Vector3f(0.0f,1.0f,0.0f);
        this.viewMatrix.identity();
        this.viewMatrix.lookAt(
                new Vector3f(position.x,position.y,20.0f)
                            ,camerafront.add(position.x,position.y,0.0f)
                            ,cameraUp);

        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return ProjectionMatrix;
    }

    public Vector2f getPosition() {
        return position;
    }
}
