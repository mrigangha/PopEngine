package com.PopEngine;

public interface IGameLogic {
    public void InitGame();
    public void UpdateGameState(double dt);
    public void render(Window window);
    public void CleanUp();
}
