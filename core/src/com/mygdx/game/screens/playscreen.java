package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.mariogame;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.sprites.mario;
import com.mygdx.game.tools.B2worldcreator;
import com.mygdx.game.tools.WorldContactListener;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class playscreen implements Screen {

    private mariogame game;
    private OrthographicCamera cam;
    private Viewport gameport;
    private Hud hud;

    //tmx
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    //box2d var
    private World world;
    private Box2DDebugRenderer b2dr;


    //mario-player
    mario player;
    private TextureAtlas atlas;

    public playscreen(mariogame game){

        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game=game;
        cam = new OrthographicCamera();
        gameport = new FitViewport(mariogame.V_width/ mario.PPM,mariogame.V_height/mario.PPM,cam);

        hud = new Hud(game.batch);


        //tmx
        mapLoader= new TmxMapLoader();
        map=mapLoader.load("level1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/mario.PPM);

        cam.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);

        world= new World(new Vector2(0,-10),true);
        b2dr= new Box2DDebugRenderer();


        //mario player
        player= new mario(world,this);


        new B2worldcreator(world,map);


        //contactlistener
        world.setContactListener(new WorldContactListener());


    }

    public TextureAtlas getAtlas(){
        return atlas;
    }


    public void update(float dt){

        world.step(1/60f,6,2);

        cam.position.x=player.b2body.getPosition().x;

        handleinput(dt);
        cam.update();
        renderer.setView(cam);

        player.update(dt);

    }


    public void handleinput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0,4f),player.b2body.getWorldCenter(),true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(),true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x>=-2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(),true);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        update(delta);
        renderer.render();

        b2dr.render(world,cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
