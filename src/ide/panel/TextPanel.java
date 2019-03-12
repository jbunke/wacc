package ide.panel;

import ide.IDE;
import redsquaregl.drawing.Bitmap;
import redsquaregl.drawing.instructions.FillRectangle;
import redsquaregl.games._2D.Point;
import redsquaregl.games._2D.Size;

import java.awt.*;

public final class TextPanel extends Panel {
  private IDE ide;

  public TextPanel(Point point, Size size, IDE ide) {
    this.ide = ide;
    this.point = point;
    this.size = size;
  }

  @Override
  public void render() {
    bitmap = Bitmap.fromDimensions(size.getWidth(), size.getHeight());

    // TODO
    bitmap.paint(FillRectangle.make(Color.BLUE, 0, 0,
            size.getWidth(), size.getHeight()));
  }
}
