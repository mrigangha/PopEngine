package com.PopEngine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.LinkedList;
import static org.lwjgl.opengl.GL33.*;

public class Renderer {

    public LinkedList<Entity> meshes;
    public Renderer()
    {
        meshes=new LinkedList<>();
    }

    public void submit(Entity msh)
    {
        meshes.push(msh);
    }

    public void flush(Camera camera,Shader shader)
    {
        for (Entity m : meshes)
        {
            shader.start();
            shader.loadVec4f(shader.getUniformLocation("c"),new Vector4f(0,1,0,1));

            shader.loadMat4fv(shader.getUniformLocation("uProjection"),camera.getProjectionMatrix());
            shader.loadMat4fv(shader.getUniformLocation("uView"),camera.getViewMatrix());
            shader.loadMat4fv(shader.getUniformLocation("transform"), Utils.getTransform(m.getPosition(),m.getRotation(),m.getScale()));
            glBindVertexArray(m.getMesh().getVao());
            glEnableVertexAttribArray(0);
            glDrawElements(GL_TRIANGLES,m.getMesh().getVertexCount(),GL_UNSIGNED_INT,0);
            glDisableVertexAttribArray(0);
            glBindVertexArray(0);
            shader.stop();
        }
        meshes.clear();
    }


    public void render(Camera camera,Shader shader)
    {
        for (Entity m : meshes)
        {
            shader.start();
            if(m.getMesh().getISTexture()) {
                shader.loadTexture("has_texture",1);
                shader.loadTexture("default_Sampler", 0);
                glActiveTexture(GL_TEXTURE0);
                m.getMesh().getTexture().bind();
            }

            shader.loadVec4f(shader.getUniformLocation("c"),m.getColor());
            shader.loadMat4fv(shader.getUniformLocation("uProjection"),camera.getProjectionMatrix());
            shader.loadMat4fv(shader.getUniformLocation("uView"),camera.getViewMatrix());
            shader.loadMat4fv(shader.getUniformLocation("transform"), Utils.getTransform(m.getPosition(),m.getRotation(),m.getScale()));
            glBindVertexArray(m.getMesh().getVao());
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,m.getMesh().getEbo());
            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1);
            glDrawElements(GL_TRIANGLES,m.getMesh().getVertexCount(),GL_UNSIGNED_INT,0);
            glDisableVertexAttribArray(0);
            glDisableVertexAttribArray(1);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
            glBindVertexArray(0);
            m.getMesh().getTexture().bind();
            shader.stop();
        }
    }
}
