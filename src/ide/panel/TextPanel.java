package ide.panel;

import ide.IDE;
import ide.text_editor.EditorContext;
import redsquaregl.drawing.Bitmap;
import redsquaregl.drawing.instructions.FillRectangle;
import redsquaregl.drawing.instructions.WriteText;
import redsquaregl.games._2D.Point;
import redsquaregl.games._2D.Size;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

public final class TextPanel extends Panel {
  private static Font TEXT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
  private static Color TEXT_COLOR = Color.WHITE;
  private static Color BACKGROUND_COLOR = Color.BLACK;

  private IDE ide;

  private List<String> lines;

  public TextPanel(Point point, Size size, IDE ide) {
    this.ide = ide;
    this.point = point;
    this.size = size;
  }

  public void updateInfo() {
    EditorContext context =
            ide.getEditorContexts().get(ide.getActiveContext());
    lines = context.getLines();
    TEXT_FONT = TEXT_FONT.deriveFont((float) context.getSize());
  }

  @Override
  public void render() {
    bitmap = Bitmap.fromDimensions(size.getWidth(), size.getHeight());

    updateInfo();

    // TODO
    bitmap.paint(FillRectangle.make(BACKGROUND_COLOR, 0, 0,
            size.getWidth(), size.getHeight()));

    for (int i = 0; i < lines.size(); i++) {
      /* LINE NUMBERS */
      bitmap.paint(WriteText.make(Integer.toString(i + 1), TEXT_COLOR, TEXT_FONT,
              TEXT_FONT.getSize(), 4, TEXT_FONT.getSize() * (i + 1)));

      /* LINE SEPARATOR */
      bitmap.paint(FillRectangle.make(TEXT_COLOR,
              (TEXT_FONT.getSize() * 3), 0, 2, size.getHeight()));

      /* LINE */
      String line = lines.get(i);
      bitmap.paint(WriteText.make(line, TEXT_COLOR, TEXT_FONT,
              TEXT_FONT.getSize(), TEXT_FONT.getSize() * 4,
              TEXT_FONT.getSize() * (i + 1)));
    }
  }
}
