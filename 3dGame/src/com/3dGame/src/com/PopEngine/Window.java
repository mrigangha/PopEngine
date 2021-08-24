package com.PopEngine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

import org.lwjgl.opengl.GL;

public class Window {
    private long window;
    private static int Width,Height;
    public boolean is_resized;


    public static void Frame_Buffer_Size_Callback(long win,int w,int h)
    {
        Width=w;
        Height=h;
        glViewport(0, 0, w, h);
    }

    public Window(String title,int width,int height)
    {
        is_resized=false;
        if(!glfwInit())
        {
            System.err.println("Unable to init glfw!");
            System.exit(-2);
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        Width=width;
        Height=height;
        window=glfwCreateWindow(width, height, title, 0, 0);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();
        glClearColor(0, 0, 0, 1);

        glfwSetFramebufferSizeCallback(window, Window::Frame_Buffer_Size_Callback);
        glfwSetKeyCallback(window, KeyListener::Key_Callback);
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);


    }

    public void Clear()
    {
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void ClearColor(float r,float g,float b,float a)
    {
        glClearColor(r,g,b,a);
    }

    public void work()
    {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public boolean is_Closed()
    {
        return glfwWindowShouldClose(window);
    }

    public void Terminate() {
        glfwTerminate();
    }

    public static int getWidth() {
        return Width;
    }

    public static int getHeight() {
        return Height;
    }
}

