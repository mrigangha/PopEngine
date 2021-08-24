#version 330


in vec3 position;
layout(location=1) in vec2 tex_coord;



uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 transform;

out vec2 coord;

void main() {

    coord=tex_coord;
    gl_Position=uProjection*uView*transform*vec4(position, 1.0);
}