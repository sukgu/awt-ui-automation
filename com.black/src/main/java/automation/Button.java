/**
 * 
 */
package automation;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Button implements automation.model.Component {

	
	private Robot robot=null;
	private Dimension bounds=null;
	private Point location=null;
	private String text=null;
	
	public Button() throws AWTException {
		
		robot=new Robot();
	}
	
	public Button(String text) throws FileNotFoundException, AWTException {
		
		FileInputStream fileIn=null;
		ObjectInputStream in=null;
		fileIn=new FileInputStream(new File(System.getProperty("user.home")+"/output.ser"));
		try {
			in = new ObjectInputStream(fileIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileIn!=null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        List<HashMap> list=null;
		try {
			list = (List)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (HashMap<String,Object> hm : list) {
			if(hm.get("Text").equals(text))
			{
				setText(text);
				setLocation((Point)hm.get("Location"));
				setBounds((Dimension)hm.get("Bounds"));
			}
		}
        
        robot=new Robot();
       
	}
	
	
	public Robot getRobot() {
		return robot;
	}



	public void setRobot(Robot robot) {
		this.robot = robot;
	}


	public void setBounds(Dimension bounds) {
		this.bounds = bounds;
	}



	public Point getLocation() {
		return location;
	}



	public void setLocation(Point location) {
		this.location = location;
	}

	
	public String getText() {
		return text;
	}

	
	
	/* (non-Javadoc)
	 * @see com.black.automation.model.Button#click()
	 */

	



	public void click() {
		
		robot.mouseMove(location.x+35,location.y+10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

	}

	/* (non-Javadoc)
	 * @see com.black.automation.model.Button#getBounds()
	 */
	public Dimension getBounds() {
		
		return bounds;
	}

	/* (non-Javadoc)
	 * @see com.black.automation.model.Button#locationOnScreen()
	 */
	public Point locationOnScreen() {
		
		return location;
	}

	/* (non-Javadoc)
	 * @see com.black.automation.model.Button#setText()
	 */

	public void setText(String text) {
		
		this.text=text;
	}

	public String text() {
		// TODO Auto-generated method stub
		return text;
	}

}
