package ide;

import ide.io.IDEListener;
import ide.text_editor.EditorContext;
import redsquaregl.drawing.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class IDE {
  /* UI */
  private Context uiContext;
  private IDEListener listener;

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
  }

  private void contextSetup() {
    uiContext = Context.create(800, 600).withTitle("WACC IDE");
    uiContext.reposition(10, 10);

    Timer renderTimer = new Timer();
    renderTimer.scheduleAtFixedRate(new RenderTask(), 0, 50);
  }

  public class RenderTask extends TimerTask {

    @Override
    public void run() {
      // TODO
      uiContext.render();
    }
  }

  public void typeString(String toAdd) {
    editorContexts.get(activeContext).typeString(toAdd);
  }
}
