/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

/**
 *
 * @author aapomalk
 */
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import java.awt.LayoutManager;

public class ScrollattavaPaneeli extends JPanel implements Scrollable {
    
    public ScrollattavaPaneeli(LayoutManager layout) {
        super(layout);
    }
    
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(1000, 100);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10000;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10000;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
    
}
