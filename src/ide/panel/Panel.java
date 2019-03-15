package ide.panel;

import redsquaregl.drawing.Bitmap;
import redsquaregl.games._2D.Point;
import redsquaregl.games._2D.Size;

public abstract class Panel {
  protected Bitmap bitmap;
  protected Point point;
  protected Size size;

  public abstract void render();

  public Bitmap getBitmap() {
    return bitmap;
  }
}
