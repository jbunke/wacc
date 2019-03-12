package ide.panel;

import redsquaregl.drawing.Bitmap;
import redsquaregl.drawing.instructions.FillRectangle;
import redsquaregl.games._2D.Point;
import redsquaregl.games._2D.Size;

import java.awt.*;

public class MenuPanel extends Panel {
  public final static int MENU_PANEL_HEIGHT = 20;

  public MenuPanel(Point point, int width) {
    this.point = point;
    this.size = new Size(width, MENU_PANEL_HEIGHT);
  }

  @Override
  public void render() {
    bitmap = Bitmap.fromDimensions(size.getWidth(), size.getHeight());

    // TODO
    bitmap.paint(FillRectangle.make(Color.RED, 0, 0,
            size.getWidth(), size.getHeight()));
  }
}
