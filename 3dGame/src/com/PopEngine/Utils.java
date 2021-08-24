package com.PopEngine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.glGetError;

public class Utils
{
    public static Matrix4f getTransform(Vector3f offset,Vector3f rotation,float scale)
    {
        Matrix4f WorldMatrix=new Matrix4f();
        WorldMatrix.identity();
        WorldMatrix.translate(offset).rotateX((float)Math.toRadians(rotation.x))
                .rotateY((float)Math.toRadians(rotation.y))
                .rotateZ((float)Math.toRadians(rotation.z))
                .scale(scale);
        return WorldMatrix;
    }

    public static void clean()
    {
        while(glGetError()!=0);
    }
    public static void check(int i)
    {
        int error=glGetError();
        if(error!=0)
        {
            System.out.println(glGetError()+"from "+i);
        }
        clean();
    }
}
