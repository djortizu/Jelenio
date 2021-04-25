package common;

import static java.lang.Runtime.getRuntime;

public class Utility {

  private static final String killRunningChromeDriverProcessesCommandLine = "powershell.exe \"Stop-Process -Name \"chromedriver\" -Force\"";

  public static void killRunningChromeDriverProcesses(){

    Runtime rt = getRuntime();

    try {
      rt.exec(killRunningChromeDriverProcessesCommandLine);
    }
    catch (Exception e) {
      // do nothing
    }

  }

}
