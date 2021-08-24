package com.PopEngine;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL33.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;

public class ResourcePool {
    private static LinkedList<Integer> vaos;
    private static LinkedList<Integer> vbos;
    private static LinkedList<Integer> ebos;
    private static LinkedList<Texture> texture;
    private static LinkedList<Shader> shaderPool;

    public static void Init()
    {
        vaos=new LinkedList<>();
        vbos=new LinkedList<>();
        ebos=new LinkedList<>();
        texture=new LinkedList<>();
        shaderPool=new LinkedList<>();
    }

    public static Mesh CreateMesh(float[] vertices,int [] indices,float[] tc,String texture_path)
    {
        int vao=glGenVertexArrays();
        glBindVertexArray(vao);
        vaos.push(vao);

        int vbo=glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glBufferData(GL_ARRAY_BUFFER,createFloatBuffer(vertices),GL_STATIC_DRAW);
        vbos.push(vbo);

        int EBO=glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,EBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,createIntBuffer(indices),GL_STATIC_DRAW);
        ebos.push(EBO);

        AttributeSetPointer(0,3);

        int tco=glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,tco);
        glBufferData(GL_ARRAY_BUFFER,createFloatBuffer(tc),GL_STATIC_DRAW);
        vbos.push(tco);
        AttributeSetPointer(1,2);

        if(texture_path==null)
        {
            return new Mesh(vao,indices.length,EBO);
        }
        else
        {
            return new Mesh(vao,indices.length,EBO,texture_path);
        }
    }

    public static void AttributeSetPointer(int index,int spv)
    {
        glVertexAttribPointer(index,spv,GL_FLOAT,false,0,0);
        glEnableVertexAttribArray(index);
    }

    private static IntBuffer createIntBuffer(int[] indices)
    {
        IntBuffer buffer= BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        return buffer;
    }

    private static FloatBuffer createFloatBuffer(float[] data)
    {
        FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static void CleanUp()
    {
        for (int i : vaos)
        {
            glDeleteVertexArrays(i);
        }

        for (int i:vbos)
        {
            glDeleteBuffers(i);
        }

        for(int i:ebos)
        {
            glDeleteBuffers(i);
        }

        for(Texture i:texture)
        {
            i.cleanUp();
        }

        for (Shader i: shaderPool)
        {
            i.CleanUp();
        }

        vaos.clear();
        vbos.clear();
        ebos.clear();
        texture.clear();
        shaderPool.clear();
    }

    public static Texture CreateTexture(String fp)
    {
        Texture t=new Texture(fp);
        texture.push(t);
        return t;
    }

    public static Texture GetTexture(int index)
    {
        return texture.get(index);
    }

    public static Shader createShader(String vs_s,String fs_s)
    {
        Shader shader=new Shader(vs_s,fs_s);

        shaderPool.add(shader);
        return shader;
    }

    public static Shader GetShader(int index)
    {
        return shaderPool.get(index);
    }
    public static void RegisterShader(Shader shader)
    {
        shaderPool.add(shader);
    }
}
