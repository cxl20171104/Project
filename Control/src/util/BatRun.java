package util;

import java.io.IOException;

public class BatRun {
	
	    public static boolean runbat(String path) {
	        String cmd = "cmd /c start "+path;
	        try {
	            Process ps = Runtime.getRuntime().exec(cmd);
	            ps.waitFor();
	            return true;
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            return false;
	        }
	        catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return false;
	        }
	    }

	
}
