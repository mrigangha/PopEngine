#version 330


out vec4 color;
in vec2 coord;
uniform int has_texture;
uniform vec4 c;
uniform sampler2D default_Sampler;

void main() {
    if(has_texture==1)
    {
        color=texture(default_Sampler, coord);
    }
    else{
        color=c;
    }
}
