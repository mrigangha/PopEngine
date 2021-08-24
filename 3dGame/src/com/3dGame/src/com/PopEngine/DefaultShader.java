package com.PopEngine;

public class DefaultShader extends Shader{


    private static final String vs_src="res/shaders/Default.vs.glsl";
    private static final String fs_src="res/shaders/Default.fs.glsl";


    public DefaultShader() {
        super(vs_src, fs_src);
        this.start();
        this.BindAttributes(0,"position");
        this.BindAttributes(1,"tex_coord");
        this.stop();
        ResourcePool.RegisterShader(this);
    }


}
