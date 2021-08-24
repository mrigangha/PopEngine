package com.PopEngine;

import org.joml.Vector3f;
import org.joml.Vector4f;

public  class Entity {
    protected Mesh mesh;
    protected Vector3f Position;
    protected float scale;
    protected Vector3f rotation;
    protected Vector4f color;
    protected boolean is_texture;

    public Entity(Mesh msh)
    {
        mesh=msh;
        Position=new Vector3f(0,0,0);
        scale=1;
        rotation=new Vector3f(0,0,0);
        color=new Vector4f();
    }


    public void setPosition(Vector3f position) {
        Position = position;
    }

    public void setRotation(float x,float y,float z) {
        this.rotation.x=x;
        this.rotation.y=y;
        this.rotation.z=z;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getPosition() {
        return Position;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public boolean getIs_texture() {
        return is_texture;
    }
}
