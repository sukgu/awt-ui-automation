/**
 * 
 */
package automation.model;

import java.awt.Dimension;
import java.awt.Point;

/**
 * @author Administrator
 *
 */
public interface Component {

	public abstract void  click();
	public abstract Dimension getBounds();
	public abstract Point locationOnScreen();
	public abstract String text();
	public abstract void setText(String text);
	public abstract void setBounds(Dimension bounds);
	
}
