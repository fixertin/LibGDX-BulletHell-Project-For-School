package com.fixertin.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Utility {
    public  final AssetManager ASSET_MANAGER = new AssetManager();

    private  final String TAG = Utility.class.getSimpleName();
    private  InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    public  void unloadAsset(String assetFilenamePath){
        // once the asset manager is done loading
        if(ASSET_MANAGER.isLoaded(assetFilenamePath) ){
            ASSET_MANAGER.unload(assetFilenamePath);
        } else {
            Gdx.app.debug(TAG, "Asset is not loaded; Nothing to unload: "+ assetFilenamePath );
        }
    }
    public  float loadCompleted(){
        return ASSET_MANAGER.getProgress();
    }
    public  int numberAssetsQueued(){
        return ASSET_MANAGER.getQueuedAssets();
    }
    public  boolean updateAssetLoading(){
        return ASSET_MANAGER.update();
    }
    public  boolean isAssetLoaded(String fileName){
        return ASSET_MANAGER.isLoaded(fileName);
    }
    public  void loadTextureAsset(String textureFilenamePath){
        if( textureFilenamePath == null || textureFilenamePath.isEmpty()){
            return;
        }
        //load asset
        if( filePathResolver.resolve(textureFilenamePath).exists() ){
            ASSET_MANAGER.setLoader(Texture.class, new TextureLoader(filePathResolver));
            ASSET_MANAGER.load(textureFilenamePath, Texture.class);
            //Until we add loading screen,
            //just block until we load the map
            ASSET_MANAGER.finishLoadingAsset(textureFilenamePath);
        }
        else{
            Gdx.app.debug(TAG, "Texture doesn’t exist!: " +
                    textureFilenamePath );
        }
    }
    public Texture getTextureAsset(String textureFilenamePath){
        Texture texture = null;
        // once the asset manager is done loading
        if(ASSET_MANAGER.isLoaded(textureFilenamePath) ){
            texture =
                    ASSET_MANAGER.get(textureFilenamePath,Texture.class);
        } else {
            Gdx.app.debug(TAG, "Texture is not loaded: " +
                    textureFilenamePath );
        }
        return texture;
    }
    public void loadTextureAtlas(String textureFilenamePath){
        if( textureFilenamePath == null || textureFilenamePath.isEmpty()){
            return;
        }
        //load asset
        if( filePathResolver.resolve(textureFilenamePath).exists() ){
            ASSET_MANAGER.setLoader(TextureAtlas.class, new TextureAtlasLoader(filePathResolver));
            ASSET_MANAGER.load(textureFilenamePath, TextureAtlas.class);
            //Until we add loading screen,
            //just block until we load the map
            ASSET_MANAGER.finishLoadingAsset(textureFilenamePath);
        }
        else{
            Gdx.app.debug(TAG, "Texture Atlas doesn’t exist!: " +
                    textureFilenamePath );
        }
    }
    public  TextureAtlas getTextureAtlas(String textureFilenamePath){
        TextureAtlas texture = null;
        // once the asset manager is done loading
        if(ASSET_MANAGER.isLoaded(textureFilenamePath) ){
            texture =
                    ASSET_MANAGER.get(textureFilenamePath,TextureAtlas.class);
        } else {
            Gdx.app.debug(TAG, "Texture Atlas is not loaded: " +
                    textureFilenamePath );
        }
        return texture;
    }

    public void loadMusic(String musicFilenamePath){
        if( musicFilenamePath == null || musicFilenamePath.isEmpty()) {
            return;
        }
        //load music
        if(filePathResolver.resolve(musicFilenamePath).exists()){
            ASSET_MANAGER.setLoader(Music.class, new MusicLoader(filePathResolver));
            ASSET_MANAGER.load(musicFilenamePath, Music.class);
            ASSET_MANAGER.finishLoadingAsset(musicFilenamePath);
        }else{
            Gdx.app.debug(TAG, "Music doesn’t exist!: " +
                    musicFilenamePath);
        }
    }

    public Music getMusic(String musicFilenamePath){
        Music music = null;
        if(ASSET_MANAGER.isLoaded(musicFilenamePath)){
            music = ASSET_MANAGER.get(musicFilenamePath, Music.class);
        } else {
            Gdx.app.debug(TAG, "Texture Atlas is not loaded: " +
                    musicFilenamePath );
        }
        return music;
    }

    public  void dispose(){
        ASSET_MANAGER.dispose();
    }
}
