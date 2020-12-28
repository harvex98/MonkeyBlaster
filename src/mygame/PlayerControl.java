/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Marti
 */
public class PlayerControl extends AbstractControl {
    private int screenWidth, screenHeight;
    
    // state of players movement
    // is moving? How fast and last rotation
    public boolean up, down, left, right;
    private float speed = 800f;
    private float lastRotation;
    
    public PlayerControl(int width, int height){
        this.screenWidth = width;
        this.screenHeight = height;
    }

    @Override
    protected void controlUpdate(float tpf) {
        // move player in appropriate direction if not off screen
        if (up) {
            if (spatial.getLocalTranslation().y < screenHeight - (Float)spatial.getUserData("radius")){
                spatial.move(0, tpf*speed, 0);
            }
            spatial.rotate(0, 0, -lastRotation + FastMath.PI/2);
            lastRotation = FastMath.PI / 2;
        } else if (down) {
            if (spatial.getLocalTranslation().y > (Float)spatial.getUserData("radius")) {
                spatial.move(0, tpf*-speed, 0);
            }
            spatial.rotate(0, 0, -lastRotation + FastMath.PI*1.5f);
            lastRotation = FastMath.PI*1.5f;
        } else if (left) {
            if (spatial.getLocalTranslation().x > (Float)spatial.getUserData("radius")) {
                spatial.move(tpf*-speed, 0, 0);
            }
            spatial.rotate(0, 0, -lastRotation + FastMath.PI);
            lastRotation = FastMath.PI;
        } else if (right) {
            if (spatial.getLocalTranslation().x < screenWidth - (Float)spatial.getUserData("radius")){
                spatial.move(tpf*speed, 0, 0);
            }
            spatial.rotate(0, 0, -lastRotation + 0);
            lastRotation = 0;
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) { }
    
    public void reset() {
        up = false;
        down = false;
        left = false;
        right = false;
   }
    
}
