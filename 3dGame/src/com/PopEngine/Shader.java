package com.PopEngine;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL33.*;

public class Shader {
    private int program;
    private int fsShader,vsShader;
    protected HashMap<String, Integer> uniformStore;

    public Shader(String vs,String fs)
    {
        uniformStore=new HashMap<>();
        vsShader=loadShader(vs,GL_VERTEX_SHADER);
        fsShader=loadShader(fs,GL_FRAGMENT_SHADER);
        program=glCreateProgram();
        glAttachShader(program,vsShader);
        glAttachShader(program,fsShader);
        glLinkProgram(program);
        glValidateProgram(program);
        start();
        LoadUniforms();
        stop();
    }

    public void LoadUniforms()
    {}

    public void start() {
        glUseProgram(program);
    }

    public void loadFloat(int location,float value)
    {
        glUniform1f(location, value);
    }

    public void loadVec4f(int location, Vector4f value)
    {
        glUniform4f(location, value.x, value.y, value.z, value.w);
    }

    public void loadMat4fv(int loc, Matrix4f mat){FloatBuffer buffer= BufferUtils.createFloatBuffer(16); mat.get(buffer);loadMat4fv(loc,buffer);}
    private void loadMat4fv(int loc,FloatBuffer fb) {
        glUniformMatrix4fv(loc,false,fb);
    }
    public void loadTexture(String varname,int slot)
    {
        start();
        glUniform1i(getUniformLocation(varname),slot);
    }

    public int getUniformLocation(String uniformName)
    {
        if(uniformStore.containsKey(uniformName))
        {
            return uniformStore.get(uniformName);
        }
        uniformStore.put(uniformName, glGetUniformLocation(program, uniformName));
        return uniformStore.get(uniformName);
    }


    public void stop() {
        glUseProgram(0);
    }


    private static int loadShader(String file,int type)
    {
        StringBuilder shaderSource=new StringBuilder();

        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));

            String line;
            try {
                while ((line = reader.readLine())!=null) {
                    shaderSource.append(line).append("\n");
                }

                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int shaderID=glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);
        if(glGetShaderi(shaderID, GL_COMPILE_STATUS)==GL_FALSE)
        {
            System.out.println(glGetShaderInfoLog(shaderID, 500)+" from file "+ file);
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }

        return shaderID;
    }
    public void BindAttributes(int attribute,String variablename)
    {
        glBindAttribLocation(program, attribute, variablename);
    }

    public void CleanUp() {
        stop();
        glDetachShader(program, vsShader);
        glDetachShader(program, fsShader);
        glDeleteShader(vsShader);
        glDeleteShader(fsShader);
        glDeleteProgram(program);
    }

}
