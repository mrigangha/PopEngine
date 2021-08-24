package com.PopEngine;

public class Mesh {
    private int vao;
    private int vertexCount;
    private int ebo;
    private Texture tex;
    private boolean texture;
    public Mesh(int _vao,int VertexCount,int _ebo,String path)
    {
        vao=_vao;
        vertexCount=VertexCount;
        ebo=_ebo;
        tex=ResourcePool.CreateTexture(path);
        texture=true;
    }
    public Mesh(int _vao,int VertexCount,int _ebo)
    {
        vao=_vao;
        vertexCount=VertexCount;
        ebo=_ebo;
        texture=false;
    }

    public Texture getTexture() { return tex; }

    public boolean getISTexture() { return texture; }

    public int getVao() {
        return vao;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEbo() { return ebo; }
}
