package com.ch;

import com.PopEngine.GameEngine;
import com.PopEngine.IGameLogic;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import com.PopEngine.Window;
import org.lwjgl.opengl.GL;

public class Main {

    public static void main(String[] args) {
        GameLogic gameLogic=new GameLogic();

       GameEngine eng=new GameEngine("Pop3dGame",600,400,gameLogic);
       eng.run();
    }
}
