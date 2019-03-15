package ide.panel;

import redsquaregl.drawing.Bitmap;
import redsquaregl.drawing.instructions.DrawImage;
import redsquaregl.games._2D.Size;

public final class TopBottomPanel extends Panel {
  private int divider;
  private Panel top;
  private Panel bottom;

  public TopBottomPanel(Panel top, Panel bottom, int divider) {
    this.top = top;
    this.bottom = bottom;
    this.divider = divider;
    this.size = new Size(top.size.getWidth(),
            top.size.getHeight() + bottom.size.getHeight());
    this.point = top.point;
  }

  @Override
  public void render() {
    bitmap = Bitmap.fromDimensions(size.getWidth(), size.getHeight());

    top.render();
    bottom.render();

    bitmap.paint(DrawImage.fromImage(top.getBitmap(), 0, 0));
    bitmap.paint(DrawImage.fromImage(bottom.getBitmap(), 0, divider));
  }
}
