package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Entity implements SpriteAnimation {
    protected String nama;
    protected int movement_speed;

    public Player(PImage[] idle, PImage[] broken, int x, int y, int res, int total_frame) {
        super(20, 3, 0, x, y, total_frame);
        super.idle = idle;
        super.broken = broken;
        super.timing = 3;
        super.frame = 0;
        super.reset_frame = -1;
        super.res = res;
//        this.movement_speed = 32 + 16;
        this.movement_speed = 5;
    }

    public void drawIdle(PApplet app, int frame_ctr){
        if(frame_ctr == reset_frame){
            frame = 0;
            frame_ctr = 0;
        }
        if(frame_ctr % timing == 0){
            frame++;
        }
        if(frame > total_frame - 1){
            frame = 0;
        }
        if (is_broken) {
            app.image(this.broken[frame], x - (res / 2), y - (res / 2), res, res);
        } else {
            app.image(this.idle[frame], x - (res / 2), y - (res / 2), res, res);
        }
    }

    public void movement(boolean up, boolean down, boolean left, boolean right) {
        if (up) y -= movement_speed;
        if (down) y += movement_speed;
        if (left) x -= movement_speed;
        if (right) x += movement_speed;
    }


}
