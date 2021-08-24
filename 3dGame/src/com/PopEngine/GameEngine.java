package com.PopEngine;


public class GameEngine implements Runnable{

    private IGameLogic gameLogic;
    private Window window;
    private Thread thread;
    private int h,w;
    private String title;

    public GameEngine(String name,int width,int height,IGameLogic logic)
    {
        h=height;
        w=width;
        title=name;
        gameLogic=logic;
    }

    public synchronized void start()
    {
        thread=new Thread(this,"Pop3dEngine");
        thread.start();
    }

    @Override
    public void run() {
        window=new Window(title,w,h);
        long LastTime=System.nanoTime();
        final double ns=1000000000.0 /60.0;
        double deltaTime=0.0;
        int frames=0;
        int updates=0;
        long Timer=System.currentTimeMillis();

        ResourcePool.Init();
        init();
        while(!window.is_Closed())
        {
            long now=System.nanoTime();
            deltaTime +=(now -LastTime)/ns;
            while(deltaTime>=1)
            {
                Update(deltaTime);
                updates++;
                deltaTime--;
            }
            window.work();

            Render();
            frames++;
            if(System.currentTimeMillis()-Timer>1000)
            {
                Timer+=1000;
                System.out.println("fps  "+frames+"  ups  "+updates );
                updates=0;
                frames=0;
            }

            LastTime=now;

        }
        gameLogic.CleanUp();
        ResourcePool.CleanUp();
        window.Terminate();
    }

    private void init() {
        gameLogic.InitGame();
    }

    private void Render() {
        window.Clear();
        gameLogic.render(window);
    }

    private void Update(Double deltaTime) {
        gameLogic.UpdateGameState(deltaTime);
    }
}
