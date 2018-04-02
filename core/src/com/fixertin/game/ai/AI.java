package com.fixertin.game.ai;

import com.fixertin.game.entities.Enemy;

public abstract class AI {
    public enum AIState{
        Success,
        Failure,
        Running
    }

    protected AIState state;
    protected Enemy e;

    public AI(Enemy e){
        this.e = e;
    }

    public void start(){
        this.state = AIState.Running;
    }
    public abstract void reset(Enemy e);
    public abstract void tick();


    protected void succeed(){
        this.state = AIState.Success;
    }
    protected void fail(){
        this.state = AIState.Failure;
    }
    public boolean isSuccess(){
        return state.equals(AIState.Success);
    }
    public boolean isFailure(){
        return state.equals(AIState.Failure);
    }
    public boolean isRunning(){
        return state.equals(AIState.Running);
    }
    public AIState getState(){
        return state;
    }

}
