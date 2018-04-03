package com.fixertin.game.ai.patterns;

import com.fixertin.game.ai.AI;

public class ShootPattern{
    private float lengthOfTime;
    private Shoot[] shoots;
    private boolean finished;

    /**
     *
     * @param shoots
     * @param lengthOfTimeInSeconds if 0 doesn't stop;
     */
    public ShootPattern(Shoot[] shoots, float lengthOfTimeInSeconds){
        super();
        this.shoots = shoots;
        this.lengthOfTime = lengthOfTime;
    }

    private float timer=0;
    public void update(float deltaTime){
        if(lengthOfTime > 0){
            if(!finished){
                timer += deltaTime;
            }
            if(!finished && timer < lengthOfTime){
                for(int i=0; i<shoots.length; i++)
                    shoots[i].update(deltaTime);
            } else if(!finished && timer >= lengthOfTime){
                finished = true;
            }
        } else {
            for(int i=0; i<shoots.length; i++)
                shoots[i].update(deltaTime);
        }
    }
    public boolean isFinished(){
        return finished;
    }
}
