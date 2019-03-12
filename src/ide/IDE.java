package ide;

import ide.io.IDEListener;
import ide.panel.MenuPanel;
import ide.panel.Panel;
import ide.panel.TextPanel;
import ide.panel.TopBottomPanel;
import ide.text_editor.EditorContext;
import redsquaregl.drawing.Bitmap;
import redsquaregl.drawing.Context;
import redsquaregl.drawing.instructions.DrawImage;
import redsquaregl.games._2D.Point;
import redsquaregl.games._2D.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class IDE {
  /* UI */
  private Context uiContext;
  private IDEListener listener;
  private Panel panel;

  /* DATA */
  private List<EditorContext> editorContexts;
  private int activeContext;

  static void runWithoutFile() {
    new IDE(null);
  }

  static void runWithFile(String filepath) {
    new IDE(filepath);
  }

  private IDE(String filepath) {
    editorContexts = new ArrayList<>();
    if (filepath == null) {
      editorContexts.add(new EditorContext());
    } else {
      editorContexts.add(new EditorContext(filepath));
    }
    activeContext = 0;

    contextSetup();
    listener = new IDEListener(this);
    uiContext.addIOListener(listener);

    panelSetup();

    Timer renderTimer = new Timer();
    renderTimer.scheduleAtFixedRate(new RenderTask(), 0, 100);
  }

  private void contextSetup() {
    uiContext = Context.create(800, 600).withTitle("WACC IDE");
    uiContext.reposition(10, 10);
  }

  private void panelSetup() {
    /* TODO: temp. panel implementation */
    panel = new TopBottomPanel(
            new MenuPanel(new Point(0, 0), 800),
            new TextPanel(
                    new Point(0, MenuPanel.MENU_PANEL_HEIGHT),
                    new Size(800, 580), this),
            MenuPanel.MENU_PANEL_HEIGHT);
  }

  public class RenderTask extends TimerTask {

    @Override
    public void run() {
      panel.render();
      Bitmap panels = panel.getBitmap();
      uiContext.addInstruction(DrawImage.fromImage(panels, 0, 0));
      uiContext.render();
    }
  }

  public void typeString(String toAdd) {
    editorContexts.get(activeContext).typeString(toAdd);
  }

  public List<EditorContext> getEditorContexts() {
    return editorContexts;
  }

  public int getActiveContext() {
    return activeContext;
  }
}
