package com.fixertin.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Utility {
    public static final AssetManager ASSET_MANAGER = new AssetManager();

    private static final String TAG = Utility.class.getSimpleName();
    private static InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    public static void unloadAsset(String assetFilenamePath){
        // once the asset manager is done loading
        if(ASSET_MANAGER.isLoaded(assetFilenamePath) ){
            ASSET_MANAGER.unload(assetFilenamePath);
        } else {
            Gdx.app.debug(TAG, "Asset is not loaded; Nothing to unload: "+ assetFilenamePath );
        }
    }
    public static float loadCompleted(){
        return ASSET_MANAGER.getProgress();
    }
    public static int numberAssetsQueued(){
        return ASSET_MANAGER.getQueuedAssets();
    }
    public static boolean updateAssetLoading(){
        return ASSET_MANAGER.update();
    }
    public static boolean isAssetLoaded(String fileName){
        return ASSET_MANAGER.isLoaded(fileName);
    }
    public static void loadTextureAsset(String textureFilenamePath){
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
    public static Texture getTextureAsset(String textureFilenamePath){
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
    public static void loadTextureAtlas(String textureFilenamePath){
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
    public static TextureAtlas getTextureAtlas(String textureFilenamePath){
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
    public static void dispose(){
        ASSET_MANAGER.dispose();
    }
}
