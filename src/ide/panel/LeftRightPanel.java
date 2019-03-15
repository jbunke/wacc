package ide.panel;

import redsquaregl.drawing.Bitmap;
import redsquaregl.drawing.instructions.DrawImage;
import redsquaregl.games._2D.Size;

public final class LeftRightPanel extends Panel {
  private int divider;
  private Panel left;
  private Panel right;

  public LeftRightPanel(Panel left, Panel right, int divider) {
    this.left = left;
    this.right = right;
    this.divider = divider;
    this.size = new Size(left.size.getWidth() + right.size.getWidth(),
            left.size.getHeight());
    this.point = left.point;
  }

  @Override
  public void render() {
    left.render();
    right.render();

    bitmap = Bitmap.fromDimensions(size.getWidth(), size.getHeight());

    bitmap.paint(DrawImage.fromImage(left.getBitmap(), 0, 0));
    bitmap.paint(DrawImage.fromImage(right.getBitmap(), divider, 0));
  }
}
