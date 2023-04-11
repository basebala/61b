package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    private int height;
    private int width;
    // TODO: Add additional instance variables here
    // 1, 4, 2, 0, 3

    public KnightWorld(int width, int height, int holeSize) {
        tiles = new TETile[width][height];
        this.width = width;
        this.height = height;
        int widthDone = 0;
        int start = 1;
        while (widthDone < width) {
            drawColumn(start, holeSize, widthDone);
            start = (start + 3) % 5;
            widthDone += holeSize;
        }
    }

    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 60;
        int height = 40;
        int holeSize = 3;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
    public void drawFloorTile (int holeSize, int left, int bottom) {
        int curLeft = left;
        while(curLeft < left + holeSize && curLeft < width) {
            int curBottom = bottom;
            while(curBottom < bottom + holeSize && curBottom < height) {
                tiles[curLeft][curBottom] = Tileset.LOCKED_DOOR;
                curBottom += 1;
            }
            curLeft += 1;
        }
    }

    public void drawHoleTile (int holeSize, int left, int bottom) {
        int curLeft = left;
        while(curLeft < left + holeSize && curLeft < width) {
            int curBottom = bottom;
            while(curBottom < bottom + holeSize && curBottom < height) {
                tiles[curLeft][curBottom] = Tileset.NOTHING;
                curBottom += 1;
            }
            curLeft += 1;
        }
    }
    public void drawColumn (int start, int holeSize, int left) {
        int heightDone = 0;
        while(heightDone < height) {
            if (start == 0) {
                drawHoleTile(holeSize, left, heightDone);
                start = 4;
            }
            else {
                drawFloorTile(holeSize, left, heightDone);
                start -=1;
            }
            heightDone += holeSize;
        }
    }
}
