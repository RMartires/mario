package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.mariogame;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private int timer;
    private float timecount;
    private static  int score;
    private int worldtimer;

    Label countdownlabel;
   static Label scorelabel;
    Label timelabel;
    Label worldlabel;
    Label mariolabel;
    Label levellabel;


    public Hud(SpriteBatch sb){
        timer=300;
        timecount=0;
        score=0;

        viewport= new FitViewport(mariogame.V_width,mariogame.V_height,new OrthographicCamera());
        stage=new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownlabel=new Label(String.format("%03d",timer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scorelabel=new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timelabel=new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldlabel=new Label("world",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mariolabel=new Label("mario",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levellabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(),Color.WHITE));


        table.add(mariolabel).expandX().padTop(10);
        table.add(worldlabel).expandX().padTop(10);
        table.add(timelabel).expandX().padTop(10);
        table.row();
        table.add(scorelabel).expandX();
        table.add(levellabel).expandX();
        table.add(countdownlabel).expandX();


        stage.addActor(table);


    }

    public void update(float dt){
        timecount+=dt;
        if(timecount>=1){
            worldtimer--;
            countdownlabel.setText(String.format("%03d",worldtimer));
            timecount=0;
        }
    }

    public static void addScore(int val){
        score+=val;
        scorelabel.setText(String.format("%03d",score));
    }


    @Override
    public void dispose() {
        stage.dispose();
    }


}
