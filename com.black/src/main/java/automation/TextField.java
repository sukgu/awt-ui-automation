package automation;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import automation.model.Component;

public class TextField implements Component{
	
	
	
	private Robot robot=null;
	private Dimension bounds=null;
	private Point location=null;
	private String text=null;
	
	
	
	

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
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
	
	
	
	public TextField() throws AWTException {
		robot=new Robot();
	}

	/**
	 * @param bounds
	 * @param location
	 * @param text
	 * @throws AWTException 
	 * @throws FileNotFoundException 
	 */
	public TextField(String text) throws AWTException, FileNotFoundException {
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
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
//        for (HashMap<String,Object> hm : list) {
//        	if(hm.get("Class").equals("JDialog")||hm.get("Class").equals("JFrame"))
//    		{
//        		setText(text);
//        		setLocation((Point)hm.get("Location"));
//    			setBounds((Dimension)hm.get("Bounds"));
//    		}
//		}
//        
//        
//        try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        robot=new Robot();
//        int x=getLocation().x+getBounds().width;
//        int y=getLocation().y+getBounds().height;
//        robot.mouseMove(x-5,y-5);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseMove(x+30, y+140);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot=new Robot();
        for (HashMap<String,Object> hm : list) {
        	if(hm.get("Text")!=null) {
			if(hm.get("Text").equals(text))
			{
				setText(text);
				setLocation((Point)hm.get("Location"));
				setBounds((Dimension)hm.get("Bounds"));
			} 
        	}
		}
	}

	public void click() {
		robot.mouseMove(location.x+100,location.y+7);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public Dimension getBounds() {
		return bounds;
	}

	public Point locationOnScreen() {
		return location;
	}

	public String text() {
		return text;
	}

	public void setText(String text) {
		this.text=text;
	}

	public void setBounds(Dimension bounds) {
		this.bounds=bounds;
	}
	
	
	public void typeCharacter(Robot robot, String letter)
    {
        try
        {
            boolean upperCase = Character.isUpperCase( letter.charAt(0) );
            
            String code=letter.toUpperCase();
            if(code.equals(" "))
            {
            	code="SPACE";
            }
            String variableName = "VK_" + code;

            Class clazz = KeyEvent.class;
            Field field = clazz.getField( variableName );
            int keyCode = field.getInt(null);

            robot.delay(200);

            if (upperCase) robot.keyPress( KeyEvent.VK_SHIFT );

            robot.keyPress( keyCode );
            robot.keyRelease( keyCode );

            if (upperCase) robot.keyRelease( KeyEvent.VK_SHIFT );
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
	


	
	
	public void type(String text) {
		robot.mouseMove(location.x+100,location.y+7);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);

		for (Character element : text.toCharArray()) {
			typeCharacter(robot, element.toString());
		}
		
		
	}

}
