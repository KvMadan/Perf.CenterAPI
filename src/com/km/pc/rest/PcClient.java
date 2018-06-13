package com.km.pc.rest;


import com.km.pc.rest.PcException;
import com.km.pc.rest.PcModel;
import com.km.pc.rest.PostRunAction;
//import hudson.FilePath;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;

public class PcClient
{
  private PcModel model;
  private PcRestProxy restProxy;
  private boolean loggedIn;
  private PrintStream logger;

  public PcClient(PcModel pcModel, PrintStream logger)
  {
    this.model = pcModel;
    this.restProxy = new PcRestProxy(this.model.getPcServerName(), this.model.getAlmDomain(), this.model.getAlmProject());
    this.logger = logger;
  }

  public <T extends PcRestProxy> PcClient(PcModel pcModel, PrintStream logger, T proxy) {
    this.model = pcModel;
    this.restProxy = proxy;
    this.logger = logger;
  }

  public boolean login() {
    try {
      String user = this.model.getAlmUserName();
      this.logger.println(String.format("Trying to login\n[PCServer='%s', User='%s']", new Object[] { this.model.getPcServerName(), user }));
      this.loggedIn = this.restProxy.authenticate(user, this.model.getAlmPassword().toString());
    } catch (PcException e) {
      this.logger.println(e.getMessage());
    } catch (Exception e) {
      this.logger.println(e);
    }
    this.logger.println(String.format("Login %s", new Object[] { this.loggedIn ? "succeeded" : "failed" }));
    return this.loggedIn;
  }

  public boolean isLoggedIn()
  {
    return this.loggedIn;
  }

  public int startRun() throws NumberFormatException, ClientProtocolException, PcException, IOException
  {
    this.logger.println("Sending run request\n" + this.model.runParamsToString());
    PcRunResponse response = this.restProxy.startRun(Integer.parseInt(this.model.getTestId()), Integer.parseInt(this.model.getTestInstanceId()), this.model.getTimeslotDuration(), this.model.getPostRunAction().getValue(), this.model.isVudsMode());

    this.logger.println(String.format("\nRun started (TestID: %s, RunID: %s, TimeslotID: %s)\n", new Object[] { Integer.valueOf(response.getTestID()), Integer.valueOf(response.getID()), Integer.valueOf(response.getTimeslotID()) }));

    return response.getID();
  }
/*
  public PcRunResponse waitForRunCompletion(int runId) throws InterruptedException, ClientProtocolException, PcException, IOException
  {
    return waitForRunCompletion(runId, 5000);
  }

  public PcRunResponse waitForRunCompletion(int runId, int interval) throws InterruptedException, ClientProtocolException, PcException, IOException {
    RunState state = RunState.UNDEFINED;
    switch (1.$SwitchMap$com$hp$application$automation$tools$model$PostRunAction[this.model.getPostRunAction().ordinal()]) {
    case 1:
      state = RunState.BEFORE_COLLATING_RESULTS;
      break;
    case 2:
      state = RunState.BEFORE_CREATING_ANALYSIS_DATA;
      break;
    case 3:
      state = RunState.FINISHED;
    }

    return waitForRunState(runId, state, interval);
  }

  private PcRunResponse waitForRunState(int runId, RunState completionState, int interval)
    throws InterruptedException, ClientProtocolException, PcException, IOException
  {
    PcRunResponse response = null;
    RunState lastState = RunState.UNDEFINED;
    do {
      response = this.restProxy.getRunData(runId);
      RunState currentState = RunState.get(response.getRunState());
      if (lastState.ordinal() < currentState.ordinal()) {
        lastState = currentState;
        this.logger.println(String.format("RunID: %s - State = %s", new Object[] { Integer.valueOf(runId), currentState.value() }));
      }
      Thread.sleep(interval);
    }while (lastState.ordinal() < completionState.ordinal());
    return response;
  }

  public FilePath publishRunReport(int runId, String reportDirectory) throws IOException, PcException, InterruptedException {
    PcRunResults runResultsList = this.restProxy.getRunResults(runId);
    if (runResultsList.getResultsList() != null) {
      for (PcRunResult result : runResultsList.getResultsList()) {
        if (result.getName().equals("Reports.zip")) {
          File dir = new File(reportDirectory);
          dir.mkdirs();
          String reportArchiveFullPath = dir.getCanonicalPath() + IOUtils.DIR_SEPARATOR + "Reports.zip";
          this.logger.println("Publishing run report");
          this.restProxy.GetRunResultData(runId, result.getID(), reportArchiveFullPath);
          FilePath fp = new FilePath(new File(reportArchiveFullPath));
          fp.unzip(fp.getParent());
          fp.delete();
          FilePath reportFile = fp.sibling("Report.html");
          if (reportFile.exists())
            return reportFile;
        }
      }
    }
    this.logger.println("Failed to get run report");
    return null;
  }
*/
  public boolean logout() {
    if (!this.loggedIn) {
      return true;
    }
    boolean logoutSucceeded = false;
    try {
      logoutSucceeded = this.restProxy.logout();
      this.loggedIn = (!logoutSucceeded);
    } catch (PcException e) {
      this.logger.println(e.getMessage());
    } catch (Exception e) {
      this.logger.println(e);
    }
    this.logger.println(String.format("Logout %s", new Object[] { logoutSucceeded ? "succeeded" : "failed" }));
    return logoutSucceeded;
  }

  public boolean stopRun(int runId) {
    boolean stopRunSucceeded = false;
    try {
      this.logger.println("Stopping run");
      stopRunSucceeded = this.restProxy.stopRun(runId, "stop");
    } catch (PcException e) {
      this.logger.println(e.getMessage());
    } catch (Exception e) {
      this.logger.println(e);
    }
    this.logger.println(String.format("Stop run %s", new Object[] { stopRunSucceeded ? "succeeded" : "failed" }));
    return stopRunSucceeded;
  }
}