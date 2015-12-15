package task1;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TelemetryDiagnosticControlsTest {
    private TelemetryDiagnosticControls diagnosticControls;

    @Test
    public void testDiagnosticInfoSetGet() {
	diagnosticControls = new TelemetryDiagnosticControls();
	Assert.assertEquals("", diagnosticControls.getDiagnosticInfo());
	diagnosticControls.setDiagnosticInfo("Test string");
	Assert.assertEquals("Test string",
		diagnosticControls.getDiagnosticInfo());
    }

    @Test(expected = Exception.class)
    public void testCheckTransmissionFailedWhenOffline() throws Exception {
	TelemetryClient mockedClient = Mockito.mock(TelemetryClient.class);
	Mockito.when(mockedClient.getOnlineStatus()).thenReturn(false);
	diagnosticControls = new TelemetryDiagnosticControls(mockedClient);
	diagnosticControls.checkTransmission();
    }

    @Test
    public void testCheckTransmissionSuccWhenOnline() throws Exception {
	TelemetryClient mockedClient = Mockito.mock(TelemetryClient.class);
	Mockito.when(mockedClient.getOnlineStatus()).thenReturn(true);
	Mockito.when(mockedClient.receive()).thenReturn("Test string recieved");
	testSuccRecieve(mockedClient);
    }

    @Test
    public void testCheckTransmissionTries3TimesToConnect() throws Exception {
	TelemetryClient mockedClient = Mockito.mock(TelemetryClient.class);
	Mockito.when(mockedClient.getOnlineStatus()).thenReturn(false, false,
		true);
	Mockito.when(mockedClient.receive()).thenReturn("Test string recieved");
	testSuccRecieve(mockedClient);
    }

    private void testSuccRecieve(TelemetryClient mockedClient) throws Exception {
	diagnosticControls = new TelemetryDiagnosticControls(mockedClient);
	diagnosticControls.checkTransmission();
	Mockito.verify(mockedClient).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
	Assert.assertEquals("Test string recieved",
		diagnosticControls.getDiagnosticInfo());
    }
}
